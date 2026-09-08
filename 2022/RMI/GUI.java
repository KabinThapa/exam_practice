import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUI implements ActionListener {
    private JLabel lalMain, lblFirst, lblSecond, lblThird;
    private JTextField txtFirst, txtSecond,txtThird;
    private JButton btnAdd, btnExit;
    private JFrame fr;
    private JPanel pn;

    public GUI(){
        fr = new JFrame("Main Window");
        fr.setSize(200,200);
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        pn = new JPanel();
        pn.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,15,10,15);
        gbc.fill = GridBagConstraints.HORIZONTAL;


        lalMain = new JLabel("Calculator");
        gbc.gridwidth = 2;
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 1;
        lalMain.setFont(new Font("Arial",Font.BOLD,16));

        pn.add(lalMain,gbc);

        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;

        lblFirst = new JLabel("First number:");
        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0.1;
        pn.add(lblFirst,gbc);

        lblSecond = new JLabel("Second number:");
        gbc.gridy=2;
        pn.add(lblSecond,gbc);

        lblThird =  new JLabel("Result:");
        gbc.gridy = 3;
        pn.add(lblThird,gbc);

        txtFirst = new JTextField(5);
        gbc.gridx=1;gbc.gridy=1;gbc.weightx=0.9;
        pn.add(txtFirst,gbc);

        txtSecond = new JTextField(5);
        gbc.gridy=2;
        pn.add(txtSecond,gbc);

        txtThird = new JTextField(5);
        gbc.gridy=3;
        txtThird.setEditable(false);
        pn.add(txtThird,gbc);

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,15,0));


        Dimension dimension = new Dimension(100,32);

        btnAdd = new JButton("Add");
        btnAdd.setPreferredSize(dimension);
        btnAdd.addActionListener(this);

        btnExit = new JButton("Exit");
        btnExit.setPreferredSize(dimension);
        btnExit.addActionListener(this);

        btnPanel.add(btnAdd);
        btnPanel.add(btnExit);

        gbc.gridwidth = 2; gbc.gridx = 0; gbc.gridy=4;gbc.weightx=1;
        pn.add(btnPanel,gbc);

        fr.add(pn);

        JScrollPane scrollPane = new JScrollPane(pn,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        fr.add(scrollPane);
        fr.setVisible(true);

    }
    @Override
    public void actionPerformed(ActionEvent e){
        try{
            if(e.getSource() == btnAdd){
                double num1 = Double.parseDouble(txtFirst.getText().trim());
                double num2 = Double.parseDouble(txtSecond.getText().trim());
                txtThird.setText(String.valueOf(num2+num1));
            }
            else{
                System.exit(0);
            }
        }catch (NumberFormatException ex){
            JOptionPane.showMessageDialog(null,"Please enter the number in proper format");
        }
    }
    public static void main(String[] args){
        new GUI();
    }
}