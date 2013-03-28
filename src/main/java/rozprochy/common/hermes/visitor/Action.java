package rozprochy.common.hermes.visitor;

public interface Action<T> {
    
    void visit(T item);

}
