import java.util.ArrayList;
import java.util.Stack;

public class TreeDiameterFinder {
    private static class Node {
        int data;
        ArrayList<Node> children = new ArrayList<>();
    }
    
    // Static variable to store the result (diameter), renamed to 'dia' as per the provided code snippet.
    public static int dia; 
    
    /**
     * Calculates the diameter of the tree rooted at 'node'.
     * This method uses an optimized approach to track the deepest (dch) and second deepest (sdch)
     * child heights in a single pass while recursively calculating the height of the subtree.
     * * @param node The current node being processed.
     * @return The height of the subtree rooted at 'node' (edge count: leaf height = 0).
     */
    public static int calculateDiaReturnHeight(Node node){
        
        // dch and sdch are initialized to -1. 
        // A height of 0 represents a leaf node.
        int dch = -1; // Deepest Child Height
        int sdch = -1; // Second Deepest Child Height

        // 1. Iterate through children to find the deepest and second deepest paths
        for(Node child : node.children){
            // Recursive call returns the height (ch >= 0) of the child's subtree
            int ch = calculateDiaReturnHeight(child); 
            
            // Logic to update dch and sdch in a single pass (O(N) total)
            if(ch > dch){
                // Found a new deepest path (ch > dch). Old dch becomes new sdch.
                sdch = dch;
                dch = ch;
            } else if(ch > sdch){
                // Found a new second deepest path (ch > sdch).
                sdch = ch;
            }
        }
        
        // 2. Calculate the candidate diameter passing through the current node.
        // cand = (dch_path_length) + (sdch_path_length)
        // Path length = height + 1 (1 edge from current node to child)
        // Since dch and sdch are heights (>= -1), the formula simplifies:
        int cand = dch + sdch + 2; 
        
        // 3. Update the global maximum diameter
        if(cand > dia){
            dia = cand;
        }
        
        // 4. Return the height of the current node's subtree
        // Current Height = 1 + dch. If dch is -1 (leaf), returns 0.
        dch += 1;
        return dch;
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
     * The main function to demonstrate and test the diameter calculation logic.
     */
    public static void main(String[] args) {
        // Sample tree structure:
        // Root 10
        //   ├── 20 (H=1, path 10-20-50)
        //   │   └── 50
        //   ├── 30 (H=2, path 10-30-70-110 or 10-30-80-120)
        //   │   ├── 70
        //   │   │   └── 110
        //   │   └── 80
        //   │       └── 120
        //   └── 40 (H=0)
        
        // Expected Diameter (Longest Path): 110 -- 70 -- 30 -- 10 -- 20 -- 50 (5 edges)
        
        int[] arr = {
            10, 
            20, 50, -1, -1, 
            30, 70, 110, -1, -1, 
            80, 120, -1, -1, 
            40, -1, 
            -1
        };
        
        Node root = construct(arr);

        // --- Initialization ---
        // Diameter must be initialized to 0.
        dia = 0;
        
        // --- Test Execution ---
        int rootHeight = calculateDiaReturnHeight(root);

        // --- Output Results ---
        System.out.println("--- N-ary Tree Diameter Result ---");
        System.out.println("Height of the entire tree (Root 10's height): " + rootHeight);
        System.out.println("Maximum Diameter Found (Longest Path in Edges): " + dia);
    }
}