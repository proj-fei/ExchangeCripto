package model;

import java.util.ArrayList;

public class Administrador extends Pessoa{
    
    private ArrayList<Investidor> users;

    public Administrador(int id, String name, String cpf, String password, ArrayList<Investidor> users) {
        super(id, name, cpf, password);
        this.users = users;
    }
    
    
}
