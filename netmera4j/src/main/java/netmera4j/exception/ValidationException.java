package netmera4j.exception;

/**
 * @author Murat Karagözgil
 */
public class ValidationException extends Exception {

    private static final long serialVersionUID = 1L;

    public ValidationException(String message) {
        super(message);
    }
}