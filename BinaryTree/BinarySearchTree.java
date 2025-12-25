import java.util.*;

public class BinarySearchTree {

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

    /**
     * Constructs a Balanced Binary Search Tree (BST) from a sorted array.
     * Uses the middle element as the root recursively.
     * @param arr The sorted array of integers.
     * @param lo The starting index of the subarray.
     * @param hi The ending index of the subarray.
     * @return The root of the constructed BST subtree.
     */
    public static Node construct(Integer[] arr, int lo, int hi){
        // Base case: If the range is invalid, return null.
        if(lo > hi){
            return null;
        }
        
        // Find the middle element to serve as the root (for balance).
        int mid = (lo + hi) / 2;

        int data = arr[mid];
        
        // Recursive calls to construct left and right subtrees.
        // Left subtree uses elements from 'lo' to 'mid-1'.
        Node lc = construct(arr, lo, mid - 1);
        // Right subtree uses elements from 'mid+1' to 'hi'.
        Node rc = construct(arr, mid + 1, hi);

        // Create and return the new node.
        Node node = new Node(data, lc, rc);
        return node;
    }

    /**
     * Prints the tree structure in a format:
     * Left_Child <- Parent_Node -> Right_Child
     * Performs a pre-order traversal.
     * @param node The current node to display.
     */
    public static void display(Node node) {
        // Base case: Stop if the node is null.
        if (node == null) {
            return;
        }

        String str = "";
        
        // Append left child's data or a dot if null.
        str += (node.left == null) ? ". " : node.left.data + "";
        str += " <- " + node.data + " -> ";
        // Append right child's data or a dot if null.
        str += (node.right == null) ? " ." : node.right.data + "";

        System.out.println(str);

        // Recurse on left and right children.
        display(node.left);
        display(node.right);
    }

    /**
     * Main method to test the BST construction and display.
     */
    public static void main(String[] args) {
        // Sorted array input for construction.
        Integer[] arr = {12, 25, 37, 50, 62, 75, 87};
        
        // Construct the balanced BST.
        Node root = construct(arr, 0, arr.length - 1);
        
        // Display the constructed tree.
        System.out.println("--- Constructed BST Display ---");
        display(root);
    }
}