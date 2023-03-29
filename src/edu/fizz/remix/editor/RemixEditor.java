package edu.fizz.remix.editor;
/*
 * TextComponentDemo.java requires one additional file:
 *   DocumentSizeFilter.java
 */

import edu.fizz.remix.runtime.Runtime;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.*;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RemixEditor extends JFrame {

    private final JTextPane editorTextPane;
//    protected final JTextArea documentPane;
    private final JSplitPane splitPane;
    private static RemixStyledDocument doc;
    protected final JTextArea remixOutput;
    protected final JTextArea docTextArea;
    private final HashMap<Object, Action> actions;

    private RemixSwingWorker remixRunner;
    protected RunAction runAction;
    protected StopAction stopAction;
    //undo helpers
    private UndoAction undoAction;
    private RedoAction redoAction;
    private final UndoManager undo = new UndoManager();

    public RemixEditor() {
        super("Remix");
        //Create the text pane and configure it.
        editorTextPane = new JTextPane();
        editorTextPane.setMargin(new Insets(5,10,5,10));
        // the base font
        editorTextPane.setFont(new Font("monospaced", Font.PLAIN, 13)); // previously "Monaco" on Mac
        doc = new RemixStyledDocument(editorTextPane);
        editorTextPane.setStyledDocument(doc);
        RemixEdLexer.initStyles(doc);

        // set up the tabs
        StyleContext sc = StyleContext.getDefaultStyleContext();
        float tabDiff = 40; // this matches 4 characters of new courier 16 font width
        List<TabStop> tabList = new ArrayList<>();
        for (int i = 1; i <= 40; i++) {
            tabList.add(new TabStop(tabDiff * i));
        }
        TabSet tabs = new TabSet(tabList.toArray(new TabStop[0]));
        AttributeSet paraSet = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.TabSet, tabs);
        editorTextPane.setParagraphAttributes(paraSet, false);

        JScrollPane scrollPane = new JScrollPane(editorTextPane);
        scrollPane.setPreferredSize(new Dimension(800, 800));
//        documentPane = new JTextArea();
//        documentPane.setEditable(false);
//        documentPane.setLineWrap(true);
//        documentPane.setWrapStyleWord(true);

        // A split pane for the code and documentation
//        JSplitPane codeDocSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scrollPane, documentPane);
//        codeDocSplitPane.setOneTouchExpandable(true);

        //Create the text area for the output and configure it.
        remixOutput = new JTextArea();
        remixOutput.setFont(new Font("Courier New", Font.PLAIN, 14));
        remixOutput.setEditable(false);
        remixOutput.setLineWrap( true );
        remixOutput.setWrapStyleWord( true );
        JScrollPane scrollPaneForOutput = new JScrollPane(remixOutput);

        //Create a split pane for the output and the text area.
        splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, scrollPane, scrollPaneForOutput);
        splitPane.setOneTouchExpandable(true);

        //Create the status area.
        JPanel statusPane = new JPanel();
        CaretListenerLabel caretListenerLabel = new CaretListenerLabel("text position: 0, style: default");
        statusPane.add(caretListenerLabel);

        //Create the text area for the documentation.
        docTextArea = new JTextArea("Shift-TAB shows function completions.");
        docTextArea.setPreferredSize(new Dimension(300, 800));
        docTextArea.setEditable(false);
        docTextArea.setLineWrap(true);
        docTextArea.setWrapStyleWord(true);

        //Add the components.
        getContentPane().add(splitPane, BorderLayout.CENTER);
        getContentPane().add(docTextArea, BorderLayout.EAST);
        getContentPane().add(statusPane, BorderLayout.PAGE_END);

        //Set up the menu bar.
        actions = createActionTable(editorTextPane);
        JMenu editMenu = createEditMenu();
        JMenuBar mb = new JMenuBar();
        mb.add(editMenu);
        setJMenuBar(mb);
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
//        doc.addDocumentListener(new MyDocumentListener());
    }

    private void addKeystrokeActions() {
        editorTextPane.getInputMap().put(KeyStroke.getKeyStroke("shift TAB"), "actionName");
        editorTextPane.getActionMap().put("actionName", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
            try {
                String docText = doc.completionHandling(editorTextPane.getCaretPosition());
                docTextArea.setText(docText);
                RemixEdLexer.fullLex(); // overkill, needs to change
            } catch (BadLocationException ex) {
                throw new RuntimeException(ex);
            }
            }
        });
    }

    //This listens for and reports caret movements.
    protected static class CaretListenerLabel extends JLabel implements CaretListener {
        public CaretListenerLabel(String label) {
            super(label);
        }

        //Might not be invoked from the event dispatch thread.
        public void caretUpdate(CaretEvent event) {
            int dot = event.getDot();
            displayPositionInfo(dot);
        }

        protected void displayPositionInfo(final int dot) {
            // if dot and mark are different then there is a selection
            SwingUtilities.invokeLater(() ->
                    setText("text position: " + dot + ", style: " + RemixEdLexer.getStyleName(dot)));
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
            try {
                remixOutput.setText("");
                remixRunner = new RemixSwingWorker(
                        RemixEditor.this,
                        doc.getText(0,doc.getLength())
                );
                stopAction.setEnabled(true);
                setEnabled(false); // changed back when running finishes or is terminated
                remixRunner.execute();
            } catch (BadLocationException ex) {
                throw new RuntimeException(ex);
            }
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

    class UndoAction extends AbstractAction {
        public UndoAction() {
            super("Undo");
            setEnabled(false);
        }

        public void actionPerformed(ActionEvent e) {
            try {
                undo.undo();
                RemixEdLexer.fullLex(); // THIS IS REALLY OVERKILL NEEDS TO CHANGE
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
                RemixEdLexer.fullLex(); // THIS IS REALLY OVERKILL NEEDS TO CHANGE
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
                frame.splitPane.setDividerLocation(0.8);
//                BuiltInFunctions.setOutputArea(frame.remixOutput);
            }
        });
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    //The standard main method.
    public static void main(String[] args) {
        // setup the Remix runtime
        try {
            Runtime.prepareEnvironment();
        } catch (Exception e) {
            System.err.println("Error: initializing REPL");
            throw new RuntimeException(e);
        }
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        SwingUtilities.invokeLater(RemixEditor::createAndShowGUI);
    }
}

