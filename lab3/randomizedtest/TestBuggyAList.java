package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
    // YOUR TESTS HERE
    @Test
    public void testThreeAddThreeRemove() {
        AListNoResizing<Integer> correct = new AListNoResizing<>();
        BuggyAList<Integer> buggy = new BuggyAList<>();
        for (int i=0; i<3; i+=1) {
            correct.addLast(i);
            buggy.addLast(i);
            assertEquals(correct.getLast(), buggy.getLast());
        }
        assertEquals(correct.size(), buggy.size());

        for (int i=0; i<3; i+=1) {
            assertEquals(correct.removeLast(), buggy.removeLast());
        }
    }

    @Test
    public void randomizedTest() {
        AListNoResizing<Integer> correct = new AListNoResizing<>();
        BuggyAList<Integer> buggy = new BuggyAList<>();

        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                correct.addLast(randVal);
                buggy.addLast(randVal);
//                System.out.println("addLast(" + randVal + ")");
            } else if (operationNumber == 1) {
                // size
                int correct_size = correct.size();
                int buggy_size = buggy.size();
//                System.out.println("correct_size: " + correct_size);
//                System.out.println("buggy_size: " + buggy_size);
            } else if (operationNumber == 2) {
                if (correct.size() > 0) {
                    int correct_last = correct.getLast();

//                    System.out.println("correct.getLast(" + correct_last + ")");
//                    System.out.println("buggy.getLast(" + buggy_last + ")");
//                    System.out.println("correct_removeLast(" + correct.removeLast() + ")");
//                    System.out.println("buggy_removeLast(" + buggy.removeLast() + ")");
                }
                if (buggy.size() > 0) {
                    int buggy_last = buggy.getLast();
                }
            } else if (operationNumber == 3) {
                if (correct.size() > 0) {
                    correct.removeLast();
                }
                if (buggy.size() > 0) {
                    buggy.removeLast();
                }

            }
        }
    }
}
