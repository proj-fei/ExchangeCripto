package model;

import DAO.Conexao;
import DAO.WalletDao;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Investidor extends Pessoa{
    
    private Wallet wallet;

    public Investidor(int id, String name, String cpf, String password, Wallet wallet) {
        super(id, name, cpf, password);
        this.wallet = wallet;
    }

    public Wallet getWallet() {
        return wallet;
    }
    
    public void updateWallet(){
        Conexao conexao = new Conexao();
        
        try{
            
            Connection conn = conexao.getConnection();
            WalletDao wDao = new WalletDao(conn);
            this.wallet = wDao.getUserWallet(super.getId());
        
        }catch(SQLException e) {
            JOptionPane.showMessageDialog(
                null, 
                "Erro de Conexão" + e,
                "Erro",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }
}
