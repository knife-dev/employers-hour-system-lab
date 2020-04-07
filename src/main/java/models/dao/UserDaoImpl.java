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

public class UserDaoImpl implements IDao<User>{

    private static final String table = "users";
    private static final String [] tableFields = {"id", "email", "password"/*, "name"*/};

    public static Integer getFieldIndex(String field) {
        for (int i = 0; i < tableFields.length; i++) {
            if(tableFields[i].equals(field)) return i;
        }
        return null;
    }

    public static String[] getTableFields() {
        return tableFields;
    }

    public static String getTableFieldsString() {
        return String.join(",", tableFields);
    }

    public static String getTableSafeFieldsString() {
        String[] fields = tableFields;
        for (int i = 0; i < fields.length; i++) {
            fields[i] = "?";
        }
        return String.join(",", fields);
    }

    public static String getTableSafeUpdateFieldsString() {
        String[] fields = tableFields;
        for (int i = 0; i < fields.length; i++) {
            fields[i] = String.format("%s = ?", fields[i]);
        }
        return String.join(",", fields);
    }

    public static String getTable() {
        return table;
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
            ps = c.prepareStatement(String.format("INSERT INTO %s (%s) values (%s)", getTable(), getTableFieldsString(), getTableSafeFieldsString()));
            ps.setInt( getFieldIndex("id"), t.getId());
            ps.setString( getFieldIndex("email"), t.getEmail());
            ps.setString( getFieldIndex("password"), t.getPassword());
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
            ps = c.prepareStatement(String.format("UPDATE %s SET %s WHERE id = %d", getTable(), getTableSafeUpdateFieldsString()));
            ps.setInt( getFieldIndex("id"), t.getId());
            ps.setString( getFieldIndex("email"), t.getEmail());
            ps.setString( getFieldIndex("password"), t.getPassword());
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
            PreparedStatement ps = c.prepareStatement(String.format("DELETE FROM %s WHERE id = %d", getTable(), t.getId()));
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
     
 
}