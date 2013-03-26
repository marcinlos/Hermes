package rozprochy.common.cli.impl;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

class ReflectionHelper {

    private ReflectionHelper() { }

    public static Collection<Class<?>> getSupertypes(Class<?> clazz) {
        Set<Class<?>> supers = new HashSet<Class<?>>();
        Class<?> sup = clazz.getSuperclass();
        if (sup != null) {
            supers.add(sup);
        }
        for (Class<?> iface: clazz.getInterfaces()) {
            supers.add(iface);
        }
        return supers;
    }
    
    public static Set<Method> getMethods(Class<?> clazz) {
        Set<Method> methods = new HashSet<Method>();
        Set<Class<?>> visited = new HashSet<Class<?>>();
        Deque<Class<?>> stack = new ArrayDeque<Class<?>>();
        stack.push(clazz);

        while (! stack.isEmpty()) {
            Class<?> top = stack.pop();
            Collections.addAll(methods, top.getDeclaredMethods());
            visited.add(top);
            
            for (Class<?> c: getSupertypes(top)) {
                if (visited.add(c)) {
                    stack.push(c);
                }
            }
        }
        return methods;
    }
    
    public static Set<Method> getAnnotated(Class<?> clazz,
            Class<? extends Annotation> annotation) {
        Set<Method> methods = getMethods(clazz);
        for (Iterator<Method> it = methods.iterator(); it.hasNext(); ) {
            Method m = it.next();
            if (! m.isAnnotationPresent(annotation)) {
                it.remove();
            }
        }
        return methods;
    }
    
}
