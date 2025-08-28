package edu.fizz.remix.editor;
/*
 * TextComponentDemo.java requires one additional file:
 *   DocumentSizeFilter.java
 */

import edu.fizz.remix.EvalVisitorForEditor;
import edu.fizz.remix.libraries.GraphicsPanel;
import edu.fizz.remix.runtime.LibraryExpression;
import edu.fizz.remix.runtime.LibrariesAndCompletions;
import org.antlr.v4.runtime.tree.ParseTree;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.*;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import static edu.fizz.remix.runtime.LibrariesAndCompletions.resetToEditorStandard;

public class RemixEditor extends JFrame {

    public static final int SIZE = 12; // 12 is small but gives 80 col printout on A4
    private final JTextPane editorTextPane;
    private Point caretPoint; // the point of the top left of the caret within the document pane
    private static JSplitPane systemSplitPane;
    private static JSplitPane outputSplitPane;
    private static RemixStyledDocument doc;
    static JTextArea systemOutput;
    protected static JTextArea remixOutput;
    protected static GraphicsPanel graphicOutput;
    private final PopupFactory popupFactory = new PopupFactory();
    private Popup docPopup;
    private final JPanel docPanel = new JPanel();
    protected final JTextArea docArea;
    protected Point popupScreenLocation = null;
    static String currentDirectory = "remixPrograms";

    private final HashMap<Object, Action> actions;

    private RemixSwingWorker remixRunner;
    protected RunAction runAction;
    protected StopAction stopAction;
    private DarkThemeAction darkThemeAction;
    private LightThemeAction lightThemeAction;
    private static final Font defaultFontForScreen = new Font("monospaced", Font.PLAIN, SIZE);
    private static final Font defaultFontForPrinter = new Font("monospaced", Font.PLAIN, 8);
    //undo helpers
    private UndoAction undoAction;
    private RedoAction redoAction;
    private final UndoManager undo = new UndoManager();

    private static boolean editing = true;

    private RemixEdLexer edLexer;
    private String currentFileName = null;

    class CatchKeys extends KeyAdapter {
        @Override
        public void keyTyped(KeyEvent e) {
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

    public static boolean isEditing() {
        return editing;
    }

    public static void setEditing(boolean editing) {
        RemixEditor.editing = editing;
    }

    protected void reparseProgramText() {
        systemOutput.setText("");
        resetToEditorStandard();
        ParseTree tree = RemixREPL.processParse(RemixEditor.this);
        EvalVisitorForEditor eval = new EvalVisitorForEditor();
        LibraryExpression programLib = (LibraryExpression) eval.visit(tree);
        programLib.setActiveLines(LibraryExpression.ALLLINES);
        LibrariesAndCompletions.addFirstLibrary(programLib);
        // the visit above fills in addedLibraries in LibrariesAndCompletions
        // this the result program is added as well.
    }

    public RemixEditor() {
        super("Remix");
        //Create the text pane and configure it.
        editorTextPane = new JTextPane();
        editorTextPane.addKeyListener(new CatchKeys());
        editorTextPane.setMargin(new Insets(5,10,5,10));
        doc = setUpStylesAndSpacing(this, editorTextPane, defaultFontForScreen, 21, true);

        JScrollPane editorScrollPane = new JScrollPane(editorTextPane);
        editorScrollPane.setMinimumSize(new Dimension(711, 800));
        TextLineNumber lineNumbers = new TextLineNumber(editorTextPane);
        editorScrollPane.setRowHeaderView(lineNumbers);

        //Create the text area for the system/error output.
        systemOutput = new JTextArea();
        systemOutput.setBackground(Color.black);
        systemOutput.setFont(new Font("Courier New", Font.PLAIN, 14));
        systemOutput.setForeground(Color.orange);
        systemOutput.setEditable(false);
        systemOutput.setWrapStyleWord(true);
        JScrollPane scrollPaneForSystem = new JScrollPane(systemOutput);

        //Create the text area for the output and configure it.
        remixOutput = new JTextArea(); // 50, 100);
        remixOutput.setBackground(Color.darkGray);
        remixOutput.setFont(new Font("Courier New", Font.PLAIN, 14));
        remixOutput.setForeground(Color.white);
        remixOutput.setEditable(false);
        remixOutput.setLineWrap( true );
        remixOutput.setWrapStyleWord( true );
        JScrollPane scrollPaneForOutput = new JScrollPane(remixOutput);

        Dimension graphicsDimension = new Dimension(1000,800);
        graphicOutput = new GraphicsPanel(graphicsDimension);
        graphicOutput.setMinimumSize(graphicsDimension);

        //Create a split pane for the program editor and system/error output.
        systemSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, editorScrollPane, scrollPaneForSystem);
        systemSplitPane.setOneTouchExpandable(true);

        //Create a split pane for the graphics and text output.
        outputSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, graphicOutput, scrollPaneForOutput);
        outputSplitPane.setOneTouchExpandable(true);

