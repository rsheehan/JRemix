package edu.fizz.remix.editor;

import javax.swing.undo.UndoManager;
import javax.swing.undo.UndoableEdit;

public class RevealUndoManager extends UndoManager {
    public UndoableEdit peekUndo() {
        return editToBeUndone();
    }

    public UndoableEdit peekRedo() {
        return editToBeRedone();
    }
}