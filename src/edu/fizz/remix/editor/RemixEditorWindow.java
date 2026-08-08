package edu.fizz.remix.editor;

import edu.fizz.remix.EvalVisitorForEditor;
import edu.fizz.remix.runtime.LibrariesAndCompletions;
import edu.fizz.remix.runtime.LibraryExpression;
import org.antlr.v4.runtime.tree.ParseTree;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.*;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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

import static edu.fizz.remix.editor.RemixPrepareRun.EDITORTEXT;

public class RemixEditorWindow extends JFrame {
    public static final int SIZE = 12; // 12 is small but gives 80 col printout on A4

    private final JTextPane editorTextPane;
    private Point caretPoint;
    private static RemixStyledDocument2 doc;
    private final PopupFactory popupFactory = new PopupFactory();
    private Popup docPopup;
    private final JPanel docPanel = new JPanel();
    protected JTextArea docArea;
    protected Point popupScreenLocation = null;

    private HashMap<Object, Action> actions;
    private final CaretListenerLabel caretListenerLabel;

    private RemixSwingWorker remixRunner;
    protected RemixEditor.RunAction runAction;
    protected RemixEditor.StopAction stopAction;
    private RemixEditor.DarkThemeAction darkThemeAction;
    private RemixEditor.LightThemeAction lightThemeAction;
    private static final Font defaultFontForScreen = new Font("monospaced", Font.PLAIN, SIZE);
    private static final Font defaultFontForPrinter = new Font("monospaced", Font.PLAIN, 8);

    //undo helpers
    private UndoAction undoAction;
    private RedoAction redoAction;
    private final RevealUndoManager undo = new RevealUndoManager();

    private static boolean editing = true;

    private RemixEdLexer2 edLexer;
    private String currentFileName;
    private String currentAbsoluteFileName = null;
    private boolean editorContentSaved = true;

    public RemixEditorWindow() {
        super("Remix - untitled");
        editorTextPane = new JTextPane();
        editorTextPane.addKeyListener(new CatchKeys());
        editorTextPane.setMargin(new Insets(5,10,5,10));
        doc = setUpStylesAndSpacing(this, editorTextPane, defaultFontForScreen, 21, true);

        JScrollPane editorScrollPane = new JScrollPane(editorTextPane);
        TextLineNumber lineNumbers = new TextLineNumber(editorTextPane);
        editorScrollPane.setRowHeaderView(lineNumbers);

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
//        JMenu controlMenu = createControlMenu();
//        mb.add(controlMenu);
        setJMenuBar(mb);
    }

