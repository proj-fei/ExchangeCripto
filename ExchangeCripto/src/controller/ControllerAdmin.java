package controller;

import DAO.Conexao;
import DAO.CurrencyDao;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import model.Administrador;
import model.Investidor;
import model.Moeda;
import view.AdminHubFrame;

public class ControllerAdmin {
    
    private AdminHubFrame view;
    private Administrador adm;

    public ControllerAdmin(AdminHubFrame view, Administrador adm) {
        this.view = view;
        this.adm = adm;
        this.updateUsersTable();
        this.updateMoedasTable();
    }
    
    public void updateUsersTable(){
       DefaultTableModel model = (DefaultTableModel) view
               .getjTableUsers()
               .getModel();
       model.setRowCount(0);
       ArrayList<Object[]> users = new ArrayList<>();
       for(Investidor i : adm.getUsers()){
            Object[] linha = { 
                i.getCpf(),
                i.getName(),
                i.getWallet().getBalance(),
                i.getWallet().getBTCBalance(),
                i.getWallet().getETHBalance(),
                i.getWallet().getXRPBalance()
            };
            model.addRow(linha);
       }
       
       DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
       centralizado.setHorizontalAlignment(SwingConstants.CENTER);
       for(int i = 0; i < view.getjTableUsers().getColumnCount(); i++){
           view.getjTableUsers()
                   .getColumnModel()
                   .getColumn(i)
                   .setCellRenderer(centralizado);
       }
    }
    
    public void updateMoedasTable(){
        Conexao conexao = new Conexao();
        ArrayList<Moeda> moedas = null;
        
        try{
            
            Connection conn = conexao.getConnection();
            CurrencyDao cDao = new CurrencyDao(conn);
            moedas = cDao.getCurrency();
        
        }catch(SQLException e) {
            JOptionPane.showMessageDialog(
                view, 
                "Erro de Conexão" + e,
                "Erro",
                JOptionPane.ERROR_MESSAGE
            );
        }
        DefaultTableModel model = (DefaultTableModel) view
               .getjTableMoedas()
               .getModel();
        model.setRowCount(0);
        
        ArrayList<String> criptoNames = new ArrayList<>();
        for(Moeda mo: moedas){
            if(!mo.getName().equalsIgnoreCase("Bitcoin") && 
                    !mo.getName().equalsIgnoreCase("Ethereum") && 
                    !mo.getName().equalsIgnoreCase("Ripple")){
                criptoNames.add(mo.getName());
            }
        }
        Map<String, BigDecimal> ball = this.getQuantidadeComprada(criptoNames);
        ArrayList<Object[]> littleMoedas = new ArrayList<>();
        for(Moeda m : moedas){
            Object[] linha = {
                m.getName(),
                m.getAcronym(),
                m.getCotacao(),
                String.format("%,2f%%", m.getTaxVenda()),
                String.format("%,2f%%", m.getTaxCompra()),
                ball.get(m.getName()).setScale(5,BigDecimal.ROUND_HALF_UP) 
                    + " " + m.getAcronym()
            };
            model.addRow(linha);
        }
        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);
        for(int i = 0; i < view.getjTableMoedas().getColumnCount(); i++){
            view.getjTableMoedas()
                   .getColumnModel()
                   .getColumn(i)
                   .setCellRenderer(centralizado);
        }
    }
    
    public Map<String, BigDecimal> getQuantidadeComprada(ArrayList<String> criptos){
        Map<String, BigDecimal> saldoMoedas = new HashMap<>();
        
        for (Investidor i : adm.getUsers()) {
            for (String cripto: criptos){
                saldoMoedas.merge(
                        cripto, 
                        i.getWallet().getCriptoByName(cripto).getBalance(), 
                        BigDecimal::add
                );
            }
            saldoMoedas.merge(
                    "Bitcoin", 
                    i.getWallet().getBTCBalance(), 
                    BigDecimal::add
            );
            saldoMoedas.merge(
                    "Ethereum", 
                    i.getWallet().getETHBalance(), 
                    BigDecimal::add
            );
            saldoMoedas.merge(
                    "Ripple", 
                    i.getWallet().getXRPBalance(), 
                    BigDecimal::add
            );
        }

        return saldoMoedas;  
    }
}
