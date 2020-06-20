package exceptions;

public class EmployerException extends Exception {
    /**
     * Custom Exception
     */
    private static final long serialVersionUID = 5483083799687701549L;

    public EmployerException(String message) {
        super(message);
    }
}