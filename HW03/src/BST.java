import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

/**
 * Your implementation of a BST.
 *
 * @author Han Choi
 * @version 11.0.13
 * @userid hchoi395
 * @GTID 903598626
 *
 * Collaborators: LIST ALL COLLABORATORS YOU WORKED WITH HERE
 *
 * Resources: LIST ALL NON-COURSE RESOURCES YOU CONSULTED HERE
 */
public class BST<T extends Comparable<? super T>> {

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private BSTNode<T> root;
    private int size;

    /**
     * Constructs a new BST.
     *
     * This constructor should initialize an empty BST.
     *
     * Since instance variables are initialized to their default values, there
     * is no need to do anything for this constructor.
     */
    public BST() {
        // DO NOT IMPLEMENT THIS CONSTRUCTOR!
    }

    /**
     * Constructs a new BST.
     *
     * This constructor should initialize the BST with the data in the
     * Collection. The data should be added in the same order it is in the
     * Collection.
     *
     * Hint: Not all Collections are indexable like Lists, so a regular for loop
     * will not work here. However, all Collections are Iterable, so what type
     * of loop would work?
     *
     * @param data the data to add
     * @throws java.lang.IllegalArgumentException if data or any element in data
     *                                            is null
     */
    public BST(Collection<T> data) {
        this.size = 0;
        if (data == null) {
            throw new IllegalArgumentException("Data or an element in data is null so data cannot be added");
        }
        for (T d : data) {
            if (d == null) {
                throw new IllegalArgumentException("Data or an element in data is null so data cannot be added");
            } else {
                add(d);
            }
        }
    }

    /**
     * Adds the data to the tree.
     *
     * This must be done recursively.
     *
     * The data becomes a leaf in the tree.
     *
     * Traverse the tree to find the appropriate location. If the data is
     * already in the tree, then nothing should be done (the duplicate
     * shouldn't get added, and size should not be incremented).
     *
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data the data to add
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot add null data into the tree");
        } else {
            root = helperAdd(root, data);
        }
    }

    /**
     * Helper method for the add method.
     * @param curr the current BSTNode.
     * @param data the data to add.
     * @return the current BSTNode.
     */
    private BSTNode<T> helperAdd(BSTNode<T> curr, T data) {
        if (curr == null) {
            size++;
            return new BSTNode<T>(data);
        } else if (data.compareTo(curr.getData()) < 0) {
            curr.setLeft(helperAdd(curr.getLeft(), data));
        } else if (data.compareTo(curr.getData()) > 0) {
            curr.setRight(helperAdd(curr.getRight(), data));
        }
        return curr;
    }

