import java.util.*;

public class BinarySearchMinMaxFind {

    /**
     * Node Class: Represents a single node in the Binary Tree.
     */
    public static class Node {
        int data;
        Node left;
        Node right;

        /**
         * Constructor for a Node.
         */
        Node(int data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

    // ------------------------------------------------------------------
    // BST Operations: All have O(log n) time complexity in a balanced BST.
    // ------------------------------------------------------------------

    /**
     * Finds the maximum value in the BST.
     * The maximum value is always the rightmost node.
     * @param node The current node.
     * @return The largest data value in the subtree.
     */
    public static int max(Node node) {
        // Keep traversing to the right until the right child is null.
        if (node.right != null) {
            return max(node.right);
        } else {
            // Found the rightmost node (max).
            return node.data;
        }
    }

    /**
     * Finds the minimum value in the BST.
     * The minimum value is always the leftmost node.
     * @param node The current node.
     * @return The smallest data value in the subtree.
     */
    public static int min(Node node) {
        // Keep traversing to the left until the left child is null.
        if (node.left != null) {
            // Must call min() recursively
            return min(node.left); 
        } else {
            // Found the leftmost node (min).
            return node.data;
        }
    }

    /**
     * Searches for a specific data value in the BST.
     * Uses the BST property to efficiently discard half the tree.
     * @param node The current node.
     * @param data The value to search for.
     * @return true if the data is found, false otherwise.
     */
    public static boolean find(Node node, int data) {
        // Base case 1: Reached a leaf's child (null) without finding the data.
        if (node == null) {
            return false;
        }
        
        // Go right if data is greater than current node's data.
        if (data > node.data) {
            return find(node.right, data);
        // Go left if data is smaller than current node's data.
        } else if (data < node.data) {
            return find(node.left, data);
        // Base case 2: Data is equal to current node's data.
        } else {
            return true;
        }
    }

    // ------------------------------------------------------------------
    // Utility Functions (Construction and Display)
    // ------------------------------------------------------------------

    /**
     * Constructs a Balanced Binary Search Tree (BST) from a sorted array.
     * Uses the middle element as the root recursively.
     * @param arr The sorted array of integers.
     * @param lo 	The starting index of the subarray.
     * @param hi 	The ending index of the subarray.
     * @return The root of the constructed BST subtree.
     */
    public static Node construct(Integer[] arr, int lo, int hi) {
        if (lo > hi) {
            return null;
        }

        int mid = (lo + hi) / 2;
        int data = arr[mid];

        Node lc = construct(arr, lo, mid - 1);
        Node rc = construct(arr, mid + 1, hi);

        Node node = new Node(data, lc, rc);
        return node;
    }

    /**
     * Prints the tree structure in a format: Left_Child <- Parent_Node -> Right_Child
     * @param node The current node to display.
     */
    public static void display(Node node) {
        if (node == null) {
            return;
        }

        String str = "";
        str += (node.left == null) ? ". " : node.left.data + "";
        str += " <- " + node.data + " -> ";
        str += (node.right == null) ? " ." : node.right.data + "";

        System.out.println(str);

        display(node.left);
        display(node.right);
    }

    /**
     * Main method to test the BST functionality.
     */
    public static void main(String[] args) {
        Integer[] arr = { 12, 25, 37, 50, 62, 75, 87 };
        Node root = construct(arr, 0, arr.length - 1);

        System.out.println("--- Constructed BST Display ---");
        display(root);
        
        System.out.println("-----------------------------");
        System.out.println("Max value (rightmost): " + max(root)); // Should be 87
        System.out.println("Min value (leftmost): " + min(root)); // Should be 12
        System.out.println("Find 62: " + find(root, 62)); // Should be true
        System.out.println("Find 99: " + find(root, 99)); // Should be false
    }
}