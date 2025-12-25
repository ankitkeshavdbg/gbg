import java.util.*;

public class BSTRangePrinter {

    /**
     * Node class for the Binary Search Tree (BST).
     */
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
    // Core Function: Print In Range (pir)
    // ------------------------------------------------------------------

    /**
     * Recursively prints all node data in the BST that falls within the
     * range [d1, d2] in sorted order (inclusive).
     *
     * @param node The current node being processed.
     * @param d1 The lower bound of the range.
     * @param d2 The upper bound of the range.
     */
    public static void pir(Node node, int d1, int d2) {
        // Base Case: Stop recursion when an empty subtree is reached.
        if (node == null) {
            return;
        }

        // Condition 1 (Optimized): Range is entirely in the Left Subtree
        // If the upper bound d2 is less than the current node's data,
        // then all values in the right subtree are > d2 and can be skipped.
        if (d2 < node.data) {
             pir(node.left, d1, d2);
        }

        // Condition 2 (Optimized): Range is entirely in the Right Subtree
        // If the lower bound d1 is greater than the current node's data,
        // then all values in the left subtree are < d1 and can be skipped.
        else if (d1 > node.data) {
            pir(node.right, d1, d2);
        }

        // Condition 3 (Standard Inorder): Node is the ancestor of the range [d1, d2]
        // The current node's data falls within the range [d1, d2].
        // The traversal structure ensures values are printed in sorted order.
        else {
            // 1. Traverse Left (Check for smaller values)
            pir(node.left, d1, d2);

            // 2. Print Current Node (Inorder processing)
            System.out.println(node.data);

            // 3. Traverse Right (Check for larger values)
            pir(node.right, d1, d2);
        }
    }


    // ------------------------------------------------------------------
    // Helper Functions for BST Construction
    // ------------------------------------------------------------------

    /**
     * Constructs a Balanced Binary Search Tree (BST) from a sorted array.
     * @param arr The sorted array of integers.
     * @param lo The low index of the array segment.
     * @param hi The high index of the array segment.
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
     * Main method to test the BST and pir functionality.
     */
    public static void main(String[] args) {
        // Sorted array for a balanced BST: {10, 20, 30, 40, 50, 60, 70}
        Integer[] arr = { 10, 20, 30, 40, 50, 60, 70 };
        Node root = construct(arr, 0, arr.length - 1); // Root will be 40

        System.out.println("--- 1. Constructed Balanced BST (Root: 40) ---");
        display(root);

        // 
        // --- Test Cases for Print In Range (pir) ---

        // Test Case 1: Range [25, 65] (Expected: 30, 40, 50, 60)
        int d1_1 = 25;
        int d2_1 = 65;
        System.out.println("\n--- 2. Printing nodes in range [" + d1_1 + ", " + d2_1 + "] (Expected: 30, 40, 50, 60) ---");
        pir(root, d1_1, d2_1);

        // Test Case 2: Range [10, 30] (Expected: 10, 20, 30)
        int d1_2 = 10;
        int d2_2 = 30;
        System.out.println("\n--- 3. Printing nodes in range [" + d1_2 + ", " + d2_2 + "] (Expected: 10, 20, 30) ---");
        pir(root, d1_2, d2_2);

        // Test Case 3: Range [75, 100] (Expected: None)
        int d1_3 = 75;
        int d2_3 = 100;
        System.out.println("\n--- 4. Printing nodes in range [" + d1_3 + ", " + d2_3 + "] (Expected: None) ---");
        pir(root, d1_3, d2_3);
    }
}