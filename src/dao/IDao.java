package dao;

import java.util.List;

import exceptions.EmployerException;

// Generic DAO class, used for many entities
public interface IDao<T> {
     
    T get(Long id) throws EmployerException;
     
    List<T> getAll() throws EmployerException;
     
    T insert(T t) throws EmployerException;
    int update(T t) throws EmployerException;
    int delete(T t) throws EmployerException;

    /*
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

    **
     * Modified user in DB
     * @param user
     * @return int - affectedRows
     * @throws EmployerException
     *
    public int modifyUser(User user) throws EmployerException {
        return daoImpl.update(user);
    }

    public int deleteUser(User user) throws EmployerException {
        return daoImpl.delete(user);
    }

    public User authUser(String email, String password) throws EmployerException {
        return daoImpl.authenticate(email, password);
    }
    */
}