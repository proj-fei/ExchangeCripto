package controller;

import javax.swing.JOptionPane;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import DAO.Conexao;
import DAO.CurrencyDao;
import DAO.ExtratoDao;
import DAO.UserDao;
import DAO.WalletDao;
import java.awt.Choice;
import java.math.RoundingMode;
import model.Cripto;
import model.Moeda;
import model.Investidor;
import model.Wallet;
import view.FormCriptoFrame;
import view.FormFrame;
import view.HubFrame;

public class ControllerForm {
    private FormFrame ffView;
    private FormCriptoFrame ffCriptoView;
    private Choice selector;
    private HubFrame hbView;
    private String function;
    private Investidor user;
    private Wallet wallet;

    public ControllerForm(FormFrame view, String title, Investidor user, HubFrame hbView) {
        this.ffView = view;
        this.function = title;
        this.user = user;
        this.hbView = hbView;
        this.wallet = user.getWallet();
    }

    public ControllerForm(FormCriptoFrame ffCriptoView, String function, Investidor user, HubFrame hbView) {
        this.ffCriptoView = ffCriptoView;
        this.hbView = hbView;
        this.function = function;
        this.user = user;
        this.wallet = user.getWallet();
        
        selector = ffCriptoView.getjSelectCriptos();
        selector.removeAll();
        for(Cripto c : wallet.getCriptos()) {
            selector.add(c.getName());
        }
        selector.add("Bitcoin");
        selector.add("Ethereum");
        selector.add("Ripple");
    }
    
    public void action(){
        switch (function) {
            case "Depósito" -> this.deposito();
            case "Saque" -> this.saque();
            case "Comprar Cripto" -> this.comprarCripto();
            case "Vender Cripto" -> this.venderCripto();
        }
    }
    
