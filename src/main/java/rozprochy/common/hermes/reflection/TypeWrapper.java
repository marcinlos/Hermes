package rozprochy.common.hermes.reflection;


public abstract class TypeWrapper<T> extends TypeToken<T> {
    
    private T value;
    
    public TypeWrapper(T value) {
        this.value = value;
    }
    
    public T value() {
        return value;
    }
    
    static <U> void f(U arg) {
        System.out.println(arg.getClass());
    }
    
    static <U> void f(TypeWrapper<U> u) {
        System.out.println(u.type());
    }
    
}
