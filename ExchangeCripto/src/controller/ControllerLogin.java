package controller;

import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import DAO.Conexao;
import DAO.UserDao;
import model.Administrador;
import model.Investidor;
import model.Pessoa;
import view.AdminHubFrame;
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
            Pessoa user = dao.authUser(
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
            
            view.dispose();
            // if for investidor faz oq esta abaixo else abre uma outr pagina de adm
            if(user instanceof Investidor investidor){
                HubFrame hf = new HubFrame(investidor);
                hf.setVisible(true);
            } else if(user instanceof Administrador adm){
                AdminHubFrame ahf = new AdminHubFrame(adm);
                ahf.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(
                    view, 
                    "Usuário não encontrado!",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
                );
                return;
            }
            
            
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
