package deque;

import java.util.Comparator;

public  class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator<T> comp;

    public MaxArrayDeque(Comparator<T> c) {
        super();
        comp = c;
    }

    public T max(Comparator<T> c) {
        if (isEmpty() || c==null) {
            return null;
        }

        int max_idx = 0;
        for (int i=1; i<size(); i+=1) {
            if (c.compare(get(i), get(max_idx)) > 0) {
                max_idx = i;
            }
        }
        return get(max_idx);
    }

    public T max() {
        return max(comp);
    }
}