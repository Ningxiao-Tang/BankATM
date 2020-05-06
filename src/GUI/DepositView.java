package GUI;

import Database.BankData;
import bank.CheckingAccount;
import bank.Currency;
import bank.CurrencyType;
import bank.Customer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class DepositView extends JDialog {
//    private Customer customer;
//    private Bank bank;
    private BankData db;
    private Customer customer;
    private double oldVal;


    public DepositView(JFrame parent, boolean model, Customer customer, BankData db, double oldVal) {
        super(parent,model);
        initComponets(this);
        setLocationRelativeTo(parent);
        this.db = db;
        this.customer = customer;
        this.oldVal = oldVal;
    }

    private void initComponets(DepositView depositView) {
        JLabel amountLabel = new JLabel("Amount");
        JTextField amountField = new JTextField(10);
        JLabel currencyLabel = new JLabel("Currency");
        JComboBox currencyOptions = new JComboBox(new DefaultComboBoxModel(new String[]{"USD","RMB","EUR"}));
        JButton depositBtn = new JButton("Deposit");
        JButton cancelBtn = new JButton("Cancel");

        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setTitle("Deposit Menu");

        JPanel jp = new JPanel(new GridLayout(3,3,5,5));
        jp.add(amountLabel);
        jp.add(amountField);
        jp.add(currencyLabel);
        jp.add(currencyOptions);

        depositBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder warnings = new StringBuilder();
                //Verify the deposit is not empty
                if (amountField.getText().isEmpty()) {
                    warnings.append("Deposit amount is required.\n");
                } else {
                    double amount = 0;
                    String currency = currencyOptions.getSelectedItem().toString();
                    //Verify the deposit is a positive number
                    try {
                        amount = Double.parseDouble(amountField.getText());
                        int result = JOptionPane.showConfirmDialog(depositView, "Deposit " + String.format("%.2f ", amount)+currencyOptions.getSelectedItem().toString());
                        if (result == JOptionPane.OK_OPTION) {
                            try {
                                //Make the deposit
                                /*
                                if ( currency == "USD"){

                                }
                                if (currency == "RMB"){

                                }
                                else if (currency == "EUR"){

                                } */
                                CheckingAccount acc = new CheckingAccount(new Currency(CurrencyType.USD, oldVal+amount), db);

                                db.updateCheckingAccount(acc, customer);

                                dispose();
                            } catch (Exception ex) {
                                warnings.append("Deposit amount is invalid.\n");
                            }

                        }
                    } catch (NumberFormatException ex) {
                        warnings.append("Deposit must be a number.\n");
                    }
                }
                if (warnings.length() > 0) {
                    JOptionPane.showMessageDialog(depositView, warnings.toString(), "Deposit Warnings", JOptionPane.WARNING_MESSAGE);
                }
            }


        });
        jp.add(depositBtn);

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
