package controller;

import model.Administrador;
import model.Investidor;
import model.Pessoa;
import view.AdminHubFrame;
import view.HubFrame;
import view.LoginFrame;
import view.UserFrame;

public class ControllerUser {
    private UserFrame view;
    private Pessoa pessoa;
    private AdminHubFrame viewAdmin;
    private HubFrame viewInvest;
    public ControllerUser(UserFrame view, Pessoa pessoa, HubFrame viewInvest) {
       
        this.view = view;
        this.pessoa = pessoa;
        this.viewInvest = viewInvest;
        
        this.populatePage();
    }
    
    public ControllerUser(UserFrame view, Pessoa pessoa, AdminHubFrame viewAdmin) {
       
        this.view = view;
        this.pessoa = pessoa;
        this.viewAdmin = viewAdmin;
        
        this.populatePage();
    }
    
    public void populatePage() {
        view.getjLabelName().setText(pessoa.getName());
        view.getjLabelCPF().setText("CPF: " + pessoa.getCpf());
        
        if (pessoa instanceof Investidor investidor) {
            view.getjLabelConta().setText("Investidor");
            view.getjLabelEspecial().setText("Saldo: R$ " + investidor.getWallet().getBalance());
        }
        else if(pessoa instanceof Administrador adm){
            view.getjLabelConta().setText("Administrador");
            view.getjLabelEspecial().setText("Investidores: " + adm.getUsers().size());
        }
    }
    
    public void logout(){
        LoginFrame lf = new LoginFrame();
        lf.setVisible(true);
        
        view.dispose();
        if (pessoa instanceof Investidor) {
            viewInvest.dispose();
        }
        else if(pessoa instanceof Administrador){
            viewAdmin.dispose();
        }
        
    }
    
    
    
}
