import java.util.*;

public class removeFromBST {
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
    // Core BST Operations: Add, Remove
    // ------------------------------------------------------------------

    /**
     * Inserts a new data element into the correct position in the BST.
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

        return node; 
    }

    /**
     * Finds and removes the specified data element from the BST.
     * Handles the three main deletion cases (0, 1, or 2 children).
     * 
     * @param node The current node.
     * @param data The value to be removed.
     * @return The updated root of the current subtree.
     */
    public static Node remove(Node node, int data) {
        // Case 1: Search miss (data not found).
        if (node == null) {
            return null;
        }

        // Search phase: Traverse left or right.
        if (data > node.data) {
            node.right = remove(node.right, data);
        } else if (data < node.data) {
            node.left = remove(node.left, data);
        } 
        
        // Found the node to be removed (data == node.data)
        else {
            // Case 4: Node has TWO children.
            if (node.left != null && node.right != null) {
                // Find the largest element in the left subtree (lmax / In-order Predecessor).
                int lmax = max(node.left);
                // Replace the node's data with lmax.
                node.data = lmax;
                // Recursively delete the lmax node from the left subtree.
                node.left = remove(node.left, lmax);
                return node;
            } 
            // Case 3: Node has ONE child (left).
            else if (node.left != null) {
                return node.left; // Promote the left child.
            } 
            // Case 3: Node has ONE child (right).
            else if (node.right != null) {
                return node.right; // Promote the right child.
            } 
            // Case 2: Node has ZERO children (leaf node).
            else {
                return null; // Return null to detach the leaf.
            }
        }

        return node;
    }

    // ------------------------------------------------------------------
    // Utility Functions: Min, Max, Find, Construction, Display
    // ------------------------------------------------------------------

    /**
     * Finds the maximum value in the BST by traversing rightward.
     * @param node The current node.
     * @return The largest data value in the subtree.
     */
    public static int max(Node node) {
        // Keep traversing right until the right child is null.
        if (node.right != null) {
            return max(node.right);
        } else {
            return node.data; // Found the rightmost node (max).
        }
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

        System.out.println("--- 1. Initial Balanced BST (Root: 50) ---");
        display(root);
        
        // --- Test Deletion ---
        
        System.out.println("\n--- 2. Deleting Node 25 (One child) ---");
        root = remove(root, 25);
        display(root); // 12's parent becomes 37

        System.out.println("\n--- 3. Deleting Node 87 (Zero children/Leaf) ---");
        root = remove(root, 87);
        display(root); // 75's right child becomes null

        System.out.println("\n--- 4. Deleting Node 50 (Two children/Root) ---");
        root = remove(root, 50); 
        // 50 should be replaced by 37 (lmax of the original left subtree).
        display(root);
        
        // --- Final Check ---
        System.out.println("\n--- 5. Final Check ---");
        System.out.println("New Root Data: " + root.data); // 37
    }
}