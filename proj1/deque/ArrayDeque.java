package deque;

public class ArrayDeque<T> {
    private T[] items;
    private int capacity;
    private int head;
    private int tail;

    private void resize(int new_capacity) {
        T[] new_items = (T[]) new Object[new_capacity];
        System.arraycopy(items, head, new_items, 0, capacity);
        items = new_items;
    }
    
    public ArrayDeque() {
        capacity = 8;
        items = (T[]) new Object[capacity];
        head = 0;
        tail = 0;
    }

    public ArrayDeque(ArrayDeque other) {
        int size = other.size();
        items = (T[]) new Object[size];
        System.arraycopy(other.items, other.head, items, 0, size);
    }

    public void addFirst(T item) {
        if ( (tail+1)%capacity == head )
            resize(capacity * 2);
        
        head = (head + capacity - 1) % capacity;
        items[head] = item;
    }

    public void addLast(T item) {
        if ( (tail+1)%capacity == head )
            resize(capacity * 2);

        tail = (tail+1)%capacity;
        items[tail] = item;
    }

    public T removeFirst() {
        T ret = get(head);
        head = (head+1) % capacity;
        return ret;
    }

    public T removeLast() {
        T ret = get(tail);
        tail = (tail-1+capacity) % capacity;
        return ret;
    }

    public T get(int idx) {
        if (idx < 0 || idx >= size())
            return null;
        return items[idx];
    }

    public boolean isEmpty() {
        if (size()>0)
            return false;
        return true;
    }

    public int size() {
        return head+tail+1;
    }
}
