package controller;

import view.LoginFrame;
import view.SignInFrame;

public class ControllerSignIn {
    private SignInFrame view;
    private LoginFrame loginView;

    public ControllerSignIn(SignInFrame view, LoginFrame loginView) {
        this.view = view;
        this.loginView = loginView;
    }
    
    public void goToLogIn(){
       view.setVisible(false);
       loginView.setVisible(true);  
    }
}
