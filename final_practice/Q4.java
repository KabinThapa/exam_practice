import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class Login implements ActionListener{
    private JFrame fr;
    private JLabel userName, passWord;
    private JTextField userTxt;
    private JPasswordField passField;
    private JButton btnLogin, btnClear;

    public Login(){
        fr = new JFrame("Login Page");
        fr.setSize(350,100);

        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel pn = new JPanel();
        pn.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,15,10,15);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        userName = new JLabel("Username: ");
        gbc.gridx=0;gbc.gridy=0;gbc.weightx=0.1;
        userName.setFont(new Font("Arial",Font.BOLD,16));
        pn.add(userName,gbc);

        passWord = new JLabel("Password: ");
        gbc.gridy = 1;
        passWord.setFont(new Font("Arial",Font.BOLD,16));
        pn.add(passWord,gbc);

        userTxt = new JTextField(15);
        gbc.gridx=1;gbc.gridy=0;gbc.weightx=0.9;
        pn.add(userTxt,gbc);

        passField = new JPasswordField(15);
        gbc.gridy=1;
        pn.add(passField,gbc);

        btnLogin = new JButton("Login");
        btnClear = new JButton("Clear");
        btnLogin.addActionListener(this);
        btnClear.addActionListener(this);

        Dimension dimension = new Dimension(100,32);
        btnLogin.setPreferredSize(dimension);
        btnClear.setPreferredSize(dimension);

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER,15,0));
        buttons.add(btnLogin);
        buttons.add(btnClear);

        gbc.gridx = 0; gbc.gridy=3;gbc.gridwidth=2;gbc.weightx=1;
        pn.add(buttons,gbc);

        JScrollPane scroll = new JScrollPane(pn,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        fr.add(scroll);

        fr.setVisible(true);

    }
    @Override
    public void actionPerformed(ActionEvent e){
        String user = userTxt.getText().trim();
        String pass = new String(passField.getPassword());
        try{
            if(e.getSource() == btnLogin){
                if(user.equals("Admin") && pass.equals("1234")){
                    JOptionPane.showMessageDialog(fr,"Login successful");
                }else{
                    JOptionPane.showMessageDialog(fr,"Invalid Credentials");
                }
            }
            if(e.getSource() == btnClear){
                userTxt.setText("");
                passField.setText("");
            }
        } catch (Exception ex) {
            System.out.println("Cannot resolve");
        }
    }

    public static void main(String[] args){
        SwingUtilities.invokeLater(() -> new Login());
    }
}