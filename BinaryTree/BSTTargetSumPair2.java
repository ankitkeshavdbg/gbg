import java.util.*;

public class BSTTargetSumPair2 {

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
    // 1. Inorder Traversal to Populate Sorted List (tnf)
    // ------------------------------------------------------------------
    
    /**
     * Performs an Inorder Traversal (Left -> Root -> Right) of the BST.
     * Crucially, this traversal visits all nodes in ascending sorted order
     * and adds them to the provided ArrayList.
     * * @param node The current node being processed.
     * @param list The ArrayList to store the sorted elements.
     */
    public static void tnf(Node node, ArrayList<Integer> list){
        // Base case: If the current node is null, we return.
        if(node == null){
            return;
        }
        
        // 1. Recurse on the left subtree. 
        // This ensures all smaller elements are processed first.
        tnf(node.left, list);
        
        // 2. Process the current node (Root).
        // Since we process left first, this node's data is added
        // after all data in its left subtree, maintaining sorted order.
        list.add(node.data);
        
        // 3. Recurse on the right subtree.
        // This ensures all larger elements are processed last.
        tnf(node.right, list);
    }


    // ------------------------------------------------------------------
    // Helper Functions for BST Construction and Display
    // ------------------------------------------------------------------

    /**
     * Constructs a balanced BST from a sorted array.
     */
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

    /**
     * Prints the BST structure (Node <- Root -> Node).
     */
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
    // Main Method for Testing: Two-Pointer Algorithm
    // ------------------------------------------------------------------

    public static void main(String[] args) {
        // BST Array: {10, 20, 30, 40, 50, 60, 70} -> Root: 40 (Balanced BST)
        Integer[] arr = { 10, 20, 30, 40, 50, 60, 70 };
        Node root = construct(arr, 0, arr.length - 1);

        System.out.println("--- 1. Constructed BST ---");
        display(root);
        
        // --------------------------------------------------------------
        // Phase 1: Convert BST to Sorted Array
        // --------------------------------------------------------------
        ArrayList<Integer> list = new ArrayList<>();
        // 'tnf' populates the list in sorted order: [10, 20, 30, 40, 50, 60, 70]
        tnf(root, list); 
        
        // Target sum (data)
        int data = 80; 
        System.out.println("\n--- 2. Finding Pairs for Target Sum: " + data + " ---");
        
        // --------------------------------------------------------------
        // Phase 2: Two-Pointer Algorithm on Sorted List (O(N))
        // --------------------------------------------------------------
        int li = 0;                  // Left Pointer: starts at the smallest element
        int ri = list.size() - 1;    // Right Pointer: starts at the largest element
        
        while(li < ri){
            int left = list.get(li);
            int right = list.get(ri);
            int sum = left + right;

            if(sum < data){
                // Sum is too small. We need a larger 'left' element.
                li++; // Move left pointer to the right.
            } else if(sum > data){
                // Sum is too large. We need a smaller 'right' element.
                ri--; // Move right pointer to the left.
            } else{
                // Sum is equal to the target. Pair found!
                System.out.println(left + " " + right);
                
                // Move both pointers inward to look for the next distinct pair.
                li++;
                ri--;
            }
        }
    }
}