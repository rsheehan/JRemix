package edu.fizz.remix.editor;

import edu.fizz.remix.EvalVisitorForEditor;
import edu.fizz.remix.runtime.LibrariesAndCompletions;
import edu.fizz.remix.runtime.LibraryExpression;
import org.antlr.v4.runtime.tree.ParseTree;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.*;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

//import static edu.fizz.remix.editor.RemixPrepareRun.EDITORTEXT;

public class RemixEditorWindow extends JFrame {
    public static final int SIZE = 12; // 12 is small but gives 80 col printout on A4

    private final JTextPane editorTextPane;
    private Point caretPoint;
    private final RemixStyledDocument theDocument;
    private final PopupFactory popupFactory = new PopupFactory();
    private Popup docPopup;
    private final JPanel docPanel = new JPanel();
    protected JTextArea docArea;
    protected Point popupScreenLocation = null;
//    boolean moveToParam = false;

    private HashMap<Object, Action> actions;
    private final CaretListenerLabel caretListenerLabel;

    private RemixSwingWorker remixRunner;
    protected RunAction runAction;
    protected StopAction stopAction;

    private static final Font defaultFontForScreen = new Font("monospaced", Font.PLAIN, SIZE);
    private static final Font defaultFontForPrinter = new Font("monospaced", Font.PLAIN, 8);

    //undo helpers
    private UndoAction undoAction;
    private RedoAction redoAction;
    private final RevealUndoManager undo = new RevealUndoManager();

//    private boolean editing = true;

    private RemixEdLexer edLexer;
    private static String untitledName = "untitled";
    private String currentFileName;
    private String currentAbsoluteFileName = null;
    private boolean editorContentSaved = true;
    private final REPLInputOutput programOutput;
    private final DocumentListener documentListener;
    private final RemixEdFilter filter;

    public RemixEditorWindow(RemixApp remixApp, REPLInputOutput remixOutput, boolean darkTheme) {
        programOutput = remixOutput;
        super("Remix - untitled");
        editorTextPane = new JTextPane();

        editorTextPane.addKeyListener(new CatchKeys());
        editorTextPane.setMargin(new Insets(5, 10, 5, 10));
        theDocument = setUpStylesAndSpacing(editorTextPane, defaultFontForScreen, 21, darkTheme);
        edLexer = new RemixEdLexer(theDocument, darkTheme);

        // Define the keystroke for Tab
        KeyStroke tabKey = KeyStroke.getKeyStroke("TAB");

        // Override the default Tab behavior
        editorTextPane.getInputMap().put(tabKey, "customTab");
        editorTextPane.getActionMap().put("customTab", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int caretPos = editorTextPane.getCaretPosition();
                try {
                    System.out.println(theDocument.getText(0, theDocument.getLength()));
                } catch (BadLocationException ex) {
                    throw new RuntimeException(ex);
                }
                if (filter.couldInsertTab(caretPos)) {
                    // Insert a standard tab character into the document
                    editorTextPane.replaceSelection("\t");
                } else {
                    moveCursorToNextParam();
                }
            }
        });

        documentListener = new RemixDocumentListener();
        theDocument.addDocumentListener(documentListener);
        theDocument.addUndoableEditListener(new MyUndoableEditListener());
        filter = new RemixEdFilter(theDocument);
//        filter.setEdLexer(edLexer);
        theDocument.setDocumentFilter(filter);

        JScrollPane editorScrollPane = new JScrollPane(editorTextPane);
//        TextLineNumber lineNumbers = new TextLineNumber(editorTextPane);
//        editorScrollPane.setRowHeaderView(lineNumbers);

        add(editorScrollPane, BorderLayout.CENTER);

        //Create the status area.
        JPanel statusPane = new JPanel();
        caretListenerLabel = new CaretListenerLabel("line: 1, line offset: 0, offset from start: 0, style: default");
        statusPane.add(caretListenerLabel);
        add(statusPane, BorderLayout.PAGE_END);

        //Set up the menu bar.
        actions = createActionTable(editorTextPane);
        JMenuBar mb = new JMenuBar();
        JMenu fileMenu = createFileMenu();
        mb.add(fileMenu);
        JMenu editMenu = createEditMenu();
        mb.add(editMenu);
        JMenu controlMenu = createControlMenu();
        mb.add(controlMenu);
        setJMenuBar(mb);

        //Add some key bindings.
        addEditBindings(editMenu);
        addControlBindings(controlMenu);

        docArea = new JTextArea("Document goes here.");
        docArea.setForeground(Color.red);
        docPanel.add(docArea);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                // Called after dispose() finishes
                remixApp.editorWindowClosed(RemixEditorWindow.this);
            }
        });
    }

    public String getCurrentFileName() {
        return currentFileName;
    }

