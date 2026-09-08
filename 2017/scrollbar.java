import java.awt.*;
import javax.swing.*;

class ScrollDemo {
    private JFrame frame;
    private JPanel contentPanel;
    private JScrollPane scrollPane;

    public ScrollDemo() {
        // 1. Setup the main window frame
        frame = new JFrame("Scrollbar Demonstration");
        frame.setSize(450, 400); // Intentionally small window to force scrolling
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 2. Create the main content panel with GridBagLayout
        contentPanel = new JPanel();
        contentPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 12, 8, 12); // Padding around items
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        // 3. Flood the panel with 50 rows of data to force scrolling
        for (int i = 1; i <= 50; i++) {
            gbc.gridy = i; // Move to the next row layout sequentially

            // Column 0: Label
            JLabel lblItem = new JLabel("System Log Entry #" + i);
            lblItem.setFont(new Font("Arial", Font.PLAIN, 14));
            gbc.gridx = 0;
            gbc.weightx = 0.3;
            contentPanel.add(lblItem, gbc);

            // Column 1: Status TextField
            JTextField txtStatus = new JTextField("Status: Operation " + i + " successful.");
            txtStatus.setEditable(false);
            gbc.gridx = 1;
            gbc.weightx = 0.7;
            contentPanel.add(txtStatus, gbc);
        }

        // 4. Wrap the overflowing panel inside a JScrollPane
        scrollPane = new JScrollPane(contentPanel);

        // Ensure the vertical scrollbar is active and visible
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // Optional: Increase scroll speed (Default Swing scrolling can feel slow)
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        // 5. Add the scroll pane to the frame and display
        frame.add(scrollPane);
        frame.setLocationRelativeTo(null); // Center window on screen
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        // Safely build the GUI on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> new ScrollDemo());
    }
}
