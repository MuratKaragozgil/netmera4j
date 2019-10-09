package netmera4j.exception;

/**
 * @author Murat Karagözgil
 */
public class RetryException extends Exception {

    private static final long serialVersionUID = 1L;

    public RetryException(String message) {
        super(message);
    }
}