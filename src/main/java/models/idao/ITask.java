package main.java.models.idao;

import main.java.exceptions.DaoException;
import main.java.models.idao.IDao;

public interface ITask<T> extends IDao<T> {

    Float getTaskHoursByUserId() throws DaoException;

}