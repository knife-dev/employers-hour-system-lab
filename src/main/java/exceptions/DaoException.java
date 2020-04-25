package main.java.exceptions;

public class DaoException extends Exception {
    /**
     * Custom Exception
     */
    private static final long serialVersionUID = 5483083799687701549L;

    public DaoException(String message) {
        super(message);
    }
}