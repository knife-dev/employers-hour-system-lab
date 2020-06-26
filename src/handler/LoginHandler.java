package handler;

import java.awt.event.ActionEvent;

import entities.User;
import exceptions.EmployerException;
import services.UserBO;
import ui.helpers.KDialog;
import ui.helpers.KDialog.dialogType;
import ui.views.LoginFrame;

/*
    Handler:
        Implements Views & BO.
    Manipulates:
        Views & BO
*/

public class LoginHandler {

    // Services
    UserBO userBO;

    // Views
    LoginFrame loginView;

    // Handlers
    MainHandler mainHandler;

    public LoginHandler() {
        userBO = new UserBO();
        mainHandler = new MainHandler();
    }

    public void initApp() {
        loginView = new LoginFrame(this); //
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
            new KDialog("Login Error", e.getMessage(), dialogType.ERROR);
            e.printStackTrace();
        }
        if(user != null) {
            mainHandler.initApp();
            loginView.dispose();
            return true;
        }
        return false;
    }
}