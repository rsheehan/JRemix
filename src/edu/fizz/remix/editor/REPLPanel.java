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

    private static JScrollPane scrollPane;

    public static RemixTextArea getCurrentCodeArea() {
        return currentCodeArea;
    }

    private static RemixTextArea currentCodeArea;
    private static final Font outputFont = new Font("Courier New", Font.PLAIN, 15);

    /* Whatever this is at the moment is where all output and errors should be sent. */
    public static JTextArea currentOutputArea;

    /* Keep all text from current code areas. */
    public static StringBuilder allText;

    private final GridBagConstraints gbcForCode;
    private final GridBagConstraints gbcForOutput;
    private final GridBagConstraints gbcForSpace;
    private final Component glueBox;
    private final Component lastLineBox;

    private boolean darkAreas;

    public REPLPanel(boolean dark)  {
        super(new GridBagLayout());
        darkAreas = dark;
        setBackgroundDark(dark);
        gbcForCode = new GridBagConstraints();
        gbcForCode.gridx = 0;
        gbcForCode.gridy = GridBagConstraints.RELATIVE;
        gbcForCode.fill = GridBagConstraints.HORIZONTAL; // Stretch horizontally
        gbcForCode.weightx = 1.0;                        // Take full container width
        gbcForCode.weighty = 0.0;                        // Crucial: Do NOT stretch vertically
        gbcForCode.insets = new Insets(1, 1, 1, 1); // Spacing between text areas
        gbcForOutput = (GridBagConstraints) gbcForCode.clone();
        gbcForOutput.fill = GridBagConstraints.NONE;
        gbcForSpace = (GridBagConstraints) gbcForCode.clone();
        gbcForSpace.weighty = 1.0;
        glueBox = Box.createGlue();
        lastLineBox = Box.createRigidArea(new Dimension(600, 20));
        add(lastLineBox, gbcForCode);
        add(glueBox, gbcForSpace);
        addREPLTextArea(); // this moves glue and last to the end
        scrollPane = new JScrollPane(this);
        allText = new StringBuilder();
    }

    protected void addREPLTextArea() {
        RemixTextArea REPLTextArea = new RemixTextArea(true, darkAreas);
        if (currentCodeArea != null) {
            // change the document listener
            AbstractDocument document =  (AbstractDocument)currentCodeArea.getDocument();
            document.setDocumentFilter(new REPLPanelDocumentFilter(document));
            currentCodeArea.setHighlightCurrentLine(false);
            allText.append(currentCodeArea.getText()).append("\n");
            currentCodeArea = REPLTextArea;
        } else {
            currentCodeArea = REPLTextArea;
        }
        remove(lastLineBox);
        remove(glueBox);
        add(createAutoSizingArea(REPLTextArea), gbcForCode);
        add(glueBox, gbcForSpace);
        add(lastLineBox, gbcForCode);
        revalidate();
        repaint();

        JFrame frame = (JFrame)SwingUtilities.getWindowAncestor(this);
        if (frame != null && RemixApp.noDrawingWindow()) {
            frame.toFront();
            frame.requestFocus();
        }
        scrollToBottom();
    }

    public static void scrollToBottom() {
        // this is gross but SwingUtilites.invokeLater wasn't as reliable.
        Timer timer = new Timer(100, _ -> {
            JScrollBar verticalBar = scrollPane.getVerticalScrollBar();
            verticalBar.setValue(verticalBar.getMaximum());
            currentCodeArea.requestFocusInWindow();
        });
        timer.setRepeats(false);
        timer.start();
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
                    gutter.setBackground(Color.black);
                    gutter.setLineNumberColor(Color.yellow);
                } else {
                    gutter.setBackground(Color.white);
                    gutter.setLineNumberColor(Color.blue);
                }
            } else if (component != glueBox && component != lastLineBox)
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
        currentOutputArea.setColumns(81);
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
        remove(lastLineBox);
        add(currentOutputArea, gbcForOutput);
        add(glueBox, gbcForSpace);
        add(lastLineBox);
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

        return createScrollPane(textArea);
    }

    private RTextScrollPane createScrollPane(RemixTextArea textArea) {
        RTextScrollPane scrollPane = new RTextScrollPane(textArea);
        Gutter gutter = scrollPane.getGutter();
        if (darkAreas) {
            gutter.setBackground(Color.black);
            gutter.setLineNumberColor(Color.yellow);
        } else {
            gutter.setBackground(Color.white);
            gutter.setLineNumberColor(Color.blue);
        }
        // Disable scrollbars inside the individual text area so it forces expansion
        scrollPane.setVerticalScrollBarPolicy(RTextScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollPane.setHorizontalScrollBarPolicy(RTextScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        return scrollPane;
    }

    private void invalidateAndResize() {
        revalidate();
        repaint();
    }

}
