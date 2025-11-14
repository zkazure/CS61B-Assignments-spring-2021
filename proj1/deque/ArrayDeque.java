package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Iterable<T>, Deque<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    private void resize(int new_capacity) {
        T[] new_items = (T[]) new Object[new_capacity];
        int first = (nextFirst+1)%items.length;
        if (first+size<=capacity()) {
            System.arraycopy(items, first, new_items, 0, size);
        } else {
            int right = capacity()-first;
            System.arraycopy(items, first, new_items, 0, right);
            System.arraycopy(items, 0, new_items, right, size-right);
        }
        nextFirst = new_capacity-1;
        nextLast = size;
        items = new_items;
    }

    private int capacity() {
        return items.length;
    }
    
    public ArrayDeque() {
        items = (T[]) new Object[8];
        nextFirst = 0;
        nextLast = 1;
        size = 0;
    }

    // public ArrayDeque(ArrayDeque other) {
    //     size = other.size();
    //     int capacity = other.capacity();
    //     items = (T[]) new Object[capacity];
    //     int first = (other.nextFirst+1)%capacity; 
    //     if (first+size <= capacity) {
    //         System.arraycopy(other.items, first, items, 0, size);
    //     } else {
    //         int right = capacity-first; 
    //         System.arraycopy(other.items, first, items, 0, right);
    //         System.arraycopy(other.items, 0, items, right, size-right);
    //     }
        
    //     nextFirst = capacity -1;
    //     nextLast = size;
    // }

    @Override
    public void addFirst(T item) {
        if (size == items.length) {
            resize(items.length * 2);
        }

        items[nextFirst] = item;
        size += 1;
        nextFirst = (nextFirst-1+items.length)%items.length;
    }

    @Override
    public void addLast(T item) {
        if (size == items.length) {
            resize(items.length * 2);
        }
        items[nextLast] = item; 
        size += 1; 
        nextLast = (nextLast+1)%items.length;
    }

    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        nextFirst = (nextFirst+1)%items.length;
        size -= 1;
        T ret = items[nextFirst];
        if (items.length>=16 && size*4 < items.length) {
            resize(items.length / 2);
        }

        return ret;
    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        nextLast = (nextLast-1+items.length)%items.length;
        size -= 1; 
        T ret = items[nextLast];
        if (items.length>=16 && size*4 < items.length) {
            resize(items.length / 2);
        }

        return ret;
    }

    @Override
    public T get(int idx) {
        if (idx>=size || idx<0)
            return null;
//        if (idx <= nextFirst && idx >= nextLast)
//            return null;
        idx = (nextFirst +1 + idx) % items.length;
        return items[idx];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        for (int i=0; i<size; i+=1) {
            int idx = (nextFirst+1+i)%size;
            System.out.print(items[idx] + " ");
        }
        System.out.println();
    }

    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }
    private class ArrayDequeIterator implements Iterator<T> {
        private int wizPos;
        public ArrayDequeIterator() {
            wizPos = 0;
        }
        public boolean hasNext() {
            return wizPos < size;
        }
        public T next() {
            T returnItem = items[wizPos];
            wizPos += 1;
            return returnItem;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;

        if (o instanceof Deque) {
            Deque<T> other = (Deque<T>) o;
            if (other.size() != this.size()) {
                return false;
            }
            for (int i=0; i<this.size(); i++) {
                if (other.get(i) != this.get(i)) {
                    return false;
                }
            }
        }

        return true;
    }
}
