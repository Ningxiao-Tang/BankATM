package GUI;

import Database.BankData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;

public class SignInView extends JFrame {
    JLabel usernameL, passwordL;
    JTextField usernameField;
    JPasswordField passwordField;
    JButton signInBtn, cancelBtn;
    private BankData db;
    //private User user;


    public SignInView(BankData db){
        this.db = db;
        int width = 600;
        int height = 400;
        this.setTitle("Sign In");
        this.setSize(width, height);
        usernameL = new JLabel("Username:");
        passwordL = new JLabel("Password:");
        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        signInBtn = new JButton("Sign In");
        cancelBtn = new JButton("Cancel");
        JPanel jp = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        jp.add(usernameL,gbc);
        jp.add(usernameField,gbc);
        jp.add(passwordL,gbc);
        jp.add(passwordField,gbc);
        jp.add(signInBtn);
        jp.add(cancelBtn);
        this.add(jp);
        this.setVisible(true);

        init();
    }

    private void init() {
        signInBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = String.valueOf(passwordField.getPassword());
                //TODO: return user instance from BankData
                // user = bank.getUser(username, password);
                // if (user instanceof Customer)
                //      CustomerView customerView = new CustomerView(bank, (Customer) user);
                //      ...
                // else
                //      ManagerView managerView = new ManagerView(bank, (Manager) user);
                if (username.equals("admin") && password.equals("admin")) {
                    ManagerView managerView = new ManagerView();
                    managerView.setVisible(true);
                    dispose();
                }
                try {
                    String realPass = db.getCredentials(username);
                    // hash password submitted and see if it matches saved password
                    MessageDigest digest = MessageDigest.getInstance("SHA-256");
                    byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
                    String encoded = Base64.getEncoder().encodeToString(hash);
                    System.out.println(encoded);

                    if (encoded.equals(realPass)) {
                        CustomerView customerView = new CustomerView();
                        customerView.setVisible(true);
                        dispose();
                    }

                }catch (Exception exception) {
                    System.out.println(exception);
                }
                /*
                if (username.equals("Alice") && password.equals("12345")) {
                    CustomerView customerView = new CustomerView();
                    customerView.setVisible(true);
                    dispose();
                }
                else{
                    ManagerView managerView = new ManagerView();
                    managerView.setVisible(true);
                    dispose();
                }

                 */


            }
        });

        cancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}
