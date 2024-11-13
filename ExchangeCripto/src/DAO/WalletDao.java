package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import model.Bitcoin;
import model.Cripto;
import model.Ethereum;
import model.Extrato;
import model.Moeda;
import model.Ripple;
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
        ArrayList<Moeda> currencies = new ArrayList<>();
        CurrencyDao cDao = new CurrencyDao(this.conn);
        currencies = cDao.getCurrencyByWallet(walletId);
        
        Bitcoin btc = null;
        Ethereum eth = null;
        Ripple xrp = null;
        ArrayList<Cripto> criptos = new ArrayList<>();
        Iterator<Moeda> iterator = currencies.iterator();
        while (iterator.hasNext()) {
            Moeda moeda = iterator.next();
           
            if (moeda instanceof Bitcoin bitcoin) {
                btc = bitcoin;
                iterator.remove();
            } else if (moeda instanceof Ethereum ethereum) {
                eth = ethereum;
                iterator.remove();
            } else if (moeda instanceof Ripple ripple) {
                xrp = ripple;
                iterator.remove();
            } else if (moeda instanceof Cripto cripto) {
                criptos.add(cripto);
            }
            
        }
        Wallet wallet = new Wallet(
            res.getInt("id"),
            res.getBigDecimal("balance"),
            btc,
            eth,
            xrp,
            criptos
        );
        
        Extrato extrato = new Extrato(wallet);
        extrato.updateExtrato();
        wallet.setExtrato(extrato);
        
        res.close();
        statement.close();
        return wallet;
    }
    
    public BigDecimal getSaldo(int id) throws SQLException {
        String sql = "select balance from wallets where id = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet res = statement.executeQuery();
        if(!res.next()) {
            return null;
        }
        return res.getBigDecimal("balance");
    }
    
    public void setBalance(int id, BigDecimal value) throws SQLException {        
        String sql = "UPDATE wallets SET balance = ? WHERE id = ?";
        PreparedStatement statement = conn.prepareStatement(sql); 
        statement.setBigDecimal(1, value);
        statement.setInt(2, id);
        statement.executeUpdate();
        statement.close();
    }
    
    
}
