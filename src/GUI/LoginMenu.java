import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginMenu extends JFrame{
    private JPanel panel;
    private JButton customerSignInButton;
    private JButton customerRegisterButton;
    private JButton managerSignInButton;

    public LoginMenu() {
        int width = 600;
        int height = 400;
        this.setTitle("Bank ATM");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setSize(width, height);
        this.setLayout(new GridBagLayout());


        customerSignInButton = new JButton("Customer Sign In");
        customerRegisterButton = new JButton("Customer Register");
        managerSignInButton = new JButton("Manager Sign In");

        panel = new JPanel();
        panel.setSize(width, height);
        panel.setLayout(new FlowLayout());

        panel.add(customerRegisterButton);
        panel.add(customerSignInButton);
        panel.add(managerSignInButton);
        this.add(panel);

        init();


    }
    private void init() {
        customerRegisterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //display register frame
                RegisterView register = new RegisterView();
            }
        });

        customerSignInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //display sign-in frame,
                SignInView customerSign = new SignInView();

            }
        });

        managerSignInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //display sign-in frame
                SignInView managerSign = new SignInView();
            }
        });
    }

    public static void main(String[] args) {
        new LoginMenu();
    }


//    {
//// GUI initializer generated by IntelliJ IDEA GUI Designer
//// >>> IMPORTANT!! <<<
//// DO NOT EDIT OR ADD ANY CODE HERE!
//        $$$setupUI$$$();
//    }
//
//    /**
//     * Method generated by IntelliJ IDEA GUI Designer
//     * >>> IMPORTANT!! <<<
//     * DO NOT edit this method OR call it in your code!
//     *
//     * @noinspection ALL
//     */
//    private void $$$setupUI$$$() {
//        panel1 = new JPanel();
//        panel1.setLayout(new CardLayout(0, 0));
//        final JPanel panel2 = new JPanel();
//        panel2.setLayout(new CardLayout(0, 0));
//        panel1.add(panel2, "Card1");
//        final JPanel panel3 = new JPanel();
//        panel3.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(4, 1, new Insets(0, 0, 0, 0), -1, -1));
//        panel2.add(panel3, "Card1");
//        cutomerSignInButton = new JButton();
//        cutomerSignInButton.setText("Cutomer Sign in");
//        panel3.add(cutomerSignInButton, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
//        customerRegisterButton = new JButton();
//        customerRegisterButton.setText("Customer Register");
//        panel3.add(customerRegisterButton, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
//        managerSignInButton = new JButton();
//        managerSignInButton.setText("Manager Sign in");
//        panel3.add(managerSignInButton, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
//    }
//
//    /**
//     * @noinspection ALL
//     */
//    public JComponent $$$getRootComponent$$$() {
//        return panel1;
//    }
}
