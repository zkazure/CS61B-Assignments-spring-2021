public class AList<T> {
    private T[] items;
    private int size;
    private int RFACTOR = 2;
    
    private void resize(int capacity) {
        T[] tmp = (T[]) new Object[capacity];
        System.arraycopy(items, 0, tmp, 0, size);
        items = tmp;
    }
    
    public AList() {
        items = (T[]) new Object[10];
        size = 0;
    }
    
    public void addLast(T x) {
        if (size >= items.length) {
            resize(size * RFACTOR);
        }
        items[size] = x;
        size += 1;
    }
    
    public T getLast() {
        return items[size-1];
    }
    
    public T get(int i) {
        return items[i];
    }
    
    public int size() {
        return size;
    }
    
    public T removeLast() {
        size -= 1;
        return items[size];
    }
}
