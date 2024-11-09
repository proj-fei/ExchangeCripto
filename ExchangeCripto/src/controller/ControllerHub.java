package controller;

import model.User;
import view.FormCriptoFrame;
import view.HubFrame;
import view.FormFrame;

public class ControllerHub {
    private HubFrame view;
    private User user;

    public ControllerHub(HubFrame view, User user) {
        this.view = view;
        this.user = user;
        
        this.populateHomePageData();
    }
    
    public void populateHomePageData(){
        view.getjLabelWelcome().setText("bem-vindo(a) " + user.getName() + "!");
        view.getjLabelCPF().setText(user.getCpf());
        
        // Definição do saldo
        view.getjLabelSaldo().setText("R$ " + user.getWallet().getBalance());
        view.getjLabelBTCsaldo().setText(user.getWallet().getBTCBalance() + " BTC");
        view.getjLabelETHsaldo().setText(user.getWallet().getETHBalance() + " ETH");
        view.getjLabelXRPsaldo().setText(user.getWallet().getXRPBalance() + " XRP");
        
        // Definição das cotação
        view.getjLabelBTCquote().setText("Cotação: " + user.getWallet().getBTCQuote());
        view.getjLabelETHquote().setText("Cotação: " + user.getWallet().getETHQuote());
        view.getjLabelXRPquote().setText("Cotação: " + user.getWallet().getXRPQuote());
    }
    
    public void callAction(int num,String title, String subTitle, String btnText){
        if (num == 1){
            FormFrame ff = new FormFrame(title, subTitle, btnText, user, view);
            ff.setVisible(true);
        }
        else {
            FormCriptoFrame fcf = new FormCriptoFrame(title, subTitle, btnText, user, view);
            fcf.setVisible(true);
        }
        
        this.populateHomePageData();
    }
}
