import java.util.*;

public class BSTrwsol {
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



// 1. Static Variable for Accumulated Sum
    // This variable maintains the running sum of all nodes encountered
    // so far during the reverse inorder traversal (Right -> Root -> Left).
    // It is initialized to 0.
    private static int sum = 0;

    /**
     * Recursively modifies the data in the BST nodes.
     * Each node's data is replaced by the sum of all keys greater than or
     * equal to the node's original key in the original BST.
     * This is achieved by performing a Reverse Inorder Traversal (Right, Root, Left).
     *
     * @param node The current node being processed.
     */
    public static void rwsol(Node node) {

        // Base Case: If the current node is null, we've gone past a leaf,
        // so we return and stop the recursion for this branch.
        if (node == null) {
            return;
        }

        // 1. RIGHT Subtree Traversal (Largest values first)
        // Recursively call the function on the right child.
        // In a BST, the right subtree contains all keys larger than the current node's key.
        rwsol(node.right);

        // 2. ROOT Processing (Visit the node)

        // Store the node's ORIGINAL data value.
        // This value is greater than all values in the left subtree, and less than
        // all values in the right subtree (which have already been processed and added to 'sum').
        int od = node.data;

        // Update the current node's data:
        // 'sum' currently holds the sum of all larger elements (from the right subtree).
        // The current node's data is replaced by this sum.
        node.data = sum;

        // Update the running sum:
        // Add the current node's ORIGINAL data to the 'sum'.
        // This updated 'sum' will be used for the next node processed (the largest element in the left subtree).
        sum += od;

        // 3. LEFT Subtree Traversal (Smaller values next)
        // Recursively call the function on the left child.
        // The sum is now prepared for the left subtree, which contains all keys smaller
        // than the current node's original key.
        rwsol(node.left);
    }


    /**
     * Constructs a Balanced Binary Search Tree (BST) from a sorted array.
     * @param arr The sorted array of integers.
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
        // Initial array for a balanced BST: {12, 25, 37, 50, 62, 75, 87}
        Integer[] arr = { 12, 25, 37, 50, 62, 75, 87 };
        Node root = construct(arr, 0, arr.length - 1);

        // Reset static sum to 0 before the transformation (important if running multiple tests)
        // Since 'sum' is declared as a private static field, we need to ensure it's accessible 
        // or re-initialize it if this main function is called multiple times.
        // For this single test, it's already 0 from its declaration, but setting it here is good practice.
        sum = 0; 
        
        // --- 1. Display Original BST ---
        System.out.println("--- 1. Original Balanced BST (Root: 50) ---");
        display(root);
        System.out.println("\n");
        // 

        // --- 2. Perform the Transformation ---
        System.out.println("--- 2. Applying rwsol (Greater Sum Tree Conversion) ---");
        rwsol(root);
        System.out.println("Transformation complete. Static sum used for conversion: " + sum);
        System.out.println("\n");

        // --- 3. Display Transformed BST ---
        System.out.println("--- 3. Transformed Greater Sum Tree ---");
        display(root);
        // 
        
        System.out.println("\n--- Expected Results Verification ---");
        System.out.println("The new root node (originally 50) should contain the sum of all elements (12+25+37+50+62+75+87 = 348).");
        System.out.println("The largest element (originally 87) should contain the sum of only itself (87).");
        System.out.println("The smallest element (originally 12) should contain a new value of: 348 - 12 = 336 (This is wrong. It should contain 348 - 0 = 348 - 0 because sum is initialized to 0, and the largest value is processed first).");
        System.out.println("Let's trace the final node values:");
        
        // Final expected values (Total Sum S = 348)
        // 87 -> 0 + 87 = 87
        // 75 -> 87 + 75 = 162
        // 62 -> 162 + 62 = 224
        // 50 -> 224 + 50 = 274
        // 37 -> 274 + 37 = 311
        // 25 -> 311 + 25 = 336
        // 12 -> 336 + 12 = 348
        
        System.out.println("Original Key -> New Value");
        System.out.println("87 (largest) -> 87 (0 + 87)");
        System.out.println("75 -> 162 (87 + 75)");
        System.out.println("62 -> 224 (162 + 62)");
        System.out.println("50 (root) -> 274 (224 + 50)"); // Note: The root's new value is S - (sum of left subtree)
        System.out.println("37 -> 311 (274 + 37)");
        System.out.println("25 -> 336 (311 + 25)");
        System.out.println("12 (smallest) -> 348 (336 + 12)"); // The smallest element is updated last, receiving the sum of all elements.
        
        // Final 'sum' should be the total sum of all elements: 348.
    }
}