import java.util.*;

public class BinaryTreeTiltOfBT {

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

    static int totalTreeTilt=0;
    public static int calculateTiltAndSum(Node currentNode) {

            // Base Case: If the node is null, its sum is 0 (it contributes no value).
            // This also acts as the base sum for leaf nodes' null children.
            if (currentNode == null) {
                return 0;
            }

            // 1. Recursive Calls (Post-Order Traversal Logic)
            // 'leftSubtreeSum' gets the total sum of all values in the left child's subtree.
            int leftSubtreeSum = calculateTiltAndSum(currentNode.left);
            
            // 'rightSubtreeSum' gets the total sum of all values in the right child's subtree.
            int rightSubtreeSum = calculateTiltAndSum(currentNode.right);

            // 2. Calculate and Accumulate Tilt (Side Effect)
            // The tilt of the current node is the absolute difference between the
            // sums of its left and right subtrees.
            int currentNodeTilt = Math.abs(leftSubtreeSum - rightSubtreeSum);
            
            // Add the current node's tilt to the running total for the entire tree.
            totalTreeTilt += currentNodeTilt;

            // 3. Prepare Return Value
            // The value returned to the parent is the total sum of this entire subtree.
            int totalSubtreeSum = leftSubtreeSum + rightSubtreeSum + currentNode.data;

            return totalSubtreeSum;
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

        calculateTiltAndSum(root);
        System.out.println("The tilt of the tree is: " + totalTreeTilt);
    }
}