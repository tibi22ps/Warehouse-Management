package presentation;

import javax.swing.*;

/**
 * Clasa ProdusOp
 */

public class ProdusOp {
    private JButton addProductButton;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JButton editProductButton;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JButton deleteProductButton;
    private JTextField textField7;
    private JButton viewAllProductsButton;
    private JPanel Panou;

    public ProdusOp() {
        JFrame frame = new JFrame("Meniu");
        frame.setContentPane(Panou);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(700, 500);
        Controller c = new Controller();

        addProductButton.addActionListener(c.produsAdd(textField1,textField2,textField3));

        editProductButton.addActionListener(c.produsEdit(textField6,textField4,textField5));

        deleteProductButton.addActionListener(c.produsDelete(textField7));

        viewAllProductsButton.addActionListener(c.viewAllProducts());
    }
}
