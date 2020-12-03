package ua.edu.ucu.tries;

import org.junit.Test;

import static org.junit.Assert.*;

public class TupleTest {
    @Test
    public void testTuple() {
        Tuple tuple = new Tuple("hello", 5);
        assertEquals("hello", tuple.term);
        assertEquals(5, tuple.weight);
    }

}
