/** This view represents the frame for all stocks, managers can view all stocks,
 * customers can view their bought stocks */

package GUI;

import javax.swing.*;
import java.awt.*;

public class StockListView extends JPanel {

    public StockListView() {
        initComponent();
    }

    private void initComponent() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createLineBorder(Color.black));
    }

}
