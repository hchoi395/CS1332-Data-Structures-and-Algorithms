import java.util.NoSuchElementException;

/**
 * Your implementation of a non-circular SinglyLinkedList with a tail pointer.
 *
 * @author Han Choi
 * @version 11.0.2
 * @hchoi395
 * @903598626
 */
public class SinglyLinkedList<T> {

    // Do not add new instance variables or modify existing ones.
    private SinglyLinkedListNode<T> head;
    private SinglyLinkedListNode<T> tail;
    private int size;

    // Do not add a constructor.

    /**
     * Adds the element to the specified index.
     *
     * Must be O(1) for indices 0 and size and O(n) for all other cases.
     *
     * @param index the index to add the new element
     * @param data  the data to add
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index > size
     * @throws java.lang.IllegalArgumentException  if data is null
     */
    public void addAtIndex(int index, T data) {
        SinglyLinkedListNode<T> newNode = new SinglyLinkedListNode<T>(data);
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("The data cannot "
                    + "be added at index since index < 0 or index > size");
        } else if (data == null) {
            throw new IllegalArgumentException("The data"
                    + " cannot be added at index since it is null");
        } else if (index == 0) {
            addToFront(data);
        } else if (index == size) {
            addToBack(data);
        } else {
            SinglyLinkedListNode<T> curr = head;
            for (int i = 1; i < index; i++) {
                curr = curr.getNext();
            }
            newNode.setNext(curr.getNext());
            curr.setNext(newNode);
            size++;
        }
    }

    /**
     * Adds the element to the front of the list.
     *
     * Must be O(1).
     *
     * @param data the data to add to the front of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToFront(T data) {
        SinglyLinkedListNode<T> newNode = new SinglyLinkedListNode<T>(data);
        if (data == null) {
            throw new IllegalArgumentException("The data "
                    + "cannot be added to front since it is null");
        } else if (head == null) {
            head = newNode;
            tail = newNode;
            size++;
        } else {
            newNode.setNext(head);
            head = newNode;
            size++;
        }
    }

    /**
     * Adds the element to the back of the list.
     *
     * Must be O(1).
     *
     * @param data the data to add to the back of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToBack(T data) {
        SinglyLinkedListNode<T> newNode = new SinglyLinkedListNode<T>(data);
        if (data == null) {
            throw new IllegalArgumentException("The data "
                    + "cannot be added to back since it is null");
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
     * Removes and returns the element at the specified index.
     *
     * Must be O(1) for indices 0 and O(n) for all other
     * cases.
     *
     * @param index the index of the element to remove
     * @return the data that was removed
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index >= size
     */
    public T removeAtIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Removal of "
                    + "the node at the index "
                    + "cannot occur due to index < 0 or index >= size");
        } else if (index == 0) {
            return removeFromFront();
        } else if (index == size - 1) {
            return removeFromBack();
        } else {
            SinglyLinkedListNode<T> curr = head;
            for (int i = 1; i < index; i++) {
                curr = curr.getNext();
            }
            SinglyLinkedListNode<T> temp = curr.getNext();
            curr.setNext(curr.getNext().getNext());
            size--;
            return temp.getData();
        }
    }

    /**
     * Removes and returns the first data of the list.
     *
     * Must be O(1).
     *
     * @return the data formerly located at the front of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromFront() {
        if (head == null) {
            throw new NoSuchElementException("Cannot "
                    + "remove from front due to the list being empty");
        } else if (size == 1) {
            SinglyLinkedListNode<T> temp = head;
            head = null;
            tail = null;
            size--;
            return temp.getData();
        } else {
            SinglyLinkedListNode<T> temp = head;
            head = head.getNext();
            size--;
            return temp.getData();
        }
    }

    /**
     * Removes and returns the last data of the list.
     *
     * Must be O(n).
     *
     * @return the data formerly located at the back of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromBack() {
        SinglyLinkedListNode<T> curr = head;
        if (head == null) {
            throw new NoSuchElementException("Cannot "
                    + "remove from back due to list being empty");
        } else if (size == 1) {
            SinglyLinkedListNode<T> temp = head;
            head = null;
            tail = null;
            size--;
            return temp.getData();
        } else {
            while (curr.getNext().getNext() != null) {
                curr = curr.getNext();
            }
            SinglyLinkedListNode<T> temp = curr.getNext();
            curr.setNext(null);
            tail = curr;
            size--;
            return temp.getData();
        }
    }

    /**
     * Returns the element at the specified index.
     *
     * Must be O(1) for indices 0 and size - 1 and O(n) for all other cases.
     *
     * @param index the index of the element to get
     * @return the data stored at the index in the list
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index >= size
     */
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Cannot get "
                    + "the element at the index due to index < 0 or index >= size");
        } else if (index == 0) {
            return head.getData();
        } else if (index == size - 1) {
            return tail.getData();
        } else {
            SinglyLinkedListNode<T> curr = head;
            for (int i = 0; i < index; i++) {
                curr = curr.getNext();
            }
            return curr.getData();
        }
    }

    /**
     * Returns whether or not the list is empty.
     *
     * Must be O(1).
     *
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * Clears the list.
     *
     * Clears all data and resets the size.
     *
     * Must be O(1).
     */
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * Removes and returns the last copy of the given data from the list.
     *
     * Do not return the same data that was passed in. Return the data that
     * was stored in the list.
     *
     * Must be O(n).
     *
     * @param data the data to be removed from the list
     * @return the data that was removed
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   if data is not found
     */
    public T removeLastOccurrence(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot remove "
                    + "last occurrence due to data passed in being null");
        } else {
            SinglyLinkedListNode<T> node = head;
            SinglyLinkedListNode<T> prev = null;
            SinglyLinkedListNode<T> curr = null;
            while (node != null) {
                if (node.getNext() != null
                        && node.getNext().getData().equals(data)) {
                    prev = node;
                    curr = node.getNext();
                }
                node = node.getNext();
            }
            if (head.getData().equals(data)
                    && tail.getData().equals(data)) {
                SinglyLinkedListNode<T> temp = head;
                clear();
                return temp.getData();
            } else if (head.getData().equals(data)) {
                    SinglyLinkedListNode<T> temp = head;
                    head = head.getNext();
                    size--;
                    return temp.getData();
            } else if (tail.getData().equals(data)) {
                SinglyLinkedListNode<T> temp = head;
                while (temp.getNext().getNext() != null) {
                    temp = temp.getNext();
                }
                SinglyLinkedListNode<T> temp2 = temp.getNext();
                temp.setNext(null);
                tail = temp;
                size--;
                return temp2.getData();
            } else if (prev != null) {
                prev.setNext(curr.getNext());
                size--;
                return curr.getData();
            } else {
                throw new NoSuchElementException("Cannot remove "
                        + "last occurrence due to data not being found");
            }
        }
    }

    /**
     * Returns an array representation of the linked list.
     *
     * Must be O(n) for all cases.
     *
     * @return the array of length size holding all of the data (not the
     * nodes) in the list in the same order
     */
    @SuppressWarnings("unchecked")
    public T[] toArray() {
        T[] arr = (T[]) new Object[size];
        SinglyLinkedListNode<T> curr = head;
        for (int i = 0; i < size; i++) {
            arr[i] = curr.getData();
            curr = curr.getNext();
        }
        return arr;
    }

    /**
     * Returns the head node of the list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the node at the head of the list
     */
    public SinglyLinkedListNode<T> getHead() {
        // DO NOT MODIFY!
        return head;
    }

    /**
     * Returns the tail node of the list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the node at the tail of the list
     */
    public SinglyLinkedListNode<T> getTail() {
        // DO NOT MODIFY!
        return tail;
    }

    /**
     * Returns the size of the list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the size of the list
     */
    public int size() {
        // DO NOT MODIFY!
        return size;
    }
}
