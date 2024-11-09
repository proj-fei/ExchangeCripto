package model;
import java.math.BigDecimal;

public class Coin {
    private int id;
    private int taxVenda;
    private int taxCompra;
    private String name;
    private String acronym;
    private BigDecimal balance;
    private BigDecimal quotation;

    public Coin(
            int id, 
            int taxVenda, 
            int taxCompra, 
            String name, 
            String acronym, 
            BigDecimal quotation, 
            BigDecimal balance
    ) {
        this.id = id;
        this.taxVenda = taxVenda;
        this.taxCompra = taxCompra;
        this.name = name;
        this.acronym = acronym;
        this.balance = balance;
        this.quotation = quotation;
    }
    
    

    public int getTaxVenda() {
        return taxVenda;
    }

    public void setTaxVenda(int taxVenda) {
        this.taxVenda = taxVenda;
    }

    public int getTaxCompra() {
        return taxCompra;
    }

    public void setTaxCompra(int taxCompra) {
        this.taxCompra = taxCompra;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getQuotation() {
        return quotation;
    }

    public void setQuotation(BigDecimal quotation) {
        this.quotation = quotation;
    }

    public int getId() {
        return id;
    }
    
}

// As classe de Bitcoin, Ehtereum e Ripple mantém a mesma estrutura de Coin e não possuem diferença, já que no projeto
// utilizamos polimorfismo tratamos todas as moedas de forma genérica então elas são classes para representar as principais
// moedas do sistema.