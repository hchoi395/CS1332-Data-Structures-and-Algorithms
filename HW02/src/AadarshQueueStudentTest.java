import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import org.junit.Before;
import org.junit.Test;

/**
 * This is a slightly more comprehensive set of unit tests for ArrayQueue and LinkedQueue.
 *
 * The purpose of these tests is to cover a few edge-cases not covered by the TAs, although
 * I most definitely may have missed some!
 *
 * Note that this doesn't cover any of the cases the TAs cover, and so should be used as a
 * complement to their tests, not a replacement.
 *
 * @author Aadarsh Vavilikolanu
 * @version 1.0
 */
public class AadarshQueueStudentTest {

    /* Change this if you're experiencing timeout errors for a lot of your test, as it could be
       the result of a slower computer. If you're only experiencing it for a single test, however,
       it could be the result of a slow method  (maybe check your time complexity?) */
    private static final int TIMEOUT = 200;

    private ArrayQueue<String> array;
    private LinkedQueue<String> linked;



    // =============================== SETUP TESTS ========================= //

    @Before
    public void setup() {
        array = new ArrayQueue<>();
        linked = new LinkedQueue<>();
    }

    @Test(timeout = TIMEOUT)
    public void testInitialization() {
        assertEquals(0, array.size());
        assertArrayEquals(new Object[ArrayQueue.INITIAL_CAPACITY],
                array.getBackingArray());
        assertEquals(0, linked.size());
        assertNull(linked.getHead());
    }



    // ============================ ERROR TESTS ============================ //

    /**
     * The following tests ensure that the proper errors are being raised when the
     * error conditions as specified in the javadoc for the methods are met.
     */


    // Array Implementations //
    @Test(expected = java.lang.IllegalArgumentException.class)
    public void testArrayEnqueueNull() {
        array.enqueue(null);
    }

    @Test(expected = java.util.NoSuchElementException.class)
    public void testArrayDequeueEmpty() {
        array.dequeue();
    }

    @Test(expected = java.util.NoSuchElementException.class)
    public void testArrayPeekEmpty() {
        array.peek();
    }


    // Linked Implementations //
    @Test(expected = java.lang.IllegalArgumentException.class)
    public void testLinkedEnqueueNull() {
        linked.enqueue(null);
    }

    @Test(expected = java.util.NoSuchElementException.class)
    public void testLinkedDequeueEmpty() {
        linked.dequeue();
    }

    @Test(expected = java.util.NoSuchElementException.class)
    public void testLinkedPeekEmpty() {
        linked.peek();
    }



    // ======================= ARRAY SPECIFIC TESTS ======================== //

    /**
     * The following tests test a couple of quirks regarding specifically the array implementation
     * of queues.
     */


    /**
     * Testing that the queue is able to resize properly above the default initial capacity.
     */
    @Test(timeout = TIMEOUT)
    public void testArrayResize() {

        array.enqueue("0a");    // 0a
        array.enqueue("1a");    // 0a, 1a
        array.enqueue("2a");    // 0a, 1a, 2a
        array.enqueue("3a");    // 0a, 1a, 2a, 3a
        array.enqueue("4a");    // 0a, 1a, 2a, 3a, 4a
        array.enqueue("5a");    // 0a, 1a, 2a, 3a, 4a, 5a
        array.enqueue("6a");    // 0a, 1a, 2a, 3a, 4a, 5a, 6a
        array.enqueue("7a");    // 0a, 1a, 2a, 3a, 4a, 5a, 6a, 7a
        array.enqueue("8a");    // 0a, 1a, 2a, 3a, 4a, 5a, 6a, 7a, 8a
        array.enqueue("9a");    // 0a, 1a, 2a, 3a, 4a, 5a, 6a, 7a, 8a, 9a

        assertEquals(10, array.size());

        /* Testing that the front index was set to 0 when resizing the array, as well as that
           the array was resized to the proper length.
           Note that I might be slightly wrong in my interpretation of the directions and the
           array size might need to be 20 rather than 18 here (assuming an initial capacity of 9).
           If that's the case, please inform me and I'll update the gist.
        */

        Object[] expected = new Object[ArrayQueue.INITIAL_CAPACITY * 2];
        expected[0] = "0a";
        expected[1] = "1a";
        expected[2] = "2a";
        expected[3] = "3a";
        expected[4] = "4a";
        expected[5] = "5a";
        expected[6] = "6a";
        expected[7] = "7a";
        expected[8] = "8a";
        expected[9] = "9a";

        assertArrayEquals(expected, array.getBackingArray());
    }

