package dao;

import entities.User;
import exceptions.EmployerException;

public interface IUser extends IDao<User> {
    User authenticate(String email, String password) throws EmployerException;

    User getByEmail(String email) throws EmployerException;

}