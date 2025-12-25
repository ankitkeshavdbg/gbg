import java.util.*;

public class BinaryTreeIsBalancedOptimized {

    // --- 1. Node Class ---
    public static class Node {
        int data;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    // --- 2. Pair Class for Iterative Tree Construction ---
    public static class Pair {
        Node node;
        int state; 

        Pair(Node node, int state) {
            this.node = node;
            this.state = state;
        }
    }

    // --- 3. BalPair Class for Height and Balance Status ---
    public static class BalPair {
        int ht;     // height of the subtree rooted at this node (using -1 for null)
        boolean isBal; // balance status of the subtree rooted at this node
    }

    // --- 4. Display Function (Preorder Visualization) ---
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
    
    /**
     * Optimized isBalanced function using a single post-order traversal (O(N) time).
     * Returns a BalPair containing the height and balance status of the subtree.
     */
    public static BalPair isBalanced(Node node){
        // **CRITICAL FIX: Must return BalPair in the base case**
        if(node == null){
            BalPair bp = new BalPair();
            bp.ht = -1; // Height of null is -1
            bp.isBal = true;
            return bp;
        }

        // Recursive calls: Get results from left and right subtrees
        BalPair lb = isBalanced(node.left);
        BalPair rb = isBalanced(node.right);

        // Calculate result for the current node (mp = my pair)
        BalPair mp = new BalPair();
        
        // 1. Calculate Balance Status: 
        // Current node is balanced if:
        // a) Left subtree is balanced (lb.isBal) AND
        // b) Right subtree is balanced (rb.isBal) AND
        // c) Absolute height difference is <= 1 (|lb.ht - rb.ht| <= 1)
        mp.isBal = lb.isBal && rb.isBal && (Math.abs(lb.ht - rb.ht) <= 1);
        
        // 2. Calculate Height: 
        // Height is 1 + maximum height of its children
        mp.ht = Math.max(lb.ht, rb.ht) + 1;

        return mp;
    }

    /**
     * Public wrapper function for cleaner usage.
     */
    public static boolean checkBalance(Node root) {
        if (root == null) return true;
        return isBalanced(root).isBal;
    }


    // --- Helper function to build the tree from the array (Iterative Construction) ---
    public static Node buildTree(Integer[] arr) {
        if (arr == null || arr.length == 0 || arr[0] == null) return null;

        Stack<Pair> st = new Stack<>();
        Node root = new Node(arr[0]);
        Pair rp = new Pair(root, 1);
        st.push(rp);

        int idx = 0;
        while (st.size() > 0) {
            Pair top = st.peek();
            
            if (top.state == 1) { // State 1: Left Child
                idx++;
                if (idx < arr.length && arr[idx] != null) {
                    Node ln = new Node(arr[idx]);
                    top.node.left = ln;
                    st.push(new Pair(ln, 1)); 
                } else {
                    top.node.left = null;
                }
                top.state++; 
            } 
            
            else if (top.state == 2) { // State 2: Right Child
                idx++;
                if (idx < arr.length && arr[idx] != null) {
                    Node rn = new Node(arr[idx]);
                    top.node.right = rn;
                    st.push(new Pair(rn, 1));
                } else {
                    top.node.right = null;
                }
                top.state++; 
            } 
            
            else { // State 3: Pop
                st.pop();
            }
        }
        return root;
    }


    // --- 6. Main Method for Testing ---
    public static void main(String[] args) {
        // Balanced Tree
        Integer[] balancedArr = {1, 2, 3, 4, 5, 6, 7};
        Node balancedRoot = buildTree(balancedArr);
        
        System.out.println("🌳 Balanced Tree Visualization (Height difference is at most 1 everywhere):");
        display(balancedRoot);
        boolean isBalanced1 = checkBalance(balancedRoot);
        System.out.println("\nIs the first tree balanced? **" + isBalanced1 + "**"); // Should be true

        System.out.println("\n" + "-".repeat(30) + "\n");
        
        // Unbalanced Tree
        Integer[] unbalancedArr = {1, null, 2, null, 3, null, 4}; // Deeply skewed tree
        Node unbalancedRoot = buildTree(unbalancedArr);

        System.out.println("🌲 Unbalanced Tree Visualization (Right Skewed):");
        display(unbalancedRoot);
        boolean isBalanced2 = checkBalance(unbalancedRoot);
        System.out.println("\nIs the second tree balanced? **" + isBalanced2 + "**"); // Should be false
    }
}