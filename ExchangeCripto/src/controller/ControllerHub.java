package controller;

import DAO.Conexao;
import DAO.UserDao;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import model.Cripto;
import model.Investidor;
import model.Pessoa;
import model.Wallet;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import view.AboutUsFrame;
import view.ExtratoFrame;
import view.FormCriptoFrame;
import view.HubFrame;
import view.FormFrame;
import view.LoginFrame;
import view.UserFrame;

public class ControllerHub {
    private HubFrame view;
    private Investidor user;
    private Wallet wallet;

    public ControllerHub(HubFrame view, Investidor user) {
        this.view = view;
        this.user = user;
        this.wallet = user.getWallet();
        
        wallet.updateQuote();
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
        this.pieChart();
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
        
        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);
        
        for (int i = 0; i < view.getjTableCriptos().getColumnCount(); i++) {
            view.getjTableCriptos()
                .getColumnModel()
                .getColumn(i)
                .setCellRenderer(centralizado);
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
            Pessoa temp = dao.authUser(
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
    
    public void updateQuote(){
        wallet.updateQuote();
        view.getjLabelBTCquote().setText("Cotação: " + wallet.getBTCQuote());
        view.getjLabelETHquote().setText("Cotação: " + wallet.getETHQuote());
        view.getjLabelXRPquote().setText("Cotação: " + wallet.getXRPQuote());
        
        this.updateCriptoTable();
    }
    
    public void pieChart() {
       DefaultPieDataset dataset = new DefaultPieDataset();
       
       
       dataset.setValue("BTC", wallet
               .getBitcoin()
               .calcularCriptoToReal(wallet.getBTCBalance())
       );
       
       dataset.setValue("ETH", wallet
               .getEthereum()
               .calcularCriptoToReal(wallet.getETHBalance())
       );
       
       dataset.setValue("XRP", wallet
               .getRipple()
               .calcularCriptoToReal(wallet.getXRPBalance())
       );
       
       for(Cripto c : wallet.getCriptos()){
           dataset.setValue(
                   c.getAcronym(),
                   c.calcularCriptoToReal(c.getBalance())
           );
       }
       
       JFreeChart pieChart = ChartFactory.createPieChart(
               "Valor em Cripto Moedas",      // Título
               dataset,                            // Dados
               true,
               true,
               false
       );
       
       ChartPanel chartPanel = new ChartPanel(pieChart);
       chartPanel.setMouseWheelEnabled(true);
       chartPanel.setPreferredSize(new Dimension(400,250));
       chartPanel.setSize(new Dimension(400, 250));
       chartPanel.setBackground(Color.WHITE);
       chartPanel.setForeground(Color.WHITE);
       view.getjPanelChart().setPreferredSize(new Dimension(400, 250));
       view.getjPanelChart().removeAll();
       view.getjPanelChart().add(chartPanel, BorderLayout.CENTER);
       view.getjPanelChart().revalidate();
       view.getjPanelChart().repaint();
       view.pack();

    }
    
    public void aboutUs(){
        AboutUsFrame abf = new AboutUsFrame();
        abf.setVisible(true);
    }
    
    public void conta(){
        UserFrame uf = new UserFrame(this.user, view);
        uf.setVisible(true);
    }
    
    public void logout(){
        LoginFrame lf = new LoginFrame();
        lf.setVisible(true);
        
        view.dispose();
    }
}
