package handler;

import java.util.ArrayList;
import java.util.List;

import dao.daoimpl.UserDaoImpl;
import entities.User;
import exceptions.EmployerException;
import services.UserBO;
import ui.helpers.KDialog;
import ui.helpers.KDialog.dialogType;
import ui.views.MainFrame;
import ui.views.UserControlPanel;


/*
    Handler:
        Implements Views & BO.
    Manipulates:
        Views & BO
*/

// ok creo que si entendi XD
// 

public class MainHandler {

    // DAO Implementations
    private UserDaoImpl userDaoImpl;

    // Services
    private UserBO userBO;

    // Frame
    private MainFrame frame;

    // Views
    private UserControlPanel userControlPanel;

    public MainHandler() {

        // initialize DAO implementations
        userDaoImpl      = new UserDaoImpl();

        // initialize BOs
        userBO           = new UserBO(userDaoImpl);

        // initialize Panels
        userControlPanel = new UserControlPanel(this);

        // initialize Frame
        frame = new MainFrame(this);
    }

    public void exitApp() {
        System.exit(0);
    }

    public void initApp() {
        frame.onViewCreated();
    }

    public void showUserControlPanel() {
        frame.switchPanel(userControlPanel);
    }
    
    /**
     * Gets  All users from the DB
     * @return List<User>
     */
    public List<User> loadUsers() {
        List<User> users = new ArrayList<>();
        try {
            users = userBO.getAllUsers();
        } catch (EmployerException e) {
            // TODO add error message dialog
            e.printStackTrace();
            new KDialog("Error", e.getMessage(), dialogType.ERROR);
        }
        return users;
    }

    /**
     * Adds a user to the db and returns it with the id filled up
     * @param user
     * @return
     */
    public User createUser(User user) {
        try {
            user = userBO.createUser(user);
            
        } catch (EmployerException e) {
            // TODO add error message dialog
            e.printStackTrace();
            new KDialog("Error", e.getMessage(), dialogType.ERROR);
        }
        return user;
    }


    /**
     * Modifies a user in the DB
     * @param user
     * @return true if successed
     */
    public boolean modifyUser(User user) {
        int affectedRows = 0;
        try {
            affectedRows = userBO.modifyUser(user);
        } catch (EmployerException e) {
            // TODO add error message dialog
            e.printStackTrace();
            new KDialog("Error", e.getMessage(), dialogType.ERROR);
        }
        return affectedRows > 0;
    }

    /**
     * Deletes a user from the DB
     * @param user
     * @return true if successed
     */
    public boolean deleteUser(User user) {
        int affectedRows = 0;
        try {
            affectedRows = userBO.deleteUser(user);
        } catch (EmployerException e) {
            // TODO add error message dialog
            e.printStackTrace();
            new KDialog("Error", e.getMessage(), dialogType.ERROR);
        }
        return affectedRows > 0;
    }
    
}