package main.java.exceptions;

public class UserNotFoundException extends DaoException {
    /**
     * Custom Exception
     */
    private static final long serialVersionUID = 1L;

    public UserNotFoundException(String message) {
        super(message);
    }

}