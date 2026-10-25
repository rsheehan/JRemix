package edu.fizz.remixd.editor;

import com.formdev.flatlaf.FlatDarculaLaf;
import edu.fizz.remixd.runtime.LibrariesAndCompletions;
import org.fife.ui.rtextarea.SearchContext;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class RemixdApp extends RemixdBaseWindow {

    private static final String REMIX = "Remix'd";
    public static REPLPanel replPanel;
    public static String currentDirectory = "remixdPrograms";
    private final List<RemixdEditorWindow> listOfWindows = new ArrayList<>();
    public static RemixdSwingWorker currentSwingWorker;

    LightThemeAction lightThemeAction;
    DarkThemeAction darkThemeAction;
    TopAction topAction;
    BottomAction bottomAction;

    private static final int WIDTH = 800;

    public RemixdApp() {
        super(REMIX);

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = createFileMenu();
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);
        JMenu editMenu = createEditMenu();
        menuBar.add(editMenu);
        JMenu controlMenu = createControlMenu();
        menuBar.add(controlMenu);
        JMenu viewMenu = createViewMenu();
        menuBar.add(viewMenu);
        JMenu windowMenu = createWindowMenu();
        menuBar.add(windowMenu);

        replPanel = new REPLPanel(lightThemeAction.isEnabled());
        JScrollPane scrollPane = replPanel.getScrollPane();
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        add(scrollPane, BorderLayout.CENTER);
        setMinimumSize(new Dimension(WIDTH, 600));
        setPreferredSize(new Dimension(WIDTH, 1080));
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Listen for resize events and lock the height
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                Dimension size = getSize();
                if (size.width != WIDTH) {
                    setSize(WIDTH, size.height);
                }
            }
        });
        setVisible(true);
    }

    /***********************************/

    protected JMenu createFileMenu() {
        JMenu menu = new JMenu("File");
        NewFileAction newAction = new NewFileAction();
        menu.add(newAction);
        OpenFileAction openAction = new OpenFileAction();
        menu.add(openAction);
        return menu;
    }

    public void editorWindowClosed(RemixdEditorWindow window) {
        window.dispose();
        listOfWindows.remove(window);
    }

    public RemixdTextArea getTextArea() {
        return REPLPanel.getCurrentCodeArea();
    }

    @Override
    public String getCurrentFileName() {
        return RemixdPrepareRun.INTERACTIVETEXT;
    }

    class NewFileAction extends AbstractAction {
        public NewFileAction() {
            super("New file in editor");
            int shortcutKey = Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx();
            putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N, shortcutKey));
        }

        public void actionPerformed(ActionEvent event) {
            RemixdEditorWindow editorWindow = new RemixdEditorWindow(RemixdApp.this, lightThemeAction.isEnabled());
            listOfWindows.add(editorWindow);
            editorWindow.newFileInWindow();
            Rectangle bounds = RemixdApp.this.getBounds();
            editorWindow.setBounds(bounds.x + bounds.width, bounds.y , 800, 1080); // for my Mac. Was 50, 50, 1700, 1050
            editorWindow.setVisible(true);
        }
    }

    class OpenFileAction extends AbstractAction {
        public OpenFileAction() {
            super("Open file in editor");
            int shortcutKey = Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx();
            putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_O, shortcutKey));
        }

        public void actionPerformed(ActionEvent event) {
            JFileChooser chooser = new JFileChooser(currentDirectory); // "Tests"); //
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "Remixd programs", "rem");
            chooser.setFileFilter(filter);
            int returnVal = chooser.showOpenDialog(RemixdApp.this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File remFile;
                try {
                    currentDirectory = chooser.getCurrentDirectory().getAbsolutePath();
                    remFile = chooser.getSelectedFile();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                // create new RemixdEditorWindow
                // and read in file
                RemixdEditorWindow editorWindow = new RemixdEditorWindow(RemixdApp.this, lightThemeAction.isEnabled());
                Rectangle bounds = RemixdApp.this.getBounds();
                boolean successful = editorWindow.openFileInWindow(remFile);
                if (successful) {
                    listOfWindows.add(editorWindow);
                    editorWindow.setBounds(bounds.width, bounds.y, 800, 1080); // for my Mac. Was 50, 50, 1700, 1050
                    editorWindow.setVisible(true);
                } else {
                    editorWindow.dispose();
                }

            }
        }
    }

    /***********************************/

    protected JMenu createEditMenu() {
        JMenu menu = new JMenu("Edit ");
        NewFindAcrossAction newFindAcross = new NewFindAcrossAction();
        menu.add(newFindAcross);
        ClearFindAcrossAction clearFindAcrossAction = new ClearFindAcrossAction();
        menu.add(clearFindAcrossAction);
        return menu;
    }

    class NewFindAcrossAction extends AbstractAction {
        public NewFindAcrossAction() { super("Find (across all windows)");}

        public void actionPerformed(ActionEvent event) {
            String findWord = JOptionPane.showInputDialog(null, "Enter search term for all windows:");
            SearchContext context = new SearchContext();
            context.setSearchFor(findWord); // The word you want to highlight

            for (RemixdEditorWindow window : listOfWindows) {
                if (window.highlightSearch(context))
                    window.toFront();
            }
        }
    }

    class ClearFindAcrossAction extends AbstractAction {
        public ClearFindAcrossAction() { super("Clear find (across all windows)");}

        public void actionPerformed(ActionEvent event) {
            SearchContext context = new SearchContext();
            context.setSearchFor(""); // unhighlight

            for (RemixdEditorWindow window : listOfWindows) {
                window.highlightSearch(context);
            }
        }
    }

    /***********************************/

    private JMenu createViewMenu() {
        JMenu menu = new JMenu("View");
        darkThemeAction = new DarkThemeAction();
        menu.add(darkThemeAction);
        lightThemeAction = new LightThemeAction();
        menu.add(lightThemeAction);
        return menu;
    }

    class DarkThemeAction extends AbstractAction {

        public DarkThemeAction() {
            super("Dark theme");
            setEnabled(false);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            for (RemixdEditorWindow window : listOfWindows) {
                window.setDark(true);
            }
            replPanel.setDark(true);
            lightThemeAction.setEnabled(true);
            setEnabled(false);
        }
    }

    class LightThemeAction extends AbstractAction {

        public LightThemeAction() {
            super("Light theme");
            setEnabled(true);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            for(RemixdEditorWindow window : listOfWindows){
                window.setDark(false);
            }
            replPanel.setDark(false);
            darkThemeAction.setEnabled(true);
            setEnabled(false);
        }
    }


    /***********************************/

    private JMenu createWindowMenu() {
        JMenu menu = new JMenu("Windows");
        // Populate the menu dynamically when it is clicked
        menu.addMenuListener(new javax.swing.event.MenuListener() {
            @Override
            public void menuSelected(javax.swing.event.MenuEvent e) {
                populateWindowMenu(menu);
            }

            @Override
            public void menuDeselected(javax.swing.event.MenuEvent e) {}

            @Override
            public void menuCanceled(javax.swing.event.MenuEvent e) {}
        });

        return menu;
    }

    /**
     * Returns true if one of the windows is called "Drawing".
     */
    public static boolean noDrawingWindow() {
        Frame[] frames = Frame.getFrames();
        for (Frame frame : frames) {
            if (frame.getTitle().equals("Drawing"))
                return false;
        }
        return true;
    }

    /**
     Helper method to clear and rebuild the window list dynamically
     */
    private static void populateWindowMenu(JMenu windowMenu) {
        windowMenu.removeAll(); // Clear old items

        // Get all frames managed by the application
        Frame[] frames = Frame.getFrames();

        for (Frame frame : frames) {
            // Create a menu item using the window's title
            String title = frame.getTitle();
            if (frame.isDisplayable() && !title.equals(REMIX)) {

                JMenuItem menuItem = new JMenuItem(title);

                // Add action listener to bring the frame to focus
                menuItem.addActionListener(_ -> {
                    // Bring window to front and restore it if minimized
                    if (frame.getState() == Frame.ICONIFIED) {
                        frame.setState(Frame.NORMAL);
                    }
                    frame.toFront();
                    frame.requestFocus();
                });

                windowMenu.add(menuItem);
            }
        }
    }

    /***** Control menu and items *****/
    protected JMenu createControlMenu() {
        JMenu menu = new JMenu("Control");
        runAction = new RunAction();
        menu.add(runAction);
        stopAction = new StopAction();
        menu.add(stopAction);
        menu.addSeparator();
        topAction = new TopAction();
        menu.add(topAction);
        bottomAction = new BottomAction();
        menu.add(bottomAction);
        menu.addSeparator();
        ResetAction resetAction = new ResetAction();
        menu.add(resetAction);
        ClearComponentsAction clearComponentsAction = new ClearComponentsAction();
        menu.add(clearComponentsAction);
        return menu;
    }

    protected class RunAction extends edu.fizz.remixd.editor.RunAction {

        protected RunAction() {
            super("Run");
            int shortcutKey = Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx();
            putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_R, shortcutKey));
            setEnabled(true);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            run();
        }
    }

    public void run() {
        replPanel.newOutputArea(getCurrentFileName());

        String programText = REPLPanel.getCurrentCodeArea().getText();

        remixRunner = new RemixdSwingWorker(RemixdPrepareRun.INTERACTIVETEXT, RemixdApp.this, programText);
        System.setErr(new PrintStream(new TextAreaOutputStream(remixRunner)));

        stopAction.setEnabled(true);
        runAction.setEnabled(false); // changed back when running finishes or is terminated
        animations.clear();
        remixRunner.execute(); // this causes the program to run in a background thread
    }

    protected static class TopAction extends AbstractAction {
        protected TopAction() {
            super("Top of REPL");
            int shortcutKey = Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx();
            putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_T, shortcutKey));
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            REPLPanel.scrollToTop();
        }
    }

    protected static class BottomAction extends AbstractAction {
        protected BottomAction() {
            super("Bottom of REPL");
            int shortcutKey = Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx();
            putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_B, shortcutKey));
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            REPLPanel.scrollToBottom();
        }
    }

    protected static class ClearComponentsAction extends AbstractAction {

        protected ClearComponentsAction() {
            super("Clear window");
            int shortcutKey = Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx();
            putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_C, shortcutKey | InputEvent.SHIFT_DOWN_MASK));
            setEnabled(true);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            replPanel.removeExistingTextAreas();
            replPanel.addREPLTextArea();
        }
    }

    protected static class ResetAction extends AbstractAction {

        protected ResetAction() {
            super("Reset REPL");
            setEnabled(true);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            replPanel.removeExistingTextAreas();
            replPanel.addREPLTextArea();
            // also clear the repl context
            LibrariesAndCompletions.resetREPLEnvironment();
        }
    }

    /*-----------------------------------*/

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event dispatch thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        new RemixdApp();
        // TODO make a method to change the output to a particular text area
    }

    static void main() {
        try {
            LibrariesAndCompletions.prepareEnvironment();
            LibrariesAndCompletions.resetToEditorStandard();
            // after this the currentLibrary is the program library
        } catch (Exception e) {
            System.err.println("Error: initializing REPL");
            throw new RuntimeException(e);
        }
        // need this to turn the frame dark
        System.setProperty("apple.awt.application.appearance", "NSAppearanceNameDarkAqua");
        System.setProperty("flatlaf.useNativeLibrary", "false");
        System.setProperty("flatlaf.FlatDarkulaLaf", "false"); // FlatDarkulaLaf, FlatDarkLaf, FlatMacDarkLaf
        FlatDarculaLaf.setup();
        UIManager.put("ScrollBar.width", 16);

        SwingUtilities.invokeLater(RemixdApp::createAndShowGUI);
    }
}
