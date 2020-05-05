import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CustomerView extends JFrame {
    private JButton createAccBtn , depositBnt, withdrawBtn, transferBtn, buyStockBtn, sellStockBtn, historyBtn,logOutBtn ;

    //private Customer customer;
    //private ArrayList<Transactions> transactions;
    JTable accountTable = new JTable();
    private final DefaultTableModel model;

    public CustomerView() {
        //pass customer, transactions to CustomerView
//        this.customer = customer;
//        this.transactions = transactions;
        initComponents();
        model = (DefaultTableModel) accountTable.getModel();
    }

    private void initComponents() {
        createAccBtn = new JButton("Create Account");
        depositBnt = new JButton("Deposit");
        withdrawBtn = new JButton("Withdraw");
        transferBtn = new JButton("Transfer");
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


        //TODO: initialize account table of the customer, display customer's accounts
        String[] columnNames = {"Account Number", "Balance ($)","Balance (￥)","Balance (€)"};
//        int dataSize = customer.getCheckingAccount().getAccount().size();
//        String[][] data = new String[dataSize][3];
//        for(int i = 0; i < dataSize; i++) {
//            data[i][0] = balance in $
//            data[i][1] =
//            data[i][2] =
//        }
        accountTable.setModel(new DefaultTableModel());
        String [][] data = new String[][]{{"Alice","2000","50.45","0.00"}, {"Bob","50","0","0"}};
        accountTable = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(accountTable);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        accountTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        int width = 600;
        int height = 400;
        this.setTitle("Bank ATM");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(width, height);
        this.setVisible(true);

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
        setAccountButtonsActive(true);
        CreateAccountMenu menu = new CreateAccountMenu(this,true);
        menu.setVisible(true);
        //TODO: add created account to account table
//        if (menu.getCustomer() != null) {
//            addCustomerToTable(menu.getCustomer());
//        }
    }

//    private void addCustomerToTable(Customer customer) {
//        model.addRow(new Object[]{});
//        reloadCustomerRowData(model.getRowCount() - 1, customer.getAccount().getAccountNumber());
//    }
//
//    private void reloadCustomerRowData(int selectedRow, int accountId) {
//        Customer customer = bank.getCustomer(accountId);
//        model.setValueAt(customer.getName(), selectedRow, 0);
//        model.setValueAt(String.format("%.2f", customer.getAccount().getBalance()), selectedRow, 1);
//    }

    private void setAccountButtonsActive(boolean active) {
        depositBnt.setEnabled(active);
        withdrawBtn.setEnabled(active);
        transferBtn.setEnabled(active);
        buyStockBtn.setEnabled(active);
        sellStockBtn.setEnabled(active);
    }

}
