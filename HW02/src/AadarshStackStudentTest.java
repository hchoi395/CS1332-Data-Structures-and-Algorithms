import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

/**
 * This is a basic set of unit tests for ArrayStack and LinkedStack.
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
public class AadarshStackStudentTest {

    /* Change this if you're experiencing timeout errors for a lot of your test, as it could be
       the result of a slower computer. If you're only experiencing it for a single test, however,
       it could be the result of a slow method  (maybe check your time complexity?) */
    private static final int TIMEOUT = 200;

    private ArrayStack<String> array;
    private LinkedStack<String> linked;



    // =============================== SETUP TESTS ========================= //

    @Before
    public void setup() {
        array = new ArrayStack<>();
        linked = new LinkedStack<>();
    }

    @Test(timeout = TIMEOUT)
    public void testInitialization() {
        assertEquals(0, array.size());
        assertArrayEquals(new Object[ArrayStack.INITIAL_CAPACITY],
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
    public void testArrayPushNull() {
        array.push(null);
    }

    @Test(expected = java.util.NoSuchElementException.class)
    public void testArrayPopEmpty() {
        array.pop();
    }

    @Test(expected = java.util.NoSuchElementException.class)
    public void testArrayPeekEmpty() {
        array.peek();
    }


    // Linked Implementations //
    @Test(expected = java.lang.IllegalArgumentException.class)
    public void testLinkedPushNull() {
        linked.push(null);
    }

    @Test(expected = java.util.NoSuchElementException.class)
    public void testLinkedPopEmpty() {
        linked.pop();
    }

    @Test(expected = java.util.NoSuchElementException.class)
    public void testLinkedPeekEmpty() {
        linked.peek();
    }



    // ======================= ARRAY SPECIFIC TESTS ======================== //

    /**
     * The following test tests a couple of quirks regarding specifically the array implementation
     * of stacks.
     */

    /**
     * Testing that the queue is able to resize properly above the default initial capacity.
     */
    @Test(timeout = TIMEOUT)
    public void testArrayResize() {

        array.push("0a");    // 0a
        array.push("1a");    // 0a, 1a
        array.push("2a");    // 0a, 1a, 2a
        array.push("3a");    // 0a, 1a, 2a, 3a
        array.push("4a");    // 0a, 1a, 2a, 3a, 4a
        array.push("5a");    // 0a, 1a, 2a, 3a, 4a, 5a
        array.push("6a");    // 0a, 1a, 2a, 3a, 4a, 5a, 6a
        array.push("7a");    // 0a, 1a, 2a, 3a, 4a, 5a, 6a, 7a
        array.push("8a");    // 0a, 1a, 2a, 3a, 4a, 5a, 6a, 7a, 8a
        array.push("9a");    // 0a, 1a, 2a, 3a, 4a, 5a, 6a, 7a, 8a, 9a

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

    /* As far as I know, the tests in StackStudentTest.java are decently exhaustive regarding the linked
       implementation of stacks (since there's really no edge cases), so I haven't added any tests here. */

}
