import java.util.*;

public class BinaryTreeDiameter2 {

    // --- 1. Node Class ---
    public static class Node {
        int data;
        Node left;
        Node right;

        // Constructor
        Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }

        // Original Constructor (kept for completeness)
        Node(int data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

    // --- 2. Pair Class for Iterative Construction ---
    public static class Pair {
        Node node;
        int state; // 1: go left, 2: go right, 3: pop

        Pair(Node node, int state) {
            this.node = node;
            this.state = state;
        }
    }

    // --- 3. Display Function (Preorder Visualization) ---
    public static void display(Node node) {
        if (node == null) {
            return;
        }

        // Create the string representation: "LNode <- RootNode -> RNode"
        String str = "";
        str += (node.left == null) ? ". " : node.left.data + "";
        str += " <- " + node.data + " -> ";
        str += (node.right == null) ? " ." : node.right.data + "";

        System.out.println(str);

        // Recursive calls
        display(node.left);
        display(node.right);
    }

    // --- 4. Height Function (Edges) ---
    public static int height(Node node){
        // Height is the number of edges. Base case: null tree has height -1.
        if(node == null){
            return -1;
        }

        int lh = height(node.left);
        int rh = height(node.right);
        
        // Max height of subtrees + 1 (for the current edge)
        int th = Math.max(lh, rh) + 1; 
        return th;
    }

    // --- 5. Diameter Calculation (O(N^2) Approach) ---
    public static int diameter1(Node node){
        if(node == null){
            return 0;
        }

        // Max distance in the left subtree
        int ld = diameter1(node.left); 
        
        // Max distance in the right subtree
        int rd = diameter1(node.right);
        
        // Max distance passing *through* the current node
        // (Height of left + 1) + (Height of right + 1) = Height of left + Height of right + 2
        int f = height(node.left) + height(node.right) + 2;

        int dia = Math.max(f, Math.max(ld, rd));
        return dia;
    }

    public static class DiaPair {
        int height;
        int diameter;
    }

    public static DiaPair diameter2(Node node) {
        if (node == null) {
            DiaPair bp = new DiaPair();
            bp.height = -1; // -1 for null tree
            bp.diameter = 0;
            return bp;
        }

        DiaPair lp = diameter2(node.left);  // Result from left child
        DiaPair rp = diameter2(node.right); // Result from right child

        DiaPair mp = new DiaPair();

        // 1. Calculate new diameter through the current node
        int f = lp.height + rp.height + 2; 

        // 2. The diameter of the current subtree is the max of the three possibilities
        mp.diameter = Math.max(f, Math.max(lp.diameter, rp.diameter));

        // 3. Calculate new height
        mp.height = Math.max(lp.height, rp.height) + 1;

        return mp;
    }

    // --- 6. Main Method ---
    public static void main(String[] args) {

        // Array representing the tree structure in a custom level-order fashion
        Integer[] arr = {
            50, // Root
            25, // Left of 50
            12, // Left of 25
            null, // Left of 12
            null, // Right of 12
            37, // Right of 25
            30, // Left of 37
            null, // Left of 30
            null, // Right of 30
            null, // Right of 37
            75, // Right of 50
            62, // Left of 75
            null, // Left of 62
            70, // Right of 62
            null, // Left of 70
            null, // Right of 70
            87, // Right of 75
            null, // Left of 87
            null // Right of 87
        };

        // --- Iterative Tree Construction Logic ---
        Stack<Pair> st = new Stack<>();

        // 1. Initialize Root and Stack
        Node root = new Node(arr[0]);
        Pair rp = new Pair(root, 1);
        st.push(rp);

        int idx = 0;
        while (st.size() > 0) {
            Pair top = st.peek();
            
            if (top.state == 1) { // State 1: Try to build Left Child
                idx++;
                if (arr[idx] != null) {
                    Node ln = new Node(arr[idx]);
                    top.node.left = ln;
                    Pair lp = new Pair(ln, 1);
                    st.push(lp); // Push new node to stack
                } else {
                    top.node.left = null;
                }
                top.state++; // Move to State 2
            } 
            
            else if (top.state == 2) { // State 2: Try to build Right Child
                idx++;
                if (arr[idx] != null) {
                    Node rn = new Node(arr[idx]);
                    top.node.right = rn;
                    Pair rnp = new Pair(rn, 1);
                    st.push(rnp); // Push new node to stack
                } else {
                    top.node.right = null;
                }
                top.state++; // Move to State 3
            } 
            
            else { // State 3: Pop (finished processing this node)
                st.pop();
            }
        }
        
        // --- Output ---
        System.out.println("--- Tree Structure Display (Preorder Format) ---");
        display(root);
        System.out.println("-------------------------------------------------");

        // --- Execute diameter1 and print result ---
        DiaPair p = diameter2(root);
        System.out.println("The Diameter (longest path in edges) of the tree is: " + p.diameter);
    }
}