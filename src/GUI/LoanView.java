import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoanView extends JDialog {
    public LoanView(JFrame parent, boolean modal) {
        super(parent,modal);
        initComponent(this);
        setLocationRelativeTo(parent);
    }

    private void initComponent(LoanView loanView) {
        JLabel amountLabel = new JLabel("Amount");
        JTextField amountField = new JTextField(10);
        JLabel currencyLabel = new JLabel("Currency");
        JComboBox currencyOptions = new JComboBox(new DefaultComboBoxModel(new String[]{"USD","RMB","EUR"}));
        JLabel collateralLabel = new JLabel("Collateral");
        JTextField collateralField = new JTextField(10);
        JButton okBtn = new JButton("Make Loan");
        JButton cancelBtn = new JButton("Cancel");

        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setTitle("Withdrawal Menu");

        JPanel jp = new JPanel(new GridLayout(4,2,5,5));
        jp.add(amountLabel);
        jp.add(amountField);
        jp.add(currencyLabel);
        jp.add(currencyOptions);
        jp.add(collateralLabel);
        jp.add(collateralField);

        okBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder warnings = new StringBuilder();
                //Verify the amount is not empty
                if (amountField.getText().isEmpty()) {
                    warnings.append("Deposit amount is required.\n");
                }
                if (collateralField.getText().isEmpty()){
                    warnings.append("Collateral is required.\n");
                } else {
                    double amount = 0;
                    String currency = currencyOptions.getSelectedItem().toString();
                    String collateral = collateralField.getText();
                    //Verify the deposit is a positive number
                    try {
                        amount = Double.parseDouble(amountField.getText());
                        int result = JOptionPane.showConfirmDialog(loanView, "Make loan " + String.format("%.2f ", amount)+ currencyOptions.getSelectedItem().toString());
                        if (result == JOptionPane.OK_OPTION) {
                            try {
                                //Make the loan
                                // Loan loan = new Loan();
                                //bank.addLoan();

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
                    JOptionPane.showMessageDialog(loanView, warnings.toString(), "Loan Warnings", JOptionPane.WARNING_MESSAGE);
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
        this.add(jp);
        this.pack();
    }
}
