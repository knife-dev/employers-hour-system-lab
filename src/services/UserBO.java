package services;

import java.util.List;

import dao.daoimpl.UserDaoImpl;
import entities.User;
import exceptions.EmployerException;

public class UserBO {


    /*
    Services:
        Implentación DAO (con JDBC):
    Manipulates:
        DATA
    */

    private UserDaoImpl daoImpl;
    

    public UserBO() {
        daoImpl = new UserDaoImpl();
    }


    public List<User> getAllUsers() throws EmployerException {
        // Load users
        return daoImpl.getAll();
    }

    public User createUser(User user) throws EmployerException {
        return daoImpl.insert(user);
    }

    /**
     * Modified user in DB
     * @param user
     * @return int - affectedRows
     * @throws EmployerException
     */
    public int modifyUser(User user) throws EmployerException {
        return daoImpl.update(user);
    }

    public int deleteUser(User user) throws EmployerException {
        return daoImpl.delete(user);
    }

    public User authUser(String email, String password) throws EmployerException {
        return daoImpl.authenticate(email, password);
    }
}