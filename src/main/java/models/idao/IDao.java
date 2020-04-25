package main.java.models.idao;

import java.util.List;

import main.java.exceptions.DaoException;

// Generic DAO class, used for many entities
public interface IDao<T> {
     
    T get(Long id) throws DaoException;
     
    List<T> getAll() throws DaoException;
     
    T insert(T t) throws DaoException;
    int update(T t) throws DaoException;
    int delete(T t) throws DaoException;
}