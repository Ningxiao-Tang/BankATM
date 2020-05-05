import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManagerStockView extends JFrame {

    public ManagerStockView() {
        initComponent();
    }

    private void initComponent() {
        JPanel jp = new JPanel();
        jp.setLayout(new BoxLayout(jp,BoxLayout.Y_AXIS));
        StockListView  stocklistPanel = new StockListView();
        jp.add(stocklistPanel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel,BoxLayout.X_AXIS));
        JButton okBtn = new JButton("Confirm");
        JButton cancelBtn = new JButton("Cancel");

        okBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO:read edited field, update db
                dispose();

            }
        });
        buttonPanel.add(okBtn);
        buttonPanel.add(Box.createHorizontalGlue());
        cancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        buttonPanel.add(cancelBtn);

        jp.add(buttonPanel);

        this.add(jp);

        int width = 600;
        int height = 400;
        this.setTitle("Stock Menu");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setSize(width, height);


    }

}
