package rozprochy.common.hermes.visitor;

import java.util.Collection;

public class AmbiguousMatchException extends RuntimeException {
    
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
    
    public Collection<Class<?>> getMatches() {
        return matches;
    }

}
