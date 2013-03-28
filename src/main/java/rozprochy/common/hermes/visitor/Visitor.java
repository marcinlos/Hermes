package rozprochy.common.hermes.visitor;

import java.util.HashMap;
import java.util.Map;

/**
 * Generic class providing some simple visitor/pattern-matching like 
 * functionality through a simple {@code (class, action)} mapping.
 *   
 * @author los
 *
 * @param <T>
 */
public class Visitor<T> {
    
    private Map<Class<?>, Function<T, ?>> actions = 
            new HashMap<Class<?>, Function<T, ?>>();

    public Visitor() {
    }
    
    public <U> AfterMatch<U> with(Class<U> clazz) {
        return new AfterMatch<U>(clazz);
    }
    
    public <U> T visit(U object) {
        Class<?> key = object.getClass();
        @SuppressWarnings("unchecked")
        Function<T, ? super U> action = (Function<T, ? super U>) actions
                .get(key);
        return action.visit(object);
    }
    
    //private
    
    /**
     * Helper object returned by {@linkplain Visitor#visit(Object)} as a part
     * of {@code with(...).use(...)} syntax.
     * 
     * @author los
     *
     * @param <U> 
     */
    public class AfterMatch<U> {
        
        private Class<U> clazz;
        
        private AfterMatch(Class<U> clazz) {
            this.clazz = clazz;
        }
        
        public Visitor<T> use(Function<T, ? super U> action) {
            actions.put(clazz, action);
            return Visitor.this;
        }
        
        public Visitor<T> use(final Action<? super U> action) {
            actions.put(clazz, new Function<T, U>() {
                @Override
                public T visit(U item) {
                    action.visit(item);
                    return null;
                }
            });
            return Visitor.this;
        }
        
    }
    
    
    public static void main(String[] args) {
        Visitor<Void> matcher = new Visitor<Void>()
            .with(String.class).use(new Action<String>() {
                @Override
                public void visit(String value) {
                    System.out.printf("String: '%s'\n", value);
                }
            })
            .with(Integer.class).use(new Action<Integer>() {
                @Override
                public void visit(Integer value) {
                    System.out.println("Integer: " + value);
                }
            });
        matcher.visit("Meeh");
        matcher.visit(3);
    }

}
