package edu.fizz.remix.editor;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

public class RemixApp extends JFrame {

    public static String currentDirectory = "remixPrograms";

    public RemixApp() {
        super("Remix Control");

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = createFileMenu();
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);
        JMenu editMenu = createEditMenu();
        menuBar.add(editMenu);
        JMenu viewMenu = createViewMenu();
        menuBar.add(viewMenu);
    }

    /***********************************/

    protected JMenu createFileMenu() {
        JMenu menu = new JMenu("File ");
        NewFileAction newAction = new NewFileAction();
        menu.add(newAction);
        OpenFileAction openAction = new OpenFileAction();
        menu.add(openAction);
        return menu;
    }

    class NewFileAction extends AbstractAction {
        public NewFileAction() { super("New file in editor");}

        public void actionPerformed(ActionEvent event) {
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
                RemixEditorWindow editorWindow = new RemixEditorWindow();
                boolean successful = editorWindow.openFileInWindow(remFile);
                if (successful) {
                    editorWindow.setBounds(100, 100, 800, 1080); // for my Mac. Was 50, 50, 1700, 1050
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
        JMenu menu = new JMenu("View ");
        DarkThemeAction darkThemeAction = new DarkThemeAction();
        menu.add(darkThemeAction);
        LightThemeAction lightThemeAction = new LightThemeAction();
        menu.add(lightThemeAction);
        return menu;
    }

    class DarkThemeAction extends AbstractAction {

        public DarkThemeAction() {
            super("Dark theme");
//            setEnabled(false);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
        }
    }

    class LightThemeAction extends AbstractAction {

        public LightThemeAction() {
            super("Light theme");
//            setEnabled(true);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
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
        RemixApp frame = new RemixApp();
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent event) {
                super.windowOpened(event);
            }
        });
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(200, 50));
        //Display the window.
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);

//        RemixEditorWindow editorWindow = new RemixEditorWindow();
//        editorWindow.setBounds(100, 100, 800, 1080); // for my Mac. Was 50, 50, 1700, 1050
//        editorWindow.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        SwingUtilities.invokeLater(RemixApp::createAndShowGUI);
    }
}
