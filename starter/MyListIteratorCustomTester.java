// DO NOT CHANGE THE METHOD NAMES

import static org.junit.Assert.*;
import org.junit.*;

public class MyListIteratorCustomTester {

    /**
     * This sets up the test fixture. JUnit invokes this method before
     * every testXXX method. The @Before tag tells JUnit to run this method
     * before each test.
     */
    @Before
    public void setUp() throws Exception {
        private MyLinkedList linkedList;
        private MyLinkedList.MyListIterator listIter;

        linkedList = new MyLinkedList();
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.add(4);
        listIter = linkedList.new MyListIterator();
    }

    /**
     * Aims to test the next() method when iterator is at end of the list 
     */
    @Test
    public void testNextEnd() {
        linkedList.next();
        linkedList.next();
        linkedList.next();
        assertThrows(NoSuchElementException.class, () -> {
            linkedList.next();
        });

    }

    /**
     * Aims to test the previous() method when iterator is at the start of the 
     * list 
     */
    @Test
    public void testPreviousStart() {
        assertThrows(NoSuchElementException.class, () -> {
            linkedList.previous();
        });
        
    }

    /**
     * Aims to test the add(E e) method when an invalid element is added
     */
    @Test
    public void testAddInvalid() {

        linkedList.next();
        assertThrows(NullPointerException.class, () -> {
            linkedList.add(null);
        });


    }

    /**
     * Aims to test the set(E e) method when canRemoveOrSet is false
     */
    @Test
    public void testCantSet() {

        iterator.next();
        iterator.next();
        iterator.canRemoveOrSet = false;
        assertThrows(IllegalStateException.class, () -> {
            iterator.set(7);
        });
    }


    /**
     * Aims to test the set(E e) method when an invalid element is set
     */
    @Test
    public void testSetInvalid() {

        iterator.next();
        assertThrows(NullPointerException.class, () -> {
            iterator.set(null);
        });
    }

    /**
     * Aims to test the remove() method when canRemoveOrSet is false
     */
    @Test
    public void testCantRemove() {

        iterator.next();
        iterator.next();
        iterator.canRemoveOrSet = false;
        assertThrows(IllegalStateException.class, () -> {
            iterator.remove();
        });

    }

    /**
     * Aims to tests the hasNext() method at the end of a list
     */
    @Test
    public void testHasNextEnd() {
        iterator.next();
        iterator.next();
        iterator.next();
        assertEquals("hasNext() at the end of a list", false,
                iterator.hasNext());
    }

    /**
     * Aims to test the hasPrevious() method at the start of a list
     */
    @Test
    public void testHasPreviousStart() {
        assertEquals("hasPrevious() at the end of a list", false,
            iterator.hasPrevious());

    }

    /**
     * Aims to test the previousIndex() method at the start of a list
     */
    @Test
    public void testPreviousIndexStart() {
        assertEquals("previousIndex() at the end of a list", -1,
            iterator.previousIndex());
    }

    /**
     * Aims to test the nextIndex() method at the end of a list
     */
    @Test
    public void testNextIndexEnd() {
        assertEquals("previousIndex() at the end of a list", linkedList.size(),
            iterator.nextIndex());
    }
}
