package edu.fizz.remix.editor;

import org.fife.ui.rtextarea.Gutter;
import org.fife.ui.rtextarea.RTextScrollPane;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
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

    private final GridBagConstraints gbcForText;
    private final GridBagConstraints gbcForSpace;
    private final Component glueBox;

    private boolean darkAreas;

    public REPLPanel(boolean dark)  {
        super(new GridBagLayout());
        darkAreas = dark;
        setBackgroundDark(dark);
        gbcForText = new GridBagConstraints();
        gbcForText.gridx = 0;
        gbcForText.gridy = GridBagConstraints.RELATIVE;
        gbcForText.fill = GridBagConstraints.HORIZONTAL; // Stretch horizontally
        gbcForText.weightx = 1.0;                        // Take full container width
        gbcForText.weighty = 0.0;                        // Crucial: Do NOT stretch vertically
        gbcForText.insets = new Insets(1, 1, 1, 1);       // Spacing between text areas
        gbcForSpace = (GridBagConstraints) gbcForText.clone();
        gbcForSpace.weighty = 1.0;
        glueBox = Box.createGlue();
        add(glueBox, gbcForSpace);
        addREPLTextArea(); // this moves glue to the end
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
        remove(glueBox);
        add(createAutoSizingArea(REPLTextArea), gbcForText);
        add(glueBox, gbcForSpace);
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

    private void setBackgroundDark(boolean dark) {
        if (dark) {
            setBackground(Color.black);
        } else {
            setBackground(Color.white);
        }
    }

    public void setDark(boolean dark) {
        darkAreas = dark;
        setBackgroundDark(dark);
        Component[] allAreas = getComponents();
        for (Component component : allAreas) {
            if (component instanceof RTextScrollPane textScrollPane) {
                RemixTextArea remixTextArea = (RemixTextArea) textScrollPane.getTextArea();
                remixTextArea.setDark(dark);
                remixTextArea.modifyScheme(dark);
                Gutter gutter = textScrollPane.getGutter();
                if (dark) {
//                    editorTextArea.setSelectionColor(new Color(100, 100, 100));
                    gutter.setBackground(Color.black);
                    gutter.setLineNumberColor(Color.yellow);
                } else {
//                    editorTextArea.setSelectionColor(new Color(220, 220, 220));
                    gutter.setBackground(Color.white);
                    gutter.setLineNumberColor(Color.blue);
                }
            } else if (component != glueBox)
                outputSetDark((JTextArea)component);
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
    public void newOutputArea(String programName) {
        currentOutputArea = new JTextArea();
        currentOutputArea.setLineWrap(true);
        currentOutputArea.setWrapStyleWord(true);
        currentOutputArea.setEnabled(false);
        currentOutputArea.setFont(outputFont);

        TitledBorder border = BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.RED),
                programName + " output"
        );

        currentOutputArea.setBorder(border);
        outputSetDark(currentOutputArea);
        remove(glueBox);
        add(currentOutputArea, gbcForText);
        add(glueBox, gbcForSpace);
        // Inform the layout manager and refresh the UI
        revalidate();
        repaint();
        // Auto-scroll to the newly added component
//        currentOutputArea.scrollRectToVisible(currentOutputArea.getBounds());
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

    private Component createAutoSizingArea(RemixTextArea textArea) {
        // Initialize with 0 rows and columns so preferred size is derived entirely from text

        // Update size whenever text changes
        textArea.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) { invalidateAndResize(); }
            @Override
            public void removeUpdate(DocumentEvent e) { invalidateAndResize(); }
            @Override
            public void changedUpdate(DocumentEvent e) { invalidateAndResize(); }
        });
        // Disable scrollbars inside the individual text area so it forces expansion
        RTextScrollPane scrollPane = new RTextScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(RTextScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollPane.setHorizontalScrollBarPolicy(RTextScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        return scrollPane;
    }

    private void invalidateAndResize() {
        revalidate();
        repaint();
    }


}
