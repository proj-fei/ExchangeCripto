package model;

public class Investidor extends Pessoa{
    
    private Wallet wallet;

    public Investidor(int id, String name, String cpf, String password, Wallet wallet) {
        super(id, name, cpf, password);
        this.wallet = wallet;
    }

    public Wallet getWallet() {
        return wallet;
    }
}
