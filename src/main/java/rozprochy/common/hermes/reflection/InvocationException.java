package rozprochy.common.hermes.reflection;

/**
 * Exception thrown when reflective method invocation has failed, for whatever
 * reason. 
 * 
 * @author los
 */
public class InvocationException extends RuntimeException {

    public InvocationException(String message) {
        super(message);
    }

    public InvocationException(Throwable cause) {
        super(cause);
    }

    public InvocationException(String message, Throwable cause) {
        super(message, cause);
    }

}
