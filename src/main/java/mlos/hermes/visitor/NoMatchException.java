package mlos.hermes.visitor;

/**
 * Signals that during matching no compatible handler was found.
 * 
 * @author los
 */
public class NoMatchException extends MatchException {
    
    /** Cause of an exception */
    private Class<?> clazz;

    public NoMatchException() {
    }
    
    public NoMatchException(Class<?> clazz) {
        this.clazz = clazz;
    }

    public NoMatchException(String message) {
        super(message);
    }
    
    /**
     * @return class of object that have caused this exception
     */
    public Class<?> getUnmatchedClass() {
        return this.clazz;
    }

}
