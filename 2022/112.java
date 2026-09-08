import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class lloo implements ActionListener{
    private JLabel userName, passWord;
    private JTextField userTxt;
    private JPasswordField  passTxt;
    private JButton btnLogin, btnClear;
    private JFrame fr;

    public lloo(){
        fr = new JFrame("Login Form");
        fr.setSize(300,350);
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,15,10,15);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        userName = new JLabel("Username: ");
        gbc.gridx=0;gbc.gridy=0;gbc.weightx=0.1;
        userName.setFont(new Font("Arial",Font.BOLD,16));
        fr.add(userName,gbc);

        passWord = new JLabel("Password: ");
        passWord.setFont(new Font("Arial",Font.BOLD,16));
        gbc.gridy=1;
        fr.add(passWord,gbc);

        userTxt = new JTextField(15);
        gbc.gridx = 1;gbc.gridy=0;gbc.weightx=0.9;
        fr.add(userTxt,gbc);

        passTxt = new JPasswordField(15);
        gbc.gridy=1;gbc.gridx=1;
        fr.add(passTxt,gbc);

         btnLogin = new JButton("Login");
         btnClear = new JButton("Clear");
        Dimension dimension = new Dimension(100,34);
        btnLogin.setPreferredSize(dimension);
        btnClear.setPreferredSize(dimension);

        btnLogin.addActionListener(this);
        btnClear.addActionListener(this);

        JPanel pn = new JPanel(new FlowLayout(FlowLayout.CENTER,15,0));
        pn.add(btnLogin);
        pn.add(btnClear);

        gbc.gridwidth = 2;gbc.gridx=0;gbc.gridy=3;gbc.weightx=1;
        fr.add(pn,gbc);
        fr.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        try{
            String user = userTxt.getText().trim();
            String pass = passTxt.getText().trim();
            if(e.getSource() == btnLogin){
                if(user.equals("admin") && pass.equals("12345")){
                    JOptionPane.showMessageDialog(null,"User login successful");
                }
            }
            if(e.getSource() == btnClear){
                userTxt.setText("");
                passTxt.setText("");
            }
        }catch(Exception ignored){}
    }

    public static void main(String[] args){
        new lloo();
    }
}