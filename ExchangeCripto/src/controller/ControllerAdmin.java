package controller;

import DAO.Conexao;
import DAO.CurrencyDao;
import DAO.UserDao;
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
import view.AboutUsFrame;
import view.AdminHubFrame;
import view.ExtratoFrame;
import view.LoginFrame;
import view.UserFrame;
import controller.ControllerHub;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import model.Pessoa;
import view.NewUserFrame;

public class ControllerAdmin {
    
    private AdminHubFrame view;
    private Administrador adm;
    private int selectedUserIndex = -1;
    private NewUserFrame nuf;

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
       int index = 0;
       for(Investidor i : adm.getUsers()){
            Object[] linha = {
                index++,
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
        
        if(!adm.getUsers().isEmpty()){
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

        }else{
            for (String cripto: criptos){
                saldoMoedas.merge(
                    cripto, 
                    BigDecimal.ZERO,
                    BigDecimal::add
                );
            }
            saldoMoedas.merge(
                "Bitcoin", 
                BigDecimal.ZERO, 
                BigDecimal::add
            );
            saldoMoedas.merge(
                "Ethereum", 
                BigDecimal.ZERO, 
                BigDecimal::add
            );
            saldoMoedas.merge(
                "Ripple", 
                BigDecimal.ZERO, 
                BigDecimal::add
            );
        }
        return saldoMoedas;
        
          
    }
        
    public void logout(){
        LoginFrame lf = new LoginFrame();
        lf.setVisible(true);
        
        view.dispose();
    }
    
    public void aboutUs(){
        AboutUsFrame abf = new AboutUsFrame();
        abf.setVisible(true);
    }
    
    public void conta(){
        UserFrame uf = new UserFrame(this.adm, view);
        uf.setVisible(true);
    }
    
    public boolean authPanel() {
        // Cria o painel e o campo de senha
        JPasswordField passwordField = new JPasswordField(10);
        JPanel panel = new JPanel();
        panel.add(passwordField);
    
        // Exibe o painel de senha
        int option = JOptionPane.showConfirmDialog(
            null,
            panel,
            "Digite sua senha:",
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.PLAIN_MESSAGE
        );
    
        // Verifica se o botão OK foi pressionado
        if (option == JOptionPane.OK_OPTION) {
            // Obtém a senha digitada
            Conexao conexao = new Conexao();
            String senha = new String(passwordField.getPassword());
        
            try{
                Connection conn = conexao.getConnection();
                UserDao dao = new UserDao(conn);
                Pessoa temp = dao.authUser(adm.getCpf(), senha);
            
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
            }catch(SQLException e) {
                JOptionPane.showMessageDialog(
                    view, 
                    "Erro de Conexão",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
                );
                return false;
            }
        }
        return false; // Cancelado
    }
    
    public void createUserScreen(){
        boolean canOpen = authPanel();
        if(canOpen){
            nuf = new NewUserFrame(this);   
            nuf.setVisible(true);
            
        }    
        
    }   
        
    public void createUser(){
        Conexao conexao = new Conexao();
        try{
            Connection conn = conexao.getConnection();
            UserDao udao = new UserDao(conn);
            String cpf = nuf.getjTxtCpf().getText();
            String name = nuf.getjTxtName().getText();
            String password = nuf.getjTxtPassword().getText();
            
            if (cpf.isEmpty() || name.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(
                    nuf, 
                    "Preencha todos os campos!",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
                );
            }
            else if (cpf.length() != 11){
                JOptionPane.showMessageDialog(
                    nuf, 
                    "O CPF só pode ter 11 algarismos! sem caracteres",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
                );
            }
            else if (password.length() < 5){
                JOptionPane.showMessageDialog(
                    nuf, 
                    "A senha precisa ter pelo menos 6 caracteres!",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
                );
            }else{
                udao.createUser(cpf, name, password, 0);
               
                JOptionPane.showMessageDialog(
                    nuf, 
                    "Usuário Criado com Sucesso!",
                    "Erro",
                    JOptionPane.INFORMATION_MESSAGE
                );
                
                nuf.dispose();
                adm.updateUsers();
                updateUsersTable();
            }
        }catch(SQLException e) {
            JOptionPane.showMessageDialog(
                view, 
                "Erro de Conexão",
                "Erro",
                JOptionPane.ERROR_MESSAGE
            );
        }
        
    }
    
    public void deleteUserByIndex(){
        Conexao conexao = new Conexao();
        boolean canOpen = authPanel();
        if(canOpen){
            try{
                Connection conn = conexao.getConnection();
                UserDao udao = new UserDao(conn);
                if (selectedUserIndex != -1){
                    udao.deleteUser(adm.getUsers().get(selectedUserIndex));
                    adm.updateUsers();
                    updateUsersTable();
                }else{
                    JOptionPane.showMessageDialog(
                            view, 
                            "Investidor não selecionado!", 
                            "Erro", 
                            JOptionPane.ERROR_MESSAGE
                    );
                }
                
            }catch(SQLException e) {
                JOptionPane.showMessageDialog(
                    view, 
                    "Erro de Conexão",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
                );
            }
            
        }
    }
    
    public void UserMouseCLicked(java.awt.event.MouseEvent evt){
        int selectedRow = view.getjTableUsers().rowAtPoint(evt.getPoint());
        
        if(selectedRow != -1){
            selectedUserIndex = Integer.parseInt(
                    view.getjTableUsers()
                            .getValueAt(selectedRow, 0)
                            .toString()
            );
            
        }else{
            System.out.println("Nenhum linha foi clicada.");
            selectedUserIndex = -1;
        }
    }
    
    public void getExtratoByIndex(){
        if (selectedUserIndex != -1){
            Investidor user = adm.getUsers().get(selectedUserIndex);
            
            ExtratoFrame extratoUI = new ExtratoFrame(user.getWallet());
            extratoUI.setVisible(true);
        }else{
            JOptionPane.showMessageDialog(
                    view, 
                    "Investidor não selecionado!", 
                    "Erro", 
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
    
}
