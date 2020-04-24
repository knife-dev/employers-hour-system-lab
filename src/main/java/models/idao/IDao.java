package main.java.models.idao;

import java.util.List;
import java.util.Optional;

import main.java.exceptions.MyException;

// Generic DAO class, used for many entities
public interface IDao<T> {
     
    T get(long id) throws MyException;
     
    List<T> getAll() throws MyException;
     
    boolean insert(T t);
    boolean update(T t);
    boolean delete(T t);
}