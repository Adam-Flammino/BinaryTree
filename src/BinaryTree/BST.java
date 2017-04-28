package BinaryTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Flammino on 4/26/2017.
 * Description: Creates a binary tree from a random array, displays breadth first
 * with level
 */
public class BST<E extends Comparable<E>> extends AbstractTree<E> implements Cloneable {

    String output = "\n";

    /* Protected declarations */
    protected TreeNode<E> root;
    protected int size = 0;

    /**
     * Create default binary tree
     */
    public BST() {
    }

    /**
     * Create a binary tree from an array of objects
     *
     * @param objects
     */
    public BST(ArrayList<E> objects) {
        for (E object : objects) {
            insert(object);
        }
    }

    /**
     * Insert element e into the binary tree Return true if the element is
     * inserted successfully
     *
     * @param e
     * @return boolean value
     */
    @Override
    public boolean insert(E e) {
        if (root == null) {
            root = createNewNode(e); // Create a new root
        } else {
            // Locate the parent node
            TreeNode<E> parent = null;
            TreeNode<E> current = root;
            while (current != null) {
                if (e.compareTo(current.element) < 0) {
                    parent = current;
                    current = current.left;
                } else if (e.compareTo(current.element) > 0) {
                    parent = current;
                    current = current.right;
                } else {
                    return false; // Duplicate node not inserted
                }
            }
            // Create the new node and attach it to the parent node
            if (e.compareTo(parent.element) < 0) {
                parent.left = createNewNode(e);
            } else {
                parent.right = createNewNode(e);
            }
        }
        size++;
        return true; // Element inserted
    }

    /**
     * Create new node protected method
     *
     * @param e
     * @return
     */
    protected TreeNode<E> createNewNode(E e) {
        return new TreeNode<>(e);
    }


    /**
     * Breadth First traversal from the root
     */
    public void breadthFirst() {
        breadthFirst(root);
    }

    /**
     * Breadth First traversal helper. Adds nodes to string, includes level node is at
     *
     * @param root
     */
    protected void  breadthFirst(TreeNode<E> root) {
        Queue<TreeNode> qTN = new LinkedList<>();
        if (root == null) {
            return;
        }
        qTN.add(root);
        int line = 0;
        while (!qTN.isEmpty()) {
            TreeNode currRoot = qTN.remove();
            int level = level((E) currRoot.element);
            if (level != line){
                output += "-=-=-=-=-=-=-=-==-=-=-=-=-=\n"; // Adds a line between levels of tree
            }
            output += currRoot.element;
            output += "\t\tis at level\t\t";
            output += level - 1;
            output += "\n";
            line = level;
            //treeArr.add((E) currRoot.element);
            if (currRoot.left != null) {
                qTN.add(currRoot.left);
            }
            if (currRoot.right != null) {
                qTN.add(currRoot.right);
            }
        }
    }

    /**
     * @return output string
     */
    public String getOutput(){
        return output;
    }

    public int level(E e) {
        int l;
        ArrayList<TreeNode<E>> list = new ArrayList<>();
        TreeNode<E> current = root; // Start from the root

        while (current != null) {
            list.add(current); // Add the node to the list
            if (e.compareTo(current.element) < 0) {
                current = current.left;
            } else if (e.compareTo(current.element) > 0) {
                current = current.right;
            } else {
                break;
            }
        }
        return list.size(); // Return an array of nodes
    }


    /**
     * Inner class tree node
     *
     * @param <E>
     */
    public static class TreeNode<E extends Comparable<E>> {

        //Declarations
        E element;
        TreeNode<E> left;
        TreeNode<E> right;

        public TreeNode(E e) {
            element = e;
        }
    }

    /**
     * Obtain an iterator. Use inorder.
     * @return
     */
    @Override
    public java.util.Iterator iterator() {
        return inorderIterator();
    }

    /**
     * Obtain an inorder iterator
     * @return
     */
    public java.util.Iterator inorderIterator() {
        return new InorderIterator();
    }

    // Inner class InorderIterator
    class InorderIterator implements java.util.Iterator {

        // Store the elements in a list
        private java.util.ArrayList<E> list = new java.util.ArrayList<>();
        private int current = 0; // Point to the current element in list

        public InorderIterator() {
            inorder(); // Traverse binary tree and store elements in list
        }

        /**
         * Inorder traversal from the root
         */
        private void inorder() {
            inorder(root);
        }

        /**
         * Inorder traversal from a subtree
         */
        private void inorder(TreeNode<E> root) {
            if (root == null) {
                return;
            }
            inorder(root.left);
            list.add(root.element);
            inorder(root.right);
        }

        /**
         * Next element for traversing?
         */
        @Override
        public boolean hasNext() {
            return current < list.size();
        }

        /**
         * Get the current element and move cursor to the next
         */
        @Override
        public Object next() {
            return list.get(current++);
        }

    }

    /**
     * Remove all elements from the tree
     */
    public void clear() {
        root = null;
        size = 0;
    }
}
