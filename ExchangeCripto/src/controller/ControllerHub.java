package controller;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import model.Investidor;
import model.Wallet;
import view.FormCriptoFrame;
import view.HubFrame;
import view.FormFrame;

public class ControllerHub {
    private HubFrame view;
    private Investidor user;
    private Wallet wallet;

    public ControllerHub(HubFrame view, Investidor user) {
        this.view = view;
        this.user = user;
        this.wallet = user.getWallet();
        
        this.populateHomePageData();
    }
    
    public final void populateHomePageData(){
        view.getjLabelWelcome().setText("bem-vindo(a) " + user.getName() + "!");
        view.getjLabelCPF().setText(user.getCpf());
        
        // Definição do saldo
        view.getjLabelSaldo().setText("R$ " + user.getWallet().getBalance());
        view.getjLabelBTCsaldo().setText(wallet.getBTCBalance() + " BTC");
        view.getjLabelETHsaldo().setText(wallet.getETHBalance() + " ETH");
        view.getjLabelXRPsaldo().setText(wallet.getXRPBalance() + " XRP");
        
        // Definição das cotação
        view.getjLabelBTCquote().setText("Cotação: " + wallet.getBTCQuote());
        view.getjLabelETHquote().setText("Cotação: " + wallet.getETHQuote());
        view.getjLabelXRPquote().setText("Cotação: " + wallet.getXRPQuote());
        
        this.updateCriptoTable();
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
    
    public void updateCriptoTable() {
        DefaultTableModel modelo = (DefaultTableModel) view.getjTableCriptos().getModel();
        modelo.setRowCount(0);
        
        ArrayList<Object[]> moedas = wallet.getCriptoData();
        
        for (Object[] linha : moedas) {
            modelo.addRow(linha);
        }
    }
}
