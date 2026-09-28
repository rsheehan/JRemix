package edu.fizz.remix.editor;

import edu.fizz.remix.libraries.Graphics;
import org.fife.ui.rsyntaxtextarea.Style;
import org.fife.ui.rsyntaxtextarea.SyntaxScheme;
import org.fife.ui.rtextarea.Gutter;
import org.fife.ui.rtextarea.RTextScrollPane;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.BadLocationException;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class RemixEditorWindow extends JFrame {

    private final REPLInputOutput remixOutput;
    private final RemixTextArea editorTextArea;
    private final RTextScrollPane lineNumberPane;
    private final HashMap<Object, Action> actions;
    private static String untitledName = "untitled";

    final PopupFactory popupFactory = new PopupFactory();
    Popup docPopup;
    final JPanel docPanel = new JPanel();
    JTextArea docArea;
    protected Point popupScreenLocation = null;

    public String getCurrentFileName() {
        return currentFileName;
    }

    private String currentFileName;
    private String currentAbsoluteFileName = null;
    private boolean editorContentSaved = true;

    private RemixSwingWorker remixRunner;

    private static final ArrayList<Graphics.AnimateFunction.AnimationBlock> animations = new ArrayList<>();
    private static final Lock animationLock = new ReentrantLock();
    private static final Condition animationFinished = animationLock.newCondition();

    public static void addAnimation(Graphics.AnimateFunction.AnimationBlock animation) {
        animations.add(animation);
    }

    public static boolean animationsStopped() {
        for (Graphics.AnimateFunction.AnimationBlock animationBlock : animations) {
            if (!animationBlock.isStopped())
                return false;
        }
        return true;
    }

    private static void stopAllAnimations() {
        for (Graphics.AnimateFunction.AnimationBlock animationBlock : animations) {
            animationBlock.stopAnimation();
        }
    }

    public static void indicateAnAnimationFinished() { // called from any animation in Graphics AnimationBlock
        animationLock.lock();
        try {
            animationFinished.signalAll();
        } finally {
            animationLock.unlock();
        }
    }

    public static void waitForProgramFinish() {
        animationLock.lock();
        try {
            while (!animationsStopped()) {
                animationFinished.await();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            animationLock.unlock();
        }
    }

    public RemixEditorWindow(RemixApp remixApp, boolean darkTheme) {
        super("untitled");
        this.remixOutput = RemixApp.remixOutput;
        editorTextArea = new RemixTextArea(60, 90);
        lineNumberPane = new RTextScrollPane(editorTextArea);

        JPanel remixPanel = new JPanel(new BorderLayout());
        remixPanel.add(lineNumberPane, BorderLayout.CENTER);

        editorTextArea.setSyntaxScheme(createRemixScheme());
        setDark(darkTheme);

        //Set up the menu bar.
        actions = createActionTable(editorTextArea);
        JMenuBar mb = new JMenuBar();
        JMenu fileMenu = createFileMenu();
        mb.add(fileMenu);
        JMenu editMenu = createEditMenu();
        mb.add(editMenu);
        JMenu controlMenu = createControlMenu();
        mb.add(controlMenu);
        setJMenuBar(mb);

        setContentPane(remixPanel);
        pack();

        docArea = new JTextArea("Document goes here.");
        docArea.setForeground(Color.red);
        docPanel.add(docArea);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
//            @Override
//            public void windowClosing(WindowEvent e) {
//                RemixEditorWindow.this.dispose();
//            }

            @Override
            public void windowClosed(WindowEvent e) {
                // Called after dispose() finishes
                remixApp.editorWindowClosed(RemixEditorWindow.this);
            }
        });
    }

    public void setDark(boolean dark) {
        editorTextArea.setDark(dark);
        Gutter gutter = lineNumberPane.getGutter();
        if (dark) {
            editorTextArea.setSelectionColor(new Color(100, 100, 100));
            gutter.setBackground(Color.black);
            gutter.setLineNumberColor(Color.yellow);
        } else {
            editorTextArea.setSelectionColor(new Color(220, 220, 220));
            gutter.setBackground(Color.white);
            gutter.setLineNumberColor(Color.blue);
        }
        modifyScheme(dark);
    }

    private SyntaxScheme createRemixScheme() {
        SyntaxScheme scheme = new SyntaxScheme(true);
        Font defaultFont = editorTextArea.getFont();

        Font smallerFont = defaultFont.deriveFont(defaultFont.getSize() - 1f);
        Font italicFont = smallerFont.deriveFont(Font.ITALIC);

        Style commentStyle = new Style(new Color(170, 121, 66), null, italicFont);
        Style keywordStyle = new Style(Color.red);

        scheme.setStyle(RemixTokenTypes.RESERVED_WORD, keywordStyle);
        scheme.setStyle(RemixTokenTypes.COMMENT_EOL, commentStyle);
        scheme.setStyle(RemixTokenTypes.COMMENT_MULTILINE, commentStyle);
        return scheme;
    }

    private void modifyScheme(boolean dark) {
        Font defaultFont = editorTextArea.getFont();
        Font boldFont = defaultFont.deriveFont(Font.BOLD);
        Style darkLiteralStyle = new Style(Color.cyan);
        Style lightLiteralStyle = new Style(new Color(205, 127, 50));

        SyntaxScheme scheme = editorTextArea.getSyntaxScheme();
        scheme.setStyle(RemixTokenTypes.CONSTANT,
                        new Style(new Color(100, 200, 255), null, boldFont));
        if (dark) {
            scheme.setStyle(RemixTokenTypes.FUNCTION,
                            new Style(Color.white));
            scheme.setStyle(RemixTokenTypes.VARIABLE,
                            new Style(new Color(255, 255, 200)));
            scheme.setStyle(RemixTokenTypes.LITERAL_STRING_DOUBLE_QUOTE,
                            new Style(new Color(255, 200, 200)));
            scheme.setStyle(RemixTokenTypes.LITERAL_NUMBER_DECIMAL_INT,
                            darkLiteralStyle);
            scheme.setStyle(RemixTokenTypes.LITERAL_BOOLEAN,
                            darkLiteralStyle);
            scheme.setStyle(RemixTokenTypes.OPERATOR,
                            new Style(Color.green));

        } else {
            scheme.setStyle(RemixTokenTypes.FUNCTION,
                            new Style(Color.black));
            scheme.setStyle(RemixTokenTypes.VARIABLE,
                            new Style(new Color(0, 100, 150)));
            scheme.setStyle(RemixTokenTypes.LITERAL_STRING_DOUBLE_QUOTE,
                            new Style(new Color(200, 10, 200)));
            scheme.setStyle(RemixTokenTypes.LITERAL_NUMBER_DECIMAL_INT,
                            lightLiteralStyle);
            scheme.setStyle(RemixTokenTypes.LITERAL_BOOLEAN,
                            lightLiteralStyle);
            scheme.setStyle(RemixTokenTypes.OPERATOR,
                            new Style(new Color(205, 127, 50)));
        }
        editorTextArea.repaint();
    }

    private Point getPopupScreenLocation() {
        Point editorLocation = editorTextArea.getLocationOnScreen();
        Point caretPoint = editorTextArea.getCaretScreenLocation();
        return new Point(caretPoint.x + editorLocation.x, caretPoint.y + editorLocation.y + 16);
    }

    //The following two methods allow us to find an
    //action provided by the editor kit by its name.
    private HashMap<Object, Action> createActionTable(JTextComponent textComponent) {
        HashMap<Object, Action> actions = new HashMap<>();
        Action[] actionsArray = textComponent.getActions();
        for (Action action : actionsArray) {
            actions.put(action.getValue(Action.NAME), action);
        }
        return actions;
    }

