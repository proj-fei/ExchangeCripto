package DAO;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Coin;


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
    
    public ArrayList<Coin> getCurrencyByWallet(int walletId ) throws SQLException {
        String sql = "SELECT * FROM wallet_currency_balances where walletid = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, walletId);
        ResultSet res = statement.executeQuery();
        
        ArrayList<Coin> currencies = new ArrayList<>();
        while (res.next()) {
            int currencyId = res.getInt("currencyid");
            BigDecimal balance = res.getBigDecimal("balance");
            Coin coinDetails = getCurrencyById(currencyId);
            coinDetails.setBalance(balance);
            currencies.add(coinDetails);
        }
        res.close();
        statement.close();
        
        return currencies;
    }
    
    public Coin getCurrencyById(int currencyId) throws SQLException {
        String sql = "SELECT * FROM currency where id = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, currencyId);
        ResultSet res = statement.executeQuery();
        Coin currency = null;
        if(res.next()) {
            int id = res.getInt("id");
            String name = res.getString("name");
            String acronym = res.getString("acronym");
            BigDecimal quote = res.getBigDecimal("quotation");
            int taxV = res.getInt("taxv");
            int taxC = res.getInt("taxc");
            
            currency = new Coin(id, taxV, taxC, name, acronym, quote, BigDecimal.ZERO);
        }
        
        res.close();
        statement.close();
        
        return currency;
    }
}
