package edu.fizz.remix.editor;

import edu.fizz.remix.runtime.Runtime;

import javax.swing.*;
import java.util.List;

public class RemixSwingWorker extends SwingWorker<Boolean, String> {

    private final String programText;
    private final RemixEditor editor;

    public RemixSwingWorker(RemixEditor editor, String programText) {
        this.editor = editor;
        this.programText = programText;
    }

    @Override
    protected Boolean doInBackground() {
        Runtime.resetToStandard();
        RemixREPL.runEditorText(programText, this);
        return true; // can make it false on an error in the program
    }

    @Override
    protected void done() {
        // called when the doInBackground method finishes
        editor.stopAction.setEnabled(false);
        editor.runAction.setEnabled(true);
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

    public JTextPane getTextPane() {
        return editor.getEditorTextPane();
    }
}
