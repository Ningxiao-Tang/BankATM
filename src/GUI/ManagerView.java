package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManagerView extends JFrame {

    public ManagerView() {
        initComponent();
    }

    private void initComponent() {
        JPanel jp = new JPanel();
        jp.setLayout(new BoxLayout(jp, BoxLayout.Y_AXIS));
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.X_AXIS));
        JButton stockBtn = new JButton("Stock Setting");
        JButton customerBtn = new JButton("View Customer");
        JButton reportBtn = new JButton("Report");

//        JPanel rightPanel = new JPanel(new CardLayout());
//        JPanel rightContentP = new JPanel();
        stockBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ManagerStockView msv = new ManagerStockView();
                msv.setVisible(true);

            }
        });
        menuPanel.add(stockBtn);
        //menuPanel.add(Box.createVerticalStrut(20));

        customerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Todo: retreive andn display cutomer infos
                CustomerInfoView civ = new CustomerInfoView();
                civ.setVisible(true);
            }
        });
        menuPanel.add(customerBtn);
        //menuPanel.add(Box.createVerticalStrut(20));

        reportBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ReportView rpv = new ReportView();
                rpv.setVisible(true);

            }
        });
        menuPanel.add(reportBtn);
        jp.add(menuPanel);
        this.add(jp);

        int width = 600;
        int height = 400;
        this.setTitle("Bank ATM");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setSize(width, height);




    }

}
