package main.java.models.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.List;
import java.util.Optional;

import main.java.managers.DBManager;
import main.java.models.Transportist;
import main.java.models.idao.IDao;
import main.java.utils.SqlTable;

public class TransportistDaoImpl implements IDao<Transportist>{

    SqlTable SQLTable = new SqlTable("transportists", new String[] {"userid", "licensePlate"});

    public TransportistDaoImpl() {

    }

    @Override
    public Optional<Transportist> get(long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Transportist> getAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean insert(Transportist t) {
		Connection c = DBManager.connect();
        int res = 0;
		try {
            PreparedStatement ps = null;
            ps = c.prepareStatement(SQLTable.buildInsert());
            ps.setString( SQLTable.getFieldIndex("licensePlate"), t.getLicensePlate());
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
    public boolean update(Transportist t) {
        Connection c = DBManager.connect();
        int res = 0;
		try {
            PreparedStatement ps = null;
            ps = c.prepareStatement( String.format("%s WHERE id = %d", SQLTable.buildUpdate(), t.getUserId()) );
            ps.setString( SQLTable.getFieldIndex("licensePlate"), t.getLicensePlate());
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
    public boolean delete(Transportist t) {
        Connection c = DBManager.connect();
        int res = 0;
		try {
            PreparedStatement ps = c.prepareStatement(String.format("DELETE FROM %s WHERE id = %d", SQLTable.getTableName(), t.getUserId()));
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