        //Create a split pane for the output and the text area.
        // this is always set when a caret update occurs
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, systemSplitPane, outputSplitPane);
        splitPane.setOneTouchExpandable(true);
        splitPane.setResizeWeight(1);

        //Create the status area.
        JPanel statusPane = new JPanel();
        CaretListenerLabel caretListenerLabel = new CaretListenerLabel("line: 1, offset: 0, style: default");
        statusPane.add(caretListenerLabel);

        //Add the components.
        getContentPane().add(splitPane, BorderLayout.CENTER);
        getContentPane().add(statusPane, BorderLayout.PAGE_END);

        //Set up the menu bar.
        actions = createActionTable(editorTextPane);
        JMenuBar mb = new JMenuBar();
        JMenu fileMenu = createFileMenu();
        mb.add(fileMenu);
        JMenu editMenu = createEditMenu();
        mb.add(editMenu);
        setJMenuBar(mb);
        JMenu viewMenu = createViewMenu();
        mb.add(viewMenu);
        JMenu controlMenu = createControlMenu();
        mb.add(controlMenu);

        //Add some key bindings.
        addEditBindings(editMenu);
        addControlBindings(controlMenu);

        //Put the initial text into the text pane.
        editorTextPane.setCaretPosition(0);
        addKeystrokeActions();

        //Start watching for undoable edits and caret changes.
        doc.addUndoableEditListener(new MyUndoableEditListener());
        editorTextPane.addCaretListener(caretListenerLabel);

        //Setup System.out and System.err to the corresponding panels
        System.setOut(new PrintStream(new TextAreaOutputStream(remixOutput)));
        System.setErr(new PrintStream(new TextAreaOutputStream(systemOutput)));

