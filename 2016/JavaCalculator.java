import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class JavaCalculator implements ActionListener {
    // UI Component Variables
    private JFrame fr;
    private JLabel mainTitle, lblFirst, lblSecond, lblResult;
    private JTextField txtFirst, txtSecond, txtResult;
    private JButton btnAdd, btnSub;

    public JavaCalculator() {
        // Step 1: Initialize the Master Window Frame
        fr = new JFrame("java program calculator");
        fr.setLayout(new GridBagLayout());
        fr.setSize(450, 400);
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Step 2: Configure the Layout Rules
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12, 15, 12, 15); // Adds uniform margin cushions around fields
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // --- ROW 0: Calculator Title (Forced Left Aligned) ---
        mainTitle = new JLabel("CALCULATOR");
        mainTitle.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.gridwidth = 2; // Stretch cell block width across both columns
        gbc.anchor = GridBagConstraints.WEST; // Force text to hug the left wall
        fr.add(mainTitle, gbc);

        // Reset grid cell width back to 1 for standard elements below
        gbc.gridwidth = 1;

        // --- ROW 1: First Number Field ---
        lblFirst = new JLabel("First Number:");
        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0.1;
        gbc.anchor = GridBagConstraints.WEST;
        fr.add(lblFirst, gbc);

        txtFirst = new JTextField(15);
        gbc.gridx = 1; gbc.gridy = 1; gbc.weightx = 0.9;
        fr.add(txtFirst, gbc);

        // --- ROW 2: Second Number Field ---
        lblSecond = new JLabel("Second Number:");
        gbc.gridx = 0; gbc.gridy = 2; gbc.weightx = 0.1;
        gbc.anchor = GridBagConstraints.WEST;
        fr.add(lblSecond, gbc);

        txtSecond = new JTextField(15);
        gbc.gridx = 1; gbc.gridy = 2; gbc.weightx = 0.9;
        fr.add(txtSecond, gbc);

        // --- ROW 3: Result Display Box ---
        lblResult = new JLabel("Result:");
        gbc.gridx = 0; gbc.gridy = 3; gbc.weightx = 0.1;
        gbc.anchor = GridBagConstraints.WEST;
        fr.add(lblResult, gbc);

        txtResult = new JTextField(15);
        txtResult.setEditable(false); // Make it read-only so users can't modify answers
        gbc.gridx = 1; gbc.gridy = 3; gbc.weightx = 0.9;
        fr.add(txtResult, gbc);

        // --- ROW 4: Centered Control Buttons ---
        // We drop buttons into a FlowLayout panel to keep them identical size and centered
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));

        btnAdd = new JButton("Add");
        btnSub = new JButton("Sub");

        // Force both buttons to share identical visual dimensions
        Dimension buttonSize = new Dimension(100, 32);
        btnAdd.setPreferredSize(buttonSize);
        btnSub.setPreferredSize(buttonSize);

        // Attach action click listeners directly to this class instance
        btnAdd.addActionListener(this);
        btnSub.addActionListener(this);

        buttonPanel.add(btnAdd);
        buttonPanel.add(btnSub);

        // Drop the button layout sub-panel into the master frame grid sheet
        gbc.gridx = 0; gbc.gridy = 4;
        gbc.gridwidth = 2; // Span across both columns to align right down the middle
        gbc.weightx = 1.0;
        fr.add(buttonPanel, gbc);

        // Step 3: Draw window onto screen
        fr.setVisible(true);
    }

    // Mathematical Action Processing Logic Block
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            // Strip out blank text values and convert strings into numerical values
            double num1 = Double.parseDouble(txtFirst.getText().trim());
            double num2 = Double.parseDouble(txtSecond.getText().trim());
            double result = 0;

            // Figure out which calculation button was clicked
            if (e.getSource() == btnAdd) {
                result = num1 + num2;
            } else if (e.getSource() == btnSub) {
                result = num1 - num2;
            }

            // Print output text back onto the non-editable result box
            txtResult.setText(String.valueOf(result));

        } catch (NumberFormatException ex) {
            // Crash proof guard: alerts user if they typed invalid alphabetical text into fields
            JOptionPane.showMessageDialog(fr, "Error: Please input valid numbers only.", "Input Format Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Direct executable application entry point
    public static void main(String[] args) {
        new JavaCalculator();
    }
}
