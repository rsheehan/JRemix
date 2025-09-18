package edu.fizz.remix.editor;

import javax.swing.*;
import java.util.List;

public class RemixSwingWorker extends SwingWorker<Boolean, String> {

    private final RemixEditor editor;

    public RemixSwingWorker(RemixEditor editor) { //, String programText) {
        this.editor = editor;
    }

    protected RemixEditor getEditor() {
        return editor;
    }

    @Override
    protected Boolean doInBackground() {
        RemixREPL.runEditorText(this);
        return true; // can make it false on an error in the program
    }

    @Override
    protected void done() {
        // called when the doInBackground method finishes
        // careful : this is on the event dispatch thread
        Thread thread = new Thread(() -> {
            RemixEditor.waitForProgramFinish();
            editor.stopAction.setEnabled(false);
            editor.runAction.setEnabled(true);
            RemixEditor.setEditing(true);
        });
        thread.start();
    }

    public void publish(String output) {
        super.publish(output);
    }

    @Override
    protected void process(List<String> chunks) {
        for (String value : chunks) {
            RemixEditor.remixOutput.append(value);
        }
    }

}
