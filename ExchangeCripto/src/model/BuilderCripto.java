package model;

import java.math.BigDecimal;

/**
 *
 * @author art5m
 */
public class BuilderCripto {
    private Moeda resultado;
    
    public void build(int id, String name, String acronym, BigDecimal balance, BigDecimal cotacao, double taxC, double taxV) {
        resultado = switch (acronym) {
            case "BTC" -> new Bitcoin(id, name, acronym, balance, cotacao, taxC, taxV);
            case "ETH" -> new Ethereum(id, name, acronym,balance, cotacao, taxC, taxV);
            case "XRP" -> new Ripple(id, name, acronym, balance, cotacao, taxC, taxV);
            default -> new Cripto(id, name, acronym, balance, cotacao, taxC, taxV);
        };
    }
    
    public Moeda gerResultado(){
        return resultado;
    }
    
    public void clean(){
        resultado = null;
    }
}
