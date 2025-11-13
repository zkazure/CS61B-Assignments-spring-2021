package deque;

import org.junit.Test;
import java.util.Comparator;
import static org.junit.Assert.*;

public class MaxArrayDequeTest {
    @Test
    public void maxNumberTest() {
        MaxArrayDeque<Integer> mad1 = new MaxArrayDeque<>(Integer::compare);
        for (int i=0; i<100; i+=1) {
            mad1.addLast(i);
        }
        int actual = mad1.max();
        assertEquals(99, actual);

        mad1.addFirst(123);
        actual = mad1.max(Integer::compare);
        assertEquals(123, actual);
    }
}