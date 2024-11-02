package model;

public class User {
    private int id;
    private String cpf;
    private String name;
    private String password; 
    private boolean isAdmin;
    private Wallet wallet;

    public User(int id, String cpf, String name, String password, boolean isAdmin, Wallet wallet) {
        this.id = id;
        this.cpf = cpf;
        this.name = name;
        this.password = password;
        this.isAdmin = isAdmin;
        this.wallet = wallet;
    }

    public int getId() {
        return id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getIsAdmin() {
        return isAdmin;
    }

    public Wallet getWallet() {
        return wallet;
    }
}
