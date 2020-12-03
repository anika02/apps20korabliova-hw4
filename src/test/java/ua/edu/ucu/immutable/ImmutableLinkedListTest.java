package ua.edu.ucu.immutable;

import org.junit.Test;

import static org.junit.Assert.*;

public class ImmutableLinkedListTest {

//--------------------------------------------------------------------------
    @Test
    public void testImmutableLinkedListEmpty() {
        ImmutableLinkedList linkedList = new ImmutableLinkedList();
        int expResult = 0;
        int actualResult = linkedList.size();
        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testImmutableLinkedListFromArray() {
        Integer[] elements = {3, -5, 1};
        ImmutableLinkedList linkedList = new ImmutableLinkedList(elements);
        Object[] actualResult = linkedList.toArray();
        assertEquals(elements[0], actualResult[0]);
        assertEquals(elements[1], actualResult[1]);
        assertEquals(elements[2], actualResult[2]);
        int expSize = 3;
        int actualSize = linkedList.size();
        assertEquals(expSize, actualSize, 0.00001);
    }

//--------------------------------------------------------------------------
    @Test
    public void testAddFirstGetFirstSize() {
        Integer[] elements = {3, -5};
        ImmutableLinkedList linkedList = new ImmutableLinkedList(elements);
        Integer[] expResult = {1, 3, -5};
        linkedList = linkedList.addFirst(1);
        Object[] actualResult = linkedList.toArray();
        assertEquals(expResult[0], linkedList.getFirst());
        assertEquals(expResult[1], actualResult[1]);
        assertEquals(expResult[2], actualResult[2]);
        int expSize = 3;
        int actualSize = linkedList.size();
        assertEquals(expSize, actualSize, 0.00001);
    }

    @Test
    public void testAddLastGetLastSize() {
        Integer[] elements = {3, -5};
        ImmutableLinkedList linkedList = new ImmutableLinkedList(elements);
        Integer[] expResult = {3, -5, 1};
        linkedList = linkedList.addLast(1);
        Object[] actualResult = linkedList.toArray();
        assertEquals(expResult[0], actualResult[0]);
        assertEquals(expResult[1], actualResult[1]);
        assertEquals(expResult[2], linkedList.getLast());
        int expSize = 3;
        int actualSize = linkedList.size();
        assertEquals(expSize, actualSize, 0.00001);
    }
    @Test
    public void testGetFirstEmpty() {
        ImmutableLinkedList linkedList = new ImmutableLinkedList();
        assertNull(linkedList.getFirst());
    }

    @Test
    public void testGetLastEmpty() {
        ImmutableLinkedList linkedList = new ImmutableLinkedList();
        assertNull(linkedList.getLast());
    }

    @Test
    public void testRemoveFirst() {
        Integer[] elements = {1, 3, -5};
        ImmutableLinkedList linkedList = new ImmutableLinkedList(elements);
        Integer[] expResult = {3, -5};
        linkedList = linkedList.removeFirst();
        Object[] actualResult = linkedList.toArray();
        assertEquals(expResult[0], actualResult[0]);
        assertEquals(expResult[1], actualResult[1]);
    }

    @Test
    public void testRemoveLastSize() {
        Integer[] elements = {3, -5, 1};
        ImmutableLinkedList linkedList = new ImmutableLinkedList(elements);
        Integer[] expResult = {3, -5};
        linkedList = linkedList.removeLast();
        Object[] actualResult = linkedList.toArray();
        assertEquals(expResult[0], actualResult[0]);
        assertEquals(expResult[1], actualResult[1]);
        int expSize = 2;
        int actualSize = linkedList.size();
        assertEquals(expSize, actualSize, 0.00001);
    }

//----------------------------------------------------------------------
    @Test
    public void testAdd() {
        Integer[] elements = {3, -5};
        ImmutableLinkedList linkedList = new ImmutableLinkedList(elements);
        Integer[] expResult = {3, -5, 1};
        linkedList = linkedList.add(1);
        Object[] actualResult = linkedList.toArray();
        assertEquals(expResult[0], actualResult[0]);
        assertEquals(expResult[1], actualResult[1]);
        assertEquals(expResult[2], actualResult[2]);
    }

    @Test
    public void testAddWithIndex() {
        Integer[] elements = {3, -5};
        ImmutableLinkedList linkedList = new ImmutableLinkedList(elements);
        Integer[] expResult = {3, 4, -5};
        linkedList = linkedList.add(1);
        linkedList = linkedList.add(1, 4);
        Object[] actualResult = linkedList.toArray();
        assertEquals(expResult[0], actualResult[0]);
        assertEquals(expResult[1], actualResult[1]);
        assertEquals(expResult[2], actualResult[2]);
    }

    @Test
    public void testAddAll() {
        Integer[] elements = {3};
        Integer[] newElements = {7, 9};
        ImmutableLinkedList linkedList = new ImmutableLinkedList(elements);
        Integer[] expResult = {3, 7, 9};
        linkedList = linkedList.addAll(newElements);
        Object[] actualResult = linkedList.toArray();
        assertEquals(expResult[0], actualResult[0]);
        assertEquals(expResult[1], actualResult[1]);
        assertEquals(expResult[2], actualResult[2]);
    }

    @Test
    public void testAddAllIndex() {
        Integer[] elements = {3, 4};
        Integer[] newElements = {7, 9};
        ImmutableLinkedList linkedList = new ImmutableLinkedList(elements);
        Integer[] expResult = {3, 7, 9, 4};
        linkedList = linkedList.addAll(1, newElements);
        Object[] actualResult = linkedList.toArray();
        assertEquals(expResult[0], actualResult[0]);
        assertEquals(expResult[1], actualResult[1]);
        assertEquals(expResult[2], actualResult[2]);
        assertEquals(expResult[3], actualResult[3]);
    }

//---------------------------------------------------------------------------------
    @Test
    public void testRemoveGet() {
        Integer[] elements = {3, 7, 9, 4};
        ImmutableLinkedList linkedList = new ImmutableLinkedList(elements);
        Integer[] expResult = {3, 7, 4};
        linkedList = linkedList.remove(2);
        assertEquals(expResult[0], linkedList.get(0));
        assertEquals(expResult[1], linkedList.get(1));
        assertEquals(expResult[2], linkedList.get(2));
    }

    @Test
    public void testSet() {
        Integer[] elements = {3, 7, 4};
        ImmutableLinkedList linkedList = new ImmutableLinkedList(elements);
        Integer[] expResult = {1, 2, 3};
        linkedList = linkedList.set(0, 1);
        linkedList = linkedList.set(1, 2);
        linkedList = linkedList.set(2, 3);
        assertEquals(expResult[0], linkedList.get(0));
        assertEquals(expResult[1], linkedList.get(1));
        assertEquals(expResult[2], linkedList.get(2));
    }

    @Test
    public void testIndexOf() {
        Integer[] elements = {3, 4};
        ImmutableLinkedList linkedList = new ImmutableLinkedList(elements);
        assertEquals(0, linkedList.indexOf(3));
        assertEquals(1, linkedList.indexOf(4));
        assertEquals(-1, linkedList.indexOf(0));
    }

//---------------------------------------------------------------------------------
    @Test
    public void testClear() {
        Integer[] elements = {3, 7, 9, 4};
        ImmutableLinkedList linkedList = new ImmutableLinkedList(elements);
        linkedList = linkedList.clear();
        Object[] array = linkedList.toArray();
        assertEquals(0, array.length);
        assertEquals(0, linkedList.size());
    }

    @Test
    public void testIsEmpty() {
        Integer[] elements = {3, 7, 9, 4};
        ImmutableLinkedList emptyLinkedList = new ImmutableLinkedList();
        ImmutableLinkedList linkedList = new ImmutableLinkedList(elements);
        assertTrue(emptyLinkedList.isEmpty());
        assertFalse(linkedList.isEmpty());
    }

    @Test
    public void testToString() {
        Integer[] elements = {3, 7, 9, 4};
        ImmutableLinkedList linkedList = new ImmutableLinkedList(elements);
        String expResult = "[3, 7, 9, 4]";
        String actualResult = linkedList.toString();
        assertEquals(expResult, actualResult);
    }

//---------------------------------------------------------------------------
    @Test(expected = IndexOutOfBoundsException.class)
    public void testIndexErrorAddLess() {
        Integer[] elements = {3, 7, 9, 4};
        ImmutableLinkedList linkedList = new ImmutableLinkedList(elements);
        linkedList.add(-1, 4);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testIndexErrorAddMore() {
        Integer[] elements = {3, 7, 9, 4};
        ImmutableLinkedList linkedList = new ImmutableLinkedList(elements);
        linkedList.add(linkedList.size() + 1, 5);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testIndexErrorRemoveLess() {
        Integer[] elements = {3, 7, 9, 4};
        ImmutableLinkedList linkedList = new ImmutableLinkedList(elements);
        linkedList.remove(-1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testIndexErrorRemoveMore() {
        Integer[] elements = {3, 7, 9, 4};
        ImmutableLinkedList linkedList = new ImmutableLinkedList(elements);
        linkedList.remove(linkedList.size());
    }

//---------------------------------------------------------------------------
    @Test
    public void testImmutable() {
        Integer[] elements = {3, 7, 9};
        ImmutableLinkedList linkedList = new ImmutableLinkedList(elements);
        linkedList.remove(2);
        linkedList.addAll(0, elements);
        linkedList.set(1, 5);

        assertEquals(elements[0], linkedList.get(0));
        assertEquals(elements[1], linkedList.get(1));
        assertEquals(elements[2], linkedList.get(2));
    }
}
