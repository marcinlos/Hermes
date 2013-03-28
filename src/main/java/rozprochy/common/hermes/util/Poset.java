package rozprochy.common.hermes.util;

import java.util.Collection;

public interface Poset<T> extends Iterable<T> {

    boolean add(T e);
    
    boolean contains(T e);
    
    Collection<T> getRoots();
    
    Collection<T> immediatePredecessors(T e);
    
    Collection<T> greatestLowerBound(T e);
    
    PosetOrder<? super T> getComparator();
    
}
