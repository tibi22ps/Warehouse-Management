package presentation;

import javax.swing.*;

/**
 * ClientOp
 */

public class ClientOp {
    private JButton addClientButton;
    private JTextField textField1;
    private JTextField textField2;
    private JButton editClientButton;
    private JTextField textField3;
    private JTextField textField4;
    private JButton deleteClientButton;
    private JTextField textField5;
    private JButton viewAllClientsButton;
    private JPanel Panou;
    private JTextField textField7;

    public ClientOp() {
        JFrame frame = new JFrame("Meniu");
        frame.setContentPane(Panou);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(700, 500);
        Controller c = new Controller();

        addClientButton.addActionListener(c.clientAdd(textField1,textField2));

        editClientButton.addActionListener(c.clientEdit(textField7,textField3, textField4));

        deleteClientButton.addActionListener(c.clientDelete(textField5));

        viewAllClientsButton.addActionListener(c.viewAllClients());

    }

}
