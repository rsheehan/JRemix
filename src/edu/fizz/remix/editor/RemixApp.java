package edu.fizz.remix.editor;

import edu.fizz.remix.runtime.LibrariesAndCompletions;
import org.fife.ui.rtextarea.SearchContext;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class RemixApp extends JFrame {

    private static final String REMIX = "Remix";

    public static REPLInputOutput remixOutput;
    public static String currentDirectory = "remixPrograms";

    private final List<RemixEditorWindow> listOfWindows = new ArrayList<>();

    LightThemeAction lightThemeAction;
    DarkThemeAction darkThemeAction;

    public RemixApp() {
        super(REMIX);

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = createFileMenu();
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);
        JMenu editMenu = createEditMenu();
        menuBar.add(editMenu);
        JMenu viewMenu = createViewMenu();
        menuBar.add(viewMenu);
        JMenu windowMenu = createWindowMenu();
        menuBar.add(windowMenu);

        remixOutput = new REPLInputOutput();
        remixOutput.append(REPLInputOutput.INFOSTRING);
        JScrollPane scrollPaneForOutput = new JScrollPane(remixOutput);
        add(scrollPaneForOutput,  BorderLayout.CENTER);
        setMinimumSize(new Dimension(600, 600));
        setPreferredSize(new Dimension(800, 1080));
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

    public void editorWindowClosed(RemixEditorWindow window) {
        window.dispose();
        listOfWindows.remove(window);
    }

    class NewFileAction extends AbstractAction {
        public NewFileAction() {
            super("New file in editor");
            putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.META_DOWN_MASK));
        }

        public void actionPerformed(ActionEvent event) {
            RemixEditorWindow editorWindow = new RemixEditorWindow(RemixApp.this, lightThemeAction.isEnabled());
            listOfWindows.add(editorWindow);
            editorWindow.newFileInWindow();
            Rectangle bounds = RemixApp.this.getBounds();
            editorWindow.setBounds(bounds.x + bounds.width, bounds.y , 800, 1080); // for my Mac. Was 50, 50, 1700, 1050
            editorWindow.setVisible(true);
        }
    }

    class OpenFileAction extends AbstractAction {
        public OpenFileAction() {
            super("Open file in editor");
            putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.META_DOWN_MASK));
        }

        public void actionPerformed(ActionEvent event) {
            JFileChooser chooser = new JFileChooser(currentDirectory); // "Tests"); //
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "Remix programs", "rem");
            chooser.setFileFilter(filter);
            int returnVal = chooser.showOpenDialog(RemixApp.this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File remFile;
                try {
                    currentDirectory = chooser.getCurrentDirectory().getAbsolutePath();
                    remFile = chooser.getSelectedFile();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                // create new RemixEditorWindow
                // and read in file
                RemixEditorWindow editorWindow = new RemixEditorWindow(RemixApp.this, lightThemeAction.isEnabled());
                Rectangle bounds = RemixApp.this.getBounds();
                boolean successful = editorWindow.openFileInWindow(remFile);
                if (successful) {
                    listOfWindows.add(editorWindow);
//                    editorWindow.setDarkTheme(lightThemeAction.isEnabled());
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

            for (RemixEditorWindow window : listOfWindows) {
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

            for (RemixEditorWindow window : listOfWindows) {
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
            for (RemixEditorWindow window : listOfWindows) {
                window.setDark(true);
            }
            remixOutput.setDarkMode(true);
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
            for(RemixEditorWindow window : listOfWindows){
                window.setDark(false);
            }
            remixOutput.setDarkMode(false);
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

    /*-----------------------------------*/

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event dispatch thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        new RemixApp();
        //Setup System.out and System.err to the corresponding panels
        System.setOut(new PrintStream(new TextAreaOutputStream(remixOutput)));
        System.setErr(new PrintStream(new TextAreaOutputStream(remixOutput)));
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
        SwingUtilities.invokeLater(RemixApp::createAndShowGUI);
    }
}
