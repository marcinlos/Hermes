package rozprochy.common.hermes.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Trie<K, V> {

    private K key;
    private V value;
    private Map<K, Trie<K, V>> children;
    
    public class Partial {
        public final List<K> consumed;
        public final List<K> rest;
        public final Trie<K, V> result;
        
        public Partial(List<K> consumed, List<K> rest, Trie<K, V> result) {
            this.consumed = consumed;
            this.rest = rest;
            this.result = result;
        }
    }
    
    public Trie() { }

    public Trie(K key, V value) {
        this.key = key;
        this.value = value;
    }
    
    public Map<K, Trie<K, V>> getChildren() {
        if (children == null) {
            children = new HashMap<K, Trie<K, V>>();
        }
        return children;
    }
    
    public Trie<K, V> getChild(Iterable<K> keys) {
        Trie<K, V> trie = this;
        for (K key: keys) {
            trie = trie.getChildren().get(key);
            if (trie == null) {
                return null;
            }
        }
        return trie;
    }
    
    public Trie<K, V> getChild(K... keys) {
        Iterable<K> iterable = Arrays.asList(keys);
        return getChild(iterable);
    }
    
    
    public Trie<K, V> createChild(Iterable<K> keys) {
        Trie<K, V> trie = this;
        for (K key: keys) {
            Trie<K, V> next = trie.getChildren().get(key);
            if (next == null) {
                next = new Trie<K, V>(key, null);
                trie.getChildren().put(key, next);
            }
            trie = next;
        }
        return trie;
    }
    
    public Trie<K, V> createChild(K... keys) {
        Iterable<K> iterable = Arrays.asList(keys);
        return createChild(iterable);
    }
    
    public V getValue() {
        return value;
    }
    
    public K getKey() {
        return key;
    }
    
    public V get(Iterable<K> keys) {
        Trie<K, V> child = getChild(keys);
        return child == null ? null : child.getValue();
    }
    
    public V get(K... keys) {
        Iterable<K> iterable = Arrays.asList(keys);
        return get(iterable);
    }
    
    public boolean containsKey(Iterable<K> keys) {
        return getChild(keys) != null;
    }
    
    public boolean containsKey(K... keys) {
        Iterable<K> it = Arrays.asList(keys);
        return containsKey(it);
    }

    public void put(V value, Iterable<K> keys) {
        Trie<K, V> child = createChild(keys);
        child.value = value;
    }
        
    public void put(V value, K... keys) {
        Iterable<K> iterable = Arrays.asList(keys);
        put(value, iterable);
    }
    
    public Partial getByPrefix(Iterable<K> keys) {
        Trie<K, V> trie = this;
        List<K> consumed = new ArrayList<K>();
        List<K> rest = new ArrayList<K>();
        Iterator<K> it = keys.iterator();
        while (it.hasNext()) {
            Map<K, Trie<K, V>> map = trie.children;
            if (map == null) {
                break;
            } else {
                K key = it.next();
                Trie<K, V> child = trie.children.get(key);
                if (child == null) {
                    rest.add(key);
                    break;
                } else {
                    consumed.add(key);
                    trie = child;
                }
            }
        }
        while (it.hasNext()) {
            rest.add(it.next());
        }
        return new Partial(consumed, rest, trie);
    }
    
    public Set<V> values() {
        Set<V> vals = new HashSet<V>();
        if (value != null) {
            vals.add(value);
        }
        if (children != null) {
            for (Trie<K, V> child: children.values()) {
                vals.addAll(child.values());
            }
        }
        return vals;
    }
    
    @Override
    public String toString() {
        return children.toString();
    }
}
