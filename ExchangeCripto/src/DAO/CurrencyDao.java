package DAO;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.BuilderCripto;
import model.Moeda;


public class CurrencyDao {
    private Connection conn;

    public CurrencyDao(Connection conn) {
        this.conn = conn;
    }
    
    public void createCurrency(String name, String acronym, BigDecimal quote, int taxV, int taxC) throws SQLException{
        String sql = "INSERT INTO currency (name, acronym, quotation, taxc, taxv) values (?,?,?,?, ?)";
        PreparedStatement statement = conn.prepareStatement(sql);
        
        statement.setString(1, name);
        statement.setString(2, acronym);
        statement.setBigDecimal(3, quote);
        statement.setInt(4, taxC);
        statement.setInt(5, taxV);
        statement.execute();

        conn.close();
    }
    
    public void createLinkWalletCurrency(int walletId, int currencyId) throws SQLException{
        String sql = "INSERT INTO wallet_currency_balances (walletid, currencyid, balance) values (?,?,?)";
        PreparedStatement statement = conn.prepareStatement(sql);
        BigDecimal bDecimal = new BigDecimal("0.00");
        
        statement.setInt(1, walletId);
        statement.setInt(2, currencyId);
        statement.setBigDecimal(3, bDecimal);
        statement.execute();
        statement.close();
    }
    
    public ArrayList<Moeda> getCurrencyByWallet(int walletId ) throws SQLException {
        String sql = "SELECT * FROM wallet_currency_balances where walletid = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, walletId);
        ResultSet res = statement.executeQuery();
        
        ArrayList<Moeda> currencies = new ArrayList<>();
        while (res.next()) {
            int currencyId = res.getInt("currencyid");
            BigDecimal balance = res.getBigDecimal("balance");
            
            Moeda coinDetails = getCurrencyById(currencyId);
            coinDetails.setBalance(balance);
            currencies.add(coinDetails);
        }
        res.close();
        statement.close();
        
        return currencies;
    }
    
    public Moeda getCurrencyById(int currencyId) throws SQLException {
        String sql = "SELECT * FROM currency where id = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, currencyId);
        ResultSet res = statement.executeQuery();
        Moeda currency = null;
        if(res.next()) {
            int id = res.getInt("id");
            String name = res.getString("name");
            String acronym = res.getString("acronym");
            BigDecimal quote = res.getBigDecimal("quotation");
            double taxV = res.getDouble("taxv");
            double taxC = res.getDouble("taxc");
            
            // Cria um Construtor Genérico de Cripto Moedas e que Cria o 
            // Objeto da Moeda Específica
            BuilderCripto builder = new BuilderCripto();
            builder.build(
                    id, 
                    name, 
                    acronym, 
                    BigDecimal.ZERO,
                    quote, 
                    taxC, 
                    taxV
            );
            currency = builder.gerResultado();
        }
        
        res.close();
        statement.close();
        
        return currency;
    }
    
    public BigDecimal getCurrencyBalance(int walletId, int currencyId) throws SQLException {
        String sql = "select balance from wallet_currency_balances WHERE walletid = ? AND currencyid = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, walletId);
        statement.setInt(2, currencyId);
        ResultSet res = statement.executeQuery();
        if(!res.next()) {
            return null;
        }
        return res.getBigDecimal("balance");
    }
    
    public void updateCurrency(int walletId, int currencyId, BigDecimal value ) throws SQLException {
        String sql = "UPDATE wallet_currency_balances SET balance = ? WHERE walletid = ? AND currencyid = ?";
        PreparedStatement statement = conn.prepareStatement(sql); 
        statement.setBigDecimal(1, value);
        statement.setInt(2, walletId);
        statement.setInt(3, currencyId);
        statement.executeUpdate();
        statement.close();
    }
}
