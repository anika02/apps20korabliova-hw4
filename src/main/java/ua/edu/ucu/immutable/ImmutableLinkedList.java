package ua.edu.ucu.immutable;

import java.util.Arrays;

public final class ImmutableLinkedList implements ImmutableList {
    private int size = 0;
    private Node head = null;
    private Node tail = null;

    public static class Node {
        private final Object data;
        private Node next;

        public Node(Object data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    public ImmutableLinkedList() {
    }

    public ImmutableLinkedList(Object[] objects) {
        this.size = objects.length;
        if (this.size != 0) {
            this.head = new Node(objects[0], null);
            Node temp = this.head;
            for (int i = 1; i < this.size; ++i) {
                temp.next = new Node(objects[i], null);
                temp = temp.next;
            }
            this.tail = temp;
        }
    }

    private void indexError(int index, int check) {
        // check we use when we have adding elements
        if ((index < 0) || (this.size + check <= index)) {
            throw new IndexOutOfBoundsException();
        }
    }

    public ImmutableLinkedList addFirst(Object e) {
        return this.add(0, e);
    }

    public ImmutableLinkedList addLast(Object e) {
        return this.add(e);
    }

    public Object getFirst() {
        if (this.isEmpty()) {
            return null;
        }
        return head.data;
    }

    public Object getLast() {
        if (this.isEmpty()) {
            return null;
        }
        return tail.data;
    }

    public ImmutableLinkedList removeFirst() {
        return this.remove(0);
    }

    public ImmutableLinkedList removeLast() {
        return this.remove(this.size - 1);
    }

    @Override
    public ImmutableLinkedList add(Object e) {
        return this.add(this.size, e);
    }

    @Override
    public ImmutableLinkedList add(int index, Object e) {
        return this.addAll(index, new Object[]{e});
    }

    @Override
    public ImmutableLinkedList addAll(Object[] c) {
        return this.addAll(this.size, c);
    }

    @Override
    public ImmutableLinkedList addAll(int index, Object[] c) {
        indexError(index, 1);
        Object[] array = this.toArray();
        Object[] newArray = new Object[this.size + c.length];
        System.arraycopy(array, 0, newArray, 0, index);
        System.arraycopy(c, 0, newArray, index, c.length);
        if (this.size > index) {
            System.arraycopy(array, index,
                    newArray, index + c.length, this.size - index);
        }
        return new ImmutableLinkedList(newArray);
    }

    @Override
    public Object get(int index) {
        indexError(index, 0);
        Node temp = this.head;
        for (int i = 0; i < index; ++i) {
            temp = temp.next;
        }
        return temp.data;
    }

    @Override
    public ImmutableLinkedList remove(int index) {
        indexError(index, 0);
        Object[] array = this.toArray();
        Object[] newArray = new Object[this.size - 1];
        System.arraycopy(array, 0, newArray, 0, index);
        System.arraycopy(array, index + 1,
                newArray, index, this.size - 1 - index);
        return new ImmutableLinkedList(newArray);
    }

    @Override
    public ImmutableLinkedList set(int index, Object e) {
        indexError(index, 0);
        Object[] array = this.toArray();
        array[index] = e;
        return new ImmutableLinkedList(array);
    }

    @Override
    public int indexOf(Object e) {
        Node temp = this.head;
        int counter = 0;
        while (counter < this.size) {
            if (temp.data.equals(e)) {
                return counter;
            }
            temp = temp.next;
            ++counter;
        }
        return -1;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public ImmutableLinkedList clear() {
        return new ImmutableLinkedList();
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public Object[] toArray() {
        Object[] objects = new Object[this.size];
        Node temp = this.head;
        for (int i = 0; i < this.size; ++i) {
            objects[i] = temp.data;
            temp = temp.next;
        }
        return objects;
    }

    @Override
    public String toString() {
        return Arrays.toString(this.toArray());
    }


}
