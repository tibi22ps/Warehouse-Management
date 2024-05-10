package model;

/**
 * Clasa Produs
 */

public class Produs {
    private int id;
    private String NumeProdus;
    private int Stock;



    public Produs() {
    }

    public Produs(int id) {
        this.id=id;
    }

    public Produs(int id, String NumeProdus, int Stock){
        this.id=id;
        this.NumeProdus = NumeProdus;
        this.Stock=Stock;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStock() {
        return Stock;
    }

    public void setStock(int stock) {
        Stock = stock;
    }

    public String getNumeProdus() {
        return NumeProdus;
    }

    public void setNumeProdus(String numeProdus) {
        NumeProdus = numeProdus;
    }

}