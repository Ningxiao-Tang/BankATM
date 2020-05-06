package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateAccountMenu extends JDialog {
//    private BankData bank;
//    private Customer customer = null;
        //private AccountType acc;

    public CreateAccountMenu(JFrame parent, boolean model ) { //also need to pass bankdata,and customer
        super(parent,model);
        initComponents(this);
        setLocationRelativeTo(parent);
        //this.bank = bank;
        //this.customer = customer;
    }

    private void initComponents(JDialog parent) {
        JDialog parentComponent = parent;
        JLabel depositLabel = new JLabel("Initial Deposit:");
        JTextField depositField = new JTextField(20);
        JLabel typeLabel = new JLabel("Account Type");
        JComboBox typeField = new JComboBox(new DefaultComboBoxModel(new String[] {"Checking", "Saving", "Security"}));
        JButton okBtn = new JButton("Confirm");
        JButton cancelBtn = new JButton("Cancel");

        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setTitle("Create Account");
        JPanel jp = new JPanel();

        jp.add(depositLabel);
        jp.add(depositField);
        jp.add(typeLabel);
        jp.add(typeField);

        okBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder warnings = new StringBuilder();
                double amount = 0;
                if (depositField.getText().isEmpty()) {
                    warnings.append("Initial deposit must be entered.");
                } else {
                    try {
                        amount = Double.parseDouble(depositField.getText());
                    } catch (NumberFormatException ex) {
                        warnings.append("Initial deposit must be a number.");
                    }
                }
                if (warnings.length() > 0) {
                    JOptionPane.showMessageDialog(parentComponent, warnings.toString(), "Input Warnings", JOptionPane.WARNING_MESSAGE);
                } else {

                    String acctType = "";
                    if (typeField.getSelectedItem().toString() == "Checking") {
                        if (amount >= 100) {
                            acctType = "Checking";

                        } else {
                            warnings.append("Initial deposit must be at least $100 for Checking accounts.");
                        }
                    } else if (typeField.getSelectedItem().toString() == "Savings") {
                        if (amount >= 200) {
                            acctType = "Savings";
                        } else {
                            warnings.append("Initial deposit must be at least $200 for Savings accounts.");
                        }
                    }
                    //TODO:
                        //acc = new AccountType(amount)
                        //acc =  bank.addAccount(customer, acc, acctType);
                        dispose();

                }
            }
        });

        jp.add(okBtn);

        cancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        jp.add(cancelBtn);
        jp.setLayout(new GridLayout(3,2,5,5));
        this.add(jp);
        this.pack();


    }


//    public Customer getCustomer() {
//
//    }
}
