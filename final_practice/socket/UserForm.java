import javax.swing.*;
import java.awt.*;

public class UserForm {
    public static void main(String[] args) {
// 1. Create the main window frame
        JFrame frame = new JFrame("User Profile Registration");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(450, 300);

// Use BorderLayout for the main window content pane
        frame.setLayout(new BorderLayout(10, 10));

// 2. NORTH: Header Label
        JLabel headerLabel = new JLabel("Create Your Account", JLabel.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 18));
        headerLabel.setBorder(BorderFactory.createEmptyBorder(15, 10, 10, 10));
        frame.add(headerLabel, BorderLayout.NORTH);

// 3. CENTER: Form Fields (Using a sub-panel with GridLayout)
        JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 15));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));

// Adding Form Labels and Text Fields
        formPanel.add(new JLabel("Full Name:"));
        JTextField nameField = new JTextField();
        formPanel.add(nameField);

        formPanel.add(new JLabel("Email Address:"));
        JTextField emailField = new JTextField();
        formPanel.add(emailField);

        formPanel.add(new JLabel("Password:"));
        JPasswordField passwordField = new JPasswordField();
        formPanel.add(passwordField);

        frame.add(formPanel, BorderLayout.CENTER);

// 4. SOUTH: Action Buttons (Using a sub-panel with FlowLayout)
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 15, 30));

        JButton cancelBtn = new JButton("Cancel");
        JButton submitBtn = new JButton("Submit");

        buttonPanel.add(cancelBtn);
        buttonPanel.add(submitBtn);

        frame.add(buttonPanel, BorderLayout.SOUTH);

// 5. Center the window on the screen and display it
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

}