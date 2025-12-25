import java.util.*;

public class BinaryTreeIterativeTraversal {
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

// ⭐ Iterative Traversal (Preorder, Inorder, Postorder)
    public static void IterativeTraversal(Node node){
        // Only run if the root is not null
        if (node == null) return;
        
        Stack<Pair> st = new Stack<>();
        Pair rtp = new Pair(node, 1);
        st.push(rtp);

        String pre = "";
        String in = "";
        String post = "";

        while(st.size() > 0){
            Pair top = st.peek();
            
            if(top.state == 1){ // State 1: Pre-order logic, then increment state and go left
                pre += top.node.data + " ";
                top.state++;

                if(top.node.left != null){
                    Pair lp = new Pair(top.node.left, 1);
                    st.push(lp);
                }
            } else if(top.state == 2){ // State 2: In-order logic, then increment state and go right
                in += top.node.data + " ";
                top.state++;

                if(top.node.right != null){
                    Pair rp = new Pair(top.node.right, 1);
                    st.push(rp);
                }
            } else { // State 3: Post-order logic, then pop
                post += top.node.data + " ";
                st.pop();
            }
        }
        System.out.println("🚀 Iterative Traversal Results:");
        System.out.println("   Preorder: " + pre);
        System.out.println("   Inorder:  " + in);
        System.out.println("   Postorder: " + post);
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
        
        IterativeTraversal(root);
    }
}