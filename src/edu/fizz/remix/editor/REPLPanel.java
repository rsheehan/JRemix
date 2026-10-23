package edu.fizz.remix.editor;

import org.fife.ui.rtextarea.Gutter;
import org.fife.ui.rtextarea.RTextScrollPane;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AbstractDocument;
import java.awt.*;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class REPLPanel extends JPanel {

    private static JScrollPane scrollPane;
    private static RemixTextArea currentCodeArea;
    private static final Font outputFont = new Font("Courier New", Font.PLAIN, 15);
    /* Whatever this is at the moment is where all output and errors should be sent.
    At first this is just a dummy in case data gets sent here before it is non-null. */
    private static final JTextArea dummyTextArea = new JTextArea();
    public static JTextArea currentOutputArea = dummyTextArea;

    /* Keep all text from current code areas. */
    public static StringBuilder allText;

    public static RemixTextArea getCurrentCodeArea() {
        return currentCodeArea;
    }

    private final GridBagConstraints gbcForCode;
    private final GridBagConstraints gbcForOutput;
    private final GridBagConstraints gbcForSpace;
    private final Component glueBox;

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
        add(glueBox, gbcForSpace);
        scrollPane = new JScrollPane(this);
        addREPLTextArea(); // this moves glue and last to the end
        allText = new StringBuilder();
    }

    private void makeComponentSendScrollingUp(Component component) {
        component.addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                RTextScrollPane replTextAreaScrollPane = (RTextScrollPane) SwingUtilities.getAncestorOfClass(
                        RTextScrollPane.class,
                        component
                );
                scrollPane.dispatchEvent(SwingUtilities.convertMouseEvent(replTextAreaScrollPane, e, scrollPane));
            }
        });
    }

    protected void addREPLTextArea() {
        RemixTextArea REPLTextArea = new RemixTextArea(true, darkAreas);
        makeComponentSendScrollingUp(REPLTextArea);
        if (currentCodeArea != null) {
            // change the document listener
            AbstractDocument document =  (AbstractDocument)currentCodeArea.getDocument();
            document.setDocumentFilter(new REPLPanelDocumentFilter(document));
            currentCodeArea.setHighlightCurrentLine(false);
            allText.append(currentCodeArea.getText()).append("\n");
        }
        currentCodeArea = REPLTextArea;
        remove(glueBox);
        // createAutoSizingArea also adds scrollable panel for the textArea
        add(createAutoSizingArea(REPLTextArea), gbcForCode);
        add(glueBox, gbcForSpace);
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

    public static void scrollToTop() {
        JScrollBar verticalBar = scrollPane.getVerticalScrollBar();
        verticalBar.setValue(verticalBar.getMinimum());
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
    Not only adds a new output text area, a new code area is added beneath it
    by the completing RemixSwingWorker.
     */
    public void newOutputArea(String programName) {
        currentOutputArea = new JTextArea();
        currentOutputArea.setTabSize(2);
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
        add(currentOutputArea, gbcForOutput);
        add(glueBox, gbcForSpace);
        // Inform the layout manager and refresh the UI
        revalidate();
        repaint();
    }

    public void removeExistingTextAreas() {
        currentCodeArea = null;
        currentOutputArea = dummyTextArea;
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
