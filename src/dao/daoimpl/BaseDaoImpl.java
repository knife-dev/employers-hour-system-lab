package dao.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Wrapper;

import db.DBManager;
import exceptions.EmployerException;

public class BaseDaoImpl {

    BaseDaoImpl() {
    }


    public PreparedStatement preparedStatement(Connection connection ,String sqlStatement) throws SQLException {
        return connection.prepareStatement(sqlStatement);
    }

    public ResultSet executeQuery(PreparedStatement ps) {
        try {
            return ps.executeQuery();
        } catch (SQLException e) {
            try {
                ps.getConnection().rollback();
                e.printStackTrace();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            try {
                ps.getConnection().close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        return null;
    }

    public ResultSet executeQueryFirstRow(PreparedStatement ps) throws EmployerException {
        ResultSet rs = null;
        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                ps.getConnection().rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        return rs;
    }

    protected void closeConnection(Connection connection) throws EmployerException {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new EmployerException("Error al terminar la conexión");
        }
    }

    protected Connection createConnection() {
        return DBManager.getInstance().connect();
    }

    /*
    protected void closeConnection(PreparedStatement ps) throws EmployerException {
        try {
            getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new EmployerException("Error al terminar la conexión");
        }
    }*/

}