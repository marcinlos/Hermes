package rozprochy.common.hermes.visitor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map.Entry;

import rozprochy.common.hermes.util.poset.HashPosetMap;
import rozprochy.common.hermes.util.poset.PosetMap;

/**
 * Generic class providing some simple visitor/pattern-matching like 
 * functionality through a simple {@code (class, action)} mapping.
 *   
 * @author los
 *
 * @param <T> return type of the visitor
 */
public class Visitor<T> {
    
    /** Data structure used to obtain handlers */
    private PosetMap<Class<?>, Function<T, ?>> actions =
            new HashPosetMap<Class<?>, Function<T,?>>(ClassPosetOrder.INSTANCE);
    
    

    /**
     * Method designed to use together with {@linkplain AfterMatch} to add
     * actions after having created a {@code Visitor} object.
     * 
     * @param clazz class to bind the action to
     * @return {@linkplain AfterMatch} object to be used 
     */
    public <U> AfterMatch<U> with(Class<U> clazz) {
        return new AfterMatch<U>(clazz);
    }
    
    /**
     * Performs matching and calls a method appropriate for the passed
     * argument.
     * 
     * @param object value to match against registered handlers
     * @return result of calling an appropriate handler
     */
    public <U> T visit(U object) {
        Class<?> key = object.getClass();
        Collection<Entry<Class<?>, Function<T, ?>>> viable = actions.get(key);
        
        if (viable.isEmpty()) {
            throw new NoMatchException(key);
        } else if (viable.size() > 1) {
            // Ambiguity, create the list of conflicting classes
            List<Class<?>> matches = new ArrayList<Class<?>>();
            for (Entry<Class<?>, Function<T, ?>> entry: viable) {
                matches.add(entry.getKey());
            }
            throw new AmbiguousMatchException(null, matches);
        } else {
            @SuppressWarnings("unchecked")
            Function<T, ? super U> action = (Function<T, ? super U>) 
                    viable.iterator().next();
            return action.visit(object);
        }
    }
    
    /**
     * Helper object returned by {@linkplain #visit(Object)} as a part
     * of {@code with(...).use(...)} syntax.
     * 
     * @author los
     *
     * @param <U> Type for which the handler is to be defined
     */
    public class AfterMatch<U> {
        
        private Class<U> clazz;
        
        private AfterMatch(Class<U> clazz) {
            this.clazz = clazz;
        }
        
        /**
         * Registers a {@code Function} in the visitor.
         * 
         * @param function function to register
         * @return {@code Visitor}
         */
        public Visitor<T> use(Function<T, ? super U> function) {
            actions.put(clazz, function);
            return Visitor.this;
        }
        
        /**
         * Registers an {@code Action} in the visitor.
         * 
         * @param action action to register
         * @return {@code Visitor}
         */
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
            })
            .with(Object.class).use(new Action<Object>() {
                @Override
                public void visit(Object item) {
                    System.out.println("Object: " + item);
                }
            });
        matcher.visit("Meeh");
        matcher.visit(3);
        matcher.visit(new Date());
    }

}
