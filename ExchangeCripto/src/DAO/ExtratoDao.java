
package DAO;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import model.Wallet;

/**
 *
 * @author art5m
 */
public class ExtratoDao {
    private Connection conn;

    public ExtratoDao(Connection conn) {
        this.conn = conn;
    }
    
    public void saveTransaction(
            Wallet wallet, 
            int currencyId, 
            String operation, 
            BigDecimal value,
            BigDecimal tax,
            BigDecimal quotation
    ) throws SQLException{
        String sql = "INSERT INTO extracts" 
            + "(operation, value, btc, eth, xrp, real, tax, quotation, currencyid, walletid)"
            + "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = conn.prepareStatement(sql);
        
        statement.setString(1, operation);
        statement.setBigDecimal( 2, value);
        statement.setBigDecimal( 3, wallet.getBTCBalance());
        statement.setBigDecimal( 4, wallet.getETHBalance());
        statement.setBigDecimal( 5, wallet.getXRPBalance());
        statement.setBigDecimal( 6, wallet.getBalance());
        statement.setBigDecimal( 7, tax);
        statement.setBigDecimal( 8, quotation );
        if (currencyId == 0) {
            statement.setNull( 9, java.sql.Types.INTEGER);
        } else{  
            statement.setInt( 9, currencyId);
        }
        statement.setInt( 10, wallet.getId());
        statement.execute();
        
        // Data é definida diretamente pelo bannco
        conn.close();
    }
}
