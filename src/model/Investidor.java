package model;
import java.util.ArrayList;

public class Investidor extends User {
    private Carteira carteira; 

    public Investidor(Carteira carteira, String name, String cpf, String password) {
        super(name, cpf, password);
        this.carteira = carteira;
    }
    
 
    


    
    
}
