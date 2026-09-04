package edu.fizz.remix.editor;

import javax.swing.*;
import java.util.List;

public class RemixSwingWorker extends SwingWorker<Boolean, String> {

    private final RemixEditorWindow editor;

    public RemixSwingWorker(RemixEditorWindow editor) { //, String programText) {
        this.editor = editor;
    }

    protected RemixEditorWindow getEditor() {
        return editor;
    }

    @Override
    protected Boolean doInBackground() {
//        BuiltInFunctionsLibrary.editorWindow = editor;
        RemixPrepareRun.runEditorText(this);
        return true; // can make it false on an error in the program
    }

    @Override
    protected void done() {
        // called when the doInBackground method finishes
        // careful : this is on the event dispatch thread
        Thread thread = new Thread(() -> {
            RemixEditorWindow.waitForProgramFinish();
            editor.stopAction.setEnabled(false);
            editor.runAction.setEnabled(true);
//            BuiltInFunctionsLibrary.editorWindow = null;
//            editor.setEditing(true);
        });
        thread.start();
    }

    public void publish(String output) {
        super.publish(output);
    }

    @Override
    protected void process(List<String> chunks) {
        for (String value : chunks) {
            RemixApp.remixOutput.append(value);
        }
    }

}
