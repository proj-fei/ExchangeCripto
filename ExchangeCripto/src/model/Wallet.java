package model;
import java.util.ArrayList;
import java.math.BigDecimal;
import java.math.RoundingMode;
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
    private Extrato extrato;

    public Wallet(int id, BigDecimal balance, Bitcoin bitcoin, Ethereum ethereum, Ripple ripple, ArrayList<Cripto> criptos) {
        this.id = id;
        this.balance = balance;
        this.bitcoin = bitcoin;
        this.ethereum = ethereum;
        this.ripple = ripple;
        this.criptos = criptos;
        
        this.extrato = new Extrato(this);
    }
    
    public Moeda getCriptoByName(String name) {
        if(name.equalsIgnoreCase(bitcoin.getName())) {
            return bitcoin;
        } else if (name.equalsIgnoreCase(ethereum.getName())){
            return ethereum;
        } else if (name.equalsIgnoreCase(ripple.getName())) {
            return ripple;
        } else {
            for(Cripto cripto : this.criptos) {
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

    public Bitcoin getBitcoin() {
        return bitcoin;
    }

    public Ethereum getEthereum() {
        return ethereum;
    }

    public Ripple getRipple() {
        return ripple;
    }
    
    public void updateQuote() {
        bitcoin.updateCotacao();
        ethereum.updateCotacao();
        ripple.updateCotacao();
        
        if (!criptos.isEmpty()){
            for(Cripto c : criptos) {
                c.updateCotacao();
            }
        }
    }
    
    // Coleta generia de cotação para demais moedas
    public BigDecimal getGenericCoinQuote(int index) {
        return criptos.get(index).getBalance();
    }

    public ArrayList<Cripto> getCriptos() {
        return criptos;
    }

    public Extrato getExtrato() {
        return extrato;
    }
    
    public void setExtrato(Extrato extrato) {
        this.extrato = extrato;
    }
    
    public ArrayList<Object[]> getCriptoData() {
        ArrayList<Object[]> criptoData = new ArrayList<>();
        ArrayList<Moeda> temp_criptos = new ArrayList<>();
        temp_criptos.add(bitcoin);
        temp_criptos.add(ethereum);
        temp_criptos.add(ripple);
        temp_criptos.addAll(criptos);
        
        for (Moeda c : temp_criptos){
            Object[] linha = {
                c.getName(),
                c.getAcronym(),
                c.getCotacao().setScale(6, RoundingMode.HALF_UP),
                String.format("%,2f%%", c.getTaxCompra()),
                String.format("%,2f%%", c.getTaxVenda()),
                "R$ "+ c.calcularCriptoToReal(c.getBalance()).setScale(2,RoundingMode.HALF_UP ),
                c.getBalance().setScale(6, RoundingMode.HALF_UP) + " " + c.getAcronym()
                
            };
            criptoData.add(linha);
        }
        return criptoData;
    }
    
}


