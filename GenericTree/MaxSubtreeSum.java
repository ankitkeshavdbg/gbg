import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Main class containing the logic to find the maximum subtree sum.
 */
public class MaxSubtreeSum {
    
    /**
     * Class representing a node in an N-ary tree.
     */
    private static class Node {
        int data;
        ArrayList<Node> children = new ArrayList<>();
    }
    
    // Static variables to store the result
    public static int stnode; // Stores the data of the node with the max subtree sum
    public static int maxsum; // Stores the max subtree sum found
    
    /**
     * Recursively calculates the sum of the current node's subtree (including itself).
     * It also updates the static variables 'stnode' and 'maxsum' if a greater sum is found.
     * @param node The current node being processed.
     * @return The total sum of the subtree rooted at 'node'.
     */
    public static int maxSubTree(Node node){
        // sum will store the sum of all children subtrees + current node's data
        int sum = 0;
        
        // 1. Calculate sum of all children's subtrees recursively
        for(Node child : node.children){
            int childSubtreeSum = maxSubTree(child);
            sum += childSubtreeSum;
        }
        
        // 2. Add the current node's data to the total sum
        sum += node.data;

        // 3. Compare with the global maximum and update if necessary
        if(sum > maxsum){
            maxsum = sum;
            stnode = node.data; // Store the data of the node
        }
        
        // 4. Return the calculated sum of the current subtree
        return sum;
    }
    
    /**
     * Helper function to construct a sample N-ary tree from an array
     * using the convention where -1 signifies returning to the parent.
     * @param arr The array representing the tree structure.
     * @return The root node of the constructed tree.
     */
    public static Node construct(int[] arr) {
        Node root = null;
        Stack<Node> stack = new Stack<>();
        
        for (int val : arr) {
            if (val == -1) {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else {
                // Use default constructor and set data separately
                Node t = new Node(); 
                t.data = val; 
                
                if (stack.isEmpty()) {
                    root = t;
                } else {
                    stack.peek().children.add(t);
                }
                stack.push(t);
            }
        }
        return root;
    }

    /**
     * The main function to demonstrate and test the maxSubTree logic.
     */
    public static void main(String[] args) {
        // Sample tree structure represented as an array (using -1 to indicate parent return).
        // Tree: 10 -> [20, 30, 40]
        // 20 -> [50, 60]
        // 30 -> [70, 80]
        // 80 -> [110, 120]
        // 40 -> [100]
        int[] arr = {
            10, 20, 50, -1, 60, -1, -1, 
            30, 70, -1, 80, 110, -1, 120, -1, -1, -1, 
            40, 100, -1, -1, -1
        };
        
        Node root = construct(arr);

        // --- Initialization ---
        // It's crucial to initialize maxsum to the smallest possible integer 
        // to ensure any valid sum (even negative ones) is captured.
        maxsum = Integer.MIN_VALUE;
        stnode = -1; // Initialize the node data to an impossible value
        
        // --- Test Execution ---
        maxSubTree(root);

        // --- Output Results ---
        System.out.println("--- Max Subtree Sum Result ---");
        System.out.println("Node with Max Subtree Sum (Data): " + stnode);
        System.out.println("Maximum Subtree Sum Found: " + maxsum);
        System.out.println("\n(Note: The data value " + stnode + " represents the root of the maximum sum subtree.)");
    }
}