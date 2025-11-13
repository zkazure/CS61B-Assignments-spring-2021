package deque;

public interface Deque<T> {
    public void addFirst(T item);
    public void addLast(T item);
    public T removeFirst();
    public T removeLast();
    public int size();
    public T get(int idx);
    public void printDeque();
    default public boolean isEmpty() {
        return 0 == size();
    }
}
