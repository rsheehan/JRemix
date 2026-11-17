package edu.fizz.remixd.editor;

import javax.swing.*;
import java.util.List;

import static java.lang.Thread.sleep;

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
        RemixdApp.replPanel.requestFocus();
        RemixdApp.replPanel.clearOutputArea();
        RemixdApp.replPanel.appendTextOutput(null);

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
                publish(result.toString());
            window.stopAction.setEnabled(false);
            window.runAction.setEnabled(true);
            // and add a code area to the app window
            try {
                sleep(100); // this is gross but I couldn't get invokeLater
                // to do what I expected. The output are was created after the
                // new code area. It may be because of similar gross code
                // in REPLPanel.scrollToBottom
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            RemixdApp.replPanel.addREPLTextArea();
        });
        thread.start();
    }

    public void publish(java.lang.String output) {
        super.publish(output);
    }

    @Override
    protected void process(List chunks) {
        for (Object value : chunks) {
            RemixdApp.replPanel.appendTextOutput((String) value);
        }
    }

}
