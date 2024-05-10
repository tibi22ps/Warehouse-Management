package model;

/**
 * Clasa Comanda
 */

public class Comanda {
    private int id;
    private String NumeClient;
    private String PrenumeClient;
    private String NumeProdus;
    private int Cantitate;

    public Comanda(){}

    public Comanda(int id){
        this.id=id;
    }


    public Comanda(int id, String NumeClient, String PrenumeClient, String NumeProdus, int Cantitate){
        this.id = id;
        this.NumeClient = NumeClient;
        this.PrenumeClient=PrenumeClient;
        this.NumeProdus = NumeProdus;
        this.Cantitate = Cantitate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumeProdus() {
        return NumeProdus;
    }

    public void setNumeProdus(String numeProdus) {
        NumeProdus = numeProdus;
    }

    public int getCantitate() {
        return Cantitate;
    }

    public void setCantitate(int cantitate) {
        Cantitate = cantitate;
    }


    public String getNumeClient() {
        return NumeClient;
    }

    public void setNumeClient(String numeClient) {
        NumeClient = numeClient;
    }

    public String getPrenumeClient() {
        return PrenumeClient;
    }

    public void setPrenumeClient(String prenumeClient) {
        PrenumeClient = prenumeClient;
    }
}
