package main.java.models.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.java.exceptions.MyException;
import main.java.managers.DBManager;
import main.java.models.Transportist;
import main.java.models.idao.IEmployee;
import main.java.utils.SqlTable;

public class TransportistDaoImpl implements IEmployee<Transportist>{

    SqlTable SQLTable = new SqlTable("transportists", new String[] {"userId", "licensePlate"});

    public TransportistDaoImpl() {

    }

    @Override
    public Transportist get(long id) throws MyException {
        Connection c = DBManager.getInstance().connect();

        try {
            PreparedStatement ps = null;
            ps = c.prepareStatement( SQLTable.buildSelect("*", String.format("id = %d", id)) );
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                return new Transportist( rs.getInt("userId"), rs.getString("licensePlate") );
            }
        } catch (SQLException ex) {
            throw new MyException("Error al recuperar el usuario.");
        }
        return null;
    }

    @Override
    public List<Transportist> getAll() throws MyException {
        List<Transportist> transportists = new ArrayList<>();
        
		Connection c = DBManager.getInstance().connect();
		try {
            PreparedStatement ps = null;
            ps = c.prepareStatement( SQLTable.buildSelect("*") );
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
                Transportist transportist = new Transportist( rs.getInt("userId"), rs.getString("licensePlate") );
                transportists.add(transportist);
            }
            return transportists;
		} catch (SQLException e) {
			try {
				c.rollback();
			} catch (SQLException e1) {
                // rollback falló
            }
            throw new MyException("Error al recuperar los usuarios");
		} finally {
			try {
				c.close();
			} catch (SQLException e1) {
                throw new MyException("Error al terminar la conexión");
             }
        }
    }

    @Override
    public boolean insert(Transportist t) {
		Connection c = DBManager.getInstance().connect();
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
        Connection c = DBManager.getInstance().connect();
        int res = 0;
		try {
            PreparedStatement ps = null;
            ps = c.prepareStatement( String.format("%s WHERE userId = %d", SQLTable.buildUpdate(), t.getUserId()) );
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
        Connection c = DBManager.getInstance().connect();
        int res = 0;
		try {
            PreparedStatement ps = c.prepareStatement(String.format("DELETE FROM %s WHERE userId = %d", SQLTable.getTableName(), t.getUserId()));
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