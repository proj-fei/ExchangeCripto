
package DAO;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import model.Moeda;
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
    
    public ArrayList<Object[]> getExtrato(int walletId) throws SQLException {
        String sql = "SELECT * FROM extracts WHERE walletid = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, walletId);
        ResultSet res = statement.executeQuery();
        
        ArrayList<Object[]> transactions = new ArrayList<>();
        CurrencyDao cDao = new CurrencyDao(this.conn);
        
        while (res.next()) {
            Moeda moeda = cDao.getCurrencyById(res.getInt("currencyid"));
            
            BigDecimal value = res
                .getBigDecimal("value")
                .stripTrailingZeros()
                .setScale(2, BigDecimal.ROUND_HALF_UP);
                          
            String valueMsg = " " + res.getString("operation") + " " + value;
            
            if (res.getInt("currencyid") == 0 ) {
                valueMsg += " Real";
            } else {
                valueMsg += " " + moeda.getAcronym();
            }
            
            Timestamp timestamp = res.getTimestamp("date");
            LocalDate date = timestamp.toLocalDateTime().toLocalDate();
            LocalTime time = timestamp.toLocalDateTime().toLocalTime().withSecond(0).withNano(0);
            
            String formattedTime = time.format(DateTimeFormatter.ofPattern("HH:mm"));
            
            Object[] linha = {
                date,
                formattedTime,
                valueMsg,
                res.getBigDecimal("quotation").stripTrailingZeros().setScale(2, BigDecimal.ROUND_HALF_UP),
                res.getBigDecimal("tax").stripTrailingZeros().setScale(2, BigDecimal.ROUND_HALF_UP),
                res.getBigDecimal("real").stripTrailingZeros().setScale(2, BigDecimal.ROUND_HALF_UP),
                res.getBigDecimal("btc").stripTrailingZeros().setScale(6, BigDecimal.ROUND_HALF_UP),
                res.getBigDecimal("eth").stripTrailingZeros().setScale(6, BigDecimal.ROUND_HALF_UP),
                res.getBigDecimal("xrp").stripTrailingZeros().setScale(6, BigDecimal.ROUND_HALF_UP),
            };
            
            transactions.add(linha);
        }
        return transactions;
    }
}