package idao;

import java.util.List;

import exceptions.EmployerException;

// Generic DAO class, used for many entities
public interface IDao<T> {
     
    T get(Long id) throws EmployerException;
     
    List<T> getAll() throws EmployerException;
     
    T insert(T t) throws EmployerException;
    int update(T t) throws EmployerException;
    int delete(T t) throws EmployerException;
}