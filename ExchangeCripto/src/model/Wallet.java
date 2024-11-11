package model;
import java.util.ArrayList;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;

public class Wallet {
    private int id;
    private BigDecimal balance;
    private ArrayList<Moeda> criptos = new ArrayList<>(); // 0 = BTC, 1 = ETH, 2 = XRP, ... = novas moedas

    public Wallet(int id, BigDecimal balance, ArrayList<Moeda> currencies) {
        this.id = id;
        this.balance = balance;
        currencies.sort(Comparator.comparing(Moeda::getName));
        this.criptos = currencies;
    }
    
    public Moeda getCriptoByName(String name) {
        for (Moeda cripto : criptos) {
            if (cripto.getName().equalsIgnoreCase(name)) {
                return cripto;
            }
        }
        return null;
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

    public ArrayList<Moeda> getCriptos() {
        return criptos;
    }
    
    public ArrayList<Object[]> getCriptoData() {
        ArrayList<Object[]> criptoData = new ArrayList<>();
        for (Moeda c : criptos){
            Object[] linha = {
                c.getName(),
                c.getQuotation(),
                c.getTaxCompra(),
                c.getTaxVenda(),
                c.getBalance().multiply(c.getQuotation()),
                c.getBalance()
            };
            criptoData.add(linha);
        }
        return criptoData;
    }
}
