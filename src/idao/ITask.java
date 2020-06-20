package idao;

import java.util.List;

import exceptions.EmployerException;
import entities.Task;

public interface ITask<T> extends IDao<T> {

    List<Task> getTasksByDate(String date) throws EmployerException;

    List<Task> getTaskByUserId(Long userId) throws EmployerException;
    int deleteByUserId(Long userId) throws EmployerException;
    int deleteAll() throws EmployerException;
}