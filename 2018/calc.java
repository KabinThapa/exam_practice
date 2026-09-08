import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

class calc implements ActionListener {
    private JFrame fr;
    private JLabel lblFirst, lblSecond, lblThird;
    private JTextField txtFirst, txtSecond, txtThird;
    private JButton btnAdd, btnClear;

    public calc() {
        fr = new JFrame("Java Calc");
        fr.setSize(400, 450);
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 17, 15, 17);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // --- First Row ---
        lblFirst = new JLabel("First Number:");
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0.1;
        gbc.anchor = GridBagConstraints.WEST;
        lblFirst.setFont(new Font("ARIAL", Font.BOLD, 16));
        fr.add(lblFirst, gbc);

        txtFirst = new JTextField(15);
        gbc.gridx = 1; gbc.gridy = 0; gbc.weightx = 0.9;
        fr.add(txtFirst, gbc);

        // --- Second Row ---
        lblSecond = new JLabel("N2");
        lblSecond.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0.1;
        gbc.anchor = GridBagConstraints.WEST;
        fr.add(lblSecond, gbc);

        txtSecond = new JTextField(15);
        gbc.gridx = 1; gbc.gridy = 1; gbc.weightx = 0.9;
        fr.add(txtSecond, gbc);

        // --- Third Row ---
        lblThird = new JLabel("Result");
        lblThird.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 0; gbc.gridy = 2; gbc.weightx = 0.1;
        gbc.anchor = GridBagConstraints.WEST;
        fr.add(lblThird, gbc);

        txtThird = new JTextField(15);
        txtThird.setEditable(false);
        gbc.gridx = 1; gbc.gridy = 2; gbc.weightx = 0.9;
        fr.add(txtThird, gbc);

        // --- Fourth Row (Buttons Fix) ---
        btnAdd = new JButton("add");
        btnAdd.addActionListener(this); // FIXED: Add listener after instantiating btnAdd
        gbc.gridx = 0; gbc.gridy = 3; gbc.weightx = 0.5;
        fr.add(btnAdd, gbc);

        btnClear = new JButton("clear");
        btnClear.addActionListener(this); // FIXED: Add listener after instantiating btnClear
        gbc.gridx = 1; gbc.gridy = 3; gbc.weightx = 0.5;
        fr.add(btnClear, gbc);

        fr.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == btnAdd) {
                double n1 = Double.parseDouble(txtFirst.getText());
                double n2 = Double.parseDouble(txtSecond.getText());
                txtThird.setText(String.valueOf(n1 + n2));
            } else if (e.getSource() == btnClear) { // FIXED: explicit check for clear button
                txtFirst.setText("");
                txtSecond.setText("");
                txtThird.setText(""); // FIXED: Clear the result field too
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(fr, "Error in handling numbers input!", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    public static void main(String[] args) {
        new calc();
    }
}
