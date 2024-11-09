package controller;

import javax.swing.JOptionPane;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import DAO.Conexao;
import DAO.UserDao;
import DAO.WalletDao;
import model.User;
import view.FormFrame;
import view.HubFrame;

public class FormController {
    private FormFrame view;
    private String function;
    private User user;
    private HubFrame hbView;

    public FormController(FormFrame view, String title, User user, HubFrame hbView) {
        this.view = view;
        this.function = title;
        this.user = user;
        this.hbView = hbView;
    }
    
    public void action(){
        switch (function) {
            case "Depósito":
                this.deposito();
                break;
            case "Saque":
                this.saque();
                break;
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
         view.getjPasswordInput().getText(), 
          user.getId()
            );
        
            if (!auth){
                throw new IllegalArgumentException("Senha Inválida!");
            }
            
            // Obetendo e validado valor
            BigDecimal value = new BigDecimal(view.getjTxtInput().getText());
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
                view, 
                "Depósito Efetuado!",
                "Aviso",
                JOptionPane.INFORMATION_MESSAGE
            );
            view.setVisible(false);
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(
                view, 
                "Erro de Conexão: ",
                "Erro",
                JOptionPane.ERROR_MESSAGE
            );
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(
                view, 
                "Digite um Valor Válido!",
                "Erro",
                JOptionPane.ERROR_MESSAGE
            );
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(
                view, 
                e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE
            );
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                view, 
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
            view.getjPasswordInput().setText("");
            view.getjTxtInput().setText("");
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
         view.getjPasswordInput().getText(), 
          user.getId()
            );
        
            if (!auth){
                throw new IllegalArgumentException("Senha Inválida!");
            }
            
            // Obetendo e validado valor
            BigDecimal value = new BigDecimal(view.getjTxtInput().getText());
            if (value.compareTo(BigDecimal.ZERO) <= 0) {
                throw new IllegalArgumentException("O Valor deve ser positivo.");
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
                view, 
                "Saque Efetuado!",
                "Aviso",
                JOptionPane.INFORMATION_MESSAGE
            );
            view.setVisible(false);
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(
                view, 
                "Erro de Conexão: ",
                "Erro",
                JOptionPane.ERROR_MESSAGE
            );
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(
                view, 
                "Digite um Valor Válido!",
                "Erro",
                JOptionPane.ERROR_MESSAGE
            );
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(
                view, 
                e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE
            );
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                view, 
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
            view.getjPasswordInput().setText("");
            view.getjTxtInput().setText("");
        }
    }
    
    
}
