package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.math.BigDecimal;
import java.util.ArrayList;
import model.Coin;
import model.Wallet;


public class WalletDao {
    private Connection conn;

    public WalletDao(Connection conn) {
        this.conn = conn;
    }
    
    // Criar uma Carteira, somente a partir da criação de um novo usuário
    public void createWallet(int userId) throws SQLException {
        String sql = "INSERT INTO wallets (balance, userid) values (?,?)";
        PreparedStatement statement = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        BigDecimal bdecimal = new BigDecimal("0.00");
        
        statement.setBigDecimal(1, bdecimal);
        statement.setInt(2, userId);
        statement.executeUpdate();
        
        ResultSet generatedKeys = statement.getGeneratedKeys();
        int walletId = 0;
        
        if(generatedKeys.next()) {
            walletId = generatedKeys.getInt(1);
        }
        
        statement.close();
        if(walletId == 0){
            throw new SQLException("ID da Carteira não pode ser 0"); 
        }
        
        CurrencyDao cDao = new CurrencyDao(conn);
        cDao.createLinkWalletCurrency(walletId, 1); // Cria Saldo de Bitcoin
        cDao.createLinkWalletCurrency(walletId, 2); // Cria Saldo de Ethereum
        cDao.createLinkWalletCurrency(walletId, 3); // Cria saldo de Ripple
        
        conn.close();
    }
    
    public Wallet getUserWallet(int id) throws SQLException {
        String sql = "select * from wallets where userid = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet res = statement.executeQuery();
        
        if(!res.next()) {
            return null;
        }
        
        int walletId = res.getInt("id");
        ArrayList<Coin> currencies = new ArrayList<>();
        CurrencyDao cDao = new CurrencyDao(this.conn);
        currencies = cDao.getCurrencyByWallet(walletId);
        Wallet wallet = new Wallet(
            res.getInt("id"),
            res.getBigDecimal("balance"),
            currencies
        );
        
        res.close();
        statement.close();
        return wallet;
    }
    
}
