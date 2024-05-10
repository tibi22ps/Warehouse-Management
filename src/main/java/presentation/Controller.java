package presentation;

import dao.AbstractDAO;
import model.Client;
import model.Comanda;
import model.Produs;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clasa Controller
 */

public class Controller {
//TODO:...
    public ActionListener clientPress(){
        ActionListener a=new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClientOp clientOp=new ClientOp();
            }
        };
        return a;
    }

    public ActionListener clientAdd(JTextField jt1, JTextField jt2){
        ActionListener a=new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Client client=new Client(0,jt1.getText(), jt2.getText());
                AbstractDAO abstractDAO=new AbstractDAO(1);
                try {
                    abstractDAO.insert(client);
                } catch (IllegalAccessException ex) {
                    throw new RuntimeException(ex);
                }
            }
        };
        return a;
    }

    public ActionListener clientEdit(JTextField jt1, JTextField jt2,JTextField jt3){
        ActionListener a=new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Client client=new Client(Integer.parseInt(jt1.getText()), jt2.getText(), jt3.getText());
                AbstractDAO abstractDAO=new AbstractDAO(1);
                try {
                    abstractDAO.update(client, client.getId());
                } catch (IllegalAccessException ex) {
                    throw new RuntimeException(ex);
                }

            }
        };
        return a;
    }

    public ActionListener clientDelete(JTextField jt1){
        ActionListener a=new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Client client=new Client((int)Integer.parseInt(jt1.getText()));
                AbstractDAO abstractDAO=new AbstractDAO(1);
                try {
                    abstractDAO.delete(client, client.getId());
                } catch (IllegalAccessException ex) {
                    throw new RuntimeException(ex);
                }

            }
        };
        return a;
    }

    public ActionListener comandaPress(){
        ActionListener b=new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ComandaOp comandaOp=new ComandaOp();
            }
        };
        return b;
    }

    public ActionListener comandaAdd(JTextField jt2, JTextField jt3, JTextField jt4, JTextField jt5){
        ActionListener a=new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Comanda comanda=new Comanda(0,jt2.getText(), jt3.getText(), jt4.getText(), Integer.parseInt(jt5.getText()));
                AbstractDAO abstractDAO=new AbstractDAO(1);
                try {
                    AbstractDAO abstractDAO1=new AbstractDAO<>(1);
                    Produs produs=(Produs) abstractDAO1.findById(Integer.parseInt(jt4.getText()),Produs.class);
                    if(Integer.parseInt(jt5.getText()) <= produs.getStock()){
                    abstractDAO.insert(comanda);
                    produs.setStock(produs.getStock()-Integer.parseInt(jt5.getText()));
                    abstractDAO.update(produs, produs.getId());
                    }
                    else {
                        System.out.println("Produse insuficiente!");
                    }
                } catch (IllegalAccessException ex) {
                    throw new RuntimeException(ex);
                }
            }
        };
        return a;
    }

    public ActionListener comandaEdit(JTextField jt1, JTextField jt2,JTextField jt3, JTextField jt4, JTextField jt5){
        ActionListener a=new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Comanda comanda=new Comanda(Integer.parseInt(jt1.getText()), jt2.getText(), jt3.getText(), jt4.getText(), Integer.parseInt(jt5.getText()));
                AbstractDAO abstractDAO=new AbstractDAO(1);
                try {
                    abstractDAO.update(comanda, comanda.getId());
                } catch (IllegalAccessException ex) {
                    throw new RuntimeException(ex);
                }

            }
        };
        return a;
    }

    public ActionListener comandaDelete(JTextField jt1){
        ActionListener a=new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Comanda comanda=new Comanda((int)Integer.parseInt(jt1.getText()));
                AbstractDAO abstractDAO=new AbstractDAO(1);
                try {
                    abstractDAO.delete(comanda, comanda.getId());
                } catch (IllegalAccessException ex) {
                    throw new RuntimeException(ex);
                }

            }
        };
        return a;
    }

    public ActionListener produsPress(){
        ActionListener c=new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProdusOp produsOp=new ProdusOp();
            }
        };
        return c;
    }

    public ActionListener produsAdd(JTextField jt1, JTextField jt2, JTextField jt3){
        ActionListener a=new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Produs produs=new Produs(Integer.parseInt(jt1.getText()), jt2.getText(), Integer.parseInt(jt3.getText()));
                AbstractDAO abstractDAO=new AbstractDAO(1);
                try {
                    abstractDAO.insert(produs);
                } catch (IllegalAccessException ex) {
                    throw new RuntimeException(ex);
                }
            }
        };
        return a;
    }

    public ActionListener produsEdit(JTextField jt1, JTextField jt2,JTextField jt3){
        ActionListener a=new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Produs produs=new Produs(Integer.parseInt(jt1.getText()), jt2.getText(), Integer.parseInt(jt3.getText()));
                AbstractDAO abstractDAO=new AbstractDAO(1);
                try {
                    abstractDAO.update(produs, produs.getId());
                } catch (IllegalAccessException ex) {
                    throw new RuntimeException(ex);
                }

            }
        };
        return a;
    }

    public ActionListener produsDelete(JTextField jt1){
        ActionListener a=new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Produs produs=new Produs((int)Integer.parseInt(jt1.getText()));
                AbstractDAO abstractDAO=new AbstractDAO(1);
                try {
                    abstractDAO.delete(produs, produs.getId());
                } catch (IllegalAccessException ex) {
                    throw new RuntimeException(ex);
                }

            }
        };
        return a;
    }
    public ActionListener viewAllClients(){
        ActionListener a=new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Client client=new Client();
                String[][] matrice=AbstractDAO.retrieveTableContents(client);
                ViewAllClients viewAllClients=new ViewAllClients(matrice);
            }
        };
        return a;
    }

    public ActionListener viewAllCommands(){
        ActionListener a=new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Comanda comanda=new Comanda();
                String[][] matrice=AbstractDAO.retrieveTableContents(comanda);
                ViewAllCommands viewAllCommands=new ViewAllCommands(matrice);
            }
        };
        return a;
    }

    public ActionListener viewAllProducts(){
        ActionListener a=new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Produs produs=new Produs();
                String[][] matrice=AbstractDAO.retrieveTableContents(produs);
                ViewAllProducts viewAllProducts=new ViewAllProducts(matrice);
            }
        };
        return a;
    }

}
