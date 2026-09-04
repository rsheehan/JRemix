package edu.fizz.remix.editor;

import javax.swing.undo.UndoManager;
import javax.swing.undo.UndoableEdit;

public class RevealUndoManager extends UndoManager {
    public UndoableEdit peekUndo() {
        UndoableEdit edit = editToBeUndone();
        return edit;
    }

    public UndoableEdit peekRedo() {
        UndoableEdit edit = editToBeRedone();
        return edit;
    }
}