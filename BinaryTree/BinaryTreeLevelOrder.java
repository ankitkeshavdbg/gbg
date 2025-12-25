import java.util.*;

public class BinaryTreeLevelOrder {
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

        String str = "";
        str += (node.left == null) ? ". " : node.left.data + "";
        str += " <- " + node.data + " -> ";
        str += (node.right == null) ? " ." : node.right.data + "";
        System.out.println(str);

        display(node.left);
        display(node.right);
    }

    // --- Level Order Traversal Implementation ---
    public static void levelOrder(Node node){
        // We use ArrayDeque as the concrete implementation of the Queue interface.
        Queue<Node> q = new ArrayDeque<>(); 

        if (node != null) {
            q.add(node);
        }

        System.out.println("Level Order Traversal:");
        
        // Loop continues as long as there are nodes to process
        while(q.size() > 0){
            // 1. Get the size of the current level
            int count = q.size(); 
            
            // Loop through all nodes in the current level
            for(int i = 0; i < count; i++){
                // 2. Remove the front node (dequeue)
                Node f = q.remove(); 
                
                // Print the node data
                System.out.print(f.data + " "); 
                
                // 3. Add the left child if it exists (enqueue)
                if(f.left != null)
                    q.add(f.left);
                    
                // 4. Add the right child if it exists (enqueue)
                if(f.right != null)
                    q.add(f.right);
            }
            
            // Move to the next line after processing an entire level
            System.out.println(); 
        }
    }


    public static void main(String[] args) {

        Integer[] arr = {
                50, 25, 12, null, null, 37, 30, null, null, null, 75, 62, null, 70, null, null, 87, null, null
        };

        // --- Iterative Tree Construction Logic (provided in the prompt) ---
        Stack<Pair> st = new Stack<>();
        Node root = new Node(arr[0]);
        Pair rp = new Pair(root, 1);
        st.push(rp);

        int idx = 0;
        while (st.size() > 0) {
            Pair top = st.peek();
            if (top.state == 1) { // Process Left Child (State 1)
                idx++;
                if (arr[idx] != null) {
                    Node ln = new Node(arr[idx]);
                    top.node.left = ln;
                    st.push(new Pair(ln, 1));
                }
                top.state++;
            } else if (top.state == 2) { // Process Right Child (State 2)
                idx++;
                if (arr[idx] != null) {
                    Node rn = new Node(arr[idx]);
                    top.node.right = rn;
                    st.push(new Pair(rn, 1));
                }
                top.state++;
            } else { // Pop the node (State 3)
                st.pop();
            }
        }
        
        // 

        System.out.println("--- Tree Structure Display (Preorder Format) ---");
        display(root);
        System.out.println("-------------------------------------------------");
        
        levelOrder(root);
    }
}