//    public boolean isEditing() {
//        return editing;
//    }
//
//    public void setEditing(boolean editing) {
//        this.editing = editing;
//    }

    class CatchKeys extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            if ((e.getKeyCode() == KeyEvent.VK_V) && ((e.getModifiersEx() & KeyEvent.META_DOWN_MASK) != 0)) {
                editorContentSaved = false;
            }
        }

        @Override
        public void keyTyped(KeyEvent e) {
            editorContentSaved = false;
            switch (e.getKeyChar()) {
//                case '\t':
//                    if (moveToParam) {
//                        moveCursorToNextParam();
//                        moveToParam = false;
//                    }
//                    break;
                case 27:
                    theDocument.cancelCompletionHandling();
                default:
                    popupScreenLocation = null;
                    if (docPopup != null)
                        docPopup.hide();
            }
        }
    }

    private RemixStyledDocument setUpStylesAndSpacing(JTextPane textPane, Font baseFont, float tabSize, boolean darkTheme) {
        RemixStyledDocument document;
        setTextPaneTheme(textPane, darkTheme);
        // the base font
        textPane.setFont(baseFont); // previously "Monaco" on Mac
        document = new RemixStyledDocument(this, textPane);
        textPane.setStyledDocument(document);
//        edLexer = new RemixEdLexer(document, darkTheme);
//        document.setEdLexer(edLexer);

        // set up the tabs
        StyleContext sc = StyleContext.getDefaultStyleContext();
        // 21 matches 3 characters of 12 font width
        // 40 matches 4 characters of new courier 16 font width
        // 24 matches 3 characters of 14 font width
        List<TabStop> tabList = new ArrayList<>();
        for (int i = 1; i <= 40; i++) {
            tabList.add(new TabStop(tabSize * i));
        }
        TabSet tabs = new TabSet(tabList.toArray(new TabStop[0]));
        AttributeSet paraSet = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.TabSet, tabs);
        textPane.setParagraphAttributes(paraSet, false);

        MutableAttributeSet lineSet = new SimpleAttributeSet();
        StyleConstants.setLineSpacing(lineSet, 0.25f);
        textPane.setParagraphAttributes(lineSet, false);
        return document;
    }

    private void setTextPaneTheme(JTextPane textPane, boolean dark) {
        if (dark) {
            textPane.setForeground(Color.white);
            textPane.setBackground(Color.black);
            textPane.setCaretColor(Color.white);
            textPane.setSelectionColor(new Color(100, 80, 80));
        } else {
            textPane.setForeground(Color.black);
            textPane.setBackground(Color.white);
            textPane.setCaretColor(Color.black);
            textPane.setSelectionColor(new Color(165, 175, 175));
        }
    }

    public void newFileInWindow() {
        untitledName = "*" + untitledName + "*";
        currentFileName = untitledName;
        setTitle("Remix - " + currentFileName);
//        theDocument.setEdLexer(edLexer);
//        try {
//            edLexer.fullLex();
//        } catch (BadLocationException e) {
//            throw new RuntimeException(e);
//        }
        editorTextPane.setCaretPosition(0);
        addKeystrokeActions();
        editorContentSaved = false;
        undo.discardAllEdits();
        undoAction.updateUndoState();
        redoAction.updateRedoState();
        editorTextPane.addCaretListener(caretListenerLabel);
    }

    public boolean openFileInWindow(File remFile) {
        currentFileName = remFile.getName();
        currentAbsoluteFileName = remFile.getAbsolutePath();
        setTitle("Remix - " + currentFileName);
        try {
            Scanner myReader = new Scanner(remFile);
            // turn off document updates
            theDocument.removeDocumentListener(documentListener);
            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();
                theDocument.insertLine(line + "\n");
            }
            // turn on document updates
            theDocument.addDocumentListener(documentListener);
//            theDocument.setEdLexer(edLexer);
            edLexer.fullLex();
            myReader.close();
            editorTextPane.setCaretPosition(0);
            addKeystrokeActions();
            editorContentSaved = true;
            undo.discardAllEdits();
            undoAction.updateUndoState();
            redoAction.updateRedoState();
            editorTextPane.addCaretListener(caretListenerLabel);
        } catch (FileNotFoundException | BadLocationException e) {
            return false;
        }
        return true;
    }

    /*
    This is where completion handling is instigated.
     */
    private void addKeystrokeActions() {
        editorTextPane.getInputMap().put(KeyStroke.getKeyStroke("shift TAB"), "completionName");
        editorTextPane.getActionMap().put("completionName", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (popupScreenLocation == null)
                        popupScreenLocation = getPopupScreenLocation();
                    int caretPos = editorTextPane.getCaretPosition();
                    Element root = theDocument.getDefaultRootElement();
                    int lineNumber = root.getElementIndex(caretPos) + 1;
                    String docText = theDocument.completionHandling(caretPos, lineNumber);
                    if (docPopup != null)
                        docPopup.hide();
                    if (docText != null && !docText.isEmpty()) {
                        docArea.setText(docText);
                        docPopup = popupFactory.getPopup(editorTextPane, docPanel, popupScreenLocation.x, popupScreenLocation.y);
                        docPopup.show();
                        System.out.println("just done docPopup.show(): " + docPopup);
                    }
                } catch (BadLocationException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    private Point getPopupScreenLocation() {
        Point editorLocation = editorTextPane.getLocationOnScreen();
        return new Point(caretPoint.x + editorLocation.x, caretPoint.y + editorLocation.y + 16);
    }


    //The following two methods allow us to find an
    //action provided by the editor kit by its name.
    private HashMap<Object, Action> createActionTable(JTextComponent textComponent) {
        HashMap<Object, Action> actions = new HashMap<>();
        Action[] actionsArray = textComponent.getActions();
        for (Action a : actionsArray) {
            actions.put(a.getValue(Action.NAME), a);
        }
        return actions;
    }

    private Action getActionByName(String name) {
        return actions.get(name);
    }

    //Add a couple of emacs key bindings for navigation.
    protected void addEditBindings(JMenu editMenu) {
        InputMap inputMap = editorTextPane.getInputMap();

        //Command-z to undo last change
        KeyStroke key = KeyStroke.getKeyStroke(KeyEvent.VK_Z, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx());
        JMenuItem menuItem = editMenu.getItem(0); // undo
        inputMap.put(key, menuItem.getAction());

        //Command-shift-z to redo last undo
        key = KeyStroke.getKeyStroke(KeyEvent.VK_Z, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx() | InputEvent.SHIFT_DOWN_MASK);
        menuItem = editMenu.getItem(1); // redo
        inputMap.put(key, menuItem.getAction());
    }

    protected void addControlBindings(JMenu controlMenu) {
//        InputMap inputMap = editorTextPane.getInputMap();
        //Command-r to run
//        KeyStroke key = KeyStroke.getKeyStroke(KeyEvent.VK_R, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx());
        JMenuItem menuItem = controlMenu.getItem(0); // run
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.META_DOWN_MASK));
//        inputMap.put(key, menuItem.getAction());
    }

    private void moveCursorToNextParam() {
        // TODO: doesn't deal nicely with nested parameters e.g. (do (block))
//        SwingUtilities.invokeLater(() -> {
            int pos = editorTextPane.getCaretPosition();
            char ch;
            Segment allText = new Segment();
            try {
                theDocument.getText(0, theDocument.getLength(), allText);
            } catch (BadLocationException e) {
                return; // ignore
            }
            if (pos < allText.count) { // not at the end of text
                ch = allText.charAt(pos); //getText(pos, 1);
                // if newline move back to start of the line
                if (ch == '\n') { // end of line
                    // move past following ellipsis (if there is one)
                    int ellipsisPos = movePastEllipsis(allText, pos);
                    if (pos < ellipsisPos) {
                        pos = ellipsisPos;
                    } else {
                        pos = lineStartPos(pos); // back to start of text on this line
                    }
                }
            } else if (pos > 0 && pos == allText.count) { // if at end of the text move back too
                pos = lineStartPos(pos); // back to start of text on this line
            }
            while (pos < allText.count) {
                ch = allText.charAt(pos);
                if (ch == '\n') {
                    break;
                }
                if ("[(".indexOf(ch) >= 0) { // find opening bracket
                    pos++;
                    break;
                }
                pos++;
            }
            while (pos < allText.count) { // in case multiple "((" we start at the last on
                ch = allText.charAt(pos);
                if ("[(".indexOf(ch) == -1)
                    break;
                pos++;
            }
            editorTextPane.setSelectionStart(pos);
            while (pos < allText.count) {
                ch = allText.charAt(pos);
                if (ch == '\n') {
                    break;
                }
                if ("])".indexOf(ch) >= 0) { // find closing bracket
                    break;
                }
                pos++;
            }
            editorTextPane.setSelectionEnd(pos);
//        });
    }

    private int movePastEllipsis(Segment allText, int pos) {
        int ellipsisPos = pos + 1;
        if (ellipsisPos < allText.count) {
            char ch = allText.charAt(ellipsisPos);
            while (ch == '\t' && ellipsisPos + 1 < allText.count) {
                ellipsisPos++;
                ch = allText.charAt(ellipsisPos);
            }
            if (ch == '…') {
                return ellipsisPos + 1;
            } else {
                return pos;
            }
        }
        return pos;
    }

    /* The start of line position before the given position. */
    int lineStartPos(int pos) {
        int caretPosition = editorTextPane.getCaretPosition();
        Element root = theDocument.getDefaultRootElement();
        int lineIndex = root.getElementIndex(caretPosition);
        Element lineElement = root.getElement(lineIndex);
        return lineElement.getStartOffset();
//        do
//            pos--;
//        while (!lineStart(pos));
//        return pos;
    }

    /***** File menu and items *****/

    //Create the file menu.
    protected JMenu createFileMenu() {
        JMenu menu = new JMenu("File");
        SaveFileAction saveAction = new SaveFileAction();
        menu.add(saveAction);
        SaveAsFileAction saveAsAction = new SaveAsFileAction();
        menu.add(saveAsAction);
        PrintFileAction printAction = new PrintFileAction();
        menu.add(printAction);
        return menu;
    }

    class SaveFileAction extends AbstractAction {
        public SaveFileAction() {
            super("Save");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            saveFile();
        }
    }

    class SaveAsFileAction extends AbstractAction {
        public SaveAsFileAction() {
            super("Save as…");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            saveAsFile();
        }
    }

    class PrintFileAction extends AbstractAction {
        public PrintFileAction() {
            super("Print…");
        }

        @Override
        public void actionPerformed(ActionEvent ev) {
            RemixStyledDocument document;
            RemixEdLexer printEdLexer;
            JTextPane printTextPane = new JTextPane();
            document = setUpStylesAndSpacing(printTextPane, defaultFontForPrinter, 15, false);
            printEdLexer = new RemixEdLexer(document, false);
            String textInDocument;
            try {
                textInDocument = theDocument.getText(0, theDocument.getLength());
                document.insertString(0, textInDocument, null);
                printEdLexer.fullLex();
            } catch (BadLocationException e) {
                throw new RuntimeException(e);
            }
            try {
                printEdLexer.fullLex();
            } catch (BadLocationException e) {
                throw new RuntimeException(e);
            }
            PrinterJob job = PrinterJob.getPrinterJob();
            job.setPrintable(printTextPane.getPrintable(
                    null, // currentFileName == null ? null : new MessageFormat(currentFileName),
                    new MessageFormat("Page {0}")));
            if (job.printDialog()) {
                try {
                    job.print();
                } catch (PrinterException e) {
                    System.err.println("Error during printing: " + e.getMessage());
                }
            }
        }
    }

    private void saveFile() {
        if (currentAbsoluteFileName == null)
            saveAsFile();
        else {
            try {
                Files.write(Path.of(currentAbsoluteFileName), editorTextPane.getText().getBytes());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            editorContentSaved = true;
        }
    }

    private void saveAsFile() {
        JFileChooser chooser = new JFileChooser(RemixApp.currentDirectory);
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Remix programs", "rem");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showSaveDialog(editorTextPane);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            RemixApp.currentDirectory = chooser.getCurrentDirectory().getAbsolutePath();
            File file = chooser.getSelectedFile();
            currentFileName = file.getName();
            setTitle("Remix - " + currentFileName);
            currentAbsoluteFileName = file.getAbsolutePath();
            saveFile();
        }
    }

    /***** Edit menu and items *****/

    //Create the edit menu.
    protected JMenu createEditMenu() {
        JMenu menu = new JMenu("Edit");

        //Undo and redo are actions of our own creation.
        undoAction = new UndoAction();
        menu.add(undoAction);

        redoAction = new RedoAction();
        menu.add(redoAction);

        menu.addSeparator();

        //These actions come from the default editor kit.
        //Get the ones we want and stick them in the menu.
//        Action cut = getActionByName(DefaultEditorKit.cutAction);
        Action cut = new DefaultEditorKit.CutAction();
        cut.putValue(Action.NAME, "Cut");
//        JMenuItem cut = new JMenuItem(new DefaultEditorKit.CutAction());
//        cut.setActionCommand("Cut");
        menu.add(cut);
        Action copy = new DefaultEditorKit.CopyAction();
        //getActionByName(DefaultEditorKit.copyAction);
        copy.putValue(Action.NAME, "Copy");
        menu.add(copy);
        Action paste = new DefaultEditorKit.PasteAction();
        //getActionByName(DefaultEditorKit.pasteAction);
        paste.putValue(Action.NAME, "Paste");
        menu.add(paste);

        menu.addSeparator();

        Action find = new FindAction("Find");
        menu.add(find);
        Action findForwards = new FindForwardAction("Find forwards");
        menu.add(findForwards);
        Action backFind = new FindBackwardAction("Find backwards");
        menu.add(backFind);

        menu.addSeparator();

        Action indent = new IndentSelection("Indent selection");
        menu.add(indent);
        Action dedent = new DedentSelection("Dedent selection");
        menu.add(dedent);

//        menu.addSeparator();

//        Action select = getActionByName(DefaultEditorKit.selectAllAction);
//        select.putValue(Action.NAME, "Select All");
//        menu.add(select);
        return menu;
    }

    public class FindAction extends AbstractAction {

        public FindAction(String name) {
            super(name);
            putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.META_DOWN_MASK));
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String searchTerm = JOptionPane.showInputDialog(
                    editorTextPane,         // Parent component
                    "Enter search term:",   // Message
                    "Find",                 // Title
                    JOptionPane.QUESTION_MESSAGE // Message type (icon)
            );

            // Process the input
            if (searchTerm != null && !searchTerm.isEmpty()) {
                searchForward(searchTerm, 0, editorTextPane);
            }
        }
    }

    public class FindForwardAction extends AbstractAction {

        public FindForwardAction(String name) {
            super(name);
            putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_G, InputEvent.META_DOWN_MASK));
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            int selectionStart = editorTextPane.getSelectionStart();
            int selectionEnd = editorTextPane.getSelectionEnd();
            if (selectionStart != selectionEnd) {
                String selectedText = editorTextPane.getSelectedText();
                searchForward(selectedText, selectionEnd, editorTextPane);
            }
        }
    }

    private void searchForward(String selectedText, int selectionEnd, JTextPane editorTextPane) {
        try {
            String allText = theDocument.getText(0, theDocument.getLength());
            int location = allText.indexOf(selectedText, selectionEnd);
            if (location != -1) {
                Caret caret = editorTextPane.getCaret();
                caret.setDot(location);
                caret.moveDot(location + selectedText.length());
            }
        } catch (BadLocationException ex) {
            ex.printStackTrace();
        }
    }

    public class FindBackwardAction extends AbstractAction {

        public FindBackwardAction(String name) {
            super(name);
            putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_B, InputEvent.META_DOWN_MASK));
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            int selectionStart = editorTextPane.getSelectionStart();
            int selectionEnd = editorTextPane.getSelectionEnd();
            if (selectionStart != selectionEnd) {
                String selectedText = editorTextPane.getSelectedText();
                searchBack(selectedText, selectionStart, editorTextPane);
            }
        }
    }

    private void searchBack(String selectedText, int selectionStart, JTextPane editorTextPane) {
        try {
            String allText = theDocument.getText(0, theDocument.getLength());
            int location = allText.lastIndexOf(selectedText, selectionStart - 1);
            if (location != -1) {
                Caret caret = editorTextPane.getCaret();
                caret.setDot(location);
                caret.moveDot(location + selectedText.length());
            }
        } catch (BadLocationException ex) {
            ex.printStackTrace();
        }
    }

    public class IndentSelection extends AbstractAction {

        public IndentSelection(String name) {
            super(name);
            putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.META_DOWN_MASK));
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            int selectionStart = editorTextPane.getSelectionStart();
            int selectionEnd = editorTextPane.getSelectionEnd();
            try {
                theDocument.addTabIndent(selectionStart, selectionEnd);
            } catch (BadLocationException ex) {
                ex.printStackTrace();
            }
        }
    }

    public class DedentSelection extends AbstractAction {

        public DedentSelection(String name) {
            super(name);
            putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.META_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK));
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            int selectionStart = editorTextPane.getSelectionStart();
            int selectionEnd = editorTextPane.getSelectionEnd();
            try {
                theDocument.removeTabIndent(selectionStart, selectionEnd);
            } catch (BadLocationException ex) {
                ex.printStackTrace();
            }
        }
    }

    protected JMenu createControlMenu() {
        JMenu menu = new JMenu("Control");
        runAction = new RunAction();
        menu.add(runAction);
        stopAction = new StopAction();
        menu.add(stopAction);
        return menu;
    }

    //This one listens for edits that can be undone.
    // Attribute changes don't count.
    protected class MyUndoableEditListener implements UndoableEditListener {

        public void undoableEditHappened(UndoableEditEvent e) {
            //  Check for an attribute change
            AbstractDocument.DefaultDocumentEvent event = (AbstractDocument.DefaultDocumentEvent) e.getEdit();
            if (!event.getType().equals(DocumentEvent.EventType.CHANGE)) {
//                System.out.println(event.getType());
//                    System.out.println("doc length: " + theDocument.getLength());

                //Remember the edit and update the menus.
                undo.addEdit(event);
//                System.out.println("significant undos: " + undo.isSignificant());
                undoAction.updateUndoState();
                redoAction.updateRedoState();
//           undo.addEdit(event);
////            System.err.println(event.getType());
//                //Remember the edit and update the menus.
//                undoAction.updateUndoState();
//                redoAction.updateRedoState();
//            }
            }
        }
    }

    protected class RunAction extends AbstractAction {

        protected RunAction() {
            super("Run");
            setEnabled(true);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // hideGraphicsPanel(); // uncomment if you want the graphics panel hidden
//            systemOutput.setText(null);
//            programOutput.setText(null);
//            programOutput.setFocusable(false); // Temporarily make it non-focusable
//            RemixEditor.this.requestFocusInWindow(); // Request focus for the main panel
            programOutput.setFocusable(true); // Make it focusable again for future use
            remixRunner = new RemixSwingWorker(
                    RemixEditorWindow.this
            );
            stopAction.setEnabled(true);
            setEnabled(false); // changed back when running finishes or is terminated
//            animations.clear();
//            setEditing(false); // only changed after program completed
            remixRunner.execute(); // this causes the program to run in a background thread
        }
    }

    protected class StopAction extends AbstractAction {

        protected StopAction() {
            super("Stop");
            setEnabled(false);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            remixRunner.cancel(true);
//            stopAllAnimations();
            System.out.println("Program cancelled.");
            setEnabled(false);
            runAction.setEnabled(true);
//            setEditing(true);
        }
    }

    public static void waitForProgramFinish() {
//        animationLock.lock();
//        try {
//            while (!animationsStopped()) {
//                animationFinished.await();
//            }
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        } finally {
//            animationLock.unlock();
//        }
    }

    /*
    This is called by the completion handling code.
    So error catching is mostly ignored.
     */
    protected void reparseProgramText() {
        LibrariesAndCompletions.resetToEditorStandard();
        ParseTree tree = RemixPrepareRun.processParse(this.getProgramText());
        EvalVisitorForEditor eval = new EvalVisitorForEditor(currentFileName);
        LibraryExpression programLib = (LibraryExpression) eval.visit(tree);
        programLib.setActiveLines(LibraryExpression.ALLLINES);
        /*
        Revert added libraries to baseLibrary
        LibrariesAndCompletions.resetAddedLibraries();

        Add programLib
        LibrariesAndCompletions.addLibrary(programLib);
         */
        LibrariesAndCompletions.addAfterBaseLibrary(programLib);
        // the visit above fills in addedLibraries in LibrariesAndCompletions
        // this the result program is added as well.
    }

    public String getProgramText() {
        return editorTextPane.getText();
    }

    class UndoAction extends AbstractAction {
        public UndoAction() {
            super("Undo");
            putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.META_DOWN_MASK));
            setEnabled(false);
        }

        public void actionPerformed(ActionEvent e) {
            // undoing the insertion of a newline causes a crash
            try {
                AbstractDocument.DefaultDocumentEvent event =
                        (AbstractDocument.DefaultDocumentEvent) undo.peekUndo();
                undo.undo();
//                System.out.println("length after undo: " + theDocument.getLength());
//                System.out.println("length of undo: " + event.getLength());
//                edLexer.lexAfterUndoRedo(event, true); // REINSTATE THIS
//                edLexer.lexFromHere(event.getOffset());
            } catch (CannotUndoException ex) { // | BadLocationException ex) {
                System.err.println("length: " + theDocument.getLength() +
                                           " Exception: " + ex);
                return;
            }
            updateUndoState();
            redoAction.updateRedoState();
        }

        protected void updateUndoState() {
            if (undo.canUndo()) {
                setEnabled(true);
                putValue(Action.NAME, undo.getUndoPresentationName());
            } else {
                setEnabled(false);
                putValue(Action.NAME, "Undo");
            }
        }
    }

    class RedoAction extends AbstractAction {
        public RedoAction() {
            super("Redo");
            putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.META_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK));
            setEnabled(false);
        }

        public void actionPerformed(ActionEvent e) {
            try {
                AbstractDocument.DefaultDocumentEvent event =
                        (AbstractDocument.DefaultDocumentEvent) undo.peekRedo();
                undo.redo();
//                edLexer.lexFromHere(event.getOffset());//lexAfterUndoRedo(event, false);
            } catch (CannotRedoException ex) { //| BadLocationException ex) {
                return;
            }
            updateRedoState();
            undoAction.updateUndoState();
        }

        protected void updateRedoState() {
            if (undo.canRedo()) {
                setEnabled(true);
                putValue(Action.NAME, undo.getRedoPresentationName());
            } else {
                setEnabled(false);
                putValue(Action.NAME, "Redo");
            }
        }
    }

    private void setTextPaneTheme(boolean dark) {
        if (dark) {
            editorTextPane.setForeground(Color.white);
            editorTextPane.setBackground(Color.black);
            editorTextPane.setCaretColor(Color.white);
            editorTextPane.setSelectionColor(new Color(100, 80, 80));
        } else {
            editorTextPane.setForeground(Color.black);
            editorTextPane.setBackground(Color.white);
            editorTextPane.setCaretColor(Color.black);
            editorTextPane.setSelectionColor(new Color(165, 175, 175));
        }
    }

    protected void setDarkTheme(boolean dark) {
        setTextPaneTheme(dark);
        edLexer = new RemixEdLexer(theDocument, dark);
//        theDocument.setEdLexer(edLexer);
//        try {
//            edLexer.fullLex();
//        } catch (BadLocationException ex) {
//            throw new RuntimeException(ex);
//        }
    }

    //This listens for and reports caret movements.
    protected class CaretListenerLabel extends JLabel implements CaretListener {
        private int lastLine = 0;

        public CaretListenerLabel(String label) {
            super(label);
        }

        //Might not be invoked from the event dispatch thread.
        public void caretUpdate(CaretEvent event) {
            int mark = event.getMark();
            displayPositionInfo(mark);
            Rectangle2D rect;
            int docLength = ((JTextComponent)event.getSource()).getDocument().getLength();
            if (docLength == 0) {
                caretPoint = new Point(10, 5);
                return;
            }
            try {
                rect = ((JTextComponent)event.getSource()).modelToView2D(mark);
            } catch (BadLocationException e) {
                throw new RuntimeException(e);
            }
            caretPoint = new Point((int)rect.getX(), (int)rect.getY());
        }

        protected void displayPositionInfo(final int mark) {
            Element root = theDocument.getDefaultRootElement();
            int lineNumber = root.getElementIndex(mark) + 1;
            int startOfLine = root.getElement(lineNumber - 1).getStartOffset();
//            SwingUtilities.invokeLater(() -> {
                if (lineNumber != lastLine) { // added this so moving to a different line clears completions
                    theDocument.clearCompletions();
                    popupScreenLocation = null;
                    if (docPopup != null)
                        docPopup.hide();
                }
                lastLine = lineNumber;
                setText("line: " + lineNumber +
                                ", line offset: " + (mark - startOfLine) +
                                ", offset from start: " + mark +
                                ", style: " + edLexer.getStyleName(mark));
//            });
        }
    }

    /*
    Used to fix the position of the cursor and/or selection.
     */
    private class RemixDocumentListener implements DocumentListener {

        private boolean removedPair = false;
        private final Segment textSegment = new Segment();

        private String changedText(DocumentEvent documentEvent) throws BadLocationException {
            // WARNING: returns text from the document
            // the documentEvent may have altered the document
            int pos = documentEvent.getOffset();
            int length = documentEvent.getLength();
            theDocument.getText(pos, length, textSegment);
            return String.valueOf(textSegment);
        }

        @Override
        public void insertUpdate(DocumentEvent documentEvent) {
            System.out.println("insertUpdate: " + documentEvent.getLength());
            // first hacky attempt at moving cursor to the correct spot after [] has been replace with
            // a block, indentation and a '...'.
            String changedText = "";
            String[] matchingPairs = {"{}", "[]", "()", "\"\"", "''"};
            if (documentEvent.getType() == DocumentEvent.EventType.INSERT) {
                try {
                    changedText = changedText(documentEvent);
                    System.out.println(changedText);
                } catch (BadLocationException e) {
                    return;
                }
                if (changedText.equals("\n\t\n…")) { // inserted an implicit block and ...
                    moveCursorInsideBlock(documentEvent);
                } else if (matchStringFromArray(changedText, matchingPairs)) {
                    moveCursorOnByOne(documentEvent);
                } else if (changedText.length() == 1) { // could be a digit or constant between
                    // deleted parentheses
                    char ch = changedText.toCharArray()[0];
                    if ((Character.isDigit(ch) || Character.isUpperCase(ch)) && removedPair)
                        moveCursorOnByOne(documentEvent);
                } else if (changedText.contains("(") || changedText.contains("[")) { // could be completion
                    // move cursor back to start of insertion

                    // then move to the next parameter
                    moveCursorToNextParam();
                }
            }
            removedPair = false;
        }

        private boolean matchStringFromArray(String input, String[] possibles) {
            for (String item : possibles) {
                if (input.equals(item))
                    return true;
            }
            return false;
        }

        @Override
        public void removeUpdate(DocumentEvent documentEvent) {
            System.out.println("removeUpdate document length: " + documentEvent.getLength());
            try {
                String changedText = changedText(documentEvent);
                System.out.println(changedText);
            } catch (BadLocationException e) {}
            if (documentEvent.getLength() == 2)
                removedPair = true; // a flag to say a pair of chars removed
        }

        @Override
        public void changedUpdate(DocumentEvent documentEvent) {
            System.out.println("changedUpdate");
        }

        private void moveCursorOnByOne(DocumentEvent documentEvent) {
//            SwingUtilities.invokeLater(() -> {
                editorTextPane.setCaretPosition(documentEvent.getOffset() + 1);
//            });
        }

        private void moveCursorInsideBlock(DocumentEvent documentEvent) {
//            SwingUtilities.invokeLater(() -> {
                editorTextPane.setCaretPosition(documentEvent.getOffset() + 2);
//            });
        }
    }
}