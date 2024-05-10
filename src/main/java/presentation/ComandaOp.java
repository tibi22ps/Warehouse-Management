package presentation;

import javax.swing.*;

/**
 * ComandaOp
 */

public class ComandaOp {
    private JButton addCommandButton;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JButton editCommandButton;
    private JButton deleteCommandButton;
    private JButton viewAllCommandsButton;
    private JPanel Panou;
    private JTextField textField1;
    private JTextField textField7;
    private JTextField textField8;
    private JTextField textField9;
    private JTextField textField10;
    private JTextField textField11;

    public ComandaOp() {
        JFrame frame = new JFrame("Meniu");
        frame.setContentPane(Panou);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(700, 500);
        Controller c = new Controller();

        addCommandButton.addActionListener(c.comandaAdd(textField2,textField3, textField4, textField5));
        editCommandButton.addActionListener(c.comandaEdit(textField1, textField7, textField8, textField9, textField10));
        deleteCommandButton.addActionListener(c.comandaDelete(textField11));

        viewAllCommandsButton.addActionListener(c.viewAllCommands());
    }
}
