package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import model.User;
import model.Wallet;
import DAO.WalletDao;

public class UserDao {
    private Connection conn;

    public UserDao(Connection conn) {
        this.conn = conn;
    }
    
    // Criar Novo Usuário
    public void createUser(String cpf, String name, String password, int isAdmin) throws SQLException{
        String sql = "INSERT INTO users (name, cpf, password, isadmin) values (?,?,?,?)";
        PreparedStatement statement = conn.prepareStatement(sql);
        
        statement.setString(1, name);
        statement.setString(2, cpf);
        statement.setString(3, password);
        statement.setInt(4, isAdmin);
        statement.execute();
        
        conn.close();
    }
    
    // Autenticação de Usuário para login
    public User authUser(String cpf, String password) throws SQLException {
        String sql = "SELECT * FROM users WHERE cpf = ? AND password = ? ";
        PreparedStatement statement = conn.prepareStatement(sql);
        
        statement.setString(1, cpf);
        statement.setString(2, password);
        
        ResultSet res = statement.executeQuery();

        if (!res.next()) {
            return null;
        }
        
        // Cria e retorna o objeto User com os dados recuperados
        if(!res.getBoolean("isadmin")){
            WalletDao wDao = new WalletDao(this.conn);
            Wallet wallet = wDao.getUserWallet(res.getInt("id"));
            
            User user = new User(
                res.getInt("id"),
                res.getString("cpf"),
                res.getString("name"),
                res.getString("password"),
                res.getInt("isadmin"),
                wallet
            );
            return user;
        }
        User user = new User(
            res.getInt("id"),
            res.getString("cpf"),
            res.getString("name"),
            res.getString("password"),
            res.getInt("isadmin")
        );
        
        return user;
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