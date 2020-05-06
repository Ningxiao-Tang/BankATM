package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BuyStockView extends JFrame {
    //private BankData bank;

    public BuyStockView(){
        JPanel jp = new JPanel();

        jp.setLayout(new BoxLayout(jp, BoxLayout.Y_AXIS));
        JPanel stocklistPanel = new StockListView();
        jp.add(stocklistPanel);

        JPanel midPanel = new JPanel(new GridLayout(2,2,5,5));
        JLabel amountLabel = new JLabel("Amount");
        JTextField amountField = new JTextField();
        JButton buyBtn = new JButton("Buy");
        JButton cancelBtn = new JButton("Cancel");
        midPanel.add(amountLabel);
        midPanel.add(amountField);

        buyBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //buy stock
                dispose();

            }
        });
        midPanel.add(buyBtn);
        cancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        midPanel.add(cancelBtn);

        jp.add(midPanel);
        this.add(jp);
        int width = 600;
        int height = 400;
        this.setTitle("Buy Stock Menu");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setSize(width, height);


    }
}
