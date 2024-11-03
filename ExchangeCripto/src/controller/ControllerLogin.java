package controller;

import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import DAO.Conexao;
import DAO.UserDao;
import model.User;
import view.LoginFrame;
import view.SignInFrame;
import view.HubFrame;


public class ControllerLogin {
    private LoginFrame view;
    private SignInFrame signInView;
    private HubFrame hubView;
    
    public ControllerLogin(LoginFrame view, SignInFrame signInView, HubFrame hubView) {
        this.view = view;
        this.signInView = signInView;
        this.hubView = hubView;
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
                this.cleanFields();
                JOptionPane.showMessageDialog(
                    view, 
                    "Usuário não encontrado!",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
                );
                
                return null;
            }
            this.cleanFields();
            JOptionPane.showMessageDialog(
                view, 
                "Login Efetuado!",
                "Aviso",
                JOptionPane.INFORMATION_MESSAGE
            );
            
            return user;
        
        } catch(SQLException e) {
            this.cleanFields();
            JOptionPane.showMessageDialog(
                view, 
                "Erro de Conexão",
                "Erro",
                JOptionPane.ERROR_MESSAGE
            );
        }
       
        return null;
    }
    
    public void goToSignIn(){
       this.cleanFields();
       view.setVisible(false);
       signInView.setVisible(true);
       
    }
    
    public void cleanFields(){
        view.getjTxtCpf().setText("");
        view.getjTxtPassword().setText("");
    }
}
