package mlos.hermes.visitor;

/**
 * Signals an error during pattern matching. 
 * 
 * @author los
 */
public class MatchException extends RuntimeException {

    public MatchException() {
    }

    public MatchException(String message) {
        super(message);
    }

    public MatchException(Throwable cause) {
        super(cause);
    }

    public MatchException(String message, Throwable cause) {
        super(message, cause);
    }

}