//    private Action getActionByName(String name) {
//        return actions.get(name);
//    }

    /***** File menu and items *****/

    //Create the file menu.
    protected JMenu createFileMenu() {
        JMenu menu = new JMenu("File");
        SaveFileAction saveAction = new SaveFileAction();
        menu.add(saveAction);
        SaveAsFileAction saveAsAction = new SaveAsFileAction();
        menu.add(saveAsAction);
//        PrintFileAction printAction = new PrintFileAction();
//        menu.add(printAction);
        return menu;
    }

    public void showDocText() {
        RemixCompletions completionControl = editorTextArea.completionControl;
        try {
            if (completionControl.noCompletions())
                popupScreenLocation = getPopupScreenLocation();
            int caretPos = editorTextArea.getCaretPosition();
            int lineNumber = editorTextArea.getCaretLineNumber();

            String docText = completionControl.completionHandling(caretPos, lineNumber);
            // the completionHandling will move the caret hence clearing popupScreenLocation
            if (docPopup != null)
                docPopup.hide();
            if (docText != null && !docText.isEmpty()) {
                docArea.setText(docText);
                docPopup = popupFactory.getPopup(editorTextArea,
                                                 docPanel,
                                                 popupScreenLocation.x,
                                                 popupScreenLocation.y);
                docPopup.show();
            }
        } catch (BadLocationException ex) {
            System.out.println("Should not happen Window showDocText");
        }
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

    private void saveFile() {
        if (currentAbsoluteFileName == null)
            saveAsFile();
        else {
            try {
                Files.write(Path.of(currentAbsoluteFileName), editorTextArea.getText().getBytes());
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
        int returnVal = chooser.showSaveDialog(editorTextArea);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            RemixApp.currentDirectory = chooser.getCurrentDirectory().getAbsolutePath();
            File file = chooser.getSelectedFile();
            currentFileName = file.getName();
            setTitle(currentFileName);
            currentAbsoluteFileName = file.getAbsolutePath();
            saveFile();
        }
    }

    /***** Edit menu and items *****/

    //Create the edit menu.
    protected JMenu createEditMenu() {
        JMenu editMenu = new JMenu("Edit");
        editMenu.add(createMenuItem(RemixTextArea.getAction(RemixTextArea.UNDO_ACTION)));
        editMenu.add(createMenuItem(RemixTextArea.getAction(RemixTextArea.REDO_ACTION)));
        editMenu.addSeparator();
        editMenu.add(createMenuItem(RemixTextArea.getAction(RemixTextArea.CUT_ACTION)));
        editMenu.add(createMenuItem(RemixTextArea.getAction(RemixTextArea.COPY_ACTION)));
        editMenu.add(createMenuItem(RemixTextArea.getAction(RemixTextArea.PASTE_ACTION)));
        editMenu.add(createMenuItem(RemixTextArea.getAction(RemixTextArea.DELETE_ACTION)));
        editMenu.addSeparator();
        editMenu.add(createMenuItem(RemixTextArea.getAction(RemixTextArea.SELECT_ALL_ACTION)));
//        menu.addSeparator();
//
//        Action find = new FindAction("Find");
//        menu.add(find);
//        Action findForwards = new FindForwardAction("Find forwards");
//        menu.add(findForwards);
//
//        Action backFind = new FindBackwardAction("Find backwards");
//        menu.add(backFind);
//
//        menu.addSeparator();
//
//        Action indent = new IndentSelection("Indent selection");
//        menu.add(indent);
//        Action dedent = new DedentSelection("Dedent selection");
//        menu.add(dedent);

//        menu.addSeparator();
        return editMenu;
    }

    private static JMenuItem createMenuItem(Action action) {
        JMenuItem item = new JMenuItem(action);
        item.setToolTipText(null); // Swing annoyingly adds tool tip text to the menu item
        return item;
    }

    protected RunAction runAction;
    protected StopAction stopAction;
    
    /***** Control menu and items *****/
    protected JMenu createControlMenu() {
        JMenu menu = new JMenu("Control");
        runAction = new RunAction();
        menu.add(runAction);
        stopAction = new StopAction();
        menu.add(stopAction);
        return menu;
    }

    protected class RunAction extends AbstractAction {

        protected RunAction() {
            super("Run");
            setEnabled(true);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            remixOutput.setFocusable(true); // Make it focusable again for future use
            remixRunner = new RemixSwingWorker(
                    RemixEditorWindow.this
            );
            stopAction.setEnabled(true);
            setEnabled(false); // changed back when running finishes or is terminated
            animations.clear();
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
            stopAllAnimations();
            System.out.println("Program cancelled.");
            setEnabled(false);
            runAction.setEnabled(true);
        }
    }

    public void newFileInWindow() {
        untitledName = "*" + untitledName + "*";
        currentFileName = untitledName;
        setTitle(currentFileName);
        editorTextArea.setCaretPosition(0);
        editorContentSaved = false;
    }

    public boolean openFileInWindow(File remFile) {
        currentFileName = remFile.getName();
        currentAbsoluteFileName = remFile.getAbsolutePath();
        setTitle(currentFileName);
        try {
            Scanner myReader = new Scanner(remFile);
            int position = 0;
            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();
                editorTextArea. insert(line + "\n", position);
                position += line.length() + 1;
            }
            myReader.close();
            editorTextArea.setCaretPosition(0);
            editorContentSaved = true;
        } catch (FileNotFoundException e) {
            return false;
        }
        return true;
    }

    public String getProgramText() {
        return editorTextArea.getText();
    }

    static void main() {
        // Just to test. This is not really main.
        // Start all Swing applications on the EDT.
        SwingUtilities.invokeLater(() -> new RemixEditorWindow(null, true).setVisible(true));
    }
}
