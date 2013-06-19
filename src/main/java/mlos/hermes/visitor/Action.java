package mlos.hermes.visitor;

public interface Action<T> {
    
    void visit(T item);

}
