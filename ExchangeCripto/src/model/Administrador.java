package model;

import DAO.Conexao;
import DAO.UserDao;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Administrador extends Pessoa{
    
    private ArrayList<Investidor> users;

    public Administrador(int id, String name, String cpf, String password, ArrayList<Investidor> users) {
        super(id, name, cpf, password);
        this.users = users;
    }

    public ArrayList<Investidor> getUsers() {
        return users;
    } 
    
    public void updateUsers(){
        Conexao conexao = new Conexao();
        try{
            Connection conn = conexao.getConnection();
            UserDao udao = new UserDao(conn);
            users = udao.getInvestidores();
            
                
            }catch(SQLException e) {
                JOptionPane.showMessageDialog(
                    null, 
                    "Erro de Conexão",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
                );
            }
    }
            
}
