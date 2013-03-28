package rozprochy.common.hermes.reflection;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class TypeToken<T> {
    
    private Type type;

    public TypeToken() {
        Type parent = getClass().getGenericSuperclass();
        if (! (parent instanceof ParameterizedType)) {
            throw new RuntimeException("Cannot use raw TypeToken");
        }
        ParameterizedType p = (ParameterizedType) parent;
        this.type = p.getActualTypeArguments()[0];
    }

    public Type type() {
        return type;
    }
    
    @Override
    public boolean equals(Object o) {
        if (! (o instanceof TypeToken)) {
            return false;
        } else {
            TypeToken<?> token = (TypeToken<?>) o;
            return type.equals(token.type());
        }
    }
    
    @Override
    public int hashCode() {
        return type.hashCode();
    }
    
    @Override
    public String toString() {
        return type.toString();
    }
    
}
