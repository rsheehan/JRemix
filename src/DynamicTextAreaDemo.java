import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class DynamicTextAreaDemo extends JFrame {

    public DynamicTextAreaDemo() {
        setTitle("Shrink-to-Fit RSyntaxTextAreas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);

        // Container panel using GridBagLayout to align components to the top
        JPanel container = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.fill = GridBagConstraints.HORIZONTAL; // Stretch horizontally
        gbc.weightx = 1.0;                        // Take full container width
        gbc.weighty = 0.0;                        // Crucial: Do NOT stretch vertically
        gbc.insets = new Insets(5, 5, 5, 5);       // Spacing between text areas

        // Add a few text areas with varying content lengths
        container.add(createAutoSizingArea("System.out.println(\"Hello World!\");"), gbc);
        container.add(createAutoSizingArea("int x = 10;\nint y = 20;\nint sum = x + y;"), gbc);
        container.add(createAutoSizingArea("// Single line comment"), gbc);

        // Push everything to the top by adding a spacer with heavy weight at the bottom
        gbc.weighty = 1.0;
        container.add(Box.createGlue(), gbc);

        // Wrap the whole container panel in a main scroll pane if you expect many text areas
        add(new JScrollPane(container));
        setLocationRelativeTo(null);
    }

    private Component createAutoSizingArea(String text) {
        // Initialize with 0 rows and columns so preferred size is derived entirely from text
        RSyntaxTextArea textArea = new RSyntaxTextArea(0, 0);
        textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
        textArea.setCodeFoldingEnabled(true);
        textArea.setText(text);

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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DynamicTextAreaDemo().setVisible(true));
    }
}
