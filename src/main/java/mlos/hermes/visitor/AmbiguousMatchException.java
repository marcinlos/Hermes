package mlos.hermes.visitor;

import java.util.Collection;

/**
 * Signals that an ambiguity has arose during pattern matching - several 
 * patterns were equally good.
 * 
 * @author los
 *
 */
public class AmbiguousMatchException extends MatchException {
    
    /** List of potential equally good matches */
    private Collection<Class<?>> matches;

    public AmbiguousMatchException() {
    }

    public AmbiguousMatchException(String message) {
        super(message);
    }
    
    public AmbiguousMatchException(String message, 
            Collection<Class<?>> matches) {
        super(message);
        this.matches = matches;
    }
    
    /**
     * @return collection of viable handler cclas
     */
    public Collection<Class<?>> getMatches() {
        return matches;
    }

}
