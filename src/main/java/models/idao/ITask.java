package main.java.models.idao;

import java.util.List;

import main.java.exceptions.DaoException;
import main.java.models.Task;
import main.java.models.idao.IDao;

public interface ITask<T> extends IDao<T> {

    List<Task> getTaskByUserId(Long userId) throws DaoException;
    int deleteByUserId(Long userId) throws DaoException;
    int deleteAll() throws DaoException;
}