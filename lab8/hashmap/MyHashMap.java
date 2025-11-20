package hashmap;

import java.util.*;

/**
 *  A hash table-backed Map implementation. Provides amortized constant time
 *  access to elements via get(), remove(), and put() in the best case.
 *
 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 *  @author YOUR NAME HERE
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    /* Instance Variables */
    private Collection<Node>[] buckets;
    // You should probably define some more!
    private int N;
    private int M;
    private final double loadFactor;
    private Set<K> keySet = new HashSet<>();

    /** Constructors */
    public MyHashMap() {
        M = 16;
        loadFactor = 0.75;
        buckets = createTable(M);
    }

    public MyHashMap(int initialSize) {
        M = initialSize;
        loadFactor = 0.75;
        buckets = createTable(M);
    }

    /**
     * MyHashMap constructor that creates a backing array of initialSize.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialSize initial size of backing array
     * @param maxLoad maximum load factor
     */
    public MyHashMap(int initialSize, double maxLoad) {
        M = initialSize;
        loadFactor = maxLoad;
        buckets = createTable(M);
    }

    /**
     * Returns a new node to be placed in a hash table bucket
     */
    private Node createNode(K key, V value) {
        return new Node(key, value);
    }

    /**
     * Returns a data structure to be a hash table bucket
     *
     * The only requirements of a hash table bucket are that we can:
     *  1. Insert items (`add` method)
     *  2. Remove items (`remove` method)
     *  3. Iterate through items (`iterator` method)
     *
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     *
     * Override this method to use different data structures as
     * the underlying bucket type
     *
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        return new LinkedList<>();
    }

    /**
     * Returns a table to back our hash table. As per the comment
     * above, this table can be an array of Collection objects
     *
     * BE SURE TO CALL THIS FACTORY METHOD WHEN CREATING A TABLE SO
     * THAT ALL BUCKET TYPES ARE OF JAVA.UTIL.COLLECTION
     *
     * @param tableSize the size of the table to create
     */
    private Collection<Node>[] createTable(int tableSize) {

        Collection<Node>[] buckets = new Collection[tableSize];
        for (int i=0; i<tableSize; i++) {
            buckets[i] = createBucket();
        }
        return buckets;
    }

    // TODO: Implement the methods of the Map61B Interface below
    // Your code won't compile until you do so!

    
    @Override
    public void put(K key, V value) {

        int hashKey = hashCode(key);
        if (containsKey(key)) {
            for (Node node : buckets[hashKey]) {
                if (node.key.equals(key)) {
                    node.value = value;
                    return ;
                }
            }
        }
        Node newNode = createNode(key, value);
        buckets[hashKey].add(newNode);
        keySet.add(key);
        N += 1;

        if ((double) N /M >= loadFactor) {
            resize(M * 2);
        }
    }
    private void resize(int newSize) {
        M = newSize;
        Collection<Node>[] newBuckets = createTable(M);
        for (Collection<Node> bucket : buckets) {
            for (Node node : bucket) {
                newBuckets[hashCode(node.key)].add(node);
            }
        }
        buckets = newBuckets;
    }

    @Override
    public void clear() {
        N = 0;
        keySet.clear();
        for (Collection<Node> bucket : buckets) {
            bucket.clear();
        }
    }

    @Override
    public boolean containsKey(K key) {
        return keySet.contains(key);
    }

    @Override
    public V get(K key) {
        if (containsKey(key)) {
            for (Node node : buckets[hashCode(key)]) {
                if (node.key.equals(key)) {
                    return node.value;
                }
            }
        }
        return null;
    }
    private int hashCode(K key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    @Override
    public int size() {
        return N;
    }

    @Override
    public Set<K> keySet() {
        return keySet;
    }
    
    @Override
    public V remove(K key) {
        V deleted = null;
        for (Node node : buckets[hashCode(key)]) {
            if (node.key.equals(key)) {
                deleted = node.value;
                buckets[hashCode(key)].remove(node);
                keySet.remove(key);
            }
        }
        return deleted;
    }

    @Override
    public V remove(K key, V value) { throw new UnsupportedOperationException(); }

    @Override
    public Iterator<K> iterator() {
        return new MyHashMapIterator();
    }

    private class MyHashMapIterator implements Iterator<K> {
        private final Set<K> set;

        public MyHashMapIterator() {
            set = keySet();
        }

        public boolean hasNext() {
            return set.iterator().hasNext();
        }

        public K next() {
            return set.iterator().next();
        }
    }
}
