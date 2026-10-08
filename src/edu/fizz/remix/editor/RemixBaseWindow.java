package edu.fizz.remix.editor;

import edu.fizz.remix.libraries.Graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
Both the RemixApp and RemixEditorWindows are based on this.
 */
public abstract class RemixBaseWindow extends JFrame {

    final PopupFactory popupFactory = new PopupFactory();
    Popup docPopup;
    final JPanel docPanel = new JPanel();
    JTextArea docArea;
    protected Point popupScreenLocation = null;

    protected RemixSwingWorker remixRunner;

    protected static final ArrayList<Graphics.AnimateFunction.AnimationBlock> animations = new ArrayList<>();
    private static final Lock animationLock = new ReentrantLock();
    private static final Condition animationFinished = animationLock.newCondition();

    public static void addAnimation(Graphics.AnimateFunction.AnimationBlock animation) {
        animations.add(animation);
    }

    public RemixBaseWindow(String title) {
        super(title);
//        dynamicTextAreas = RemixApp.REPLTextAreas;

        docArea = new JTextArea("Document goes here.");
        docArea.setForeground(Color.red);
        docPanel.add(docArea);
    }

    protected abstract Point getPopupScreenLocation();
    public abstract void showDocText();

    public abstract String getCurrentFileName();

    protected RunAction runAction;
    protected StopAction stopAction;

    protected class StopAction extends AbstractAction {

        protected StopAction() {
            super("Stop");
            putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.META_DOWN_MASK));
            setEnabled(false);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            remixRunner.cancel(true);
            stopAllAnimations();
            System.out.println("Program cancelled.");
            setEnabled(false);
            runAction.setEnabled(true);
        }
    }

    private static void stopAllAnimations() {
        for (Graphics.AnimateFunction.AnimationBlock animationBlock : animations) {
            animationBlock.stopAnimation();
        }
    }

    public static boolean animationsStopped() {
        for (Graphics.AnimateFunction.AnimationBlock animationBlock : animations) {
            if (!animationBlock.isStopped())
                return false;
        }
        return true;
    }

    public static void indicateAnAnimationFinished() { // called from any animation in Graphics AnimationBlock
        animationLock.lock();
        try {
            animationFinished.signalAll();
        } finally {
            animationLock.unlock();
        }
    }

    public static void waitForProgramFinish() {
        animationLock.lock();
        try {
            while (!animationsStopped()) {
                animationFinished.await();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            animationLock.unlock();
        }
    }
}
