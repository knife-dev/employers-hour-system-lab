package exceptions;

public class NoTaskFoundException extends EmployerException {

    /**
     * Custom Exception
     */
    private static final long serialVersionUID = -6711004531242974692L;

    public NoTaskFoundException(String message) {
        super(message);
    }

}