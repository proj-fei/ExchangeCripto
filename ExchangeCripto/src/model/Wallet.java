package model;
import java.util.ArrayList;
import java.math.BigDecimal;

public class Wallet {
    private int id;
    private BigDecimal balance;
    private ArrayList<Coin> criptos = new ArrayList<>(); // 0 = BTC, 1 = ETH, 2 = XRP, ... = novas moedas

    public Wallet(int id, BigDecimal balance, ArrayList<Coin> currencies) {
        this.id = id;
        this.balance = balance;
        this.criptos = currencies;
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
    
    // Coleta o saldo do bitcoin diretamente na arraylist pelo seu index
    public BigDecimal getBTCBalance() {
        return criptos.get(0).getBalance();
    }
    
    // Coleta o saldo do ethereum diretamente na arraylist pelo seu index
    public BigDecimal getETHBalance() {
        return criptos.get(1).getBalance();
    }
        
    // Coleta o saldo do ripple diretamente na arraylist pelo seu index
    public BigDecimal getXRPBalance() {
        return criptos.get(2).getBalance();
    }
    
    // Função generica de coleta de saldo para demais moedas
    public BigDecimal getGenericCoinBalance(int index) {
        return criptos.get(index).getBalance();
    }
   
    // Coleta de cotação do bitcoin diretamente na arraylist pelo seu index
    public BigDecimal getBTCQuote() {
        return criptos.get(0).getBalance();
    }
    
    // Coleta de cotação do ethereum diretamente na arraylist pelo seu index
    public BigDecimal getETHQuote() {
        return criptos.get(1).getBalance();
    }
        
    // Coleta de cotação do ripple diretamente na arraylist pelo seu index
    public BigDecimal getXRPQuote() {
        return criptos.get(2).getBalance();
    }
    
    // Coleta generia de cotação para demais moedas
    public BigDecimal getGenericCoinQuote(int index) {
        return criptos.get(index).getBalance();
    }
}
