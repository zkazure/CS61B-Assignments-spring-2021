package deque;


import java.util.Iterator;

public class LinkedListDeque<T> implements Iterable<T>, Deque<T>{
    private int size;
    private Node sentinel;

    private class Node {
    
        private Node prev;
        private T item;
        private Node next;

        public Node() {
            prev = null;
            next = null;
        }
    }

    public LinkedListDeque() {
        size = 0;
        // initialize sentinel node so prev/next point to itself
        sentinel = new Node();
        sentinel.item = null;
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void addFirst(T item) {
        size += 1;
        Node new_item = new Node(); 
        new_item.prev = sentinel; 
        new_item.item = item; 
        new_item.next = sentinel.next;

        sentinel.next.prev = new_item;
        sentinel.next = new_item;
    }

    @Override
    public void addLast(T item) {
        size += 1;
        Node new_item = new Node();
        new_item.prev = sentinel.prev;
        new_item.item = item;
        new_item.next = sentinel;
        
        sentinel.prev.next = new_item;
        sentinel.prev = new_item;
    }

    @Override
    public void printDeque() {
        Node tmp = sentinel.next;
        while (tmp != sentinel) {
            System.out.print(tmp.item + " ");
            tmp = tmp.next;
        }
        System.out.println();
    }

    @Override
    public T removeFirst() {
        if (size==0)
            return null;

        Node tmp = sentinel.next;
        sentinel.next = tmp.next;
        sentinel.next.prev = sentinel;
        size -= 1;
        return tmp.item;
    }

    @Override
    public T removeLast() {
        if (isEmpty() == true)
            return null;

        Node tmp = sentinel.prev;
        sentinel.prev = tmp.prev;
        sentinel.prev.next = sentinel;
        size -= 1;
        return tmp.item;
    }

    @Override
    public T get(int index) {
        if (index<0 || size==0 || index>=size)
            return null;

        Node tmp_node = sentinel.next;
        for (int i = 0; i < index; i++) {
            tmp_node = tmp_node.next;
        }
        return tmp_node.item;
    }
    
 
    private T getRecursiveHelper(Node curr, int index, int count) {
        if (count == index) 
            return curr.item;

        if (curr == sentinel) 
            return null;
        
        return getRecursiveHelper(curr.next, index, count + 1);
    }

    public T getRecursive(int index) {
        if (index < 0 || size == 0 || index >= size)
            return null;
            
        return getRecursiveHelper(sentinel.next, index, 0);
    }

    // public LinkedListDeque(LinkedListDeque<T> other) {
    //     size = 0;
    //     // initialize empty sentinel, then add items from other
    //     sentinel = new Node();
    //     sentinel.item = null;
    //     sentinel.prev = sentinel;
    //     sentinel.next = sentinel;

    //     for (int i = 0; i < other.size(); i += 1) {
    //         addLast(other.get(i));
    //     }
    // }

    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }
    private class LinkedListDequeIterator implements Iterator<T> {
        private int wizPos;
        public LinkedListDequeIterator() {
            wizPos = 0;
        }
        public boolean hasNext() {
            return wizPos < size;
        }
        public T next() {
            T returnItem = get(wizPos);
            wizPos += 1;
            return returnItem;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (o.getClass() != this.getClass()) return false;

        LinkedListDeque<T> other = (LinkedListDeque<T>) o;
        if (other.size() != this.size()) return false;
        for (int i=0; i<this.size(); i+=1) {
            if (other.get(i).equals(this.get(i))) {
                return false;
            }
        }
        return true;
    }
}
