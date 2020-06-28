package dao.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.IUser;
import entities.User;
import exceptions.EmployerException;
import exceptions.InvalidUserException;
import exceptions.UserNotFoundException;
import utils.SqlTable;

public class UserDaoImpl extends BaseDaoImpl implements IUser {

    SqlTable SQLTable;

    public UserDaoImpl() {
        super();
        
        SQLTable = new SqlTable("users", new String[] { "id", "email", "password", "role" });
    }


    @Override
    public User get(Long id) throws EmployerException {
        User user = null;
        Connection connection = createConnection();
        try {
            PreparedStatement ps = preparedStatement(connection, SQLTable.buildSelect("*", "id = ?"));
            ps.setLong(1, id);
            ResultSet rs = executeQueryFirstRow(ps);
            if (rs == null || !rs.next()) {
                user = new User(rs.getLong("id"), rs.getString("email"), rs.getString("password"),rs.getString("role"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new UserNotFoundException("Error al recuperar los datos de usuario.");
        } finally {
            closeConnection(connection);
        }
        return user;
    }

    @Override
    public List<User> getAll() throws EmployerException {
        List<User> users = new ArrayList<>();
        Connection connection = createConnection();
        try {
            PreparedStatement ps = preparedStatement(connection, SQLTable.buildSelect("*"));
            ResultSet rs = executeQuery(ps);
            if (rs == null)
                throw new EmployerException("Error al consultar los usuarios");
            while (rs.next()) {
                User user = new User(rs.getLong("id"), rs.getString("email"), rs.getString("password"),
                        rs.getString("role"));
                users.add(user);
            }
        } catch (SQLException e) {
            throw new EmployerException("Error al recuperar datos de los usuarios");
        } finally {
            closeConnection(connection);
        }
        return users; // return users
    }

    @Override
    public User insert(User t) throws EmployerException {
        Connection connection = createConnection();

        try {

            PreparedStatement ps = preparedStatement(connection, SQLTable.buildInsert());
            ps.setString(SQLTable.getFieldIndex("email"), t.getEmail());
            ps.setString(SQLTable.getFieldIndex("password"), t.getPassword());
            ps.setString(SQLTable.getFieldIndex("role"), t.getRole());
            int affectedRows = ps.executeUpdate();
            ps.getConnection().commit();

            if (affectedRows == 0)
                throw new EmployerException("Error al crear el usuario, no se logro insertar.");

            PreparedStatement s = preparedStatement(connection, "CALL IDENTITY()");
            ResultSet rs = executeQueryFirstRow(s);
            if (rs == null || !rs.next()) {
                throw new EmployerException("Error al crear el usuario, no se pudo obtener el ID.");
            }
            t.setId(rs.getLong(SQLTable.getFieldIndex("id")));
        } catch (SQLException e) {
            e.printStackTrace();
            throw new EmployerException("Error al crear el usuario.");
        } finally {
            closeConnection(connection);
        }
        return t;
    }

    @Override
    public int update(User t) throws EmployerException {

        if (t.getId() == null)
            throw new InvalidUserException("El ID de usuario es invalido.");

        int affectedRows = 0;
        Connection connection = createConnection();

        try {

            PreparedStatement ps = preparedStatement(connection, String.format("%s WHERE id = %d", SQLTable.buildUpdate(), t.getId()));
            ps.setLong(SQLTable.getFieldIndex("id"), t.getId());
            ps.setString(SQLTable.getFieldIndex("email"), t.getEmail());
            ps.setString(SQLTable.getFieldIndex("password"), t.getPassword());
            ps.setString(SQLTable.getFieldIndex("role"), t.getRole());
            affectedRows = ps.executeUpdate();
            ps.getConnection().commit();
            if (affectedRows == 0)
                throw new EmployerException("Error: el usuario no se actualizó.");
        } catch (SQLException e) {
            throw new EmployerException("Error al actualizar el usuario.");
        } finally {
            closeConnection(connection);
        }
        return affectedRows;
    }

    @Override
    public int delete(User t) throws EmployerException {

        if (t.getId() == null)
            throw new InvalidUserException("El ID de usuario es invalido.");

        int affectedRows = 0;
        Connection connection = createConnection();

        try {
            PreparedStatement ps = preparedStatement(connection ,String.format("DELETE FROM %s WHERE id = %d", SQLTable.getTableName(), t.getId()));
            affectedRows = ps.executeUpdate();
            ps.getConnection().commit();
        } catch (SQLException e) {
            throw new EmployerException("Error al eliminar el usuario.");
        } finally {
            closeConnection(connection);
        }
        return affectedRows;
    }

    @Override
    public User authenticate(String email, String password) throws EmployerException {
        User user = null;
        Connection connection = createConnection();

        try {
            PreparedStatement ps = preparedStatement(connection, SQLTable.buildSelect("*", "email = ? AND password = ?"));
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = executeQueryFirstRow(ps);
            if (rs == null || !rs.next()) {
                throw new EmployerException("Las credenciales no son válidas.");
            }
            user = new User(rs.getLong("id"), rs.getString("email"), rs.getString("password"), rs.getString("role"));
        } catch (SQLException e) {
            e.printStackTrace();
            throw new EmployerException("Error al verificar las credenciales.");
        } finally {
            closeConnection(connection);
        }
        return user;
    }

    @Override
    public User getByEmail(String email) throws EmployerException {
        User user = null;
        Connection connection = createConnection();
        try {
            PreparedStatement ps = preparedStatement(connection, SQLTable.buildSelect("*", "email = ?"));
            ps.setString(1, email);
            ResultSet rs = executeQueryFirstRow(ps);
            if (rs != null && rs.next()){
                user = new User(rs.getLong("id"), rs.getString("email"), rs.getString("password"),
                rs.getString("role"));    
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new UserNotFoundException("Error al recuperar los datos de usuario.");
        } finally {
            closeConnection(connection);
        }
        return user;
    }
}