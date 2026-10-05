package edu.fizz.remix.editor;

import javax.swing.*;
import java.awt.*;

public class REPLDynamicTextAreas extends JPanel {

    private final JScrollPane scrollPane;
//    private final JPanel contentPanel;

    public static RemixTextArea currentCodeArea;
    private static final Font outputFont = new Font("Courier New", Font.PLAIN, 15);
    public volatile static JTextArea currentOutputArea;

    private boolean darkAreas = true;

    public REPLDynamicTextAreas(boolean dark)  {
        darkAreas = dark;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
//        RemixTextArea REPLTextArea = new RemixTextArea(true, dark);
//        currentCodeArea = REPLTextArea;
//        REPLTextArea.configureKeyBindings(this);
//        add(REPLTextArea);
        currentOutputArea = new JTextArea();
        currentOutputArea.setEnabled(false);
        currentOutputArea.setFont(outputFont);
        add(currentOutputArea);
        scrollPane = new JScrollPane(this);
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    public void setDark(boolean dark) {
        darkAreas = dark;
        Component[] textAreas = getComponents();
        for (Component textArea : textAreas) {
            if (textArea instanceof RemixTextArea remixTextArea) {
                remixTextArea.setDark(dark);
                remixTextArea.modifyScheme(dark);
            } else {
                outputSetDark((JTextArea)textArea);
            }
        }
        // TODO set dark on all text areas
        // TODO and modifyScheme(true) on RemixTextAreas
    }

    private void outputSetDark(JTextArea output) {
        if (darkAreas) {
            output.setBackground(Color.black);
            output.setForeground(Color.white);
            output.setDisabledTextColor(Color.white);
//            output.setCaretColor(Color.yellow);
        } else {
            output.setBackground(Color.white);
            output.setForeground(Color.black);
            output.setDisabledTextColor(Color.black);
//            output.setCaretColor(Color.blue);
        }
    }

//    public void addTextArea(JTextArea newTextArea) {
//        // Add a slight border or maximum size if needed
//        newTextArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));
//        // Add the text area to the container panel
//        add(newTextArea);
//
//        // Inform the layout manager and refresh the UI
//        revalidate();
//        repaint();
//
//        // Auto-scroll to the newly added component
//        newTextArea.scrollRectToVisible(newTextArea.getBounds());
//    }

    /*
    Not only adds a new output text area, it also adds a new code area beneath it.
     */
    public JTextArea newOutput(String programName) {
        currentOutputArea = new JTextArea();
        currentOutputArea.setEnabled(false);
        currentOutputArea.setFont(outputFont);
        outputSetDark(currentOutputArea);
        add(currentOutputArea);
//        currentOutputArea = new JTextArea();
//        currentOutputArea.setEnabled(false);
////        currentOutputArea.setFont(outputFont);
//        TitledBorder border = BorderFactory.createTitledBorder(
//                BorderFactory.createLineBorder(Color.RED),
//                programName + " output"
//        );
//        border.setTitleColor(Color.RED);
//        currentOutputArea.setBorder(border);
//        // createLineBorder(Color.RED, 2));
//        outputSetDark(currentOutputArea);
//        add(currentOutputArea);
//        // also new code area
//        currentCodeArea = new RemixTextArea(true, darkAreas);
//        add(currentCodeArea);
//        currentCodeArea.configureKeyBindings(this);
//        // Inform the layout manager and refresh the UI
//        revalidate();
//        repaint();
//        // Optional: Auto-scroll to the newly added component
//        currentOutputArea.scrollRectToVisible(currentOutputArea.getBounds());
////        currentCodeArea.scrollRectToVisible(currentCodeArea.getBounds());
        return currentOutputArea;
    }

    public void removeExistingTextAreas() {
        Component[] allTextAreas = getComponents();
        for (Component component : allTextAreas) {
            remove(component);
        }
    }

}
