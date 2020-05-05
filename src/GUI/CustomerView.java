package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CustomerView extends JFrame {

    //private Customer customer;
    //private ArrayList<Transactions> transactions;

    public CustomerView() {
        //pass customer, transactions to GUI.CustomerView
//        this.customer = customer;
//        this.transactions = transactions;
        initComponents();
    }

    private void initComponents() {
        JButton createAccBtn = new JButton("Create Account");
        JButton depositBnt = new JButton("Deposit");
        JButton withdrawBtn = new JButton("Withdraw");
        JButton transferBtn = new JButton("Transfer");
        JButton buyStockBtn = new JButton("Buy Stock");
        JButton sellStockBtn = new JButton("Sell Stock");
        JButton historyBtn = new JButton("Transactions");
        JButton logOutBtn = new JButton("Log Out");
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel,BoxLayout.Y_AXIS));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder (5,5,0,5));
        buttonPanel.add(createAccBtn);
        buttonPanel.add(depositBnt);
        buttonPanel.add(withdrawBtn);
        buttonPanel.add(transferBtn);
        buttonPanel.add(buyStockBtn);
        buttonPanel.add(sellStockBtn);
        buttonPanel.add(historyBtn);
        buttonPanel.add(Box.createRigidArea(new Dimension(15,15)));

        buttonPanel.add(logOutBtn);
        JPanel jp = new JPanel();
        jp.setLayout(new BoxLayout(jp, BoxLayout.X_AXIS));

        jp.add(buttonPanel);
        //jp.add(accountTable);
        this.add(jp);
        int width = 600;
        int height = 400;
        this.setTitle("Bank ATM");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(width, height);
        this.setVisible(true);

        //TODO: initialize account table of the customer
        String[] title = {"Currency", "Balance"};
//        int dataSize = customer.getCheckingAccount().getAccount().size();
//        String[][] data = new String[dataSize][2];
//        for(int i = 0; i < dataSize; i++) {
//            data[i][0] = tableCurrencyName.get(i);
//            data[i][1] = customer.getCheckingAccount().getBalanceByKey(data[i][0]);
//        }
        //accountTable = new JTable(title, data);
        JTable accountTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(accountTable);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        accountTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
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

        logOutBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

    }

    private void accountTableMouseClicked(MouseEvent e) {
    }

    private void sellStockBtnActionPerformed() {
    }

    private void buyStockBtnActionPerformed() {
    }

    private void transferBtnActionPerformed() {
    }

    private void withdrawBtnActionPerformed() {
    }

    private void depositBntActionPerformed() {
    }

    private void createAccBtnActionPerformed() {
    }


}
