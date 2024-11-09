package controller;

import javax.swing.JOptionPane;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import DAO.Conexao;
import DAO.CurrencyDao;
import DAO.UserDao;
import DAO.WalletDao;
import java.awt.Choice;
import model.Coin;
import model.User;
import view.FormCriptoFrame;
import view.FormFrame;
import view.HubFrame;

public class ControllerForm {
    private FormFrame ffView;
    private FormCriptoFrame ffCriptoView;
    private Choice selector;
    private HubFrame hbView;
    private String function;
    private User user;

    public ControllerForm(FormFrame view, String title, User user, HubFrame hbView) {
        this.ffView = view;
        this.function = title;
        this.user = user;
        this.hbView = hbView;
    }

    public ControllerForm(FormCriptoFrame ffCriptoView, String function, User user, HubFrame hbView) {
        this.ffCriptoView = ffCriptoView;
        this.hbView = hbView;
        this.function = function;
        this.user = user;
        
        selector = ffCriptoView.getjSelectCriptos();
        selector.removeAll();
        for(Coin coin : user.getWallet().getCriptos()) {
            selector.add(coin.getName());
        }
    }
    
    public void action(){
        switch (function) {
            case "Depósito":
                this.deposito();
                break;
            case "Saque":
                this.saque();
                break;
            case "Comprar Cripto":
                this.comprarCripto();
            case "Vender Cripto":
                this.venderCripto();
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
            BigDecimal saldoAtual = wDao.getSaldo(user.getWallet().getId());
            BigDecimal novoSaldo = saldoAtual.add(value);
            wDao.setBalance(user.getWallet().getId(),novoSaldo);
            
            // Atualizando o valor de Saldo
            user.getWallet().setBalance(novoSaldo);
            hbView.getjLabelSaldo().setText("R$ " + novoSaldo.toString());
            JOptionPane.showMessageDialog(
                ffView, 
                "Depósito Efetuado!",
                "Aviso",
                JOptionPane.INFORMATION_MESSAGE
            );
            ffView.setVisible(false);
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(
                ffView, 
                "Erro de Conexão: ",
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
                "Digite um Valor Válido!" + e.getMessage(),
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
            if (value.compareTo(user.getWallet().getBalance()) >= 0) {
                throw new IllegalArgumentException("O Valor é inválido.");
            }
            
            // Realizar Depósito
            WalletDao wDao = new WalletDao(conn);
            BigDecimal saldoAtual = wDao.getSaldo(user.getWallet().getId());
            BigDecimal novoSaldo = saldoAtual.subtract(value);
            wDao.setBalance(user.getWallet().getId(),novoSaldo);
            
            // Atualizando o valor de Saldo
            user.getWallet().setBalance(novoSaldo);
            hbView.getjLabelSaldo().setText("R$ " + novoSaldo.toString());
            JOptionPane.showMessageDialog(
                ffView, 
                "Saque Efetuado!",
                "Aviso",
                JOptionPane.INFORMATION_MESSAGE
            );
            ffView.setVisible(false);
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(
                ffView, 
                "Erro de Conexão: ",
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
                throw new IllegalArgumentException("Senha Inválida.");
            }
            
            // Obetendo e validado valor
            BigDecimal value = new BigDecimal(ffCriptoView.getjTxtInput().getText());
            if (value.compareTo(BigDecimal.ZERO) <= 0) {
                throw new IllegalArgumentException("O Valor deve ser positivo.");
            }
            if (value.compareTo(user.getWallet().getBalance()) >= 0) {
                throw new IllegalArgumentException("O Valor é inválido.");
            }
            
            // Obetendo e validado cripto
            int indexCripto = selector.getSelectedIndex();
            Coin cripto = user.getWallet().getCriptos().get(indexCripto);
            if (cripto == null) {
                throw new Exception("Cripto Moeda não encontrada.");
            }
            
            
            // Salvar Saldo de Cripto
            CurrencyDao cDao = new CurrencyDao(conn);
            BigDecimal qtdComprada = value.divide(cripto.getQuotation(), 6, BigDecimal.ROUND_HALF_UP);
            BigDecimal criptoSaldoAtual = cDao.getCurrencyBalance(user.getWallet().getId(), cripto.getId());
            BigDecimal novoCriptoSaldo = criptoSaldoAtual.add(qtdComprada);
            BigDecimal taxa = novoCriptoSaldo.multiply(BigDecimal.valueOf(cripto.getTaxCompra())).divide(BigDecimal.valueOf(100));
            BigDecimal valorFinal = novoCriptoSaldo.subtract(taxa);
            cripto.setBalance(valorFinal);
            
            cDao.updateCurrency(
                user.getWallet().getId(),
              cripto.getId(), 
                  valorFinal
            );
            
            
            // Realizar Alteração de Saldo
            WalletDao wDao = new WalletDao(conn);
            BigDecimal saldoAtual = wDao.getSaldo(user.getWallet().getId());
            BigDecimal novoSaldo = saldoAtual.subtract(value);
            wDao.setBalance(user.getWallet().getId(),novoSaldo);
            
            // Atualizando o valor de Saldo
            user.getWallet().setBalance(novoSaldo);
            hbView.getCh().populateHomePageData();
                    
            JOptionPane.showMessageDialog(
                ffCriptoView, 
                "Compra de " + cripto.getName() + " Efetuada!",
                "Aviso",
                JOptionPane.INFORMATION_MESSAGE
            );
            ffCriptoView.setVisible(false);
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
            int indexCripto = selector.getSelectedIndex();
            Coin cripto = user.getWallet().getCriptos().get(indexCripto);
            if (cripto == null) {
                throw new Exception("Cripto Moeda não encontrada.");
            }
            if (value.compareTo(cripto.getBalance()) >= 0) {
                throw new IllegalArgumentException("O Valor é inválido.");
            }
            
            CurrencyDao cDao = new CurrencyDao(conn);
            BigDecimal qtdVendida = value.multiply(cripto.getQuotation());
            BigDecimal criptoSaldoAtual = cDao.getCurrencyBalance(user.getWallet().getId(), cripto.getId());
            BigDecimal novoCriptoSaldo = criptoSaldoAtual.subtract(qtdVendida);
            BigDecimal taxa = qtdVendida.multiply(BigDecimal.valueOf(cripto.getTaxVenda())).divide(BigDecimal.valueOf(100));
            BigDecimal valorFinal = qtdVendida.subtract(taxa);
            cripto.setBalance(novoCriptoSaldo);
            
            cDao.updateCurrency(
                user.getWallet().getId(),
              cripto.getId(), 
                  novoCriptoSaldo
            );
            
            
            // Realizar Alteração de Saldo
            WalletDao wDao = new WalletDao(conn);
            BigDecimal saldoAtual = wDao.getSaldo(user.getWallet().getId());
            BigDecimal novoSaldo = saldoAtual.subtract(valorFinal);
            wDao.setBalance(user.getWallet().getId(),novoSaldo);
            
            // Atualizando o valor de Saldo
            user.getWallet().setBalance(novoSaldo);
            hbView.getCh().populateHomePageData();
                    
            JOptionPane.showMessageDialog(
                ffCriptoView, 
                "Venda de " + cripto.getName() + " Efetuada!",
                "Aviso",
                JOptionPane.INFORMATION_MESSAGE
            );
            ffCriptoView.setVisible(false);
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
