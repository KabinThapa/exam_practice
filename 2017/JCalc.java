import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class JCalc implements ActionListener {
    private JFrame fr;
    private JPanel panel; // Panel to hold components
    private JScrollPane scrollPane; // Scroll pane to provide scroll bars
    private JLabel lblFirst, lblSecond, lblThird;
    private JTextField txtFirst, txtSecond, txtThird;
    private JButton btnOk, btnExit;

    public JCalc() {
        fr = new JFrame("Java");
        fr.setSize(400, 300); // Reduced height to make the scrollbar visible/necessary
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a panel and set GridBagLayout on it
        panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12, 15, 12, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // --- First Row ---
        lblFirst = new JLabel("N1");
        lblFirst.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0.1;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(lblFirst, gbc); // Added to panel

        txtFirst = new JTextField(15);
        gbc.gridx = 1; gbc.gridy = 0; gbc.weightx = 0.9;
        panel.add(txtFirst, gbc); // Added to panel

        // --- Second Row ---
        lblSecond = new JLabel("N2");
        lblSecond.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0.1;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(lblSecond, gbc); // Added to panel

        txtSecond = new JTextField(15);
        gbc.gridx = 1; gbc.gridy = 1; gbc.weightx = 0.9;
        panel.add(txtSecond, gbc); // Added to panel

        // --- Third Row ---
        lblThird = new JLabel("Result");
        lblThird.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 0; gbc.gridy = 2; gbc.weightx = 0.1;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(lblThird, gbc); // Added to panel

        txtThird = new JTextField(15);
        txtThird.setEditable(false);
        gbc.gridx = 1; gbc.gridy = 2; gbc.weightx = 0.9;
        panel.add(txtThird, gbc); // Added to panel

        // --- Fourth Row (Buttons) ---
        btnOk = new JButton("OK");
        btnOk.addActionListener(this);
        gbc.gridx = 0; gbc.gridy = 3; gbc.weightx = 0.5;
        panel.add(btnOk, gbc); // Added to panel

        btnExit = new JButton("Exit");
        btnExit.addActionListener(this);
        gbc.gridx = 1; gbc.gridy = 3; gbc.weightx = 0.5;
        panel.add(btnExit, gbc); // Added to panel

        // Wrap the panel inside a JScrollPane
        scrollPane = new JScrollPane(panel);

        // Force the vertical scrollbar to always show (optional)
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        // Add the JScrollPane to the frame instead of the raw panel
        fr.add(scrollPane);

        fr.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnExit) {
            System.exit(0);
        } else if (e.getSource() == btnOk) {
            try {
                double n1 = Double.parseDouble(txtFirst.getText());
                double n2 = Double.parseDouble(txtSecond.getText());
                double res = n1 + n2; // Simple addition logic
                txtThird.setText(String.valueOf(res));
            } catch (NumberFormatException ex) {
                txtThird.setText("Invalid Input");
            }
        }
    }

    public static void main(String[] args) {
        // Run GUI codes on the Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(() -> new JCalc());
    }
}
