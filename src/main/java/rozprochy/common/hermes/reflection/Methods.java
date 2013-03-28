package rozprochy.common.hermes.reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Methods {

    private Methods() {
        // Non-instantiable
    }
    
    public static Iterable<Method> all(Class<?> clazz) {
        Set<Method> methods = new HashSet<Method>();
        for (Class<?> c: Classes.ancestors(clazz)) {
            Collections.addAll(methods, c.getDeclaredMethods());
        }
        return methods;
    }
    
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
    
    static class Dupa<T> {}
    
    static <U> Object dupa(U u) {
        return new Dupa<U>() { };
    }
    
    public static void main(String[] args) {
        Object o = Methods.dupa("dupa");
        ParameterizedType t = (ParameterizedType) o.getClass().getGenericSuperclass();
        System.out.println(t.getActualTypeArguments()[0]);
    }

}
