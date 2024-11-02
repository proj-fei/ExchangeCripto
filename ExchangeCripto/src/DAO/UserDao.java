package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import model.User;

public class UserDao {
    private Connection conn;

    public UserDao(Connection conn) {
        this.conn = conn;
    }
    
    // Criar Novo Usuário
    public void createUser(User user) throws SQLException{
        String sql = "INSERT INTO users (name, cpf, password, isadmin) values (?,?,?,?)";
        PreparedStatement statement = conn.prepareStatement(sql);
        
        statement.setString(1, user.getName());
        statement.setString(2, user.getCpf());
        statement.setString(3, user.getPassword());
        statement.setBoolean(4, user.getIsAdmin());
        statement.execute();
        
        conn.close();
    }
    
    // Autenticação de Usuário para login
    public ResultSet authUser(User user) throws SQLException {
        String sql = "SELECT * FROM users WHERE cpf = ? AND password = ? ";
        PreparedStatement statement = conn.prepareStatement(sql);
        
        statement.setString(1, user.getCpf());
        statement.setString(2, user.getPassword());
        statement.execute();
        
        ResultSet res = statement.getResultSet();
        
        return res;
    }
    
    // Recuperar Wallet do Usuário do banco de dados
    public ResultSet getUserWallet(User user) throws SQLException {
        String sql = "select * from wallets where userid = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        
        statement.setInt(1, user.getId());
        statement.execute();
        
        ResultSet res = statement.getResultSet();
        
        return res;
    }
    
    // Atualizar Usuário ( Em especifico sua senha )
    public void updateUser(User user) throws SQLException {
        String sql = "update users set password = ? where cpf = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, user.getPassword());
        statement.setString(2, user.getCpf());
        statement.execute();
        conn.close();
    }
    
    // Excluir Usuário
    public void excluir(User user) throws SQLException{
        String sql = "delete from users where cpf = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, user.getCpf());
        statement.execute();
        conn.close();
    }
    
}