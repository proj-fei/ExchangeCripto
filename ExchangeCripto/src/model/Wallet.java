package model;
import java.util.ArrayList;


public class Wallet {
    private int id;
    private double balance;
    private ArrayList<Coin> criptos_balance = new ArrayList<>();

    public Wallet(int id, double balance) {
        this.id = id;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public ArrayList<Coin> getCriptos_balance() {
        return criptos_balance;
    }

    public void setCriptos_balance(ArrayList<Coin> criptos_balance) {
        this.criptos_balance = criptos_balance;
    }
    
}
