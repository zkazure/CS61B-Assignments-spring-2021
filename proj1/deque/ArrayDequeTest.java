package deque;

import org.junit.Test;
import static org.junit.Assert.*;

public class ArrayDequeTest {
    @Test
    public void addIsEmptySizeTest() {
        ArrayDeque<String> lld1 = new ArrayDeque<>();
        assertTrue(lld1.isEmpty());
    }
    @Test 
    public void addRemoveTest() {
        ArrayDeque<Integer> arr = new ArrayDeque<>();
        arr.addFirst(0);
        assertEquals(0, (int)arr.get(0));
        arr.addLast(1);
        assertEquals(1, (int)arr.get(1)); 

        arr.removeFirst(); 
        assertEquals(1, (int)arr.get(0)); 
        arr.removeLast();
        assertTrue(arr.isEmpty());
    }
    @Test 
    public void resizeTest() {
        ArrayDeque<Integer> arr = new ArrayDeque<>(); 
        for (int i=0; i<33; ++i) {
            arr.addLast(i);
        }
        assertEquals("wrong the capacity is "+arr.capacity(), 64, arr.capacity());
        assertEquals(33, arr.size());
    }
    @Test 
    public void usageTest() {
        ArrayDeque<Integer> arr = new ArrayDeque<>(); 
        for (int i=0; i<32; ++i) {
            arr.addLast(i);
        }
        for (int i=0; i<25; ++i) {
            arr.removeFirst();
        }
        double usage = (double)arr.size()/arr.capacity();
        assertTrue("wrong usage ratio is "+usage, usage>0.25);
    }
}

