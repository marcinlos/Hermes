package mlos.hermes.visitor;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import mlos.hermes.reflection.InvocationException;


class MethodWrapper<R> implements Function<R, Object> {

    private Object object;
    private Method method;
    private Class<R> clazz;
    
    public MethodWrapper(Object object, Method method, Class<R> clazz) {
        this.object = object;
        this.method = method;
        method.setAccessible(true);
    }
    
    public static <T> MethodWrapper<T> make(Object object, Method method, 
            Class<T> clazz) { 
        return new MethodWrapper<T>(object, method, clazz); 
    }

    @Override
    public R visit(Object item) {
        try {
            Object value = method.invoke(object, item);
            return clazz.cast(value);
        } catch (IllegalArgumentException e) {
            throw new InvocationException(e);
        } catch (IllegalAccessException e) {
            // Shouldn't happen
            throw new Error("Access exception", e);
        } catch (InvocationTargetException e) {
            throw new InvocationException(e);
        }
    }

}
