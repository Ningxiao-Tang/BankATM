/** CustomerView is the view of a logged in customer
 * */

package GUI;

import Database.BankData;
import bank.CheckingAccount;
import bank.CurrencyType;
import bank.Customer;
import bank.SavingsAccount;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class CustomerView extends JFrame {
    private JButton createAccBtn , depositBnt, withdrawBtn, transferBtn, loanBtn, buyStockBtn, sellStockBtn, historyBtn,logOutBtn ;

    private Customer customer;
    private BankData db;
    private double oldVal;
    JTable accountTable = new JTable();
    //private final DefaultTableModel model;

    public CustomerView(Customer customer, BankData db) {
        //pass customer, transactions to CustomerView
        this.customer = customer;
        this.db = db;
        initComponents();
    }

    private void initComponents() {
        createAccBtn = new JButton("Create Account");
        depositBnt = new JButton("Deposit");
        withdrawBtn = new JButton("Withdraw");
        transferBtn = new JButton("Transfer");
        loanBtn = new JButton("Loan");
        buyStockBtn = new JButton("Buy Stock");
        sellStockBtn = new JButton("Sell Stock");
        historyBtn = new JButton("Transactions");
        logOutBtn = new JButton("Log Out");
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel,BoxLayout.Y_AXIS));
        buttonPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        buttonPanel.add(Box.createVerticalStrut(20));
        buttonPanel.add(createAccBtn);
        buttonPanel.add(depositBnt);
        buttonPanel.add(withdrawBtn);
        buttonPanel.add(transferBtn);
        buttonPanel.add(loanBtn);
        buttonPanel.add(buyStockBtn);
        buttonPanel.add(sellStockBtn);
        buttonPanel.add(historyBtn);
        buttonPanel.add(Box.createRigidArea(new Dimension(15,15)));
        buttonPanel.add(logOutBtn);
        JPanel jp = new JPanel();
        jp.setLayout(new BoxLayout(jp, BoxLayout.X_AXIS));

        jp.add(buttonPanel);

        String[] toAdd = null;
        String[] toAdd2 = null;
        String[][] data = new String[0][];

        try {
            List<CheckingAccount> checkingAccts = db.readCheckingAccounts(customer);
            if (checkingAccts.isEmpty()) {
                toAdd = new String[0];
            }
            else {
                for (CheckingAccount cAcct : checkingAccts) {
                    String acc_num = Integer.toString(cAcct.getAccID());
                    String acc_type = "Checking";
                    String balanceUSD = Double.toString(Math.round(cAcct.getBalance().getValue()* 100.00) / 100.00);
                    String balanceEUR = Double.toString(Math.round(cAcct.getBalance().getValue()*1.11*100.00)/100.00);
                    String balanceRMB = Double.toString(Math.round(cAcct.getBalance().getValue()*.14*100.00)/100.00);
                    oldVal = cAcct.getBalance().getValue();
                    toAdd = new String[]{acc_num, acc_type, balanceUSD, balanceEUR, balanceRMB};
                }
            }
            List<SavingsAccount> savingAccts = db.readSavingAccounts(customer);
            if (savingAccts.isEmpty()) {
                toAdd2 = new String[0];
            }
            else {
                for (SavingsAccount sAcct : savingAccts) {
                    String acc_num = Integer.toString(sAcct.getAccID());
                    String acc_type = "Savings";
                    String balanceUSD = Double.toString(Math.round(sAcct.getBalance().getValue()* 100.00) / 100.00);
                    String balanceEUR = Double.toString(Math.round(sAcct.getBalance().getValue()*1.11*100.00)/100.00);
                    String balanceRMB = Double.toString(Math.round(sAcct.getBalance().getValue()*.14*100.00)/100.00);
                    toAdd2 = new String[]{acc_num, acc_type, balanceUSD, balanceEUR, balanceRMB};
                }
            }
            if (checkingAccts.isEmpty() && savingAccts.isEmpty()) {
                data = new String[0][];
            }
            else if (checkingAccts.isEmpty()) {
                data = new String[][]{toAdd2};
            }
            else if (savingAccts.isEmpty()) {
                data = new String[][]{toAdd};
            }
            else {
                data = new String[][]{toAdd, toAdd2};
            }

        }
        catch (Exception e) {
            System.out.println(e);
        }



        String[] columnNames = {"Account Number", "Account Type","Balance ($)","Balance (€)", "Balance (￥)"};

        //accountTable.setModel(new DefaultTableModel());
        accountTable = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(accountTable);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        accountTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JPanel accountPanel = new JPanel();
        accountPanel.add(scrollPane);
        jp.add(accountPanel);
        this.add(jp);
        int width = 600;
        int height = 400;
        this.setTitle("Bank ATM");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(width, height);
        //this.setVisible(true);

        accountTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                accountTableMouseClicked(e);
            }
        });


        createAccBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // create account dialog
                createAccBtnActionPerformed();
            }
        });

        depositBnt.setEnabled(false);
        depositBnt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                depositBntActionPerformed();
            }
        });

        withdrawBtn.setEnabled(false);
        withdrawBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                withdrawBtnActionPerformed();
            }
        });

        transferBtn.setEnabled(false);
        transferBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                transferBtnActionPerformed();
            }
        });

        loanBtn.setEnabled(false);
        loanBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loanBtnActionPerformed();
            }
        });

        buyStockBtn.setEnabled(false);
        buyStockBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buyStockBtnActionPerformed();
            }
        });

        sellStockBtn.setEnabled(false);
        sellStockBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sellStockBtnActionPerformed();
            }
        });

        historyBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TransactionsView tsv = new TransactionsView();
                        tsv.setVisible(true);
            }
        });

        logOutBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

    }



    private void accountTableMouseClicked(MouseEvent e) {
        setAccountButtonsActive(true);

    }

    private void sellStockBtnActionPerformed() {
        SellStockView ssv = new SellStockView();
        ssv.setVisible(true);
    }

    private void buyStockBtnActionPerformed() {
        BuyStockView bsv = new BuyStockView();
        bsv.setVisible(true);
    }
    private void loanBtnActionPerformed() {
        LoanView loanView = new LoanView(this, true);
        loanView.setVisible(true);
    }

    private void transferBtnActionPerformed() {
        TransferView tv = new TransferView();
        tv.setVisible(true);
    }

    private void withdrawBtnActionPerformed() {
        WithdrawView wView = new WithdrawView(this, true, oldVal, customer, db);
        wView.setVisible(true);
    }

    private void depositBntActionPerformed() {
        DepositView dView = new DepositView(this,true, customer, db, oldVal);
        dView.setVisible(true);

    }

    private void createAccBtnActionPerformed() {
        //Customer customer = this.getSelectedCustomer();
        //CreateAccountMenu menu = new CreateAccountMenu(this,true,customer);
        CreateAccountMenu menu = new CreateAccountMenu(this,true, customer, db);
        menu.setVisible(true);
    }


    private void setAccountButtonsActive(boolean active) {
        depositBnt.setEnabled(active);
        withdrawBtn.setEnabled(active);
        transferBtn.setEnabled(active);
        loanBtn.setEnabled(active);
        buyStockBtn.setEnabled(active);
        sellStockBtn.setEnabled(active);
    }

}
