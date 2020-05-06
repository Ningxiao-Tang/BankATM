package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReportView extends JFrame {

    public ReportView() {

        initComponent();
    }

    private void initComponent() {
        JPanel jp = new JPanel();
        //jp.setLayout(new BoxLayout(jp,BoxLayout.Y_AXIS));
        jp.setLayout(new BorderLayout());
        JPanel topPanel = new JPanel();
        jp.add(topPanel);
        JPanel buttonPanel = new JPanel();
        JButton okBtn = new JButton("Return");
        okBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        buttonPanel.add(okBtn);
        jp.add(buttonPanel,BorderLayout.PAGE_END);
        this.add(jp);


        int width = 600;
        int height = 400;
        this.setTitle("Daily Report");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setSize(width, height);
    }

}
