package rozprochy.common.hermes.util;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Queue;
import java.util.Set;

public class DAGPoset<T> implements Poset<T> {

    /** Poset comparator */
    private PosetOrder<? super T> comp;

    /** Roots of the forest */
    private Set<Node<T>> root = new HashSet<Node<T>>();
    
    
    /**
     * Creates a {@code Poset} sorted according to the specified comparator
     * 
     * @param comparator the comparator that will be used to order the poset
     */
    public DAGPoset(PosetOrder<? super T> comparator) {
        this.comp = comparator;
    }
    

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterator<T> iterator() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean add(T e) {
        return insert(new Node<T>(e), root);
    }
    
    /**
     * Inserts a node {@code n} into the part of the DAG represented by the
     * {@code nodes} forest. Modifies the forest if necessary.
     * <p>
     * The algorithm is based on the following obserwation: for a given forest
     * F and a node N, one of the following is true:
     * <ol>
     * <li> No tree root in the F is comparable to N,
     * <li> Some roots are greater than N (and so N shall be inside their
     *      trees, and the rest is incomparable
     * <li> Some roots are smaller than N (and so they shall belong to tree
     *      rooted in N), the rest is incomparable
     * </ol>
     * That is, it is not possible that all 3 types of roots exist in the forest
     * at the same time. If it wasn't so, transitivity of ordering relation
     * would force the smaller roots to be parts of greater root's trees. For
     * this reason one pass pushing down roots greater than N and pushing N
     * down roots smaller than N is enough, no additional checking is required,
     * provided the tree is in valid state at the beginning of the operation.
     * 
     * @param n node to be inserted
     * @param nodes part of the DAG 
     */
    private boolean insert(Node<T> n, Set<Node<T>> nodes) {
        Set<Node<T>> copy = new HashSet<Node<T>>(nodes);
        boolean flat = true; // wether there was any element comparable to n
        for (Node<T> root: copy) {
            if (root.equals(n)) {
                return false;
            }
            Compare res = comp.compare(n.item, root.item);
            if (res != Compare.NON_CMP) {
                if (res == Compare.GT) {
                    // new element is greater, it goes deeper
                    insert(n, root.children());
                } else if (res == Compare.LT) {
                    // new element is smaller, it will be a new root
                    nodes.remove(root);
                    nodes.add(n);
                    insert(root, n.children());
                }
                flat = false;
            }
        }
        if (flat) {
            nodes.add(n);
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean contains(T e) {
        
        Set<Node<T>> visited = new HashSet<Node<T>>();
        Queue<Node<T>> nodes = new ArrayDeque<Node<T>>(root);
        
        while (! nodes.isEmpty()) {
            Node<T> node = nodes.remove();
            if (node.item.equals(e)) {
                return true;
            } else if (comp.compare(node.item, e) == Compare.LT) {
                // if the node is smaller than e, examin its children
                for (Node<T> child: node.children()) {
                    if (! visited.contains(child)) {
                        nodes.offer(child);
                        visited.add(child);
                    }
                }
            }
        }
        return false;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<T> getRoots() {
        Set<T> r = new HashSet<T>();
        for (Node<T> n: root) {
            r.add(n.item);
        }
        return r;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<T> immediatePredecessors(T e) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<T> greatestLowerBound(T e) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PosetOrder<? super T> getComparator() {
        return comp;
    }
    
    private static class Node<T> {
        T item;
        Set<Node<T>> children;
        
        Node(T item) {
            this.item = item;
        }
        
        Set<Node<T>> children() {
            if (children == null) {
                children = new HashSet<Node<T>>();
            }
            return children;
        }
        
        @Override
        public int hashCode() {
            return item.hashCode();
        }
        
        @Override
        public boolean equals(Object o) {
            if (o instanceof Node) {
                Node<?> node = (Node<?>) o;
                return item.equals(node.item);
            } else {
                return false;
            }
        }
        
        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(item).append(": ");
            for (Node<T> child: children()) {
                sb.append(child.item).append(' ');
            }
            return sb.toString();
        }
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Queue<Node<T>> nodes = new ArrayDeque<Node<T>>();
        Set<Node<T>> visited = new HashSet<Node<T>>();
        nodes.addAll(root);
        while (! nodes.isEmpty()) {
            Node<T> n = nodes.remove();
            sb.append(n).append('\n');
            for (Node<T> child: n.children()) {
                if (! visited.contains(child)) {
                    nodes.add(child);
                    visited.add(child);
                }
            }
        }
        return sb.toString();
    }
    
    public static void main(String[] args) {
        PosetOrder<String> order = new PosetOrder<String>() {
            @Override
            public Compare compare(String t1, String t2) {
                if (t1.contains(t2)) {
                    return Compare.LT;
                } else if (t2.contains(t1)) {
                    return Compare.GT;
                } else {
                    return Compare.NON_CMP;
                }
            }
        };
        
        Poset<String> set = new DAGPoset<String>(order);
        
        for (String s: new String[]{
                "kra","pi", "dup", "kra","dupi", "k", "kra", 
                "ków", "kraków", "kra","krakówwa"
        }) {
            set.add(s);
        }
        System.out.println("Set contents:");
        System.out.println(set);
        System.out.println("(end)");
        System.out.println(set.contains("dupi"));
    }
}
