package model;

public class User {
    private String name, cpf, password;

    public User(String name, String cpf, String password) {
        this.name = name;
        this.cpf = cpf;
        this.password = password;
    }
    
    public void login(){
         // Login
    }
    
    public void logout(){
        // Logout
    }

    public String getName() {
        return name;
    }
    
}
