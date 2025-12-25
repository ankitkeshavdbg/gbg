import java.util.*;

public class BinaryTreeIsBalanced {

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
    
    // Global flag for tracking balance
    static boolean isBal = true;

    /**
     * isBalanced function.
     * Checks if the tree is balanced and calculates the height of the subtree.
     * A binary tree is balanced if, for every node, the difference 
     * in height between its left and right subtrees is at most 1.
     */
    public static int isBalanced(Node node){
        if(node == null){
            // Base case: Height of a null node is -1
            return -1;
        }

        // Recursive calls to check balance and get heights of left and right subtrees
        int lb = isBalanced(node.left);
        int rb = isBalanced(node.right);

        // Check the balance condition for the current node
        if(Math.abs(lb - rb) > 1){
            isBal = false;
        }

        // The correct calculation for the height of the current subtree
        return Math.max(lb, rb) + 1;
    }

    /**
     * Public wrapper function to check balance and reset the static flag.
     * (Good practice to avoid issues when calling the function multiple times)
     */
    public static boolean checkBalance(Node root) {
        isBal = true; // Reset the static flag
        isBalanced(root);
        return isBal;
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
        // Balanced Tree (Example: Complete Binary Tree)
        Integer[] balancedArr = {1, 2, 3, 4, 5, 6, 7};
        Node balancedRoot = buildTree(balancedArr);
        
        System.out.println("🌳 Balanced Tree Visualization:");
        display(balancedRoot);
        boolean isBalanced1 = checkBalance(balancedRoot);
        System.out.println("\nIs the first tree balanced? " + isBalanced1); // Should be true

        // ---
        
        // Unbalanced Tree (Example: Skewed Tree)
        Integer[] unbalancedArr = {1, null, 2, null, 3, null, 4};
        Node unbalancedRoot = buildTree(unbalancedArr);

        System.out.println("\n---");

        System.out.println("🌲 Unbalanced Tree Visualization (Right Skewed):");
        display(unbalancedRoot);
        boolean isBalanced2 = checkBalance(unbalancedRoot);
        System.out.println("\nIs the second tree balanced? " + isBalanced2); // Should be false
    }
}