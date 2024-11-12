package model;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Extrato {
    private Wallet wallet;
    private ArrayList<Object[]> historico = new ArrayList<>();
    
    public Extrato(Wallet wallet) {
        this.wallet = wallet;
    }

    public ArrayList<Object[]> getHistorico() {
        return historico;
    }
    
    public void addTransaction(
            int currencyId,
            BigDecimal value,
            BigDecimal tax,
            BigDecimal quotation,
            String type,
            String data
     ) {

    }
    
    
}
