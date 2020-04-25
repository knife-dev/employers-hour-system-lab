package main.java.exceptions;

public class InvalidUserException extends DaoException {
    /**
     * Custom Exception
     */
    private static final long serialVersionUID = 7115862425127612012L;

    public InvalidUserException(String message) {
        super(message);
    }
}