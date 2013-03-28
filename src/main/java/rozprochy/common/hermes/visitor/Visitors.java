package rozprochy.common.hermes.visitor;

public class Visitors {

    private Visitors() {
        // Non-instantiable
    }
    
    public static <T> Visitor<T> from(Object o) {
        Visitor<T> visitor = new Visitor<T>();
        
        return visitor;
    }

}