        docArea = new JTextArea("Document goes here.");
        docArea.setForeground(Color.red);
        docPanel.add(docArea);
    }

    private RemixStyledDocument setUpStylesAndSpacing(RemixEditor editor, JTextPane textPane, Font baseFont, float tabSize, boolean darkTheme) {
        RemixStyledDocument document;
        setTextPaneTheme(textPane, darkTheme);
        // the base font
        textPane.setFont(baseFont); // previously "Monaco" on Mac
        document = new RemixStyledDocument(editor, textPane);
        textPane.setStyledDocument(document);
        edLexer = new RemixEdLexer(document, darkTheme);
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

    public String getProgramText() {
        return editorTextPane.getText();
    }

    public static GraphicsPanel getGraphicsPanel() {
        return graphicOutput;
    }

    public static void expandGraphicsPanel() {
        outputSplitPane.setDividerLocation(802); // kludge of 800
    }

    public static void expandSystemOutputPanel() {
        systemSplitPane.setDividerLocation(802);
    }

    public static void hideGraphicsPanel() {
        outputSplitPane.setDividerLocation(0);
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
                    doc.cancelCompletionHandling();
                    popupScreenLocation = null;
                    if (docPopup != null)
                        docPopup.hide();
                }
                lastLine = lineNumber;
                setText("line: " + lineNumber +
                        ", offset: " + (mark - startOfLine) +
                        ", style: " + edLexer.getStyleName(mark));
            });
        }
    }

    //This one listens for edits that can be undone.
    // Attribute changes don't count.
    protected class MyUndoableEditListener implements UndoableEditListener {
        public void undoableEditHappened(UndoableEditEvent e) {
            //  Check for an attribute change
            AbstractDocument.DefaultDocumentEvent event = (AbstractDocument.DefaultDocumentEvent)e.getEdit();
            if  (!event.getType().equals(DocumentEvent.EventType.CHANGE)) {
                undo.addEdit(e.getEdit());
                //Remember the edit and update the menus.
                undoAction.updateUndoState();
                redoAction.updateRedoState();
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
            //                hideGraphicsPanel(); // uncomment if you want the graphics panel hidden
            systemOutput.setText("");
            remixOutput.setText("");
            remixRunner = new RemixSwingWorker(
                    RemixEditor.this //,
            );
            stopAction.setEnabled(true);
            setEnabled(false); // changed back when running finishes or is terminated
            remixRunner.execute();
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
            System.out.println("cancel called");
            setEnabled(false);
            runAction.setEnabled(true);
        }
    }

    //Add a couple of emacs key bindings for navigation.
    protected void addEditBindings(JMenu editMenu) {
        InputMap inputMap = editorTextPane.getInputMap();

        //Command-z to undo last change
        KeyStroke key = KeyStroke.getKeyStroke(KeyEvent.VK_Z, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx());
        JMenuItem menuItem = editMenu.getItem(0); // undo
        inputMap.put(key, menuItem.getAction());

        //Command-shift-z to redo last undo
        key = KeyStroke.getKeyStroke(KeyEvent. VK_Z, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()  | InputEvent.SHIFT_DOWN_MASK);
        menuItem = editMenu.getItem(1); // redo
        inputMap.put(key, menuItem.getAction());
    }

    protected void addControlBindings(JMenu controlMenu) {
        InputMap inputMap = editorTextPane.getInputMap();

        //Command-r to run
        KeyStroke key = KeyStroke.getKeyStroke(KeyEvent.VK_R, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx());
        JMenuItem menuItem = controlMenu.getItem(0); // run
        inputMap.put(key, menuItem.getAction());
    }

    //Create the file menu.
    protected JMenu createFileMenu() {
        JMenu menu = new JMenu("File");
        OpenFileAction openAction = new OpenFileAction();
        menu.add(openAction);
        SaveFileAction saveAction = new SaveFileAction();
        menu.add(saveAction);
        PrintFileAction printAction = new PrintFileAction();
        menu.add(printAction);
        return menu;
    }

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
        Action cut = getActionByName(DefaultEditorKit.cutAction);
        cut.putValue(Action.NAME, "Cut");
        menu.add(cut);
        Action copy = getActionByName(DefaultEditorKit.copyAction);
        copy.putValue(Action.NAME, "Copy");
        menu.add(copy);
        Action paste = getActionByName(DefaultEditorKit.pasteAction);
        paste.putValue(Action.NAME, "Paste");
        menu.add(paste);

        menu.addSeparator();

        Action select = getActionByName(DefaultEditorKit.selectAllAction);
        select.putValue(Action.NAME, "Select All");
        menu.add(select);
        return menu;
    }

    private JMenu createViewMenu() {
        JMenu menu = new JMenu("View");
        darkThemeAction = new DarkThemeAction();
        menu.add(darkThemeAction);
        lightThemeAction = new LightThemeAction();
        menu.add(lightThemeAction);
        return menu;
    }

    protected JMenu createControlMenu() {
        JMenu menu = new JMenu("Control");
        runAction = new RunAction();
        menu.add(runAction);
        stopAction = new StopAction();
        menu.add(stopAction);
        return menu;
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

    class OpenFileAction extends AbstractAction {
        public OpenFileAction() {
            super("Open…");
        }

        @Override
        public void actionPerformed(ActionEvent event) {
            JFileChooser chooser = new JFileChooser(currentDirectory); // "Tests"); //
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "Remix programs", "rem");
            chooser.setFileFilter(filter);
            int returnVal = chooser.showOpenDialog(editorTextPane);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                try {
                    doc.remove(0, doc.getLength());
                } catch (BadLocationException e) {
                    throw new RuntimeException(e);
                }
                try {
                    currentDirectory = chooser.getCurrentDirectory().getAbsolutePath();
                    File remFile = chooser.getSelectedFile();
                    currentFileName = remFile.getName();
                    Scanner myReader = new Scanner(remFile);
                    while (myReader.hasNextLine()) {
                        String line = myReader.nextLine();
                        doc.insertLine(line + "\n");
                    }
                    edLexer.fullLex();
                    myReader.close();
                    editorTextPane.setCaretPosition(0);
                } catch (FileNotFoundException | BadLocationException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    class SaveFileAction extends AbstractAction {
        public SaveFileAction() {
            super("Save…");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser chooser = new JFileChooser(currentDirectory); // "Tests"); //
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "Remix programs", "rem");
            chooser.setFileFilter(filter);
            int returnVal = chooser.showSaveDialog(editorTextPane);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                try {
                    currentDirectory = chooser.getCurrentDirectory().getAbsolutePath();
                    String remFileName = chooser.getSelectedFile().getName();
                    Files.write(Path.of(currentDirectory, remFileName), editorTextPane.getText().getBytes());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }

    class PrintFileAction extends AbstractAction {
        public PrintFileAction() { super("Print…");}

        @Override
        public void actionPerformed(ActionEvent ev) {
            RemixStyledDocument document;
            RemixEdLexer printEdLexer;
            JTextPane printTextPane = new JTextPane();
            document = setUpStylesAndSpacing(null, printTextPane, defaultFontForPrinter, 15,false);
            printEdLexer = new RemixEdLexer(document, false);
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
                    currentFileName == null ? null : new MessageFormat(currentFileName),
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

    class DarkThemeAction extends AbstractAction {

        public DarkThemeAction() {
            super("Dark theme");
            setEnabled(false);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            RemixEditor.this.setTextPaneTheme(editorTextPane, true);
            edLexer = new RemixEdLexer(doc, true);
            setEnabled(false);
            lightThemeAction.setEnabled(true);
            try {
                edLexer.fullLex();
            } catch (BadLocationException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    class LightThemeAction extends AbstractAction {

        public LightThemeAction() {
            super("Light theme");
            setEnabled(true);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            RemixEditor.this.setTextPaneTheme(editorTextPane, false);
            edLexer = new RemixEdLexer(doc, false);
            setEnabled(false);
            darkThemeAction.setEnabled(true);
            try {
                edLexer.fullLex();
            } catch (BadLocationException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    class UndoAction extends AbstractAction {
        public UndoAction() {
            super("Undo");
            setEnabled(false);
        }

        public void actionPerformed(ActionEvent e) {
            try {
                undo.undo();
                edLexer.fullLex(); // THIS IS REALLY OVERKILL NEEDS TO CHANGE
            } catch (CannotUndoException | BadLocationException ex) {
                System.out.println("Unable to undo: " + ex);
                ex.printStackTrace();
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
                undo.redo();
                edLexer.fullLex(); // THIS IS REALLY OVERKILL NEEDS TO CHANGE
            } catch (CannotRedoException | BadLocationException ex) {
                System.out.println("Unable to redo: " + ex);
                ex.printStackTrace();
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

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event dispatch thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        final RemixEditor frame = new RemixEditor();
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent event) {
                super.windowOpened(event);
                systemSplitPane.setDividerLocation(1000); // kludge
                outputSplitPane.setDividerLocation(0);
            }
        });
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Display the window.
        frame.setBounds(100, 100, 1728, 1080); // for my Mac. Was 50, 50, 1700, 1050
        frame.setVisible(true);
    }

    //The standard main method.
    public static void main(String[] args) {
        // setup the Remix runtime
        try {
            LibrariesAndCompletions.prepareEnvironment();
            resetToEditorStandard();
            // after this the currentLibrary is the program library
        } catch (Exception e) {
            System.err.println("Error: initializing REPL");
            throw new RuntimeException(e);
        }
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        SwingUtilities.invokeLater(RemixEditor::createAndShowGUI);
    }
}

