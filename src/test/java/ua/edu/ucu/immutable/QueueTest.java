package ua.edu.ucu.immutable;

import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

public class QueueTest {

    @Test
    public void testQueue() {
        Queue queue = new Queue();
        assertNull(queue.peek());
    }

    @Test
    public void testPeekEnqueueDequeue() {
        Queue queue = new Queue();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);

        assertEquals(1, queue.peek());
        assertEquals(1, queue.dequeue());
        assertEquals(2, queue.peek());
        assertEquals(2, queue.dequeue());
        assertEquals(3, queue.peek());
        assertEquals(3, queue.dequeue());

    }

    @Test
    public void testIterator() {
        Queue queue = new Queue();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);

        Iterator<Object> it = queue.iterator();
        assertEquals(1, it.next());
        assertEquals(2, it.next());
        assertEquals(3, it.next());
        assertFalse(it.hasNext());
    }
}
