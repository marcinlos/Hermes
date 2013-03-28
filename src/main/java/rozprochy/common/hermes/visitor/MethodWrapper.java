package rozprochy.common.hermes.visitor;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import rozprochy.common.hermes.reflection.InvocationException;

class MethodWrapper<R> implements Function<R, Object> {

    private Object object;
    private Method method;
    
    public MethodWrapper(Object object, Method method) {
        this.object = object;
        this.method = method;
        method.setAccessible(true);
    }

    @Override
    public R visit(Object item) {
        try {
            @SuppressWarnings("unchecked")
            R value = (R) method.invoke(object, item);
            return value;
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
