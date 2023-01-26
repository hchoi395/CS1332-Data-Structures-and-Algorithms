import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.List;
import java.util.ArrayList;

/**
 * Your implementation of an AVL Tree.
 *
 * @author Han Choi
 * @userid hchoi395
 * @GTID 903598626
 * @version 11.0.2
 */
public class AVL<T extends Comparable<? super T>> {
    // DO NOT ADD OR MODIFY INSTANCE VARIABLES.
    private AVLNode<T> root;
    private int size;

    /**
     * A no-argument constructor that should initialize an empty AVL.
     *
     * Since instance variables are initialized to their default values, there
     * is no need to do anything for this constructor.
     */
    public AVL() {
        // DO NOT IMPLEMENT THIS CONSTRUCTOR!
    }

    /**
     * Initializes the AVL tree with the data in the Collection. The data
     * should be added in the same order it appears in the Collection.
     *
     * @throws IllegalArgumentException if data or any element in data is null
     * @param data the data to add to the tree
     */
    public AVL(Collection<T> data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot use null data");
        }
        for (T d : data) {
            if (d == null) {
                throw new IllegalArgumentException("Cannot use null data");
            } else {
                add(d);
            }
        }
    }

    /**
     * Adds the data to the AVL. Start by adding it as a leaf like in a regular
     * BST and then rotate the tree as needed.
     *
     * If the data is already in the tree, then nothing should be done (the
     * duplicate shouldn't get added, and size should not be incremented).
     *
     * Remember to recalculate heights and balance factors going up the tree,
     * rebalancing if necessary.
     *
     * @throws java.lang.IllegalArgumentException if the data is null
     * @param data the data to be added
     */
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot add null data");
        } else {
            root = helperAdd(root, data);
        }
    }

    /**
     * Private helper method for the add method.
     * @param curr root node is passed in.
     * @param data data that will be added in node.
     * @return returns the root after adding and balancing.
     */
    private AVLNode<T> helperAdd(AVLNode<T> curr, T data) {
        if (curr == null) {
            size++;
            return new AVLNode<T>(data);
        } else if (data.compareTo(curr.getData()) < 0) {
            curr.setLeft(helperAdd(curr.getLeft(), data));
        } else if (data.compareTo(curr.getData()) > 0) {
            curr.setRight(helperAdd(curr.getRight(), data));
        } else {
            return curr;
        }
        update(curr);
        return balance(curr);
    }

    /**
     * Private helper method for setting height and balancing factor.
     * @param curr node that will have height and balancing factor set.
     */
    private void update(AVLNode<T> curr) {
        int leftHeight = curr.getLeft() != null ? curr.getLeft().getHeight() : -1;
        int rightHeight = curr.getRight() != null ? curr.getRight().getHeight() : -1;
        curr.setHeight(Math.max(leftHeight, rightHeight) + 1);
        curr.setBalanceFactor(leftHeight - rightHeight);
    }

    /**
     * Private helper method for rotating until node is balanced.
     * @param curr node to be balanced.
     * @return returns the balanced node.
     */
    private AVLNode<T> balance(AVLNode<T> curr) {
        if (curr.getBalanceFactor() < -1) {
            if (curr.getRight().getBalanceFactor() > 0) {
                curr.setRight(rotateRight(curr.getRight()));
            }
            curr = rotateLeft(curr);
        }
        if (curr.getBalanceFactor() > 1) {
            if (curr.getLeft().getBalanceFactor() < 0) {
                curr.setLeft(rotateLeft(curr.getLeft()));
            }
            curr = rotateRight(curr);
        }
        return curr;
    }

    /**
     * Private helper method for rotating node left.
     * @param curr node to be rotated left.
     * @return returns the rotated node.
     */
    private AVLNode<T> rotateLeft(AVLNode<T> curr) {
        AVLNode<T> temp = curr.getRight();
        curr.setRight(temp.getLeft());
        temp.setLeft(curr);
        update(curr);
        update(temp);
        return temp;
    }

    /**
     * Private helper method for rotating node right.
     * @param curr node to be rotated right.
     * @return returns the rotated node.
     */
    private AVLNode<T> rotateRight(AVLNode<T> curr) {
        AVLNode<T> temp = curr.getLeft();
        curr.setLeft(temp.getRight());
        temp.setRight(curr);
        update(curr);
        update(temp);
        return temp;
    }

    /**
     * Removes the data from the tree. There are 3 cases to consider:
     *
     * 1: the data is a leaf. In this case, simply remove it.
     * 2: the data has one child. In this case, simply replace it with its
     * child.
     * 3: the data has 2 children. Use the predecessor to replace the data,
     * not the successor. As a reminder, rotations can occur after removing
     * the predecessor node.
     *
     * Remember to recalculate heights going up the tree, rebalancing if
     * necessary.
     *
     * @throws IllegalArgumentException if the data is null
     * @throws java.util.NoSuchElementException if the data is not found
     * @param data the data to remove from the tree.
     * @return the data removed from the tree. Do not return the same data
     * that was passed in.  Return the data that was stored in the tree.
     */
    public T remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot remove null data");
        } else {
            AVLNode<T> dummy = new AVLNode<>(null);
            root = helperRemove(root, data, dummy);
            return dummy.getData();
        }
    }

    /**
     * Private helper method for remove.
     * @param curr root node passed in.
     * @param data data that will be removed from node.
     * @param dummy dummy node that stores a node.
     * @return returns the balanced node after removal.
     */
    private AVLNode<T> helperRemove(AVLNode<T> curr, T data, AVLNode<T> dummy) {
        if (curr == null) {
            throw new NoSuchElementException("Cannot remove data that can't be found");
        } else if (data.compareTo(curr.getData()) < 0) {
            curr.setLeft(helperRemove(curr.getLeft(), data, dummy));
        } else if (data.compareTo(curr.getData()) > 0) {
            curr.setRight(helperRemove(curr.getRight(), data, dummy));
        } else {
            dummy.setData(curr.getData());
            size--;
            if (curr.getLeft() == null) {
                return curr.getRight();
            } else if (curr.getRight() == null) {
                return curr.getLeft();
            } else {
                AVLNode<T> dummy2 = new AVLNode<>(null);
                curr.setLeft(predecessorRemove(curr.getLeft(), dummy2));
                curr.setData(dummy2.getData());
            }
        }
        update(curr);
        return balance(curr);
    }

    /**
     * Private helper method for predecessor remove.
     * @param curr node passed in.
     * @param dummy2 dummy node that will store a node.
     * @return returns the balanced node after predecessor removal.
     */
    private AVLNode<T> predecessorRemove(AVLNode<T> curr, AVLNode<T> dummy2) {
        if (curr.getRight() == null) {
            dummy2.setData(curr.getData());
            return curr.getLeft();
        } else {
            curr.setRight(predecessorRemove(curr.getRight(), dummy2));
        }
        update(curr);
        return balance(curr);
    }

    /**
     * Returns the data in the tree matching the parameter passed in (think
     * carefully: should you use value equality or reference equality?).
     *
     * @throws IllegalArgumentException if the data is null
     * @throws java.util.NoSuchElementException if the data is not found
     * @param data the data to search for in the tree.
     * @return the data in the tree equal to the parameter. Do not return the
     * same data that was passed in.  Return the data that was stored in the
     * tree.
     */
    public T get(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot get null data");
        }
        return helperGet(root, data).getData();
    }

    /**
     * Private helper method for get method.
     * @param curr root node passed in.
     * @param data data that is looked for in node.
     * @return returns the data in the node that contains the wanted data.
     */
    private AVLNode<T> helperGet(AVLNode<T> curr, T data) {
        if (curr == null) {
            throw new NoSuchElementException("Data can't be found");
        }
        if (data.compareTo(curr.getData()) < 0) {
            return helperGet(curr.getLeft(), data);
        } else if (data.compareTo(curr.getData()) > 0) {
            return helperGet(curr.getRight(), data);
        } else {
            return curr;
        }
    }

    /**
     * Returns whether or not data equivalent to the given parameter is
     * contained within the tree. The same type of equality should be used as
     * in the get method.
     *
     * @throws IllegalArgumentException if the data is null
     * @param data the data to search for in the tree.
     * @return whether or not the parameter is contained within the tree.
     */
    public boolean contains(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot check contains for null data");
        }
        try {
            get(data);
        } catch (NoSuchElementException e) {
            return false;
        }
        return true;
    }

    /**
     * Finds and retrieves the median data of the passed in AVL.
     *
     * This method will not need to traverse the entire tree to
     * function properly, so you should only traverse enough branches of the tree
     * necessary to find the median data and only do so once. Failure to do so will
     * result in an efficiency penalty.
     *
     * Ex:
     * Given the following AVL composed of Integers
     *              50
     *            /    \
     *         25      75
     *        /  \     / \
     *      13   37  70  80
     *    /  \    \      \
     *   12  15    40    85
     *
     * findMedian() should return 40
     *
     * @throws java.util.NoSuchElementException if the tree is empty or contains an even number of data
     * @return the median data of the AVL
     */
    public T findMedian() {
        if (root == null || size % 2 == 0) {
            throw new NoSuchElementException("Tree is empty or contains an even number of data");
        }
        List<T> list = new ArrayList<>();
        int count = 0;
        helperFindMedian(list, root, count);
        return list.get(list.size() - 1);
    }

    /**
     * Private helper method for finding median method.
     * @param list list of data until the median is found.
     * @param curr root node passed in.
     */
    private void helperFindMedian(List<T> list, AVLNode<T> curr, int count) {
        if (curr != null) {
            if (count > size / 2) {
                return;
            }
            if (list.size() <= size / 2) {
                helperFindMedian(list, curr.getLeft(), count);
            }
            if (list.size() <= size / 2) {
                list.add(curr.getData());
            }
            if (list.size() <= size / 2) {
                helperFindMedian(list, curr.getRight(), count);
            }
        }
    }

    /**
     * Clears the tree.
     */
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * Returns the height of the root of the tree.
     *
     * Since this is an AVL, this method does not need to traverse the tree
     * and should be O(1)
     *
     * @return the height of the root of the tree, -1 if the tree is empty
     */
    public int height() {
        if (root == null) {
            return -1;
        }
        return root.getHeight();
    }

    /**
     * Returns the size of the AVL tree.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return number of items in the AVL tree
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD
        return size;
    }

    /**
     * Returns the root of the AVL tree.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the root of the AVL tree
     */
    public AVLNode<T> getRoot() {
        // DO NOT MODIFY THIS METHOD!
        return root;
    }
}

