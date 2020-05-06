package GUI;

import Database.BankData;
import bank.CheckingAccount;
import bank.Currency;
import bank.CurrencyType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class WithdrawView extends JDialog {
//    private Customer customer;
//    private Bank bank;
    private double oldVal;
    private BankData db;



    public WithdrawView(JFrame parent, boolean modal, double oldVal, BankData db) {
        super(parent,modal);
        initComponets(this);
        setLocationRelativeTo(parent);
        this.oldVal = oldVal;
        this.db = db;
    }

    private void initComponets(WithdrawView depositView) {
        JLabel amountLabel = new JLabel("Amount");
        JTextField amountField = new JTextField(10);
        JLabel currencyLabel = new JLabel("Currency");
        JComboBox currencyOptions = new JComboBox(new DefaultComboBoxModel(new String[]{"USD","RMB","EUR"}));
        JButton withdrawBtn = new JButton("Withdraw");
        JButton cancelBtn = new JButton("Cancel");

        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setTitle("Withdrawal Menu");

        JPanel jp = new JPanel(new GridLayout(3,2,5,5));
        jp.add(amountLabel);
        jp.add(amountField);
        jp.add(currencyLabel);
        jp.add(currencyOptions);

        withdrawBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder warnings = new StringBuilder();
                //Verify the amount is not empty
                if (amountField.getText().isEmpty()) {
                    warnings.append("Deposit amount is required.\n");
                } else {
                    double amount = 0;
                    String currency = "";
                    //Verify the deposit is a positive number
                    try {
                        amount = Double.parseDouble(amountField.getText());
                        int result = JOptionPane.showConfirmDialog(depositView, "Withdrawal " + String.format("%.2f ", amount)+ currencyOptions.getSelectedItem().toString());
                        if (result == JOptionPane.OK_OPTION) {
                            try {
                                //Make the withdrawal
                                //bank.withdraw(customer.getAccount().getAccountNumber(), amount);
                                /*
                                if (currencyOptions.getSelectedItem().toString() == "USD"){

                                }
                                if (currencyOptions.getSelectedItem().toString() == "RMB"){

                                }
                                else if (currencyOptions.getSelectedItem().toString() == "EUR"){

                                }*/
                                CheckingAccount acc = new CheckingAccount(new Currency(CurrencyType.USD, oldVal-amount), db);
                                db.updateCheckingAccount(acc);
                                dispose();
                            } catch (Exception ex) {
                                warnings.append("Insufficient funds to complete transaction.\n");
                            }

                        }
                    } catch (NumberFormatException ex) {
                        warnings.append("Amount must be a number.\n");
                    }
                }
                if (warnings.length() > 0) {
                    JOptionPane.showMessageDialog(depositView, warnings.toString(), "Withdrawal Warnings", JOptionPane.WARNING_MESSAGE);
                }
            }


        });
        jp.add(withdrawBtn);

        cancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();

            }
        });
        jp.add(cancelBtn);
        this.add(jp);
        this.pack();

    }
}
