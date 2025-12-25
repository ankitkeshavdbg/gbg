import java.util.ArrayList;
import java.util.Stack;

public class TreeTraversal {

    // --- 1. The Node Class for the Generic Tree (Nested) ---
    private static class Node {
        int data;
        ArrayList<Node> children = new ArrayList<>();

        public Node(int data) {
            this.data = data;
        }
    }

    // --- 2. The Pair Class for Iterative Traversal (Nested) ---
    private static class Pair {
        Node node;
        int state;

        public Pair(Node node, int state) {
            this.node = node;
            this.state = state;
            // state: -1 (Pre-order visit), 0 to N-1 (Visit child), N (Post-order visit)
        }
    }

    // --- 3. The Iterative Traversal Method ---
    public static void IterativePreandPostOrder(Node node) {
        if (node == null) return;

        // Initialize Stack and push the root node
        Stack<Pair> st = new Stack<>();
        st.push(new Pair(node, -1));

        String pre = "";
        String post = "";

        // Process nodes until the stack is empty
        while (!st.isEmpty()) {
            Pair top = st.peek();

            // Case 1: Pre-order visit (State -1)
            if (top.state == -1) {
                pre += top.node.data + " ";
                top.state++;
            } 
            // Case 2: Post-order visit (State == children size)
            else if (top.state == top.node.children.size()) {
                post += top.node.data + " ";
                st.pop();
            } 
            // Case 3: Process the next child (State is a valid index)
            else {
                // Get the child node at the current state index
                Node childNode = top.node.children.get(top.state);
                Pair cp = new Pair(childNode, -1);
                
                st.push(cp); // Push the child onto the stack
                top.state++; // Increment the parent's state to move to the next child next time
            }
        }

        // Print the final traversals
        System.out.println("✅ Pre-order Traversal: " + pre);
        System.out.println("✅ Post-order Traversal: " + post);
    }

    // --- 4. Main Method for Testing ---
    public static void main(String[] args) {
        /*
         * Example Tree Structure:
         * 10
         * /  |  \
         * 20  30  40
         * / \   |
         * 50 60  70
         */

        // Build Tree (using the nested Node class)
        Node n10 = new Node(10);
        Node n20 = new Node(20);
        Node n30 = new Node(30);
        Node n40 = new Node(40);
        Node n50 = new Node(50);
        Node n60 = new Node(60);
        Node n70 = new Node(70);

        // Connections
        n20.children.add(n50);
        n20.children.add(n60);
        n30.children.add(n70);
        n10.children.add(n20);
        n10.children.add(n30);
        n10.children.add(n40);

        // Run the Traversal
        System.out.println("--- Iterative Pre-order and Post-order Traversal ---");
        IterativePreandPostOrder(n10);
    }
}