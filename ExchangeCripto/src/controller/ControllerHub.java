package controller;

import DAO.Conexao;
import DAO.UserDao;
import java.awt.Color;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.table.DefaultTableModel;
import model.Investidor;
import model.Wallet;
import view.ExtratoFrame;
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
        switch (num) {
            case 1:
                FormFrame ff = new FormFrame(title, subTitle, btnText, user, view);
                ff.setVisible(true);
                break;
            case 2:
                FormCriptoFrame fcf = new FormCriptoFrame(title, subTitle, btnText, user, view);
                fcf.setVisible(true);
                break;
            default:
                boolean canOpen = this.authPanel();
                if (canOpen) {
                    ExtratoFrame ef = new ExtratoFrame(wallet);
                    ef.setVisible(canOpen);
                }
                break;
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
    
    
    public boolean authPanel() {
        JPasswordField passwordField = new JPasswordField(10);
        JPanel panel = new JPanel();
        panel.add(passwordField);
        
        int option = JOptionPane.showConfirmDialog(
                null,
                panel,
                "Digite sua senha:",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );
        
        
        if (option == JOptionPane.OK_OPTION) {
            Conexao conexao = new Conexao();
            String senha = new String(passwordField.getPassword());
            
            try {
            Connection conn = conexao.getConnection();
            UserDao dao = new UserDao(conn);
            Investidor temp = dao.authUser(
                user.getCpf(), 
                senha
            );
            
            if (temp == null){
                JOptionPane.showMessageDialog(
                    view, 
                    "Senha inválida.",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
                );
                return false;
            }
           
            return true;

            } catch(SQLException e) {
                JOptionPane.showMessageDialog(
                    view, 
                    "Erro de Conexão",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
                );
                return false;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Operação Cancelada.");
        }
        
        return false;
    }
}
