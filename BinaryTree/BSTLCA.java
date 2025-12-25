import java.util.*;

public class BSTLCA {

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
    // Core Function: Lowest Common Ancestor (LCA)
    // ------------------------------------------------------------------

    /**
     * Finds the Lowest Common Ancestor (LCA) of two data values (d1 and d2)
     * in a Binary Search Tree (BST). This function assumes both d1 and d2
     * are present in the BST.
     *
     * @param node The current node in the traversal (starts at the root).
     * @param d1 The data value of the first node.
     * @param d2 The data value of the second node.
     * @return The data value of the LCA node.
     */
    public static int lca(Node node, int d1, int d2) {
        // Condition 1: If both d1 and d2 are smaller than the current node's data,
        // the LCA must be in the LEFT subtree.
        if (d1 < node.data && d2 < node.data) {
            return lca(node.left, d1, d2);
        }
        
        // Condition 2: If both d1 and d2 are larger than the current node's data,
        // the LCA must be in the RIGHT subtree.
        else if (d1 > node.data && d2 > node.data) {
            return lca(node.right, d1, d2);
        }
        
        // Condition 3: Otherwise, the current node is the LCA.
        // This happens if:
        // a) One value is smaller and one is larger (node.data is between d1 and d2).
        // b) The current node's data is equal to d1 or d2.
        else {
            return node.data;
        }
    }


    // ------------------------------------------------------------------
    // Helper Functions for BST Construction and Display
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
     * Main method to test the BST and LCA functionality.
     */
    public static void main(String[] args) {
        // Sorted array for a balanced BST: {10, 20, 30, 40, 50, 60, 70}
        Integer[] arr = { 10, 20, 30, 40, 50, 60, 70 };
        Node root = construct(arr, 0, arr.length - 1); // Root will be 40

        System.out.println("--- 1. Constructed Balanced BST (Root: 40) ---");
        display(root);

        // --- Test Cases ---

        // Test Case 1: Nodes in different subtrees (LCA should be the root)
        int d1_1 = 10;
        int d2_1 = 70;
        int lca1 = lca(root, d1_1, d2_1);
        System.out.println("\nLCA of " + d1_1 + " and " + d2_1 + " is: " + lca1 + " (Expected: 40)");

        // Test Case 2: Nodes in the left subtree
        int d1_2 = 10;
        int d2_2 = 30;
        int lca2 = lca(root, d1_2, d2_2);
        System.out.println("LCA of " + d1_2 + " and " + d2_2 + " is: " + lca2 + " (Expected: 20)");

        // Test Case 3: Nodes in the right subtree
        int d1_3 = 50;
        int d2_3 = 70;
        int lca3 = lca(root, d1_3, d2_3);
        System.out.println("LCA of " + d1_3 + " and " + d2_3 + " is: " + lca3 + " (Expected: 60)");

        // Test Case 4: One node is the LCA
        int d1_4 = 40; // Root
        int d2_4 = 60;
        int lca4 = lca(root, d1_4, d2_4);
        System.out.println("LCA of " + d1_4 + " and " + d2_4 + " is: " + lca4 + " (Expected: 40)");

        // Test Case 5: One node is the LCA (in a deeper subtree)
        int d1_5 = 20;
        int d2_5 = 30;
        int lca5 = lca(root, d1_5, d2_5);
        System.out.println("LCA of " + d1_5 + " and " + d2_5 + " is: " + lca5 + " (Expected: 20)");
    }
}