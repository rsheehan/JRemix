package edu.fizz.remix.editor;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.text.AbstractDocument;
import java.awt.*;

public class REPLPanel extends JPanel {

    private final JScrollPane scrollPane;

    public static RemixTextArea getCurrentCodeArea() {
        return currentCodeArea;
    }

    private static RemixTextArea currentCodeArea;
    private static final Font outputFont = new Font("Courier New", Font.PLAIN, 15);

    /* Whatever this is at the moment is where all output and errors should be sent. */
    public static JTextArea currentOutputArea;

    /* Keep all text from current code areas. */
    public static StringBuilder allText;

    private boolean darkAreas;

    public REPLPanel(boolean dark)  {
        darkAreas = dark;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        addREPLTextArea();
        scrollPane = new JScrollPane(this);
        allText = new StringBuilder();
    }

    protected void addREPLTextArea() {
        RemixTextArea REPLTextArea = new RemixTextArea(true, darkAreas);
        if (currentCodeArea != null) {
            // change the document listener
            AbstractDocument document =  (AbstractDocument)currentCodeArea.getDocument();

            allText.append(currentCodeArea.getText()).append("\n");

            currentCodeArea = REPLTextArea;
            document.setDocumentFilter(new REPLPanelDocumentFilter(document));
        } else {
            currentCodeArea = REPLTextArea;
        }
        add(REPLTextArea);
        revalidate();
        repaint();
        // Auto-scroll to the newly added component
        JFrame frame = (JFrame)SwingUtilities.getWindowAncestor(this);
        if (frame != null) {
            frame.toFront();
            frame.requestFocus();
        }
        SwingUtilities.invokeLater(() -> {
            currentCodeArea.scrollRectToVisible(currentCodeArea.getBounds());
            currentCodeArea.requestFocusInWindow();
        });
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
    }

    private void outputSetDark(JTextArea output) {
        if (darkAreas) {
            output.setBackground(Color.black);
            output.setForeground(Color.white);
            output.setDisabledTextColor(Color.white);
            ((TitledBorder)output.getBorder()).setTitleColor(Color.white);
        } else {
            output.setBackground(Color.white);
            output.setForeground(Color.black);
            output.setDisabledTextColor(Color.black);
            ((TitledBorder)output.getBorder()).setTitleColor(Color.black);
        }
    }

    /*
    Not only adds a new output text area, it also adds a new code area beneath it.
     */
    public void newOutput(String programName) {
        currentOutputArea = new JTextArea();
        currentOutputArea.setEnabled(false);
        currentOutputArea.setFont(outputFont);

        TitledBorder border = BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.RED),
                programName + " output"
        );

        currentOutputArea.setBorder(border);
        outputSetDark(currentOutputArea);
        add(currentOutputArea);
        // Inform the layout manager and refresh the UI
        revalidate();
        repaint();
        // Auto-scroll to the newly added component
        currentOutputArea.scrollRectToVisible(currentOutputArea.getBounds());
    }

    public void removeExistingTextAreas() {
        currentCodeArea = null;
        currentOutputArea = null;
        Component[] allTextAreas = getComponents();
        for (Component component : allTextAreas) {
            remove(component);
        }
        allText = new StringBuilder();
    }

}
