package edu.fizz.remixd.editor;

import javax.swing.*;
import java.awt.event.ActionEvent;

public abstract class RunAction extends AbstractAction {

    public RunAction(String name) {
        super(name);
    }

    public abstract void actionPerformed(ActionEvent e);
}
