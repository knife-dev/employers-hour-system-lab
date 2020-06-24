package handler;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import entities.User;
import exceptions.EmployerException;
import services.UserBO;
import ui.helpers.KMessageDialog;
import ui.listeners.OnClickListener;
import ui.views.LoginView;
import ui.views.UsersControlView;

/*
    Handler:
        Implements Views & BO.
    Manipulates:
        Views & BO
*/

public class LoginHandler extends BaseHandler implements OnClickListener {

    // Services
    UserBO userBO;

    // Views
    LoginView loginView;

    // Handlers
    MainHandler mainHandler;

    public LoginHandler() {
        userBO = new UserBO();
        mainHandler = new MainHandler();
    }

    public void initApp() {
        loginView = new LoginView(this); //
        loginView.setOnClickListener(this);
    }


    /**
     * Authenticates the user
     * @return true if successed
     */
    public boolean authenticate(String email, String password) {
        User user = null;
        try {
            user = userBO.authUser(email, password);
        } catch (EmployerException e) {
            new KMessageDialog("Login Error", e.getMessage());
            e.printStackTrace();
        }
        return user != null;
    }


    @Override
    public void OnClick(ActionEvent event, String actionCommand) {
        // Handle clicks in view
        if(actionCommand.equals(LoginView.LOGIN_COMMAND)) {
            if(loginView.performLogin()) {
                mainHandler.initApp();
                loginView.dispose();
            }
        }
    }
}