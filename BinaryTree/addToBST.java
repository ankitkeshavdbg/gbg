import java.util.*;

public class addToBST {
    public static class Node {
        int data;
        Node left;
        Node right;

        Node(int data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

    // ------------------------------------------------------------------
    // Core BST Operation: Insertion
    // ------------------------------------------------------------------

    /**
     * Inserts a new data element into the correct position in the BST.
     * Preserves the BST property (left < root < right).
     * @param node The current node (or root).
     * @param data The value to be inserted.
     * @return The updated root of the subtree.
     */
    public static Node add(Node node, int data) {
        // Base case: Found the null spot, create and return the new node.
        if (node == null) {
            return new Node(data, null, null);
        }
        
        // Go right if data is greater.
        if (data > node.data) {
            node.right = add(node.right, data);
        // Go left if data is smaller.
        } else if (data < node.data) {
            node.left = add(node.left, data);
        } 
        // If data == node.data (duplicate), do nothing.

        return node; // Return the current node (linking the tree back up).
    }

    // ------------------------------------------------------------------
    // Utility Functions: Construction and Display
    // ------------------------------------------------------------------

    /**
     * Constructs a Balanced Binary Search Tree (BST) from a sorted array.
     * @param arr The sorted array of integers.
     * @param lo The starting index of the subarray.
     * @param hi The ending index of the subarray.
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

    // ------------------------------------------------------------------
    // Main Method for Testing
    // ------------------------------------------------------------------

    /**
     * Main method to test the BST functionality.
     */
    public static void main(String[] args) {
        // Initial sorted array to build a balanced BST: {12, 25, 37, 50, 62, 75, 87}
        Integer[] arr = { 12, 25, 37, 50, 62, 75, 87 };
        Node root = construct(arr, 0, arr.length - 1);

        System.out.println("--- 1. Initial Balanced BST ---");
        display(root);
        
        // --- Testing Insertion (add) ---
        root = add(root, 45); // Insert between 37 and 50
        root = add(root, 99); // Insert as the new maximum
        root = add(root, 10); // Insert as the new minimum

        System.out.println("\n--- 2. BST After Insertion (45, 99, 10) ---");
        display(root);
    }
}