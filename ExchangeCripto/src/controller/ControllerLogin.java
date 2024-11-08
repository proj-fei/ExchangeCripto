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
    
    public ControllerLogin(LoginFrame view, SignInFrame signInView) {
        this.view = view;
        this.signInView = signInView;
    }
    
    public void login(){
        Conexao conexao = new Conexao();
        
        try {
            Connection conn = conexao.getConnection();
            UserDao dao = new UserDao(conn);
            User user = dao.authUser(
                view.getjTxtCpf().getText(), 
                view.getjTxtPassword().getText()
            );
            
            this.cleanFields();
            if (user == null){
                JOptionPane.showMessageDialog(
                    view, 
                    "Usuário não encontrado!",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
                );
                return; 
            }
            
            JOptionPane.showMessageDialog(
                view, 
                "Login Efetuado!",
                "Aviso",
                JOptionPane.INFORMATION_MESSAGE
            );
            
            view.setVisible(false);
            HubFrame hf = new HubFrame(user);
            hf.setVisible(true);
            
        } catch(SQLException e) {
            this.cleanFields();
            JOptionPane.showMessageDialog(
                view, 
                "Erro de Conexão",
                "Erro",
                JOptionPane.ERROR_MESSAGE
            );
        }
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
