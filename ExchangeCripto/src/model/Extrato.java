package model;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import DAO.Conexao;
import DAO.ExtratoDao;
import java.util.Collections;

public class Extrato {
    private Wallet wallet;
    private ArrayList<Object[]> historico = new ArrayList<>();
    
    public Extrato(Wallet wallet) {
        this.wallet = wallet;
        this.historico = null;
    }
    
    public void updateExtrato() throws SQLException {
        Conexao conexao = new Conexao();
        Connection conn = conexao.getConnection();
        ExtratoDao eDao = new ExtratoDao(conn);
        
        ArrayList<Object[]> extrato = eDao.getExtrato(wallet.getId());
        Collections.reverse(extrato);
        
        this.historico = extrato;
    }

    public ArrayList<Object[]> getHistorico() {
        return historico;
    }
}
