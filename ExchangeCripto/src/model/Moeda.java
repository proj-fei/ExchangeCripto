package model;
import java.math.BigDecimal;

public abstract class Moeda {
    private int id;
    private String name;
    private String acronym;
    private BigDecimal balance;

    public Moeda(
            int id, 
            String name, 
            String acronym, 
            BigDecimal balance
    ) {
        this.id = id;

        this.name = name;
        this.acronym = acronym;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public String getAcronym() {
        return acronym;
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
    
    // Funções abstratas para permitir que classes filhas acessam esses funções 
    // nas interfaces de forma generia
    public abstract void updateCotacao();
    public abstract BigDecimal calcularRealToCripto(BigDecimal value);
    public abstract BigDecimal calcularCriptoToReal(BigDecimal value);
    public abstract BigDecimal taxarCompra(BigDecimal value);
    public abstract BigDecimal taxarVenda(BigDecimal value);
    public abstract BigDecimal getCotacao();
    public abstract double getTaxCompra();
    public abstract double getTaxVenda();
    
}

// As classe de Bitcoin, Ehtereum e Ripple mantém a mesma estrutura de Moeda e não possuem diferença, já que no projeto
// utilizamos polimorfismo tratamos todas as moedas de forma genérica então elas são classes para representar as principais
// moedas do sistema.