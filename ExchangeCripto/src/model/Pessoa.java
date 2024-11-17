package model;

public class Pessoa {
    
    private int id;
    private String name;
    private String cpf;
    private String password;

    public Pessoa() {
    }

    public Pessoa(int id, String name, String cpf, String password) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCpf() {
        return cpf;
    }

    public String getPassword() {
        return password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
}
