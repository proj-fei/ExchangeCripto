package model;

public class Investidor {
    private int id;
    private String cpf;
    private String name;
    private String password; 
    private int isAdmin; // 0 = False | 1 = True
    private Wallet wallet;

    public Investidor(int id, String cpf, String name, String password, int isAdmin) {
        this.id = id;
        this.cpf = cpf;
        this.name = name;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    public Investidor(int id, String cpf, String name, String password, int isAdmin, Wallet wallet) {
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

    public int getIsAdmin() {
        return isAdmin;
    }

    public Wallet getWallet() {
        return wallet;
    }
}
