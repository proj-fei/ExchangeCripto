package model;
import java.util.ArrayList;
import java.math.BigDecimal;

public class Wallet {
    private int id;
    private BigDecimal balance;
    private ArrayList<Coin> criptos_balance = new ArrayList<>();

    public Wallet(int id, BigDecimal balance) {
        this.id = id;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public ArrayList<Coin> getCriptos_balance() {
        return criptos_balance;
    }

    public void setCriptos_balance(ArrayList<Coin> criptos_balance) {
        this.criptos_balance = criptos_balance;
    }
    
}
