package dao.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import exceptions.EmployerException;
import exceptions.NoTaskFoundException;
import db.DBManager;
import entities.Task;
import dao.ITask;
import utils.SqlTable;

public class TaskDaoImpl implements ITask<Task> {

    SqlTable SQLTable = new SqlTable("tasks", new String[] {"id", "userId", "hours", "date"});

    public TaskDaoImpl() {
        
    }

    @Override
    public Task get(Long id) throws EmployerException {
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
            throw new EmployerException("Error al recuperar el usuario.");
        }
        return null;
    }

    @Override
    public List<Task> getAll() throws EmployerException {
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
            throw new EmployerException("Error al recuperar los usuarios");
		} finally {
			try {
				connection.close();
			} catch (SQLException e1) {
                throw new EmployerException("Error al terminar la conexión");
             }
        }
        return tasks;
    }

    // Should I throw exception in those methods too?
    @Override
    public Task insert(Task t) throws EmployerException {
		Connection connection = DBManager.getInstance().connect();

        try {
            PreparedStatement ps = null;
            ps = connection.prepareStatement( SQLTable.buildInsert() );
            // ps.setLong(SQLTable.getFieldIndex("id"), t.getId() );
            ps.setLong( SQLTable.getFieldIndex("userId"), t.getUserId() );
            ps.setFloat( SQLTable.getFieldIndex("hours"), t.getHours() );
            ps.setString( SQLTable.getFieldIndex("date"), t.getDate() );
			int affectedRows = ps.executeUpdate();
            connection.commit();

            if (affectedRows == 0) {
                throw new EmployerException("Error al crear la tarea.");
            }
            PreparedStatement s = connection.prepareStatement("CALL IDENTITY()");
            ResultSet rs = s.executeQuery();
            if(rs.next()) {
                t.setId( rs.getLong( SQLTable.getFieldIndex("id") ) );
            }
            else {
                throw new EmployerException("Error al crear la tarea, no se pudo obtener el ID.");
            }
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
        return t;
    }
    
    @Override
    public int update(Task t) throws EmployerException {
        Connection connection = DBManager.getInstance().connect();
        int affectedRows = 0;
		try {
            PreparedStatement ps = null;
            ps = connection.prepareStatement( String.format("%s WHERE id = %d", SQLTable.buildUpdate(), t.getId()) );
            ps.setLong(SQLTable.getFieldIndex("id"), t.getId());
            ps.setLong(SQLTable.getFieldIndex("userId"), t.getUserId());
            ps.setFloat( SQLTable.getFieldIndex("hours"), t.getHours());
            ps.setString( SQLTable.getFieldIndex("date"), t.getDate());
			affectedRows = ps.executeUpdate();
            connection.commit();
            if (affectedRows == 0) {
                throw new EmployerException("Error al actualizar el usuario.");
            }
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
        return affectedRows;
    }

    public Task save(Task t) throws EmployerException {
        System.out.println("save()... ");

        try {
            this.getTaskByUserId(t.getId());
            System.out.println("Update... ");
            this.update(t);
        } catch (NoTaskFoundException e) {
            System.out.println("Insert... ");
            t = this.insert(t);
        }
        return t;
    }

    @Override
    public int delete(Task t) {
        Connection connection = DBManager.getInstance().connect();
        int affectedRows = 0;
		try {
            PreparedStatement ps = connection.prepareStatement(String.format("DELETE FROM %s WHERE id = %d", SQLTable.getTableName(), t.getId()));
			affectedRows = ps.executeUpdate();
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
        return affectedRows;
    }

    @Override
    public List<Task> getTaskByUserId(Long userId) throws EmployerException {
        List<Task> tasks = new ArrayList<>();
        
		Connection connection = DBManager.getInstance().connect();
		try {
            PreparedStatement ps = null;
            ps = connection.prepareStatement( SQLTable.buildSelect("*", String.format("userId = %d",userId) ) );
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
                Task task = new Task( rs.getLong("id"), rs.getLong("userId"), rs.getFloat("hours"), rs.getString("date") );
                tasks.add(task);
            }
            if(tasks.size() <= 0 ) {
                throw new NoTaskFoundException("No se encontró ninguna tarea para el usuario.");
            }
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
                // rollback falló
            }
            throw new EmployerException("Error al recuperar las tareas");
		} finally {
			try {
				connection.close();
			} catch (SQLException e1) {
                throw new EmployerException("Error al terminar la conexión");
             }
        }
        return tasks;
    }

    @Override
    public int deleteByUserId(Long userId) throws EmployerException {
        Connection connection = DBManager.getInstance().connect();
        int affectedRows = 0;
		try {
            PreparedStatement ps = connection.prepareStatement(String.format("DELETE FROM %s WHERE userId = %d", SQLTable.getTableName(), userId));
			affectedRows = ps.executeUpdate();
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
        return affectedRows;
    }

    @Override
    public int deleteAll() throws EmployerException {
        Connection connection = DBManager.getInstance().connect();
        int affectedRows = 0;
		try {
            PreparedStatement ps = connection.prepareStatement(String.format("DELETE FROM %s", SQLTable.getTableName()));
			affectedRows = ps.executeUpdate();
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
        return affectedRows;
    }

    @Override
    public List<Task> getTasksByDate(String date) throws EmployerException {
        List<Task> tasks = new ArrayList<>();
        
		Connection connection = DBManager.getInstance().connect();
		try {
            PreparedStatement ps = null;
            ps = connection.prepareStatement( SQLTable.buildSelect("*", "date = ?") );
            ps.setString(1, date);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
                Task task = new Task( rs.getLong("id"), rs.getLong("userId"), rs.getFloat("hours"), rs.getString("date") );
                tasks.add(task);
            }
		} catch (SQLException e) {
            e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
                e1.printStackTrace();
            }
            throw new EmployerException("Error al recuperar las tareas");
		} finally {
			try {
				connection.close();
			} catch (SQLException e1) {
                e1.printStackTrace();
                throw new EmployerException("Error al terminar la conexión");
             }
        }
        return tasks;
    }
     
 
}