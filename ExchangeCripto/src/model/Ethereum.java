package model;

import DAO.Conexao;
import DAO.CurrencyDao;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Random;
import javax.swing.JOptionPane;

public class Ethereum extends Moeda implements Tarifa, Cotacao{
    private double taxCompra = 1;
    private double taxVenda = 2;
    private BigDecimal cotacao;
    private BigDecimal balance;
    
    public Ethereum(int id, String name, String acronym, BigDecimal balance, BigDecimal cotacao, double taxC, double taxV) {
        super(id, name, acronym);
        this.taxCompra = taxC;
        this.taxVenda = taxV;
        this.cotacao = cotacao;
        this.balance = balance;
    }

    public Ethereum(int id, String name, String acronym, BigDecimal cotacao, double taxC, double taxV) {
        super(id, name, acronym);
        this.cotacao = cotacao;
        this.taxCompra = taxC;
        this.taxVenda = taxV;
    }

    
    
    @Override
    public BigDecimal getBalance() {
        return balance;
    }
    
    @Override
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
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
        
        Random random = new Random();
        Conexao conexao = new Conexao();
        
        try{
            BigDecimal variationPercentage = new BigDecimal(random.nextDouble())
                .multiply(BigDecimal.valueOf(0.1))
                .subtract(BigDecimal.valueOf(0.05))
                .setScale(6, RoundingMode.HALF_UP);
            
            BigDecimal variation = cotacao.multiply(
                variationPercentage
            );
            
            
            cotacao = cotacao
                .add(variation)
                .setScale(6, RoundingMode.HALF_UP);
            
            
            Connection conn = conexao.getConnection();
            CurrencyDao cDao = new CurrencyDao(conn);
            cDao.updateCurrencyQuote(super.getId(), cotacao);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(
                null, 
                "Erro de Conexão",
                "Erro",
                JOptionPane.ERROR_MESSAGE
            );
        }
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