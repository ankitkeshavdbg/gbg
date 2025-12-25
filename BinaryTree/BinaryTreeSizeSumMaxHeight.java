import java.util.*;

public class BinaryTreeSizeSumMaxHeight {
    public static class Node {
        int data;
        Node left;
        Node right;

        // Constructor 1: Used for tree construction
        Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }

        // Constructor 2: Original (kept for completeness)
        Node(int data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

    // Pair class for Stack-based iteration
    public static class Pair {
        Node node;
        int state; // 1: go left, 2: go right, 3: pop

        Pair(Node node, int state) {
            this.node = node;
            this.state = state;
        }
    }

    public static void display(Node node) {
        if (node == null) {
            return;
        }

        // Create the string representation: "LNode <- RootNode -> RNode"
        String str = "";

        // Left Child Display
        str += (node.left == null) ? ". " : node.left.data + "";

        /*
         * if (node.left == null) {
         *       str += ". ";
         * } else {
         *       str += node.left.data + "";
         * }
         * 
         */

        str += " <- " + node.data + " -> ";

        // Right Child Display
        str += (node.right == null) ? " ." : node.right.data + "";

        System.out.println(str);

        // Recursive calls
        display(node.left);
        display(node.right);
    }

    public static int size(Node node){

        if(node == null){
            return 0;
        }

        int ls = size(node.left);
        int rs = size(node.right);
        return ls + rs + 1;

    }

    public static int sum(Node node){
        if(node == null){
            return 0;
        }

        int lsm = sum(node.left);
        int rsm = sum(node.right);
        return lsm +rsm +node.data;

    }

public static int max(Node node){
        if(node == null){
            // Return a value smaller than any possible node data
            return Integer.MIN_VALUE; 
        }

        int lm = max(node.left);
        int rm = max(node.right);
        // Compare current node's data with max of left and right subtrees
        int tm = Math.max(node.data, Math.max(lm, rm)); 
        return tm;
    }

    public static int height(Node node){
        // The height is defined as the number of edges from the root to the deepest leaf.
        if(node == null){
            return -1; // -1 for a null tree, so a single-node tree has height 0.
        }

        int lh = height(node.left);
        int rh = height(node.right);
        int th = Math.max(lh, rh) + 1; // Max height of subtrees + 1 (for the current edge)
        return th;
    }

    public static void main(String[] args) {

        Integer[] arr = {
                50, // Root
                25, // Left of 50
                12, // Left of 25
                null, // Left of 12 (missing)
                null, // Right of 12 (missing)
                37, // Right of 25
                30, // Left of 37
                null, // Left of 30 (missing)
                null, // Right of 30 (missing)
                null, // Right of 37 (missing)
                75, // Right of 50
                62, // Left of 75
                null, // Left of 62 (missing)
                70, // Right of 62
                null, // Left of 70 (missing)
                null, // Right of 70 (missing)
                87, // Right of 75
                null, // Left of 87 (missing)
                null // Right of 87 (missing)
        };

        // --- Iterative Tree Construction Logic (from your provided code) ---
        Stack<Pair> st = new Stack<>();

        // The first element must not be null for this construction logic to start
        Node root = new Node(arr[0]);
        Pair rp = new Pair(root, 1);
        st.push(rp);

        int idx = 0;
        while (st.size() > 0) {
            Pair top = st.peek();
            if (top.state == 1) { // Process Left Child (State 1)
                idx++;
                if (arr[idx] != null) {
                    Node ln = new Node(arr[idx], null, null);
                    top.node.left = ln;

                    Pair lp = new Pair(ln, 1);
                    st.push(lp);
                } else {
                    top.node.left = null;
                }
                top.state++;

            } else if (top.state == 2) { // Process Right Child (State 2)
                idx++;
                if (arr[idx] != null) {
                    Node rn = new Node(arr[idx], null, null);
                    top.node.right = rn;

                    Pair rnp = new Pair(rn, 1);
                    st.push(rnp);
                } else {
                    top.node.right = null;
                }
                top.state++;

            } else { // Pop the node (State 3)
                st.pop();
            }
        }
        System.out.println("--- Tree Structure Display (Preorder Format) ---");
        display(root);
        System.out.println("-------------------------------------------------");

        // --- Call the required functions ---
        System.out.println("✅ Tree Properties Calculation:");
        
        // 1. Calculate Size
        int size = size(root);
        System.out.println("   - Size (Total Nodes): " + size); 
        
        // 2. Calculate Sum
        int sum = sum(root);
        System.out.println("   - Sum of Nodes: " + sum);
        
        // 3. Calculate Max
        int max = max(root);
        System.out.println("   - Maximum Node Value: " + max);
        
        // 4. Calculate Height (edges)
        int height = height(root);
        System.out.println("   - Height (Max Edges from root): " + height);
        // The height of a single-node tree is 0, and a null tree is -1.
        System.out.println("-------------------------------------------------");
    }
}