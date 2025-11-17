package bstmap;

import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V>{
    private BSTNode root;
    private static final boolean RED = true;
    private static final boolean BLACK = false;
    private class BSTNode {
        K key;
        V val;
        int N;
        BSTNode left;
        BSTNode right;       
        boolean color;
        BSTNode(K key, V val) {
            this.key = key;
            this.val = val;
            this.N = 1;
            this.color = RED;
        }
    }
    private boolean isRed(BSTNode node) {
        if (node == null) {
            return false;
        }
        return node.color == RED;
    }

    @Override
    public int size() {
        return size(root);
    }
    private int size(BSTNode node) {
        if (node == null) {
            return 0;
        }
        return node.N;
    }

    @Override
    public V get(K key) {
        BSTNode node = get(root, key);
        if (node == null) {
            return null;
        }
        return node.val;
    }
    private BSTNode get(BSTNode node, K key) {
        BSTNode curr = node;
        while (curr != null) {
            int comp = key.compareTo(curr.key);
            if (comp > 0) {
                curr = curr.right;
            } else if (comp < 0) {
                curr = curr.left;
            } else {
                return curr;
            }
        }
        return null;
    }
    @Override
    public void clear() {
        root = null;
    }
    @Override
    public boolean containsKey(K key) {
        BSTNode node = get(root, key);
        return node != null;
    }
    private BSTNode rotateLeft(BSTNode h) {
        BSTNode x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        x.N = h.N;
        h.N = 1 + size(h.left) + size(h.right);
        return x;
    }
    private BSTNode rotateRight(BSTNode h) {
        BSTNode x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        x.N = h.N;
        h.N = 1 + size(h.left) + size(h.right);
        return x;
    }
    private void flipColors(BSTNode h) {
        h.color = RED;
        h.left.color = BLACK;
        h.right.color = BLACK;
    }
    public void put(K key, V val) {
        root = put(root, key, val);
        root.color = BLACK;
    }
    private BSTNode put(BSTNode node, K key, V val) {
        if (node == null) {
            return new BSTNode(key, val);
        }

        int comp = key.compareTo(node.key);
        if (comp < 0) {
            node.left = put(node.left, key, val);
        } else if (comp > 0) { 
            node.right = put(node.right, key, val);
        } else {
            node.val = val;
        }

        if (isRed(node.right) && !isRed(node.left)) {
            node = rotateLeft(node);
        }
        if (isRed(node.left) && isRed(node.left.left)) {
            node = rotateRight(node);
        }
        if (isRed(node.left) && isRed(node.right)) {
            flipColors(node);
        }

        node.N = 1 + size(node.left) + size(node.right);
        return node;
    }


    @Override                                                                         
    public Iterator<K> iterator() { throw new UnsupportedOperationException(); }  
    @Override                                                                     
    public Set<K> keySet() { throw new UnsupportedOperationException(); }         
    @Override                                                                     
    public V remove(K key) { throw new UnsupportedOperationException(); }         
    @Override                                                                     
    public V remove(K key, V value) { throw new UnsupportedOperationException(); }
}
