package model;

import java.math.BigDecimal;

public class Ripple extends Moeda implements Tarifa, Cotacao{
    private double taxCompra = 1;
    private double taxVenda = 1;
    private BigDecimal cotacao;
    
    public Ripple(int id, String name, String acronym, BigDecimal balance, BigDecimal cotacao, double taxC, double taxV) {
        super(id, name, acronym, balance);
        this.taxCompra = taxC;
        this.taxVenda = taxV;
        this.cotacao = cotacao;
    }
    
    @Override
    public double getTaxCompra() {
        return taxCompra;
    }
    
    @Override
    public double getTaxVenda() {
        return taxVenda;
    }
    @Override
    public BigDecimal getCotacao() {
        return cotacao;
    }
    
    @Override    
    public BigDecimal taxarCompra(BigDecimal value){
        return value.multiply(
            BigDecimal.valueOf(taxCompra).divide(
               BigDecimal.valueOf(100)
            )
        );
    };
    
    @Override   
    public BigDecimal taxarVenda(BigDecimal value){
        return value.multiply(
            BigDecimal.valueOf(taxVenda).divide(
               BigDecimal.valueOf(100)
            )
        );
    };
    
    @Override    
    public void updateCotacao(){
    };
    
    @Override   
    public BigDecimal calcularRealToCripto(BigDecimal value){
        return value.divide(cotacao, 6, BigDecimal.ROUND_HALF_UP);
    };
    
    @Override   
    public BigDecimal calcularCriptoToReal(BigDecimal value){
        return value.multiply(cotacao);
    };
}

// As classe de Bitcoin, Ehtereum e Ripple mantém a mesma estrutura de Moeda e não possuem diferença, já que no projeto
// utilizamos polimorfismo tratamos todas as moedas de forma genérica então elas são classes para representar as principais
// moedas do sistema.