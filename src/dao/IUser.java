package dao;

import exceptions.EmployerException;

public interface IUser<User> extends IDao<User> {
    User authenticate(String email, String password) throws EmployerException;

}