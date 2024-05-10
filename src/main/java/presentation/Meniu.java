package presentation;

import connection.ConnectionFactory;

import javax.swing.*;

/**
 * Clasa Meniu
 */

public class Meniu {
    private JButton clientButton;
    private JButton produsButton;
    private JButton comandaButton;
    private JPanel Panou;

    public Meniu(){
        JFrame frame=new JFrame("Meniu");
        frame.setContentPane(Panou);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(300,500);
        Controller c=new Controller();
        clientButton.addActionListener(c.clientPress());

        produsButton.addActionListener(c.produsPress());

        comandaButton.addActionListener(c.comandaPress());



    }
}
