package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.User;
import model.Wallet;


public class WalletDao {
    private Connection conn;

    public WalletDao(Connection conn) {
        this.conn = conn;
    }
    
    public Wallet getUserWallet(int id) throws SQLException {
        String sql = "select * from wallets where userid = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet res = statement.executeQuery();
        
        if(!res.next()) {
            return null;
        }
        
        Wallet wallet = new Wallet(
            res.getInt("id"),
            res.getBigDecimal("balance")
        );
        
        return wallet;
    }
}
