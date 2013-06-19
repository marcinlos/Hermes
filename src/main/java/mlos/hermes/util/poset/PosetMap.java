package mlos.hermes.util.poset;

import java.util.Collection;
import java.util.Map;

/**
 * Interace of a simple, poset-based map, where values returned by {@code get}
 * method correspond to greatest upper bound of the key.
 *  
 * @author los
 *
 * @param <K>
 * @param <V>
 */
public interface PosetMap<K, V> {
    
    /**
     * Associate {@code value} with the {@code key}.
     * 
     * @param key map key (poset element0
     * @param value correspoding value
     */
    void put(K key, V value);
    
    /**
     * Retrieves possible desired values, that is pairs {@code (key, value)}
     * for each {@code key} in the greatest lower bound of {@code {key}}.
     *  
     * @param key upper bound
     * @return potential values
     */
    Collection<Map.Entry<K, V>> get(K key);
    
    /**
     * Retrieves arbitrary value from the set of viable choices, as returned by 
     * {@linkplain PosetMap#get(Object)}.
     * 
     * @param key upper bound
     * @return arbitrary possible value
     * 
     * @see #get(Object)
     */
    V getAny(K key);
    
    /** 
     * Retrieves a value precisely matching the key, or returns {@code null}
     * if there is no such value.
     * 
     * @param key map key
     * @return exact match or {@code null}
     * 
     * @see #get(Object)
     */
    V getExact(K key);
    
    /**
     * Returns a {@linkplain Poset} backing the map
     * 
     * @return {@linkplain Poset} consisting the map keys
     */
    Poset<K> getKeySet();

}
