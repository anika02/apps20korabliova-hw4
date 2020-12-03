package ua.edu.ucu.immutable;

import java.util.Iterator;

public class Queue {
    private ImmutableLinkedList queue = new ImmutableLinkedList();

    public Queue() {
    }

    Object peek() {
        return queue.getFirst();
    }

    Object dequeue() {
        Object temp = this.peek();
        this.queue = this.queue.removeFirst();
        return temp;
    }

    public void enqueue(Object e) {
        this.queue = this.queue.addLast(e);
    }

    public <Type> Iterator<Type> iterator() {
        return new Iterator<Type>() {

            @Override
            public boolean hasNext() {
                return !queue.isEmpty();
            }

            @Override
            public Type next() {
                return (Type) dequeue();
            }
        };
    }
}
