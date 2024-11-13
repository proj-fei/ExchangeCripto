package controller;

import java.util.ArrayList;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import model.Wallet;
import view.ExtratoFrame;

/**
 *
 * @author art5m
 */
public class ControllerExtrato {
    private ExtratoFrame view;
    private Wallet wallet;

    public ControllerExtrato(ExtratoFrame view, Wallet wallet) {
        this.view = view;
        this.wallet = wallet;
    }
    
    public void updateTable() {
        DefaultTableModel model = (DefaultTableModel) view.getjTable().getModel();
        model.setRowCount(0);
        
        ArrayList<Object[]> historico = wallet.getExtrato().getHistorico();
        
        for (Object [] linha : historico){
            model.addRow(linha);
        }
        
        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);
        
        for (int i = 0; i < view.getjTable().getColumnCount(); i++) {
            view.getjTable()
                .getColumnModel()
                .getColumn(i)
                .setCellRenderer(centralizado);
        }
    }
    
}
