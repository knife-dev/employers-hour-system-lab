package main.java.models.dao;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {
     
    Optional<T> get(long id);
     
    List<T> getAll();
     
    boolean insert(T t);
    boolean update(T t);
    boolean delete(T t);
}