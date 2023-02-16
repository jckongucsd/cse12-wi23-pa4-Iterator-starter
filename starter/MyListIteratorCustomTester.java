// DO NOT CHANGE THE METHOD NAMES

import static org.junit.Assert.*;
import org.junit.*;
import java.util.*;

public class MyListIteratorCustomTester {
        MyLinkedList linkedList;
        MyLinkedList.MyListIterator listIter;

    /**
     * This sets up the test fixture. JUnit invokes this method before
     * every testXXX method. The @Before tag tells JUnit to run this method
     * before each test.
     */
    @Before
    public void setUp() throws Exception {

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
    @Test(expected=NoSuchElementException.class)
    public void testNextEnd() {
        listIter.next();
        listIter.next();
        listIter.next();
        listIter.next();

    }

    /**
     * Aims to test the previous() method when iterator is at the start of the 
     * list 
     */
    @Test(expected=NoSuchElementException.class)
    public void testPreviousStart() {
    listIter.previous();
        
    }

    /**
     * Aims to test the add(E e) method when an invalid element is added
     */
    @Test
    public void testAddInvalid() {

        listIter.next();
        assertThrows(NullPointerException.class, () -> {
            listIter.add(null);
        });


    }

    /**
     * Aims to test the set(E e) method when canRemoveOrSet is false
     */
    @Test(expected=IllegalStateException.class)
    public void testCantSet() {

        listIter.next();
        listIter.next();
        listIter.canRemoveOrSet = false;
    }


    /**
     * Aims to test the set(E e) method when an invalid element is set
     */
    @Test(expected=NullPointerException.class)
    public void testSetInvalid() {

        listIter.next();
        listIter.set(null);
    }

    /**
     * Aims to test the remove() method when canRemoveOrSet is false
     */
    @Test(expected=IllegalStateException.class)
    public void testCantRemove() {

        listIter.next();
        listIter.next();
        listIter.canRemoveOrSet = false;
        listIter.remove();

    }

    /**
     * Aims to tests the hasNext() method at the end of a list
     */
    @Test
    public void testHasNextEnd() {
        listIter.next();
        listIter.next();
        listIter.next();
        assertEquals("hasNext() at the end of a list", false,
                listIter.hasNext());
    }

    /**
     * Aims to test the hasPrevious() method at the start of a list
     */
    @Test
    public void testHasPreviousStart() {
        assertEquals("hasPrevious() at the end of a list", false,
            listIter.hasPrevious());

    }

    /**
     * Aims to test the previousIndex() method at the start of a list
     */
    @Test
    public void testPreviousIndexStart() {
        assertEquals("previousIndex() at the end of a list", -1,
            listIter.previousIndex());
    }

    /**
     * Aims to test the nextIndex() method at the end of a list
     */
    @Test
    public void testNextIndexEnd() {
        assertEquals("previousIndex() at the end of a list", linkedList.size(),
            listIter.nextIndex());
    }
}
