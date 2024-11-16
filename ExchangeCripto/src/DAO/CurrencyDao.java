package DAO;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.BuilderCripto;
import model.Investidor;
import model.Moeda;


public class CurrencyDao {
    private Connection conn;

    public CurrencyDao(Connection conn) {
        this.conn = conn;
    }
    
    public void createCurrency(String name, String acronym, BigDecimal quote, double taxV, double taxC) throws SQLException{
        String sql = "INSERT INTO currency (name, acronym, quotation, taxc, taxv) values (?,?,?,?, ?)";
        PreparedStatement statement = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        
        statement.setString(1, name);
        statement.setString(2, acronym);
        statement.setBigDecimal(3, quote);
        statement.setDouble(4, taxC);
        statement.setDouble(5, taxV);
        statement.executeUpdate();
        ResultSet generatedKeys = statement.getGeneratedKeys();
        int currencyId = 0;
        if(generatedKeys.next()){
            currencyId = generatedKeys.getInt(1);
        }

        statement.close();
        if(currencyId == 0){
            throw new SQLException("ID da Moeda não pode ser 0"); 
        }
        
        UserDao ud = new UserDao(conn);
        ArrayList<Investidor> investidores = ud.getInvestidores();
        for (Investidor i: investidores){
            createLinkWalletCurrency(i.getWallet().getId(), currencyId);
        }
        
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
            currency = builder.getResultado();
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
    
    public ArrayList<Moeda> getCurrency() throws SQLException{
        
        String sql = "select * from currency";
        PreparedStatement statement = conn.prepareStatement(sql);
        ResultSet res = statement.executeQuery();
        ArrayList<Moeda> moedas= new ArrayList<>();
        Moeda moeda = null;
        while(res.next()){
            BuilderCripto builder = new BuilderCripto();
            builder.build(
                    res.getInt("id"),
                    res.getString("name"),
                    res.getString("acronym"),
                    res.getBigDecimal("quotation"),
                    res.getDouble("taxc"),
                    res.getDouble("taxv")
            );
            moeda = builder.getResultado();
            moedas.add(moeda);
        }
        res.close();
        statement.close();
        return moedas;
    }
    
    public boolean moedaExiste(String name) throws SQLException {
        String sql = "SELECT COUNT(*) FROM currency WHERE name = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; 
            }
        }
        return false;
    }
    
    public void deleteCurrencyById(int id) throws SQLException {
        String sql = "DELETE FROM currency WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.execute();
            conn.close();
        }
    }

    public void updateCurrencyByIndex(Moeda moeda) throws SQLException{
        String sql = "UPDATE currency SET name = ?, acronym = ?, quotation = ?, taxc = ?, taxv = ? WHERE id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, moeda.getName());
        stmt.setString(2, moeda.getAcronym());
        stmt.setBigDecimal(3, moeda.getCotacao());
        stmt.setDouble(4, moeda.getTaxCompra());
        stmt.setDouble(5, moeda.getTaxVenda());
        stmt.setInt(6, moeda.getId());
        stmt.executeUpdate();
        stmt.close();
        conn.close(); 
    }
    
}
