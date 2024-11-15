package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import model.Administrador;
import model.Investidor;
import model.Pessoa;
import model.Wallet;

public class UserDao {
    private Connection conn;

    public UserDao(Connection conn) {
        this.conn = conn;
    }
    
    // Criar Novo Usuário
    public void createUser(String cpf, String name, String password, int isAdmin) throws SQLException{
        String sql = "INSERT INTO users (name, cpf, password, isadmin) values (?,?,?,?)";
        PreparedStatement statement = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        
        statement.setString(1, name);
        statement.setString(2, cpf);
        statement.setString(3, password);
        statement.setInt(4, isAdmin);
        statement.executeUpdate();
        
        if(isAdmin == 0){
            ResultSet generatedKeys = statement.getGeneratedKeys();
            int userId = 0;
            
            if(generatedKeys.next()) {
                userId = generatedKeys.getInt(1);
            }
            
            statement.close();
            if(userId == 0){
                throw new SQLException("ID de Usuário não pode ser 0"); 
            }
            
            WalletDao dao = new WalletDao(conn);
            dao.createWallet(userId);
        }
        conn.close();
    }
    
    public boolean authPassword(String password, int userId) throws SQLException {
        String sql = "SELECT * FROM users WHERE id = ? and password = ? ";
        PreparedStatement statement = conn.prepareStatement(sql);        
        statement.setInt(1, userId);
        statement.setString(2, password);

        ResultSet res = statement.executeQuery();
        if (res.next()){
            statement.close();
            res.close();
            return true;
        }
        statement.close();
        res.close();
        return false;
    }
    
    
    // Autenticação de Usuário para login
    public Pessoa authUser(String cpf, String password) throws SQLException {
        String sql = "SELECT * FROM users WHERE cpf = ? AND password = ? ";
        PreparedStatement statement = conn.prepareStatement(sql);
        
        statement.setString(1, cpf);
        statement.setString(2, password);
        
        ResultSet res = statement.executeQuery();
        
        if (!res.next()) {
            return null;
        }
        // Cria e retorna o objeto Investidor com os dados recuperados
        if(res.getInt("isadmin") == 0){
            WalletDao wDao = new WalletDao(this.conn);
            Wallet wallet = wDao.getUserWallet(res.getInt("id"));
            Investidor user = new Investidor(
                res.getInt("id"),
                res.getString("cpf"),
                res.getString("name"),
                res.getString("password"),
                wallet
            );
            res.close();
            statement.close();
            conn.close();
            return user;
        }
        
        ArrayList<Investidor> invest = this.getInvestidores();
        Administrador user = new Administrador(
            res.getInt("id"),
            res.getString("cpf"),
            res.getString("name"),
            res.getString("password"),
            invest
        );
        
        res.close();
        statement.close();
        conn.close();
        return user;
    }
    
    
    public ArrayList<Investidor> getInvestidores() throws SQLException {
        
        String sql = "select * from users where isadmin = 0";
        PreparedStatement statement = conn.prepareStatement(sql);
        
        ResultSet res = statement.executeQuery();
        
        ArrayList<Investidor> investidores = new ArrayList<>();
        while(res.next()){
            WalletDao wDao = new WalletDao(this.conn);
            
            int id = res.getInt("id");
            String name = res.getString("name");
            String cpf = res.getString("cpf");
            String password = res.getString("password");
            Wallet wallet = wDao.getUserWallet(res.getInt("id"));
            Investidor user = new Investidor(id, name, cpf, password, wallet);
            investidores.add(user);        
        }
        
        res.close();
        statement.close();
        return investidores;
    }
    // Atualizar Usuário ( Em especifico sua senha )
    public void updateUser(Investidor user) throws SQLException {
        String sql = "update users set password = ? where cpf = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, user.getPassword());
        statement.setString(2, user.getCpf());
        statement.execute();
        conn.close();
    }
    
    // Excluir Usuário
    public void excluir(Investidor user) throws SQLException{
        String sql = "delete from users where cpf = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, user.getCpf());
        statement.execute();
        conn.close();
    }
    
}