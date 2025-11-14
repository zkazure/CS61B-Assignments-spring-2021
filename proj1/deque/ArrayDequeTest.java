package deque;

import org.junit.Test;
import static org.junit.Assert.*;

public class ArrayDequeTest {
    @Test
    public void addIsEmptySizeTest() {
        ArrayDeque<String> adq1 = new ArrayDeque<>();
        assertTrue("A newly initialized ArrayDeque should be empty", adq1.isEmpty());

        adq1.addFirst("front");
        assertEquals(1, adq1.size());
        assertFalse("adq1 should now contain 1 item", adq1.isEmpty());

        adq1.addLast("middle");
        assertEquals(2, adq1.size());

        adq1.addLast("end");
        assertEquals(3, adq1.size());

        System.out.println("Printing out deque: ");
        adq1.printDeque();
    }

    @Test
    public void addRemoveTest() {
        ArrayDeque<Integer> adq1 = new ArrayDeque<>();
        assertTrue(adq1.isEmpty());

        adq1.addFirst(0);
        assertFalse(adq1.isEmpty());

        adq1.removeFirst();
        assertTrue(adq1.isEmpty());
    }

    @Test
    public void removeEmptyTest() {
        ArrayDeque<Integer> adq1 = new ArrayDeque<>();
        adq1.addFirst(3);

        adq1.removeLast();
        adq1.removeFirst();
        adq1.removeLast();
        adq1.removeFirst();

        int size = adq1.size();
        assertEquals(0, adq1.size());
    }

    @Test
    /* Check if you can create LinkedListDeques with different parameterized types*/
    public void multipleParamTest() {
        ArrayDeque<String>  adq1 = new ArrayDeque<String>();
        ArrayDeque<Double>  adq2 = new ArrayDeque<Double>();
        ArrayDeque<Boolean> adq3 = new ArrayDeque<Boolean>();

        adq1.addFirst("string");
        adq2.addFirst(3.14159);
        adq3.addFirst(true);

        String s = adq1.removeFirst();
        double d = adq2.removeFirst();
        boolean b = adq3.removeFirst();
    }

    @Test
    public void emptyNullReturnTest() {
        ArrayDeque<Integer> adq1 = new ArrayDeque<>();
        boolean passed1 = false;
        boolean passed2 = false;

        assertEquals("Should return null when removeFirst is called on an empty Deque,", null, adq1.removeFirst());
        assertEquals("Should return null when removeLast is called on an empty Deque,", null, adq1.removeLast());
    }

    @Test
    public void bigArrayDequeTest() {
        ArrayDeque<Integer> adq1 = new ArrayDeque<>();
        for (int i=0; i<1000000; i++) {
            adq1.addLast(i);
        }
        for (double i=0; i<500000; i++) {
            assertEquals(i, (double)adq1.removeFirst(), 0.0);
        }
        for (double i=999999; i>500000; i--) {
            assertEquals(i, (double)adq1.removeLast(), 0.0);
        }
    }

//    @Test
//    public void resizeTest() {
//        ArrayDeque<Integer> adq1 = new ArrayDeque<>();
//        for (int i=0; i<33; ++i) {
//            adq1.addLast(i);
//        }
//        assertEquals("wrong the capacity is "+adq1.capacity(), 64, adq1.capacity());
//        assertEquals(33, adq1.size());
//    }
//    @Test
//    public void usageTest() {
//        ArrayDeque<Integer> arr = new ArrayDeque<>();
//        for (int i=0; i<32; ++i) {
//            arr.addLast(i);
//        }
//        for (int i=0; i<25; ++i) {
//            arr.removeFirst();
//        }
//        double usage = (double)arr.size()/arr.capacity();
//        assertTrue("wrong usage ratio is "+usage, usage>0.25);
//    }

    @Test
    public void equalsTest() {
        ArrayDeque<Integer> adq1 = new ArrayDeque<>();
        ArrayDeque<Integer> adq2 = new ArrayDeque<>();

        for (int i=0; i<100; i+=1) {
            adq1.addLast(i);
            adq2.addLast(i);
        }

        assertTrue(adq1.equals(adq2));
    }
}

