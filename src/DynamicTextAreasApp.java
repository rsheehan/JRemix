import javax.swing.*;
import java.awt.*;

public class DynamicTextAreasApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(DynamicTextAreasApp::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Dynamic JTextAreas in JScrollPane");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        // 1. Create a container panel with a vertical BoxLayout
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        // 2. Put the panel inside a JScrollPane
        JScrollPane scrollPane = new JScrollPane(contentPanel);

        // 3. Create a button to add new text areas dynamically
        JButton addButton = new JButton("Add JTextArea");
        addButton.addActionListener(e -> {
            // Create a new JTextArea
            JTextArea newTextArea = new JTextArea(3, 20);
            newTextArea.setText("Dynamic Text Area #" + (contentPanel.getComponentCount() + 1));
            newTextArea.setLineWrap(true);
            newTextArea.setWrapStyleWord(true);

            // Add a slight border or maximum size if needed
            newTextArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));

            // Add the text area to the container panel
            contentPanel.add(newTextArea);

            // Inform the layout manager and refresh the UI
            contentPanel.revalidate();
            contentPanel.repaint();

            // Optional: Auto-scroll to the newly added component
            newTextArea.scrollRectToVisible(newTextArea.getBounds());
        });

        // Add components to the frame
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(addButton, BorderLayout.SOUTH);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
