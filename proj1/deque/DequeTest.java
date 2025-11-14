package deque;

import org.junit.Test;
import static org.junit.Assert.*;

public class DequeTest {
    @Test
    public void equalsTest() {
        LinkedListDeque<Integer> lld = new LinkedListDeque<>();
        ArrayDeque<Integer> adq = new ArrayDeque<>();
        for (int i=0; i<100; i++) {
            lld.addLast(i);
            adq.addLast(i);
        }
        assertEquals(lld, adq);
    }
}
