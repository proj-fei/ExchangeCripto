package model;

import DAO.Conexao;
import DAO.CurrencyDao;
import DAO.UserDao;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Currency;
import javax.swing.JOptionPane;

public class Administrador extends Pessoa{
    
    private ArrayList<Investidor> users;
    private ArrayList<Moeda> coins;

    public Administrador(int id, String name, String cpf, String password, ArrayList<Investidor> users) {
        super(id, name, cpf, password);
        this.users = users;
        updateMoedas();
    }

    public ArrayList<Investidor> getUsers() {
        return users;
    } 
    
    public void updateUsers(){
        Conexao conexao = new Conexao();
        try{
            Connection conn = conexao.getConnection();
            UserDao udao = new UserDao(conn);
            users = udao.getInvestidores();
            
                
            }catch(SQLException e) {
                JOptionPane.showMessageDialog(
                    null, 
                    "Erro de Conexão",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
                );
            }
    }
    
    public void updateMoedas(){
        Conexao conexao = new Conexao();
        try{
            Connection conn = conexao.getConnection();
            CurrencyDao cd = new CurrencyDao(conn);
            coins = cd.getCurrency();
        }catch(SQLException e) {
            JOptionPane.showMessageDialog(
                null, 
                "Erro de Conexão",
                "Erro",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    public ArrayList<Moeda> getCoins() {
        return coins;
    }
    
    public boolean hasCoinName(String name){
        for(Moeda m: coins){
            if(m.getName().equalsIgnoreCase(name)){
                return true;
            }
        }
        return false;
    }
    
    public boolean hasCoinNameUpdate(String name, int index){
        for(int i = 0; i < coins.size(); i++){
            if(name.equalsIgnoreCase(coins.get(i).getName()) && i != index){
                return true;
            }
        }
        return false;
    }
            
}
