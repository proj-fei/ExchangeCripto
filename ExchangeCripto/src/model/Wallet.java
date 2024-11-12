package model;
import java.util.ArrayList;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

public class Wallet {
    private int id;
    private BigDecimal balance;
    private Bitcoin bitcoin;
    private Ethereum ethereum;
    private Ripple ripple;
    private ArrayList<Cripto> criptos = new ArrayList<>(); 

    public Wallet(int id, BigDecimal balance, Bitcoin bitcoin, Ethereum ethereum, Ripple ripple, ArrayList<Cripto> criptos) {
        this.id = id;
        this.balance = balance;
        this.bitcoin = bitcoin;
        this.ethereum = ethereum;
        this.ripple = ripple;
        this.criptos = criptos;
    }
    
    public Moeda getCriptoByName(String name) {
        if(name.equalsIgnoreCase(bitcoin.getName())) {
            return bitcoin;
        } else if (name.equalsIgnoreCase(ethereum.getName())){
            return ethereum;
        } else if (name.equalsIgnoreCase(ripple.getName())) {
            return ripple;
        } else {
            for(Moeda cripto : this.criptos) {
                if  (name.equalsIgnoreCase(cripto.getName())) {
                    return cripto;
                }
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
        return bitcoin.getBalance();
    }
    
    // Coleta o saldo do ethereum diretamente na arraylist pelo seu index
    public BigDecimal getETHBalance() {
        return ethereum.getBalance();
    }
        
    // Coleta o saldo do ripple diretamente na arraylist pelo seu index
    public BigDecimal getXRPBalance() {
        return ripple.getBalance();
    }
    
    // Função generica de coleta de saldo para demais moedas
    public BigDecimal getGenericCriptoBalance(int index) {
        return criptos.get(index).getBalance();
    }
   
    // Coleta de cotação do bitcoin diretamente na arraylist pelo seu index
    public BigDecimal getBTCQuote() {
        return bitcoin.getCotacao();
    }
    
    // Coleta de cotação do ethereum diretamente na arraylist pelo seu index
    public BigDecimal getETHQuote() {
        return ethereum.getCotacao();
    }
        
    // Coleta de cotação do ripple diretamente na arraylist pelo seu index
    public BigDecimal getXRPQuote() {
        return ripple.getCotacao();
    }
    
    // Coleta generia de cotação para demais moedas
    public BigDecimal getGenericCoinQuote(int index) {
        return criptos.get(index).getBalance();
    }

    public ArrayList<Cripto> getCriptos() {
        return criptos;
    }
    
    
}

//    public ArrayList<Object[]> getCriptoData() {
//        ArrayList<Object[]> criptoData = new ArrayList<>();
//        for (Moeda c : criptos){
//            Object[] linha = {
//                c.getName(),
//                c.getQuotation(),
//                c.getTaxCompra(),
//                c.getTaxVenda(),
//                c.getBalance().multiply(c.getQuotation()),
//                c.getBalance()
//            };
//            criptoData.add(linha);
//        }
//        return criptoData;
//    }

