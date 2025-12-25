import java.util.*;

public class BSTTargetSumPair {

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
    // 1. Find Pair Sum (travelAndPrint)
    // ------------------------------------------------------------------
    
    // Helper function for the 'travelAndPrint' logic
    public static boolean find(Node node, int data) {
        if (node == null) return false;
        if (data < node.data) {
            return find(node.left, data);
        } else if (data > node.data) {
            return find(node.right, data);
        } else {
            return true; // Found the data
        }
    }

    public static void travelAndPrint(Node root, Node node, int tar) {
        if (node == null) {
            return;
        }

        // 1. Traverse Left (Inorder)
        travelAndPrint(root, node.left, tar);

        // 2. Process Current Node
        int comp = tar - node.data;

        // Check to avoid duplicate pairs (10 20 and 20 10)
        if (node.data < comp) {
            // 3. Find Complement in the entire tree
            if (find(root, comp) == true) { 
                System.out.println(node.data + " " + comp);
            }
        }

        // 4. Traverse Right (Inorder)
        travelAndPrint(root, node.right, tar);
    }

    // ------------------------------------------------------------------
    // Helper Functions for BST Construction and Display
    // ------------------------------------------------------------------

    public static Node construct(Integer[] arr, int lo, int hi) {
        if (lo > hi) {
            return null;
        }
        int mid = (lo + hi) / 2;
        Node node = new Node(arr[mid], 
                            construct(arr, lo, mid - 1), 
                            construct(arr, mid + 1, hi));
        return node;
    }

    public static void display(Node node) {
        if (node == null) return;
        String str = (node.left == null ? "." : node.left.data) + 
                     " <- " + node.data + " -> " + 
                     (node.right == null ? "." : node.right.data);
        System.out.println(str);
        display(node.left);
        display(node.right);
    }

    // ------------------------------------------------------------------
    // Main Method for Testing
    // ------------------------------------------------------------------

    public static void main(String[] args) {
        // BST Array: {10, 20, 30, 40, 50, 60, 70} -> Root: 40
        Integer[] arr = { 10, 20, 30, 40, 50, 60, 70 };
        Node root = construct(arr, 0, arr.length - 1);

        System.out.println("--- 1. Constructed BST (Root: 40) ---");
        display(root);

        // --- Test 3: travelAndPrint (Find Pair Sum) ---
        int target = 80;
        System.out.println("\n--- 4. travelAndPrint (Find Pair Sum = " + target + ") ---");
        travelAndPrint(root, root, target); // Expected: 10 70, 20 60, 30 50
    }
}