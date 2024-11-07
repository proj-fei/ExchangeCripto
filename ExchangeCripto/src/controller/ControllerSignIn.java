package controller;

import DAO.Conexao;
import DAO.UserDao;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import view.LoginFrame;
import view.SignInFrame;

public class ControllerSignIn {
    private SignInFrame view;
    private LoginFrame loginView;

    public ControllerSignIn(SignInFrame view, LoginFrame loginView) {
        this.view = view;
        this.loginView = loginView;
    }
    
    public void registerUser() {
        Conexao conexao = new Conexao();
        
        String cpf = view.getjTxtCpf().getText();
        String name = view.getjTxtName().getText();
        String password = view.getjTxtPassword().getText();
        
        if (cpf.isEmpty() || name.isEmpty() || password.isEmpty() || cpf.length() > 11) {
            JOptionPane.showMessageDialog(
                view, 
                "Preencha todos os campos corretamente!",
                "Erro",
                JOptionPane.ERROR_MESSAGE
            );
        }
        
        try {
            Connection conn = conexao.getConnection();
            UserDao dao = new UserDao(conn);
            dao.createUser(cpf, name, password, 0);
            
            this.cleanFields();
            
            JOptionPane.showMessageDialog(
                view, 
                "Usuário Criado com Sucesso!",
                "Erro",
                JOptionPane.INFORMATION_MESSAGE
            );
            this.goToLogIn();
            
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
    
    public void goToLogIn(){
       this.cleanFields();
       view.setVisible(false);
       loginView.setVisible(true);  
    }
    
    public void cleanFields(){
        view.getjTxtCpf().setText("");
        view.getjTxtName().setText("");
        view.getjTxtPassword().setText("");
    }
}
