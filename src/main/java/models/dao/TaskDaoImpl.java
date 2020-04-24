package main.java.models.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.java.exceptions.MyException;
import main.java.managers.DBManager;
import main.java.models.Task;
import main.java.models.idao.ITask;
import main.java.utils.SqlTable;

public class TaskDaoImpl implements ITask<Task> {

    SqlTable SQLTable = new SqlTable("tasks", new String[] {"id", "userId", "hours"});

    public TaskDaoImpl() {
        
    }

    @Override
    public Task get(Long id) throws MyException {
        Connection connection = DBManager.getInstance().connect();

        try {
            PreparedStatement ps = null;
            ps = connection.prepareStatement( SQLTable.buildSelect("*", String.format("id = %d", id)) );
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                return new Task( rs.getLong("id"), rs.getLong("userId"), rs.getFloat("hours"), rs.getString("date") );
            }
        } catch (SQLException ex) {
            throw new MyException("Error al recuperar el usuario.");
        }
        return null;
    }

    @Override
    public List<Task> getAll() throws MyException {
        List<Task> tasks = new ArrayList<>();
        
		Connection connection = DBManager.getInstance().connect();
		try {
            PreparedStatement ps = null;
            ps = connection.prepareStatement( SQLTable.buildSelect("*") );
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
                Task task = new Task( rs.getLong("id"), rs.getLong("userId"), rs.getFloat("hours"), rs.getString("date") );
                tasks.add(task);
            }
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
                // rollback falló
            }
            throw new MyException("Error al recuperar los usuarios");
		} finally {
			try {
				connection.close();
			} catch (SQLException e1) {
                throw new MyException("Error al terminar la conexión");
             }
        }
        return tasks; // return tasks
    }

    // Should I throw exception in those methods too?
    @Override
    public boolean insert(Task t) {
		Connection connection = DBManager.getInstance().connect();
        int res = 0;
		try {
            PreparedStatement ps = null;
            ps = connection.prepareStatement( SQLTable.buildInsert() );
            // ps.setLong(SQLTable.getFieldIndex("id"), t.getId() );
            ps.setLong( SQLTable.getFieldIndex("userId"), t.getUserId() );
            ps.setFloat( SQLTable.getFieldIndex("hours"), t.getHours() );
            ps.setString( SQLTable.getFieldIndex("date"), t.getDate() );
			res = ps.executeUpdate();
            connection.commit();

            // set userId if possible and return User "t"

		} catch (SQLException e) {
			try {
				connection.rollback();
				// e.printStackTrace();
			} catch (SQLException e1) {
				// e1.printStackTrace();
			}
		} finally {
			try {
				connection.close();
			} catch (SQLException e1) {
				// e1.printStackTrace();
			}
		}
        return res == 1 ? true : false;
    }
    
    @Override
    public boolean update(Task t) {
        Connection connection = DBManager.getInstance().connect();
        int res = 0;
		try {
            PreparedStatement ps = null;
            ps = connection.prepareStatement( String.format("%s WHERE id = %d", SQLTable.buildUpdate(), t.getId()) );
            ps.setLong(SQLTable.getFieldIndex("id"), t.getId());
            ps.setLong(SQLTable.getFieldIndex("userId"), t.getUserId());
            ps.setFloat( SQLTable.getFieldIndex("hours"), t.getHours());
            ps.setString( SQLTable.getFieldIndex("date"), t.getDate());
			res = ps.executeUpdate();
            connection.commit();
            return res == 1 ? true : false;
		} catch (SQLException e) {
			try {
				connection.rollback();
				e.printStackTrace();
			} catch (SQLException e1) {
				//no hago nada
			}
		} finally {
			try {
				connection.close();
			} catch (SQLException e1) {
				//no hago nada
			}
		}
        return res == 1 ? true : false;
    }

    @Override
    public boolean delete(Task t) {
        Connection connection = DBManager.getInstance().connect();
        int res = 0;
		try {
            PreparedStatement ps = connection.prepareStatement(String.format("DELETE FROM %s WHERE id = %d", SQLTable.getTableName(), t.getId()));
			res = ps.executeUpdate();
            connection.commit();
		} catch (SQLException e) {
			try {
				connection.rollback();
				e.printStackTrace();
			} catch (SQLException e1) {
				//no hago nada
			}
		} finally {
			try {
				connection.close();
			} catch (SQLException e1) {
				//no hago nada
			}
		}
        return res == 1 ? true : false;
    }
     
 
}