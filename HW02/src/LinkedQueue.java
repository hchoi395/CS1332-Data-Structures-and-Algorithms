import java.util.NoSuchElementException;

/**
 * Your implementation of a LinkedQueue. It should NOT be circular.
 *
 * @author Han Choi
 * @version 11.0.2
 * @userid hchoi395
 * @GTID 903598626
 *
 * Collaborators: LIST ALL COLLABORATORS YOU WORKED WITH HERE
 *
 * Resources: LIST ALL NON-COURSE RESOURCES YOU CONSULTED HERE
 */
public class LinkedQueue<T> implements Comparable<T> {

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private LinkedNode<T> head;
    private LinkedNode<T> tail;
    private int size;

    /*
     * Do not add a constructor.
     */

    /**
     * Adds the data to the back of the queue.
     *
     * Must be O(1).
     *
     * @param data the data to add to the back of the queue
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void enqueue(T data) {
        LinkedNode<T> newNode = new LinkedNode<T>(data);
        if (data == null) {
            throw new IllegalArgumentException("Cannot enqueue to queue since data is null");
        } else if (head == null) {
            head = newNode;
            tail = newNode;
            size++;
        } else {
            tail.setNext(newNode);
            tail = newNode;
            size++;
        }
    }

    /**
     * Removes and returns the data from the front of the queue.
     *
     * Must be O(1).
     *
     * @return the data formerly located at the front of the queue
     * @throws java.util.NoSuchElementException if the queue is empty
     */
    public T dequeue() {
        LinkedNode<T> temp = head;
        if (head == null) {
            throw new NoSuchElementException("Cannot dequeue since queue is empty");
        } else if (size == 1) {
            head = null;
            tail = null;
            size--;
            return temp.getData();
        } else {
            head = head.getNext();
            size--;
            return temp.getData();
        }
    }

    /**
     * Returns the data from the front of the queue without removing it.
     *
     * Must be O(1).
     *
     * @return the data located at the front of the queue
     * @throws java.util.NoSuchElementException if the queue is empty
     */
    public T peek() {
        if (head == null) {
            throw new NoSuchElementException("Cannot peek since queue is empty");
        } else {
            return head.getData();
        }
    }

    /**
     * Returns the head node of the queue.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the node at the head of the queue
     */
    public LinkedNode<T> getHead() {
        // DO NOT MODIFY THIS METHOD!
        return head;
    }

    /**
     * Returns the tail node of the queue.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the node at the tail of the queue
     */
    public LinkedNode<T> getTail() {
        // DO NOT MODIFY THIS METHOD!
        return tail;
    }

    /**
     * Returns the size of the queue.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the size of the queue
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }
}

