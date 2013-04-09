package rozprochy.common.hermes.util;

/**
 * Interface of a comparator defining a poset. 
 * 
 * @author los
 *
 * @param <T> type of elements of the poset
 */
public interface PosetOrder<T> {
    
    /**
     * Comparison method for poset elements
     * 
     * @param t1 first element to compare
     * @param t2 second elment to compare
     * @return one of the following values:
     * <ul>
     * <li> {@code LT} if {@code t1} element is less than {@code t2}
     * <li> {@code GT} if {@code t1} element is greater than {@code t2}
     * <li> {@code EQ} if {@code t1} element is equal to {@code t2}
     * <li> {@code NON_CMP} if the elements are not comparable
     */
    Compare compare(T t1, T t2);

}
