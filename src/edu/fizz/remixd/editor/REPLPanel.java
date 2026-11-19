package edu.fizz.remixd.editor;

import org.fife.ui.rtextarea.Gutter;
import org.fife.ui.rtextarea.RTextScrollPane;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AbstractDocument;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.MouseWheelListener;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class REPLPanel extends JPanel {

    private static JScrollPane scrollPane;
    private static final Font outputFont = new Font("Courier New", Font.PLAIN, 15);

    private static RemixdTextArea currentCodeArea;
    public static JTextArea currentOutputArea;
    public static JTextField currentInputArea;

    public static RemixdTextArea getCurrentCodeArea() {
        return currentCodeArea;
    }

    private final GridBagConstraints gbcForCode;
    private final GridBagConstraints gbcForOutput;
    private final GridBagConstraints gbcForSpace;
    private final Component glueBox;

    private final RemixdApp remixdApp;

    private boolean darkAreas;

    public REPLPanel(boolean dark, RemixdApp remixdApp)  {
        super(new GridBagLayout());
        this.remixdApp = remixdApp;
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
        RemixdTextArea initialArea = addREPLTextArea(); // this moves glue and last to the end
        SwingUtilities.invokeLater(() -> initialArea.setText("help REPL ; type Command/Control-return"));
    }

    public void appendTextOutput(String output) {
        if (currentOutputArea == null) {
            currentOutputArea = newOutputArea(remixdApp.getCurrentFileName());
        }
        currentOutputArea.append(output);
    }

    public void clearOutputArea() {
        if (currentOutputArea != null && currentOutputArea.getText().isEmpty())
            remove(currentOutputArea);
        currentOutputArea = null;
    }

    public String inputQuestion(String question) {
        String result;

        try {
            SwingUtilities.invokeAndWait(() -> {
                clearOutputArea();
                currentInputArea = new JTextField(81);
                currentInputArea.setFont(outputFont);
                currentInputArea.setBackground(darkAreas ? Color.black : Color.white);

                TitledBorder border = BorderFactory.createTitledBorder(
                        BorderFactory.createLineBorder(Color.RED),
                        question
                );

                currentInputArea.setBorder(border);
                remove(glueBox);
                add(currentInputArea, gbcForOutput);
                add(glueBox, gbcForSpace);
                // Inform the layout manager and refresh the UI
                revalidate();
                repaint();
                scrollToBottom(currentInputArea);
                currentInputArea.requestFocusInWindow();
            });
        } catch (InterruptedException | InvocationTargetException e) {
            return "Error in input.";
        }

        try {
            result = waitForTextFieldInput();
        } catch (ExecutionException | InterruptedException e) {
            return "Error in input.";
        }
        return result;
    }

    public static String waitForTextFieldInput() throws ExecutionException, InterruptedException {
        CompletableFuture<String> inputFuture = new CompletableFuture<>();
        // Triggered on the UI thread when the user presses Enter
        currentInputArea.addActionListener(_ -> {
            String input = currentInputArea.getText();
            currentInputArea.setEditable(false);
            inputFuture.complete(input);
        });
        return inputFuture.get();
    }

    public RemixdTextArea addREPLTextArea() {
        RemixdTextArea REPLTextArea = new RemixdTextArea(true, darkAreas);
        REPLTextArea.setUpToUseCmdReturn(remixdApp);
        // to make it scroll when the text would go off the window
        REPLTextArea.addCaretListener(_ -> SwingUtilities.invokeLater(() -> {
            if (REPLTextArea.shouldScroll()) {
                JScrollBar verticalBar = scrollPane.getVerticalScrollBar();
                verticalBar.setValue(verticalBar.getMaximum());
            }
        }));
        if (currentCodeArea != null) {
            currentCodeArea.setHighlightCurrentLine(false);
            // stop the local scroll pane from getting wheel spins
            RTextScrollPane replTextAreaScrollPane = (RTextScrollPane) SwingUtilities.getAncestorOfClass(
                    JScrollPane.class,
                    currentCodeArea
            );
            if (replTextAreaScrollPane != null) {
                for (MouseWheelListener wheelListener : replTextAreaScrollPane.getMouseWheelListeners())
                    replTextAreaScrollPane.removeMouseWheelListener(wheelListener);
            }

            // change the document listener
            AbstractDocument document =  (AbstractDocument)currentCodeArea.getDocument();
            document.setDocumentFilter(new REPLPanelDocumentFilter(document, this));
            currentCodeArea.setHighlightCurrentLine(false);
            AutoPairer.disableAutoPair(currentCodeArea);
        }
        currentCodeArea = REPLTextArea;
        remove(glueBox);
        // createAutoSizingArea also adds scrollable panel for the textArea
        add(createAutoSizingArea(currentCodeArea), gbcForCode);
        add(glueBox, gbcForSpace);
        revalidate();
        repaint();

        JFrame frame = (JFrame)SwingUtilities.getWindowAncestor(this);
        if (frame != null && RemixdApp.noDrawingWindow()) {
            frame.toFront();
            frame.requestFocus();
        }
        scrollToBottom(REPLTextArea);
        return REPLTextArea;
    }

    public static void scrollToBottom(JTextComponent requestingFocus) {
        // this is gross but SwingUtilites.invokeLater wasn't as reliable.
        Timer timer = new Timer(100, _ -> {
            JScrollBar verticalBar = scrollPane.getVerticalScrollBar();
            verticalBar.setValue(verticalBar.getMaximum());
            requestingFocus.requestFocusInWindow();
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
                RemixdTextArea remixdTextArea = (RemixdTextArea) textScrollPane.getTextArea();
                remixdTextArea.setDark(dark);
                remixdTextArea.modifyScheme(dark);
                Gutter gutter = textScrollPane.getGutter();
                if (dark) {
                    gutter.setBackground(Color.black);
                    gutter.setLineNumberColor(Color.yellow);
                } else {
                    gutter.setBackground(Color.white);
                    gutter.setLineNumberColor(Color.blue);
                }
            } else if (component != glueBox)
                outputSetDark((JTextComponent)component);
        }
    }

    private void outputSetDark(JTextComponent output) {
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
    by the completing RemixdSwingWorker.
     */
    public JTextArea newOutputArea(String programName) {
        JTextArea outputArea =  new JTextArea();
        outputArea.setTabSize(2);
        outputArea.setColumns(81);
        outputArea.setLineWrap(true);
        outputArea.setWrapStyleWord(true);
        outputArea.setEditable(false);
        outputArea.setFont(outputFont);

        TitledBorder border = BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.RED),
                programName + " output"
        );

        outputArea.setBorder(border);
        outputSetDark(outputArea);
        remove(glueBox);
        add(outputArea, gbcForOutput);
        add(glueBox, gbcForSpace);
        // Inform the layout manager and refresh the UI
        revalidate();
        repaint();
        return outputArea;
    }

    public void removeExistingTextAreas() {
        currentCodeArea = null;
        Component[] allTextAreas = getComponents();
        for (Component component : allTextAreas) {
            remove(component);
        }
    }

    private Component createAutoSizingArea(RemixdTextArea textArea) {
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

    private RTextScrollPane createScrollPane(RemixdTextArea textArea) {
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
