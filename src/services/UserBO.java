package services;

import java.util.List;
import java.util.regex.Pattern;

import dao.IUser;
import entities.User;
import exceptions.EmployerException;

public class UserBO {


    /*
    Services:
        DAOImplementation (with JDBC):
    Manipulates:
        DATA and does checks
    */
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    public static final Pattern VALID_ROLES_REGEX         = Pattern.compile("admin|sanitation|transportist", Pattern.CASE_INSENSITIVE);


    private IUser dao;

    public UserBO(IUser dao) {
        this.dao = dao;
    }

    public List<User> getAllUsers() throws EmployerException {
        // Load users
        return dao.getAll();
    }

    private void checkUserRequirements(User user) throws EmployerException {
        if(user == null)
            throw new EmployerException("Instrucción no válida");

        if(user.getEmail() == null || !VALID_EMAIL_ADDRESS_REGEX.matcher(user.getEmail()).matches())
            throw new EmployerException("El email no es válido");
        
        if(user.getPassword() == null || user.getPassword().length() < 3)
            throw new EmployerException("La contraseña debe tener tres o más caracteres.");

        
        // if(!VALID_ROLES_REGEX.matcher(user.getRole()).matches() ){
        //     throw new EmployerException("Rol de privilegio no válido.");
        // }
        
    }

    public User createUser(User user) throws EmployerException {
        
        checkUserRequirements(user);

        if(dao.getByEmail(user.getEmail()) != null)
            throw new EmployerException(String.format("Ya existe un usuario con esta dirección de email: %s", user.getEmail()));
        
        if(dao.get(user.getId()) != null) 
            throw new EmployerException(String.format("Ya existe un usuario con este ID (%d).", user.getId()));

        return dao.insert(user);
    }

    /**
     * Modified user in DB
     * @param user
     * @return int - affectedRows
     * @throws EmployerException
     */
    public int modifyUser(User user) throws EmployerException {

        checkUserRequirements(user);

        if(dao.get(user.getId()) == null) 
            throw new EmployerException(String.format("No existe ningun usuario con este ID (%d).", user.getId()));
            
        return dao.update(user);
    }

    public int deleteUser(User user) throws EmployerException {
        // no further checks needed
        return dao.delete(user);
    }

    public User authUser(String email, String password) throws EmployerException {
        // no further checks needed
        return dao.authenticate(email, password);
    }

}