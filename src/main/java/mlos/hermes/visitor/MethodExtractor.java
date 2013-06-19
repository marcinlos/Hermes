package mlos.hermes.visitor;

import java.lang.reflect.Method;

import mlos.hermes.reflection.Methods;
import mlos.hermes.util.poset.HashPosetMap;
import mlos.hermes.util.poset.PosetMap;


/**
 * Helper class, analyzing an object and extracting information about defined
 * handlers (i.e. methods annotated with {@linkplain Match}). Based on this,
 * internal {@linkplain PosetMap} of handlers is built.
 * 
 * @author los
 */
class MethodExtractor {
    
    /** Map storing key graph and exact pairs */
    private PosetMap<Class<?>, Function<?, ?>> map = 
        new HashPosetMap<Class<?>, Function<?, ?>>(ClassPosetOrder.INSTANCE);
    
    /** Return type of the visitor's functions */
    private Class<?> returnType;
    
    /** Object being analyzed */
    private Object object;

    
    public MethodExtractor(Class<?> returnType, Object object) {
        this.returnType = returnType;
    }
    
    public MethodExtractor(Object object) {
        this(Object.class, object);
    }
    
    /**
     * Processes the object's methods and fills the internal map.
     */
    public void process() {
        Class<?> clazz = object.getClass();
        for (Method method: Methods.annotated(clazz, Match.class)) {
            processMethod(method);
        }
    }
    
    /**
     * Actually processes a single method
     * 
     * @param method method to process
     */
    private void processMethod(Method method) {
        Class<?> ret = method.getReturnType();
        if (! returnType.isAssignableFrom(ret)) {
            // TODO: throw new ReturnTypeMismatchException
        }
        Class<?>[] params = method.getParameterTypes();
        if (params.length != 1) {
            // TODO: throw new InvalidParamsException
        }
        Class<?> clazz = params[0];
        Function<?, ?> fun = MethodWrapper.make(object, method, ret);
        map.put(clazz, fun);
    }

}
