package DebugExercise;

import static org.junit.Assert.*;
import org.junit.Test;

public class DebugExercise2Test {
    @Test
    public void testArrayMax() {
        int[] a = {10};
        int[] b = {5};
        int expected = 10;
        int actual = DebugExercise2.arrayMax(a, b)[0];
        assertEquals(expected, actual);
    }
}