    /**
     * Testing that peeking through the array works even after dequeueing, as the front index
     * should be different after dequeueing.
     */
    @Test(timeout = TIMEOUT)
    public void testArrayPeekAfterDequeue() {

        array.enqueue("0a");    // 0a
        array.enqueue("1a");    // 0a, 1a
        array.enqueue("2a");    // 0a, 1a, 2a
        array.enqueue("3a");    // 0a, 1a, 2a, 3a
        array.enqueue("4a");    // 0a, 1a, 2a, 3a, 4a
        assertEquals(5, array.size());

        assertSame("0a", array.peek());
        assertSame("0a", array.dequeue());

        assertEquals(4, array.size());

        assertSame("1a", array.peek());
    }



    // ======================= LINKED SPECIFIC TESTS ======================= //

    /**
     * The following tests test a couple of quirks regarding specifically the linked implementation
     * of queues.
     */


    /**
     * Performs a host of different tests to make sure that your linked queue was
     * created properly when the first element was added.
     */
    @Test(timeout = TIMEOUT)
    public void testLinkedEnqueueWhenNull() {

        linked.enqueue("0a");   // 0a

        assertEquals(1, linked.size()); // Tests to make sure the size was set properly.

        assertNotNull(linked.getHead()); // Tests if the head is instantiated.
        assertNotNull(linked.getTail()); // Tests if the tail is instantiated.

        assertNull(linked.getHead().getNext()); // Tests if the head's "next" node is null.
        assertNull(linked.getTail().getNext()); // Tests if the tail's "next" node is null.

        assertSame("0a", linked.getHead().getData()); // Tests if the head's data is what's added.
        assertSame("0a", linked.getTail().getData()); // Tests if the tail's data is what's added.

        assertSame(linked.getHead(), linked.getTail()); // Tests if the head/tail point to the same memory address.
    }

    /**
     * In complement to the method of the same name in QueueStudentTest.java, tests if head/tail are properly set.
     */
    @Test(timeout = TIMEOUT)
    public void testLinkedEnqueueTail() {

        linked.enqueue("0a");   // 0a
        linked.enqueue("1a");   // 0a, 1a

        assertEquals(2, linked.size());

        LinkedNode<String> cur = linked.getHead();
        assertNotNull(cur);
        assertSame("0a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertSame("1a", cur.getData());
        assertSame(cur, linked.getTail()); // Really the only novel part of this test compared to the other one...
    }

    /**
     * In complement to the method of the same name in QueueStudentTest.java, tests if head/tail are properly set.
     * In addition, tests if dequeue works properly when dequeueing a singleton queue.
     */
    @Test(timeout = TIMEOUT)
    public void testLinkedDequeueComprehensive() {

        linked.enqueue("0a");   // 0a
        linked.enqueue("1a");   // 0a, 1a
        linked.enqueue("2a");   // 0a, 1a, 2a

        assertEquals(3, linked.size());

        assertSame("0a", linked.dequeue());

        LinkedNode<String> cur = linked.getHead();
        assertNotNull(cur);
        assertSame("1a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertSame("2a", cur.getData());
        assertSame(cur, linked.getTail());

        assertSame("1a", linked.dequeue());
        assertSame("2a", linked.dequeue());

        assertEquals(0, linked.size());

        assertNull(linked.getHead());
        assertNull(linked.getTail());
    }

}
