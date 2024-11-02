package controller;

import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import DAO.Conexao;
import DAO.UserDao;
import model.User;
import view.LoginFrame;


public class ControllerLogin {
    private LoginFrame view;

    public ControllerLogin(LoginFrame view) {
        this.view = view;
    }
    
    public User login(){
        Conexao conexao = new Conexao();
        
        try {
            Connection conn = conexao.getConnection();
            UserDao dao = new UserDao(conn);
            User user = dao.authUser(
                view.getjTxtCpf().getText(), 
                view.getjTxtPassword().getText()
            );
            
            if (user == null){
                JOptionPane.showMessageDialog(
                    view, 
                    "Usuário não encontrado!",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
                );
                return null;
            }
            
            JOptionPane.showMessageDialog(
                view, 
                "Login Efetuado!",
                "Aviso",
                JOptionPane.INFORMATION_MESSAGE
            );
            
            return user;
        
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(
                view, 
                e,
                "Erro",
                JOptionPane.ERROR_MESSAGE
            );
        }
        
        return null;
    }
}