    public void deposito(){
        Conexao conexao = new Conexao();
        Connection conn = null;
        
        try {
            // Estabelencedo Conexão
            conn = conexao.getConnection();

            // Validando senha
            UserDao userDao = new UserDao(conn);
            boolean auth = userDao.authPassword(
                ffView.getjPasswordInput().getText(), 
                user.getId()
            );
        
            if (!auth){
                throw new IllegalArgumentException("Senha Inválida!");
            }
            
            // Obetendo e validado valor
            BigDecimal value = new BigDecimal(ffView.getjTxtInput().getText());
            if (value.compareTo(BigDecimal.ZERO) <= 0) {
                throw new IllegalArgumentException("O Valor deve ser positivo.");
            }
            
            // Realizar Depósito
            WalletDao wDao = new WalletDao(conn);
            BigDecimal saldoAtual = wDao.getSaldo(wallet.getId());
            BigDecimal novoSaldo = saldoAtual.add(value);
            wDao.setBalance(wallet.getId(),novoSaldo);
            
            // Atualizando o valor de Saldo
            wallet.setBalance(novoSaldo);
            hbView.getjLabelSaldo().setText("R$ " + novoSaldo.toString());
            
            ExtratoDao exDao = new ExtratoDao(conn);
            exDao.saveTransaction(
                    wallet,
                    0,
                    "+",
                    value,
                    BigDecimal.ZERO,
                    BigDecimal.ZERO
            );
            wallet.getExtrato().updateExtrato();
            
            JOptionPane.showMessageDialog(
                ffView, 
                "Depósito Efetuado!",
                "Aviso",
                JOptionPane.INFORMATION_MESSAGE
            );
            ffView.dispose();
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(
                ffView, 
                "Erro de Conexão: " + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE
            );
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(
                ffView, 
                "Digite um Valor Válido!",
                "Erro",
                JOptionPane.ERROR_MESSAGE
            );
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(
                ffView, 
                e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE
            );
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                ffView, 
                "Digite um Valor Válido!",
                "Erro",
                JOptionPane.ERROR_MESSAGE
            );
        } finally {
            try {
                if (conn != null){         
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ffView.getjPasswordInput().setText("");
            ffView.getjTxtInput().setText("");
        }
    }
    
    public void saque(){
        Conexao conexao = new Conexao();
        Connection conn = null;
        
        try {
            // Estabelencedo Conexão
            conn = conexao.getConnection();

            // Validando senha
            UserDao userDao = new UserDao(conn);
            boolean auth = userDao.authPassword(
           ffView.getjPasswordInput().getText(), 
            user.getId()
            );
        
            if (!auth){
                throw new IllegalArgumentException("Senha Inválida!");
            }
            
            // Obetendo e validado valor
            BigDecimal value = new BigDecimal(ffView.getjTxtInput().getText());
            if (value.compareTo(BigDecimal.ZERO) <= 0) {
                throw new IllegalArgumentException("O Valor deve ser positivo.");
            }
            if (value.compareTo(wallet.getBalance()) >= 0) {
                throw new IllegalArgumentException("O Valor é inválido.");
            }
            
            // Realizar Depósito
            WalletDao wDao = new WalletDao(conn);
            BigDecimal saldoAtual = wDao.getSaldo(wallet.getId());
            BigDecimal novoSaldo = saldoAtual.subtract(value);
            wDao.setBalance(wallet.getId(),novoSaldo);
            
            // Atualizando o valor de Saldo
            wallet.setBalance(novoSaldo);
            
            ExtratoDao exDao = new ExtratoDao(conn);
            exDao.saveTransaction(
                    wallet,
                    0,
                    "-",
                    value,
                    BigDecimal.ZERO,
                    BigDecimal.ZERO
            );
            wallet.getExtrato().updateExtrato();
            
            hbView.getjLabelSaldo().setText("R$ " + novoSaldo.toString());
            JOptionPane.showMessageDialog(
                ffView, 
                "Saque Efetuado!",
                "Aviso",
                JOptionPane.INFORMATION_MESSAGE
            );
            ffView.dispose();
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(
                ffView, 
                "Erro de Conexão",
                "Erro",
                JOptionPane.ERROR_MESSAGE
            );
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(
                ffView, 
                "Digite um Valor Válido!",
                "Erro",
                JOptionPane.ERROR_MESSAGE
            );
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(
                ffView, 
                e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE
            );
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                ffView, 
                "Digite um Valor Válido!",
                "Erro",
                JOptionPane.ERROR_MESSAGE
            );
        } finally {
            try {
                if (conn != null){         
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ffView.getjPasswordInput().setText("");
            ffView.getjTxtInput().setText("");
        }
    }
    
    public void comprarCripto(){
        Conexao conexao = new Conexao();
        Connection conn = null;
        
        try {
            // Estabelencedo Conexão
            conn = conexao.getConnection();
            
            // Validando senha
            UserDao userDao = new UserDao(conn);
            boolean auth = userDao.authPassword(
                ffCriptoView.getjPasswordInput().getText(), 
                user.getId()
            );
        
            if (!auth){
                throw new Exception("Senha Inválida.");
            }

            // Obetendo e validado valor
            BigDecimal value = new BigDecimal(ffCriptoView.getjTxtInput().getText());
            if (value.compareTo(BigDecimal.ZERO) <= 0) {
                throw new IllegalArgumentException("O Valor deve ser positivo.");
            }
            if (value.compareTo(wallet.getBalance()) > 0) {
                throw new IllegalArgumentException("Saldo Insuficiente.");
            }

            
            // Obetendo e validado cripto
            String name = selector.getSelectedItem();
            Moeda cripto = wallet.getCriptoByName(name);
            if (cripto == null) {
                throw new Exception("Cripto Moeda não encontrada.");
            }
            
            // Salvando novo Saldo da Carteira (R$)
            BigDecimal novoSaldo = wallet.getBalance().subtract(value);
            wallet.setBalance(novoSaldo);
       
            // Operação da Cripto
            BigDecimal qtdCripto = cripto
                    .calcularRealToCripto(value)
                    .setScale(6,RoundingMode.HALF_UP);
            BigDecimal taxa = cripto.taxarCompra(qtdCripto);
            BigDecimal valorFinal = qtdCripto
                    .subtract(taxa)
                    .setScale(6,RoundingMode.HALF_UP);
            
            cripto.setBalance(valorFinal.add(cripto.getBalance()));

            // Criado DAO
            CurrencyDao cDao = new CurrencyDao(conn);
            WalletDao wDao = new WalletDao(conn);
            
            // Salvando mudanças no banco de dados
            wDao.setBalance(user.getWallet().getId(),novoSaldo);
            cDao.updateCurrency(
                    wallet.getId(), 
                    cripto.getId(), 
                    cripto.getBalance()
            );

            // Salvando Extrato da Operação
            ExtratoDao exDao = new ExtratoDao(conn);
            exDao.saveTransaction(
                    wallet,
                    cripto.getId(),
                    "+",
                    valorFinal,
                    taxa,
                    cripto.getCotacao()
            );
            wallet.getExtrato().updateExtrato();
            
            // Atualizando Hub Page
            hbView.getCh().populateHomePageData();
            JOptionPane.showMessageDialog(
                ffCriptoView, 
                "Compra de " + cripto.getName() + " Efetuada!",
                "Aviso",
                JOptionPane.INFORMATION_MESSAGE
            );
            // Fechando Janela
            ffCriptoView.dispose();
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(
                ffCriptoView, 
                "Erro de Conexão",
                "Erro",
                JOptionPane.ERROR_MESSAGE
            );
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(
                ffCriptoView, 
                "Digite um Valor Válido!",
                "Erro",
                JOptionPane.ERROR_MESSAGE
            );
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(
                ffCriptoView, 
                e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE
            );
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                ffCriptoView, 
                "Ocorreu um erro inesperado." + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE
            );
        } finally {
            try {
                if (conn != null){         
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ffCriptoView.getjPasswordInput().setText("");
            ffCriptoView.getjTxtInput().setText("");
        }
    }
    
    public void venderCripto(){
        Conexao conexao = new Conexao();
        Connection conn = null;
        
        try {
            // Estabelencedo Conexão
            conn = conexao.getConnection();

            // Validando senha
            UserDao userDao = new UserDao(conn);
            boolean auth = userDao.authPassword(
                ffCriptoView.getjPasswordInput().getText(), 
                user.getId()
            );
        
            if (!auth){
                throw new IllegalArgumentException("Senha Inválida.");
            }
            
            // Obetendo e validado valor
            BigDecimal value = new BigDecimal(ffCriptoView.getjTxtInput().getText());
            if (value.compareTo(BigDecimal.ZERO) <= 0) {
                throw new IllegalArgumentException("O Valor deve ser positivo.");
            }
                        
            // Obetendo e validado cripto
            String name = selector.getSelectedItem();
            Moeda cripto = wallet.getCriptoByName(name);
            if (cripto == null) {
                throw new Exception("Cripto Moeda não encontrada.");
            }
            if (value.compareTo(cripto.getBalance()) >= 0) {
                throw new IllegalArgumentException("O Valor é inválido.");
            }            
           
            // Operação de venda da Cripto
            BigDecimal qtdVendida = cripto.calcularCriptoToReal(value);
            BigDecimal taxa = cripto.taxarVenda(qtdVendida);
            BigDecimal valorFinal = qtdVendida.subtract(taxa);
            wallet.setBalance(valorFinal.add(wallet.getBalance()));

            
            // Salvando novo Saldo da Cripto            
            BigDecimal novoSaldoCripto = cripto.getBalance().subtract(value);
            cripto.setBalance(novoSaldoCripto);
            
            // Criado DAO
            CurrencyDao cDao = new CurrencyDao(conn);
            WalletDao wDao = new WalletDao(conn);
            
            // Salvando mudanças no banco de dados
            wDao.setBalance(user.getWallet().getId(),wallet.getBalance());
            cDao.updateCurrency(
                    wallet.getId(), 
                    cripto.getId(), 
                    cripto.getBalance()
            );

            // Salvando Extrato da Operação
            ExtratoDao exDao = new ExtratoDao(conn);
            exDao.saveTransaction(
                    wallet,
                    cripto.getId(),
                    "-",
                    value,
                    taxa,
                    cripto.getCotacao()
            );
            wallet.getExtrato().updateExtrato();
            
            hbView.getCh().populateHomePageData();                    
            JOptionPane.showMessageDialog(
                ffCriptoView, 
                "Venda de " + cripto.getName() + " Efetuada!",
                "Aviso",
                JOptionPane.INFORMATION_MESSAGE
            );
            ffCriptoView.dispose();
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(
                ffCriptoView, 
                "Erro de Conexão: ",
                "Erro",
                JOptionPane.ERROR_MESSAGE
            );
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(
                ffCriptoView, 
                "Digite um Valor Válido!",
                "Erro",
                JOptionPane.ERROR_MESSAGE
            );
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(
                ffCriptoView, 
                e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE
            );
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                ffCriptoView, 
                "Digite um Valor Válido!",
                "Erro",
                JOptionPane.ERROR_MESSAGE
            );
        } finally {
            try {
                if (conn != null){         
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ffCriptoView.getjPasswordInput().setText("");
            ffCriptoView.getjTxtInput().setText("");
        }
    }
    
}
