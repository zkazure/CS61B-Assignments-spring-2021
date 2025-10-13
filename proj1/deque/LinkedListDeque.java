package deque;

public class LinkedListDeque<T> {
    private int size;
    private Node sentinel;
    
    public class Node {
    
        private Node prev;
        private T item;
        private Node next;

        public Node() {
            prev = null;
            next = null;
        }
        public Node(Node p, T i, Node n) {
            prev = p;
            item = i;
            next = n;
        }
    }
    
    public LinkedListDeque() {
        size = 0;
        sentinel = new Node(sentinel, null, sentinel);
    }

    public int size() {
        return size;
    }
    
    public void addFirst(T item) {
        size += 1;
        Node new_item = new Node(sentinel, item, sentinel.next);
        sentinel.next.prev = new_item;
        sentinel.next = new_item;
    }
    public void addLast(T item) {
        size += 1;
        Node new_item = new Node(sentinel.prev, item, sentinel);
        sentinel.prev.next = new_item;
        sentinel.prev = new_item;
    }
    public boolean isEmpty() {
        return size==0;
    }
    public void printDeque() {
        Node tmp = sentinel.next;
        while (tmp.item != null) {
            System.out.print(tmp.item+" ");
            tmp = tmp.next;
        }
        System.out.println();
    }
    public T removeFirst() {
        if (size==0)
            return null;

        Node tmp = sentinel.next;
        sentinel.next = tmp.next;
        sentinel.next.prev = sentinel;
        size -= 1;
        return tmp.item;
    }
    public T removeLast() {
        if (isEmpty() == true)
            return null;

        Node tmp = sentinel.prev;
        sentinel.prev = tmp.prev;
        sentinel.prev.next = sentinel;
        size -= 1;
        return tmp.item;
    }
    public T get(int index) {
        if (index<0 || size==0 || index>=size)
            return null;

        Node tmp_node = sentinel.next;
        int tmp_idx = 0;
        while (tmp_idx <= index) {
            tmp_node = tmp_node.next;
            tmp_idx += 1;
        }

        return tmp_node.item;
    }
    

    public T getRecursive(int index) {
        if (index<0 || size==0 || index>=size)
            return null;        
        private class GetHelper {
            public T helper(Node cur_node, int cur_idx) {
                if (cur_idx==idx)
                    return cur_node.item;
                else return helper(cur_node.next, cur_idx+1);
            }
        }
        GetHelper thisHelp = new GetHelper();
        
        return thisHelp.helper(sentinel.next, 0);
    }
    
    public LinkedListDeque(LinkedListDeque other) {
        size = 0;
        sentinel = new Node(sentinel, null, sentinel);

        for (int i=0; i<other.size(); i+=1) {
            addLast((T) other.get(i));
        }
    }
}
