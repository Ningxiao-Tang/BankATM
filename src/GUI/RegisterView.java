package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterView extends JFrame {
    //private BankData bank;
    private JLabel firstName, lastName, jLabel, username, setPassword;
    private JTextField fnameField, lnameField, emailField;
    private JPasswordField passwordField;

    public RegisterView() { //pass BankData to register class
        //this.bank = bank
        int width = 600;
        int height = 400;
        this.setTitle("Customer Register");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setSize(width, height);
        //this.setLayout(null);

        JPanel jp = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        firstName = new JLabel("First Name");
        lastName = new JLabel("Last Name");
        jLabel = new JLabel("Email");
        setPassword = new JLabel("Password");
        fnameField = new JTextField(20);
        lnameField = new JTextField(20);
        emailField = new JTextField(20);
        passwordField = new JPasswordField(20);
        JButton okbtn = new JButton("Register");
        JButton cancelbtn = new JButton("Cancel");

        jp.add(firstName,gbc);
        jp.add(fnameField,gbc);
        jp.add(lastName,gbc);
        jp.add(lnameField,gbc);
        jp.add(jLabel,gbc);
        jp.add(emailField,gbc);
        jp.add(setPassword,gbc);
        jp.add(passwordField,gbc);
        jp.add(okbtn);
        jp.add(cancelbtn);
        this.add(jp);
        this.setVisible(true);

        okbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fname = fnameField.getText();
                String lname = lnameField.getText();
                String email = emailField.getText();
                String password = String.valueOf(passwordField.getPassword());

                    //TODO: validate input and instantiate a customer, store info to bankdata;
                    //Name name = new name(fname, lname);
                    //Customer customer = new Customer(name, email, password);


            }
        });

        cancelbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });





    }
}
