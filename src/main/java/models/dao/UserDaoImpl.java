package main.java.models.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.java.exceptions.MyException;
import main.java.managers.DBManager;
import main.java.models.User;
import main.java.models.idao.IEmployee;
import main.java.utils.SqlTable;

public class UserDaoImpl implements IEmployee<User> {

    SqlTable SQLTable = new SqlTable("users", new String[] {"id", "email", "password", "userType"});

    public UserDaoImpl() {
        
    }

    @Override
    public User get(long id) throws MyException {
        Connection connection = DBManager.getInstance().connect();

        try {
            PreparedStatement ps = null;
            ps = connection.prepareStatement( SQLTable.buildSelect("*", String.format("id = %d", id)) );
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                return new User( rs.getInt("id"), rs.getString("email"), rs.getString("password"), rs.getString("userType") );
            }
        } catch (SQLException ex) {
            throw new MyException("Error al recuperar el usuario.");
        }
        return null;
    }

    @Override
    public List<User> getAll() throws MyException {
        List<User> users = new ArrayList<>();
        
		Connection connection = DBManager.getInstance().connect();
		try {
            PreparedStatement ps = null;
            ps = connection.prepareStatement( SQLTable.buildSelect("*") );
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
                User user = new User( rs.getInt("id"), rs.getString("email"), rs.getString("password"), rs.getString("userType") );
                users.add(user);
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
        return users; // return users
    }

    // Should I throw exception in those methods too?
    @Override
    public boolean insert(User t) {
		Connection connection = DBManager.getInstance().connect();
        int res = 0;
		try {
            PreparedStatement ps = null;
            ps = connection.prepareStatement( SQLTable.buildInsert() );
            // ps.setInt( SQLTable.getFieldIndex("id"), t.getId() );
            ps.setString( SQLTable.getFieldIndex("email"), t.getEmail() );
            ps.setString( SQLTable.getFieldIndex("password"), t.getPassword() );
            ps.setString( SQLTable.getFieldIndex("userType"), t.getUserType() );
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
    public boolean update(User t) {
        Connection connection = DBManager.getInstance().connect();
        int res = 0;
		try {
            PreparedStatement ps = null;
            ps = connection.prepareStatement( String.format("%s WHERE id = %d", SQLTable.buildUpdate(), t.getId()) );
            ps.setInt( SQLTable.getFieldIndex("id"), t.getId());
            ps.setString( SQLTable.getFieldIndex("email"), t.getEmail());
            ps.setString( SQLTable.getFieldIndex("password"), t.getPassword());
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
    public boolean delete(User t) {
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

    @Override
    public int taskStart() {
        // new Task()...
        return 0;
    }

    @Override
    public boolean taskEnd(int taskId) {
        // new Task(taskId)...
        return false;
    }

    @Override
    public int getCurrentMonthTaskHours() {
        // All Tasks Hours count...
        return 0;
    }

    @Override
    public int getCurrentMonthFee() {
        // Current month fee
        return 0;
    }

    // TODO: Should I remove this?
    @Override
    public int getFee(String start_date, String end_date) {
        // Month feee by date..
        return 0;
    }
     
 
}