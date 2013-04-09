package rozprochy.common.hermes.util;

import java.util.Collection;

public interface Poset<T> extends Iterable<T> {

    boolean add(T e);
    
    boolean contains(T e);
    
    /**
     * Returns a collection of minimal objects in the poset. That is, it
     * returns all the elements {@code x} such that there is no element 
     * {@code y} which is less than {@code x} (though there may be elements 
     * incomparable to {@code x})
     * <p>
     * The order in which the roots appear in collection is unspecified.
     * 
     * @return collection of minimal objects in the poset
     */
    Collection<T> getRoots();
    
    Collection<T> immediatePredecessors(T e);
    
    Collection<T> greatestLowerBound(T e);
    
    PosetOrder<? super T> getComparator();
    
}
