package edu.fizz.remix.editor;

import edu.fizz.remix.runtime.LibrariesAndCompletions;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class RemixApp extends JFrame {

    public static REPLInputOutput remixOutput;
    public static String currentDirectory = "remixPrograms";

    private final List<RemixEditorWindow> listOfWindows = new ArrayList<RemixEditorWindow>();

    LightThemeAction lightThemeAction;
    DarkThemeAction darkThemeAction;

    public RemixApp() {
        super("Remix");

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = createFileMenu();
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);
        JMenu editMenu = createEditMenu();
        menuBar.add(editMenu);
        JMenu viewMenu = createViewMenu();
        menuBar.add(viewMenu);

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
        listOfWindows.remove(window);
    }

    class NewFileAction extends AbstractAction {
        public NewFileAction() { super("New file in editor");}

        public void actionPerformed(ActionEvent event) {
            RemixEditorWindow editorWindow = new RemixEditorWindow(RemixApp.this, remixOutput, lightThemeAction.isEnabled());
            listOfWindows.add(editorWindow);
            editorWindow.newFileInWindow();
            Rectangle bounds = RemixApp.this.getBounds();
            editorWindow.setBounds(bounds.x + bounds.width, bounds.y , 800, 1080); // for my Mac. Was 50, 50, 1700, 1050
            editorWindow.setVisible(true);
        }
    }

    class OpenFileAction extends AbstractAction {
        public OpenFileAction() { super("Open file in editor");}

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
                RemixEditorWindow editorWindow = new RemixEditorWindow(RemixApp.this, remixOutput, lightThemeAction.isEnabled());
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
        FindAgainAction findAgain = new FindAgainAction();
        menu.add(findAgain);
        return menu;
    }

    class NewFindAcrossAction extends AbstractAction {
        public NewFindAcrossAction() { super("Find (across all windows)");}

        public void actionPerformed(ActionEvent event) {
        }
    }

    class FindAgainAction extends AbstractAction {
        public FindAgainAction() { super("Find again");}

        public void actionPerformed(ActionEvent event) {
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
                window.setDarkTheme(true);
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
                window.setDarkTheme(false);
            }
            remixOutput.setDarkMode(false);
            darkThemeAction.setEnabled(true);
            setEnabled(false);
        }
    }

    /***********************************/

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

    public static void main(String[] args) {
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
