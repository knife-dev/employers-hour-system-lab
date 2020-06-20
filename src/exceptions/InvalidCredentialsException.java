package exceptions;

public class InvalidCredentialsException extends EmployerException {
    /**
     * Custom Exception
     */
    private static final long serialVersionUID = 1L;

    public InvalidCredentialsException(String message) {
        super(message);
    }
}