    /**
     * Removes and returns the data from the tree matching the given parameter.
     *
     * This must be done recursively.
     *
     * There are 3 cases to consider:
     * 1: The node containing the data is a leaf (no children). In this case,
     * simply remove it.
     * 2: The node containing the data has one child. In this case, simply
     * replace it with its child.
     * 3: The node containing the data has 2 children. Use the successor to
     * replace the data. You MUST use recursion to find and remove the
     * successor (you will likely need an additional helper method to
     * handle this case efficiently).
     *
     * Do not return the same data that was passed in. Return the data that
     * was stored in the tree.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data the data to remove
     * @return the data that was removed
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   if the data is not in the tree
     */
    public T remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot remove null data from the tree");
        } else {
            BSTNode<T> dummy = new BSTNode<T>(null);
            root = helperRemove(root, data, dummy);
            return dummy.getData();
        }
    }

    /**
     * Helper method for the remove method.
     * @param curr the current BSTNode.
     * @param data the data to remove.
     * @param dummy the dummy BSTNode to store the removed BSTNode.
     * @return the current BSTNode.
     */
    private BSTNode helperRemove(BSTNode<T> curr, T data, BSTNode<T> dummy) {
        if (curr == null) {
            throw new NoSuchElementException("Cannot remove data that is not in the tree");
        } else if (data.compareTo(curr.getData()) < 0) {
            curr.setLeft(helperRemove(curr.getLeft(), data, dummy));
        } else if (data.compareTo(curr.getData()) > 0) {
            curr.setRight(helperRemove(curr.getRight(), data, dummy));
        } else {
            dummy.setData(curr.getData());
            size--;
            if (curr.getLeft() == null && curr.getRight() == null) {
                return null;
            } else if (curr.getLeft() != null && curr.getRight() == null) {
                return curr.getLeft();
            } else if (curr.getRight() != null && curr.getLeft() == null) {
                return curr.getRight();
            } else {
                BSTNode<T> dummy2 = new BSTNode<T>(null);
                curr.setRight(removeSuccessor(curr.getRight(), dummy2));
                curr.setData(dummy2.getData());
            }
        }
        return curr;
    }

    /**
     * Remove successor method that helps the remove helper method.
     * @param curr the current BSTNode.
     * @param dummy the dummy BSTNode that stores the removed BSTNode.
     * @return the current BSTNode.
     */
    private BSTNode<T> removeSuccessor(BSTNode<T> curr, BSTNode<T> dummy) {
        if (curr.getLeft() == null) {
            dummy.setData(curr.getData());
            return curr.getRight();
        } else {
            curr.setLeft(removeSuccessor(curr.getLeft(), dummy));
        }
        return curr;
    }

    /**
     * Returns the data from the tree matching the given parameter.
     *
     * This must be done recursively.
     *
     * Do not return the same data that was passed in. Return the data that
     * was stored in the tree.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data the data to search for
     * @return the data in the tree equal to the parameter
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   if the data is not in the tree
     */
    public T get(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot get data that is null");
        } else {
            BSTNode<T> dummy = new BSTNode<T>(null);
            getHelper(root, data, dummy);
            return dummy.getData();
        }
    }

    /**
     * Helper method for the get method.
     * @param curr the current BSTNode.
     * @param data the data to get.
     * @param dummy dummy BSTNode to store the data to get.
     * @return the BSTNode that has the data looked for.
     */
    private BSTNode<T> getHelper(BSTNode<T> curr, T data, BSTNode<T> dummy) {
        if (data.compareTo(curr.getData()) < 0 && curr.getLeft() != null) {
            return getHelper(curr.getLeft(), data, dummy);
        } else if (data.compareTo(curr.getData()) > 0 && curr.getRight() != null) {
            return getHelper(curr.getRight(), data, dummy);
        } else if (data.compareTo(curr.getData()) == 0) {
            dummy.setData(curr.getData());
            return curr;
        } else {
            throw new NoSuchElementException("Cannot get data that does not exist in the tree");
        }
    }

    /**
     * Returns whether or not data matching the given parameter is contained
     * within the tree.
     *
     * This must be done recursively.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data the data to search for
     * @return true if the parameter is contained within the tree, false
     * otherwise
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public boolean contains(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot check contains since data is null");
        } else {
            if (root == null) {
                return false;
            } else {
                return helperContains(root, data);
            }
        }
    }

    /**
     * Helper method for the contains method.
     * @param curr the current BSTNode.
     * @param data the data to search for.
     * @return boolean whether the data is contained.
     */
    private boolean helperContains(BSTNode<T> curr, T data) {
        if (data.compareTo(curr.getData()) < 0) {
            if (curr.getLeft() == null) {
                return false;
            } else {
                return helperContains(curr.getLeft(), data);
            }
        } else if (data.compareTo(curr.getData()) > 0) {
            if (curr.getRight() == null) {
                return false;
            } else {
                return helperContains(curr.getRight(), data);
            }
        } else {
            return true;
        }
    }

    /**
     * Generate a pre-order traversal of the tree.
     *
     * This must be done recursively.
     *
     * Must be O(n).
     *
     * @return the preorder traversal of the tree
     */
    public List<T> preorder() {
        List<T> list = new ArrayList<T>();
        helperPreorder(list, root);
        return list;
    }

    /**
     * Helper method for the preorder method.
     * @param list list that adds the data.
     * @param curr the current BSTNode.
     */
    private void helperPreorder(List<T> list, BSTNode<T> curr) {
        if (curr != null) {
            list.add(curr.getData());
            helperPreorder(list, curr.getLeft());
            helperPreorder(list, curr.getRight());
        }
    }

    /**
     * Generate an in-order traversal of the tree.
     *
     * This must be done recursively.
     *
     * Must be O(n).
     *
     * @return the inorder traversal of the tree
     */
    public List<T> inorder() {
        List<T> list = new ArrayList<T>();
        helperInorder(list, root);
        return list;
    }

    /**
     * Helper method for the inorder method.
     * @param list list that adds the data.
     * @param curr the current BSTNode.
     */
    private void helperInorder(List<T> list, BSTNode<T> curr) {
        if (curr != null) {
            helperInorder(list, curr.getLeft());
            list.add(curr.getData());
            helperInorder(list, curr.getRight());
        }
    }

    /**
     * Generate a post-order traversal of the tree.
     *
     * This must be done recursively.
     *
     * Must be O(n).
     *
     * @return the postorder traversal of the tree
     */
    public List<T> postorder() {
        List<T> list = new ArrayList<T>();
        helperPostorder(list, root);
        return list;
    }

    /**
     * Helper method for the postorder method.
     * @param list list that adds the data.
     * @param curr the current BSTNode.
     */
    private void helperPostorder(List<T> list, BSTNode<T> curr) {
        if (curr != null) {
            helperPostorder(list, curr.getLeft());
            helperPostorder(list, curr.getRight());
            list.add(curr.getData());
        }
    }

    /**
     * Generate a level-order traversal of the tree.
     *
     * This does not need to be done recursively.
     *
     * Hint: You will need to use a queue of nodes. Think about what initial
     * node you should add to the queue and what loop / loop conditions you
     * should use.
     *
     * Must be O(n).
     *
     * @return the level order traversal of the tree
     */
    public List<T> levelorder() {
        Queue<BSTNode<T>> queue = new LinkedList<BSTNode<T>>();
        List<T> list = new ArrayList<T>();
        queue.add(root);
        while (!queue.isEmpty()) {
            BSTNode<T> curr = queue.poll();
            list.add(curr.getData());
            if (curr.getLeft() != null) {
                queue.add(curr.getLeft());
            }
            if (curr.getRight() != null) {
                queue.add(curr.getRight());
            }
        }
        return list;
    }

    /**
     * Returns the height of the root of the tree.
     *
     * This must be done recursively.
     *
     * A node's height is defined as max(left.height, right.height) + 1. A
     * leaf node has a height of 0 and a null child has a height of -1.
     *
     * Must be O(n).
     *
     * @return the height of the root of the tree, -1 if the tree is empty
     */
    public int height() {
        return helperHeight(root);
    }

    /**
     * Helper method for the height method.
     * @param curr the current BSTNode.
     * @return the height of the BSTNode.
     */
    private int helperHeight(BSTNode<T> curr) {
        if (curr == null) {
            return -1;
        } else {
            return 1 + Math.max(helperHeight(curr.getLeft()), helperHeight(curr.getRight()));
        }
    }

    /**
     * Clears the tree.
     *
     * Clears all data and resets the size.
     *
     * Must be O(1).
     */
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * Removes all elements strictly greater than the passed in data.
     *
     * This must be done recursively.
     *
     * In most cases, this method will not need to traverse the entire tree to
     * function properly, so you should only traverse the branches of the tree
     * necessary to get the data and only do so once. Failure to do so will
     * result in an efficiency penalty.
     *
     *
     * EXAMPLE: Given the BST below composed of Integers:
     *
     *                50
     *              /    \
     *            25      75
     *           /  \
     *          12   37
     *         /  \    \
     *        10  15    40
     *           /
     *          13
     *
     * pruneGreaterThan(27) should remove 37, 40, 50, 75. Below is the resulting BST
     *             25
     *            /
     *          12
     *         /  \
     *        10  15
     *           /
     *          13
     *
     * Should have a running time of O(log(n)) for balanced tree. O(n) for a degenerated tree.
     *
     * @throws java.lang.IllegalArgumentException if data is null
     * @param data the threshold data. Elements greater than data should be removed
     * @param tree the root of the tree to prune nodes from
     * @param <T> the generic typing of the data in the BST
     * @return the root of the tree with all elements greater than data removed
     */
    public static <T extends Comparable<? super T>> BSTNode<T> pruneGreaterThan(BSTNode<T> tree, T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot pruneGreaterThan since data is null");
        } else {
            return helperPruneGreaterThan(tree, data);
        }
    }

    /**
     * Helper method for the prune greater than method.
     * @param curr the current BSTNode.
     * @param data the data being used to prune greater than BSTNodes.
     * @return the current BSTNode is returned.
     * @param <T> generic parameter T.
     */
    private static <T extends Comparable<? super T>> BSTNode<T> helperPruneGreaterThan(BSTNode<T> curr, T data) {
        if (curr == null) {
            return null;
        }
        if (data.compareTo(curr.getData()) < 0) {
            curr.setLeft(helperPruneGreaterThan(curr.getLeft(), data));
            return curr.getLeft();
        } else {
            curr.setRight(helperPruneGreaterThan(curr.getRight(), data));
            return curr;
        }
    }

    /**
     * Returns the root of the tree.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the root of the tree
     */
    public BSTNode<T> getRoot() {
        // DO NOT MODIFY THIS METHOD!
        return root;
    }

    /**
     * Returns the size of the tree.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the size of the tree
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }
}
