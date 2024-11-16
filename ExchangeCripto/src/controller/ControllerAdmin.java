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
import model.BuilderCripto;
import model.Pessoa;
import view.NewMoedaFrame;
import view.NewUserFrame;

public class ControllerAdmin {
    
    private AdminHubFrame view;
    private Administrador adm;
    private int selectedUserIndex = -1;
    private int selectedCoinIndex = -1;
    private NewUserFrame nuf;
    private NewMoedaFrame nmf;

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
                "Erro de Conexão",
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
        int index = 0;
        for(Moeda m : moedas){
            Object[] linha = {
                index++,
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
    
    public void UserMouseCLickedCoin(java.awt.event.MouseEvent evt){
        int selectedRow = view.getjTableMoedas().rowAtPoint(evt.getPoint());
        
        if(selectedRow != -1){
            selectedCoinIndex = Integer.parseInt(
                    view.getjTableMoedas()
                            .getValueAt(selectedRow, 0)
                            .toString()
            );
            
        }else{
            System.out.println("Nenhum linha foi clicada.");
            selectedCoinIndex = -1;
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
    
    public void updateMoedaScreen(){
        boolean canOpen = authPanel();
        if(canOpen){
            nmf = new NewMoedaFrame(this, "Atualizar Moeda");
            Moeda moeda = adm.getCoins().get(selectedCoinIndex);
            nmf.getjTxtCoinName().setText(moeda.getName());
            nmf.getjTxtCoinSigla().setText(moeda.getAcronym());
            nmf.getjTxtCoinCotacao().setText(moeda.getCotacao().toString());
            nmf.getjTxtCoinTxV().setText(String.format("%.2f", moeda.getTaxVenda()).replace(",", "."));
            nmf.getjTxtCoinTxC().setText(String.format("%.2f", moeda.getTaxCompra()).replace(",", "."));
            
            adm.updateMoedas();
            nmf.setVisible(true);
            
            
        }
    }
    
    public void updateMoeda() {
        Conexao conexao = new Conexao();
        try {
            Connection conn = conexao.getConnection();
            CurrencyDao cd = new CurrencyDao(conn);
            String name = nmf.getjTxtCoinName().getText();
            String acronym = nmf.getjTxtCoinSigla().getText();
            String quote = nmf.getjTxtCoinCotacao().getText();
            String taxaV = nmf.getjTxtCoinTxV().getText().replace(",", "."); // Substitui vírgula por ponto
            String taxaC = nmf.getjTxtCoinTxC().getText().replace(",", "."); // Substitui vírgula por ponto
            double taxC = Double.parseDouble(taxaC);
            double taxV = Double.parseDouble(taxaV);

            BigDecimal quoteBig = new BigDecimal(quote.replace(",", "."));
            if (name.isEmpty() || acronym.isEmpty() || quote.isEmpty() || taxaV.isEmpty() || taxaC.isEmpty()) {
                JOptionPane.showMessageDialog(
                    nmf, 
                    "Preencha todos os campos!",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
                );
            } else if (adm.hasCoinNameUpdate(name, selectedCoinIndex)) {
                JOptionPane.showMessageDialog(
                    nmf, 
                    "Já existe moeda com esse nome!",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
                );
            } else {
                BuilderCripto bc = new BuilderCripto();
                bc.build(adm.getCoins().get(selectedCoinIndex).getId(), name, acronym, quoteBig, taxC, taxV);
                Moeda moeda = bc.getResultado();
                cd.updateCurrencyByIndex(moeda);
                adm.updateMoedas();
                updateMoedasTable();
                for (Investidor i : adm.getUsers()) {
                    i.updateWallet();
                }

                JOptionPane.showMessageDialog(
                    nmf, 
                    "Moeda Criada com Sucesso!",
                    "Erro",
                    JOptionPane.INFORMATION_MESSAGE
                );

                nmf.dispose();
                adm.updateMoedas();
                updateMoedasTable();
            }
        }catch (SQLException e) {
            JOptionPane.showMessageDialog(
                view, 
                "Erro de Conexão" + e,
                "Erro",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }
    
    public void createMoedaScreen(){
        boolean canOpen = authPanel();
        if(canOpen){
            nmf = new NewMoedaFrame(this, "Nova Moeda");   
            nmf.setVisible(true);
            
        }
    }
    
    public void createMoeda(){
        Conexao conexao = new Conexao();
        try{
            Connection conn = conexao.getConnection();
            CurrencyDao cd = new CurrencyDao(conn);
            String name = nmf.getjTxtCoinName().getText();
            String acronym = nmf.getjTxtCoinSigla().getText();
            String quote = nmf.getjTxtCoinCotacao().getText();
            String taxV = nmf.getjTxtCoinTxV().getText();
            String taxC = nmf.getjTxtCoinTxC().getText();
            BigDecimal quoteBig = new BigDecimal(quote);
            adm.updateMoedas();
            
            if (name.isEmpty() || acronym.isEmpty() || quote.isEmpty() || taxV.isEmpty() || taxC.isEmpty()) {
                JOptionPane.showMessageDialog(
                    nmf, 
                    "Preencha todos os campos!",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
                );
            }
            else if(adm.hasCoinName(name)){
                JOptionPane.showMessageDialog(
                    nmf, 
                    "Já existe moeda com esse nome!",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
                );
            }
            else{
                
                cd.createCurrency(name, acronym, quoteBig, Integer.parseInt(taxV), Integer.parseInt(taxC));
                for(Investidor i : adm.getUsers()){
                    i.updateWallet();
                }
               
                JOptionPane.showMessageDialog(
                    nmf, 
                    "Moeda Criada com Sucesso!",
                    "Erro",
                    JOptionPane.INFORMATION_MESSAGE
                );
                
                nmf.dispose();
                adm.updateMoedas();
                updateMoedasTable();
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
    
    public void deleteMoeda() {
        // Verifica se algum ID foi selecionado
        if (selectedCoinIndex == -1) {
            JOptionPane.showMessageDialog(
                view,
                "Selecione uma moeda para deletar!",
                "Erro",
                JOptionPane.ERROR_MESSAGE
            );
            return;
        }
        boolean canOpen = authPanel();
        Conexao conexao = new Conexao();
        if(canOpen){
            try {
                Connection conn = conexao.getConnection();
                CurrencyDao cd = new CurrencyDao(conn);

                // Confirmação do usuário
                int confirm = JOptionPane.showConfirmDialog(
                    view,
                    "Tem certeza que deseja deletar a moeda com ID " 
                            + selectedCoinIndex + "?",
                    "Confirmação",
                    JOptionPane.YES_NO_OPTION
                );

                if (confirm == JOptionPane.YES_OPTION) {
                    Moeda moeda = adm.getCoins().get(selectedCoinIndex);
                    if(
                        moeda.getName().equalsIgnoreCase("Bitcoin") 
                        || moeda.getName().equalsIgnoreCase("Ethereum") 
                        || moeda.getName().equalsIgnoreCase("Ripple")
                    ){
                        JOptionPane.showMessageDialog(
                            view,
                            "Não é possivel excluir a moeda " + moeda.getName(),
                            "Erro",
                            JOptionPane.ERROR_MESSAGE
                        );
                    }else{
                        // Chama o DAO para excluir a moeda pelo ID
                        cd.deleteCurrencyById(adm.getCoins().get(selectedCoinIndex).getId()); 

                        JOptionPane.showMessageDialog(
                            view,
                            "Moeda deletada com sucesso!",
                            "Sucesso",
                            JOptionPane.INFORMATION_MESSAGE
                        );

                        // Atualiza a tabela de moedas
                        adm.updateMoedas();
                        updateMoedasTable();

                        // Reseta o índice selecionado
                        selectedCoinIndex = -1;
                    }
                    
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                    view,
                    "Erro ao deletar moeda: " + e.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
                );
            }
        }
        
    }
}
