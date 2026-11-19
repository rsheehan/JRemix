package edu.fizz.remixd.editor;

import javax.swing.*;

/*
A SwingWorker that both editor and repl swingworkers can be based on
*/
public class RemixdSwingWorker extends SwingWorker {

    private final String fileName;
    private final RemixdBaseWindow window;
    private final String programText;
    private Object result;

    /*
        This is the constructor for REPL execution.
        It is overridden in RemixEditorSwingWorker
         */
    public RemixdSwingWorker(String fileName, RemixdBaseWindow window, String programText) {
        this.fileName = fileName;
        RemixdPrepareRun.setFileName(fileName); // so the antlr error listener gets it
        this.window = window;
        this.programText = programText;
        RemixdApp.currentSwingWorker = this;
    }

    @Override
    protected java.lang.Boolean doInBackground() {
        SwingUtilities.invokeLater(() -> {
            RemixdApp.replPanel.requestFocus();
            RemixdApp.replPanel.clearOutputArea();
            RemixdApp.replPanel.appendTextOutput(null);
        });

        result = RemixdPrepareRun.runText(fileName, programText);
        return result != null;// can make it false on an error in the program
    }

    @Override
    protected void done() {
        // called when the doInBackground method finishes
        // careful : this is on the event dispatch thread
        Thread thread = new Thread(() -> {
            RemixdEditorWindow.waitForProgramFinish();
            if (result != null)
                printOutput(result.toString());
            window.stopAction.setEnabled(false);
            window.runAction.setEnabled(true);
            // and add a code area to the app window
            SwingUtilities.invokeLater(() -> {
                RemixdApp.replPanel.addREPLTextArea();
            });
        });
        thread.start();
    }

    public void printOutput(java.lang.String output) {
        SwingUtilities.invokeLater(() -> {
            RemixdApp.replPanel.appendTextOutput(output);
        });
    }

}
