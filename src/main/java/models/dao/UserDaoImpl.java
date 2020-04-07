package main.java.models.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.List;
import java.util.Optional;

import main.java.managers.DBManager;
import main.java.models.User;
import main.java.models.idao.IDao;
import main.java.models.idao.IEmployee;
import main.java.utils.SqlTable;

public class UserDaoImpl implements IEmployee<User>{

    SqlTable SQLTable = new SqlTable("users", new String[] {"id", "email", "password", "user_type"});

    public UserDaoImpl() {
        
    }

    @Override
    public Optional<User> get(long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<User> getAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean insert(User t) {
		Connection c = DBManager.connect();
        int res = 0;
		try {
            PreparedStatement ps = null;
            ps = c.prepareStatement( SQLTable.buildInsert() );
            ps.setInt( SQLTable.getFieldIndex("id"), t.getId() );
            ps.setString( SQLTable.getFieldIndex("email"), t.getEmail() );
            ps.setString( SQLTable.getFieldIndex("password"), t.getPassword() );
            ps.setString( SQLTable.getFieldIndex("user_type"), t.getUserType() );
			res = ps.executeUpdate();
            c.commit();
		} catch (SQLException e) {
			try {
				c.rollback();
				e.printStackTrace();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				c.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
        return res == 1 ? true : false;
    }
    
    // TODO: fix SQL update.
    @Override
    public boolean update(User t) {
        Connection c = DBManager.connect();
        int res = 0;
		try {
            PreparedStatement ps = null;
            ps = c.prepareStatement( String.format("%s WHERE id = %d", SQLTable.buildUpdate(), t.getId()) );
            ps.setInt( SQLTable.getFieldIndex("id"), t.getId());
            ps.setString( SQLTable.getFieldIndex("email"), t.getEmail());
            ps.setString( SQLTable.getFieldIndex("password"), t.getPassword());
			res = ps.executeUpdate();
            c.commit();
            return res == 1 ? true : false;
		} catch (SQLException e) {
			try {
				c.rollback();
				e.printStackTrace();
			} catch (SQLException e1) {
				//no hago nada
			}
		} finally {
			try {
				c.close();
			} catch (SQLException e1) {
				//no hago nada
			}
		}
        return res == 1 ? true : false;
    }

    @Override
    public boolean delete(User t) {
        Connection c = DBManager.connect();
        int res = 0;
		try {
            PreparedStatement ps = c.prepareStatement(String.format("DELETE FROM %s WHERE id = %d", SQLTable.getTableName(), t.getId()));
			res = ps.executeUpdate();
            c.commit();
		} catch (SQLException e) {
			try {
				c.rollback();
				e.printStackTrace();
			} catch (SQLException e1) {
				//no hago nada
			}
		} finally {
			try {
				c.close();
			} catch (SQLException e1) {
				//no hago nada
			}
		}
        return res == 1 ? true : false;
    }

    @Override
    public int taskStart() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public boolean taskEnd(int taskId) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public int getCurrentMonthTaskHours() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getCurrentMonthFee() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getFee(String start_date, String end_date) {
        // TODO Auto-generated method stub
        return 0;
    }
     
 
}