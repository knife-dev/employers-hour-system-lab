package main.java.models.idao;

import main.java.models.idao.IDao;

// What the employee does, not how does it.
public interface IEmployee<T> extends IDao<T> {

    // These are class-dependant per employee. 
    public abstract int taskStart(); // record the start of a task at current time for this employee.
    public abstract boolean taskEnd(int taskId); // record the end of a task for this employee.

    // These are most non-dependant per employee. 
    public abstract int getCurrentMonthTaskHours(); // returns all tasks hours in current mont for this employee.
    public abstract int getCurrentMonthFee(); // returns current tasks fee for this employee.
    public abstract int getFee(String start_date, String end_date); // returns current tasks fee for this employee.

}