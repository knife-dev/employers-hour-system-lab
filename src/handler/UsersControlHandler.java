package handler;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import entities.User;
import exceptions.EmployerException;
import services.UserBO;
import ui.listeners.OnClickListener;
import ui.views.UsersControlView;

/*
    Handler:
        Implements Views & BO.
    Manipulates:
        Views & BO
*/

public class UsersControlHandler extends BaseHandler implements OnClickListener {

    // Services
    UserBO userBO;

    // Views
    UsersControlView usersControlView;

    public UsersControlHandler() {
        userBO = new UserBO();
    }

    public void initApp() {
        usersControlView = new UsersControlView(this); //
        usersControlView.setOnClickListener(this);
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
        }
        return affectedRows > 0;
    }


    @Override
    public void OnClick(ActionEvent event, String actionCommand) {
        // Handle clicks in view
    }
}