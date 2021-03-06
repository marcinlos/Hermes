package mlos.hermes.util.poset;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class HashPosetMap<K, V> implements PosetMap<K, V> {
    
    /** Poset storing key graph */
    private Poset<K> keys;
    
    /** Map storing exact key -> value mappping */
    private Map<K, V> map = new HashMap<K, V>();


    /**
     * Simple map entry class.
     * @author los
     *
     * @param <K> type of the key
     * @param <V> type of the value
     */
    private static class SimpleEntry<K, V> implements Entry<K, V> {

        private K key;
        private V value;
        
        public SimpleEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
        
        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {
            V prev = this.value;
            this.value = value;
            return prev;
        }
    }

    
    public HashPosetMap(PosetOrder<? super K> comparator) {
        keys = new DAGPoset<K>(comparator);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void put(K key, V value) {
        keys.add(key);
        map.put(key, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<Entry<K, V>> get(K key) {
        Collection<K> ks = keys.greatestLowerBound(key);
        Collection<Entry<K, V>> entries = new ArrayList<Entry<K,V>>();
        for (K k: ks) {
            entries.add(new SimpleEntry<K, V>(k, map.get(k)));
        }
        return entries;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public V getAny(K key) {
        Collection<Entry<K, V>> all = get(key);
        return all.iterator().next().getValue();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public V getExact(K key) {
        if (keys.contains(key)) {
            return map.get(key);
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Poset<K> getKeySet() {
        return keys;
    }

}
