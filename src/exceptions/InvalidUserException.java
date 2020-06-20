package exceptions;

public class InvalidUserException extends EmployerException {
    /**
     * Custom Exception
     */
    private static final long serialVersionUID = 1L;

    public InvalidUserException(String message) {
        super(message);
    }
}