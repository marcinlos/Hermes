package mlos.hermes.reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Utility class containint various methods facilitating working reflectively
 * with methods.
 * 
 * @author los
 */
public class Methods {

    private Methods() {
        // Non-instantiable
    }

    /**
     * Creates a collection of all methods of a given class (declared by this
     * class and all its superclasses and implemented interfaces).
     *  
     * @param clazz class to extract methods from
     * @return {@linkplain Iterable} containing all the class' method.
     */
    public static Iterable<Method> all(Class<?> clazz) {
        Set<Method> methods = new HashSet<Method>();
        for (Class<?> c: Classes.ancestors(clazz)) {
            Collections.addAll(methods, c.getDeclaredMethods());
        }
        return methods;
    }
    
    /**
     * Creates a collection of all the methods of a given class having 
     * specified annotation.
     * 
     * @param clazz class to extract methods from
     * @param annotation class of a desired annotation
     * @return {@linkplain Iterable} containing all the annotaded methods.
     * @see #all(Class)
     */
    public static Iterable<Method> annotated(Class<?> clazz, 
            Class<? extends Annotation> annotation) {
        Set<Method> methods = new HashSet<Method>();
        for (Class<?> c: Classes.ancestors(clazz)) {
            for (Method m: c.getDeclaredMethods()) {
                if (m.isAnnotationPresent(annotation)) {
                    methods.add(m);
                }
            }
        }
        return methods;
    }
    
}
