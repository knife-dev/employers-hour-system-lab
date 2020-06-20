package dao.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import exceptions.EmployerException;
import exceptions.InvalidUserException;
import db.DBManager;
import entities.Transportist;
import dao.ITransportist;
import utils.SqlTable;

public class TransportistDaoImpl implements ITransportist<Transportist>{

    SqlTable SQLTable = new SqlTable("transportists", new String[] {"userId", "licensePlate"});

    public TransportistDaoImpl() {

    }

    @Override
    public Transportist get(Long id) throws EmployerException {
        Connection c = DBManager.getInstance().connect();

        try {
            PreparedStatement ps = null;
            ps = c.prepareStatement( SQLTable.buildSelect("*", String.format("id = %d", id)) );
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                return new Transportist( rs.getLong("userId"), rs.getString("licensePlate") );
            }
        } catch (SQLException ex) {
            throw new EmployerException("Error al recuperar el transportista.");
        }
        return null;
    }

    @Override
    public List<Transportist> getAll() throws EmployerException {
        List<Transportist> transportists = new ArrayList<>();
        
		Connection connection = DBManager.getInstance().connect();
		try {
            PreparedStatement ps = null;
            ps = connection.prepareStatement( SQLTable.buildSelect("*") );
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
                Transportist transportist = new Transportist( rs.getLong("userId"), rs.getString("licensePlate") );
                transportists.add(transportist);
            }
            return transportists;
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
                // rollback falló
            }
            throw new EmployerException("Error al recuperar los transportistas");
		} finally {
			try {
				connection.close();
			} catch (SQLException e1) {
                throw new EmployerException("Error al terminar la conexión");
             }
        }
    }

    @Override
    public Transportist insert(Transportist t) throws EmployerException {

        if(t.getUserId() == null) {
            throw new InvalidUserException("El ID de usuario es invalido.");
        }

		Connection connection = DBManager.getInstance().connect();
        try {
            PreparedStatement ps = null;
            ps = connection.prepareStatement(SQLTable.buildInsert());
            ps.setLong( SQLTable.getFieldIndex("userId"), t.getUserId());
            ps.setString( SQLTable.getFieldIndex("licensePlate"), t.getLicensePlate());
			int affectedRows = ps.executeUpdate();
            connection.commit();

            if (affectedRows == 0) {
                throw new EmployerException("Error al crear el transportista.");
            }

		} catch (SQLException e) {
			try {
				connection.rollback();
                // e.printStackTrace();
			} catch (SQLException e1) {
				e1.printStackTrace();
            }
            throw new EmployerException("Error al crear el transportista.");
		} finally {
			try {
				connection.close();
			} catch (SQLException e1) {
                // e1.printStackTrace();
                throw new EmployerException("Error al crear: error al finalizar la conexión.");
			}
		}
        return t;
    }
    
    // TODO: fix SQL update.
    @Override
    public int update(Transportist t) throws EmployerException {

        if(t.getUserId() == null) {
            throw new InvalidUserException("El ID de usuario es invalido.");
        }

        Connection connection = DBManager.getInstance().connect();
        int affectedRows = 0;
		try {
            PreparedStatement ps = null;
            ps = connection.prepareStatement( String.format("%s WHERE userId = %d", SQLTable.buildUpdate(), t.getUserId()) );
            ps.setString( SQLTable.getFieldIndex("licensePlate"), t.getLicensePlate());
			affectedRows = ps.executeUpdate();
            connection.commit();

        } catch (SQLException e) {
			try {
				connection.rollback();
				e.printStackTrace();
			} catch (SQLException e1) {
				//no hago nada
            }
            throw new EmployerException("Error al actualizar.");
		} finally {
			try {
				connection.close();
			} catch (SQLException e1) {
                throw new EmployerException("Error al actualizar: error al finalizar la conexión.");
			}
		}
        return affectedRows;
    }

    @Override
    public int delete(Transportist t) throws EmployerException {
        if(t.getUserId() == null) {
            throw new InvalidUserException("El ID de usuario es invalido.");
        }
        Connection connection = DBManager.getInstance().connect();
        int affectedRows = 0;
		try {
            PreparedStatement ps = connection.prepareStatement(String.format("DELETE FROM %s WHERE userId = %d", SQLTable.getTableName(), t.getUserId()));
			affectedRows = ps.executeUpdate();
            connection.commit();
		} catch (SQLException e) {
			try {
				connection.rollback();
				e.printStackTrace();
			} catch (SQLException e1) {
				//no hago nada
            }
            throw new EmployerException("Error al eliminar.");
		} finally {
			try {
				connection.close();
			} catch (SQLException e1) {
                throw new EmployerException("Error al eliminar: error al finalizar la conexión.");
			}
		}
        return affectedRows;
    }
     
 
}