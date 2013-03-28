package rozprochy.common.hermes.visitor;

public interface Function<R, T> {
    
    R visit(T item);

}
