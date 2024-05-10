package presentation;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * ViewAllClients
 */

public class ViewAllClients {
    private JTable table1;
    private JPanel panel1;

        public ViewAllClients(String[][] mat) {

        JFrame frame = new JFrame("Meniu");
            frame.setContentPane(panel1);
            DefaultTableModel model =new DefaultTableModel();
            table1=new JTable(model);
            JScrollPane jScrollPane=new JScrollPane(table1);
            jScrollPane.setBounds(0,0,700,500);
            model.setDataVector(mat, new Object[]{"id", "NumeClient", "PrenumeClient"});
            panel1.setLayout(null);
            panel1.add(jScrollPane);
            frame.pack();
            frame.setVisible(true);
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setSize(700,500);
        Controller c = new Controller();

    }
}