    class CatchKeys extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            if ((e.getKeyCode() == KeyEvent.VK_V) && ((e.getModifiersEx() & KeyEvent.META_DOWN_MASK) != 0)) {
                editorContentSaved = false;
            }
            super.keyPressed(e);
        }

        @Override
        public void keyTyped(KeyEvent e) {
            editorContentSaved = false;
            switch (e.getKeyChar()) {
                case '\t':
                    break;
                case 27:
                    doc.cancelCompletionHandling();
                default :
                    popupScreenLocation = null;
                    if (docPopup != null)
                        docPopup.hide();
            }
            super.keyTyped(e);
        }
    }

    private RemixStyledDocument2 setUpStylesAndSpacing(RemixEditorWindow editor, JTextPane textPane, Font baseFont, float tabSize, boolean darkTheme) {
        RemixStyledDocument2 document;
        setTextPaneTheme(textPane, darkTheme);
        // the base font
        textPane.setFont(baseFont); // previously "Monaco" on Mac
        document = new RemixStyledDocument2(editor, textPane);
        textPane.setStyledDocument(document);
        edLexer = new RemixEdLexer2(document, darkTheme);
        document.setEdLexer(edLexer);

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

    public boolean openFileInWindow(File remFile) {
        currentFileName = remFile.getName();
        currentAbsoluteFileName = remFile.getAbsolutePath();
        setTitle("Remix - " + currentFileName);
        try {
            Scanner myReader = new Scanner(remFile);
            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();
                doc.insertLine(line + "\n");
            }
            doc.setEdLexer(edLexer);
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

    private void addKeystrokeActions() {
        editorTextPane.getInputMap().put(KeyStroke.getKeyStroke("shift TAB"), "actionName");
        editorTextPane.getActionMap().put("actionName", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (popupScreenLocation == null)
                        popupScreenLocation = getPopupScreenLocation();
                    Element root = doc.getDefaultRootElement();
                    int mark = editorTextPane.getCaretPosition();
                    int lineNumber = root.getElementIndex(mark) + 1;
                    String docText = doc.completionHandling(mark, lineNumber);
                    if (docPopup != null)
                        docPopup.hide();
                    if (docText != null && !docText.isEmpty()) {
                        docArea.setText(docText);
                        docPopup = popupFactory.getPopup(editorTextPane, docPanel, popupScreenLocation.x, popupScreenLocation.y);
                        docPopup.show();
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
        public PrintFileAction() { super("Print…");}

        @Override
        public void actionPerformed(ActionEvent ev) {
            RemixStyledDocument2 document;
            RemixEdLexer2 printEdLexer;
            JTextPane printTextPane = new JTextPane();
            document = setUpStylesAndSpacing(null, printTextPane, defaultFontForPrinter, 15,false);
            printEdLexer = new RemixEdLexer2(document, false);
            String textInDocument;
            try {
                textInDocument = doc.getText(0, doc.getLength());
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

    private static void searchForward(String selectedText, int selectionEnd, JTextPane editorTextPane) {
        try {
            String allText = doc.getText(0, doc.getLength());
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

    private static void searchBack(String selectedText, int selectionStart, JTextPane editorTextPane) {
        try {
            String allText = doc.getText(0, doc.getLength());
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
                doc.addTabIndent(selectionStart, selectionEnd);
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
                doc.removeTabIndent(selectionStart, selectionEnd);
            } catch (BadLocationException ex) {
                ex.printStackTrace();
            }
        }
    }

    protected void reparseProgramText() {
//        systemOutput.setText("");
        LibrariesAndCompletions.resetToEditorStandard();
        ParseTree tree = RemixPrepareRun.processParse(RemixEditorWindow.this.getProgramText(), EDITORTEXT);
        EvalVisitorForEditor eval = new EvalVisitorForEditor();
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
            setEnabled(false);
        }

        public void actionPerformed(ActionEvent e) {
            try {
                AbstractDocument.DefaultDocumentEvent event =
                        (AbstractDocument.DefaultDocumentEvent) undo.peekUndo();
                undo.undo();
                edLexer.lexAfterUndoRedo(event, true);
            } catch (CannotUndoException | BadLocationException ex) {
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
            setEnabled(false);
        }

        public void actionPerformed(ActionEvent e) {
            try {
                AbstractDocument.DefaultDocumentEvent event =
                        (AbstractDocument.DefaultDocumentEvent) undo.peekRedo();
                undo.redo();
                edLexer.lexAfterUndoRedo(event, false);
            } catch (CannotRedoException | BadLocationException ex) {
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
            Element root = doc.getDefaultRootElement();
            int lineNumber = root.getElementIndex(mark) + 1;
            int startOfLine = root.getElement(lineNumber - 1).getStartOffset();
            SwingUtilities.invokeLater(() -> {
                if (lineNumber != lastLine) { // added this so moving to a different line clears completions
                    doc.clearCompletions();
                    popupScreenLocation = null;
                    if (docPopup != null)
                        docPopup.hide();
                }
                lastLine = lineNumber;
                setText("line: " + lineNumber +
                                ", line offset: " + (mark - startOfLine) +
                                ", offset from start: " + mark +
                                ", style: " + edLexer.getStyleName(mark));
            });
        }
    }

}
