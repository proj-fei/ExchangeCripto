package model;

import java.math.BigDecimal;

public class Cripto extends Moeda implements Tarifa, Cotacao{
    private double taxCompra;
    private double taxVenda;
    private BigDecimal cotacao;
    
    public Cripto(int id, String name, String acronym, BigDecimal balance, BigDecimal cotacao, double taxC, double taxV) {
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