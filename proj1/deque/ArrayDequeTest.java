package deque;

import org.juint.Test;
import static org.junit.Assert.*;

public class ArrayDequeTest {
    @Test
    public void addIsEmptySizeTest() {
        ArrayDeque<String> lld1 = new ArrayDeque<>();
        assertTrue("A newly initialize should be empty", lld1.isEmpty());
    }
}
