package mlos.hermes.visitor;

public interface Function<R, T> {
    
    R visit(T item);

}
