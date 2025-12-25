import java.util.*;

public class BinaryTreeTraversal {
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

    // --- Original Traversal (Euler Tour) ---
    public static void traversal(Node node){
        if(node == null){
            return;
        }
        System.out.println(node.data +" pre"); // Visiting the node on the way down (first time)
        traversal(node.left);
        System.out.println(node.data +" In"); // Visiting the node between left and right calls (second time)
        traversal(node.right);
        System.out.println(node.data +" post"); // Visiting the node on the way up (third time)
    }

    // --- Standard Traversal Functions ---
    public static void preorder(Node node) { // Root -> Left -> Right
        if (node == null) return;
        System.out.print(node.data + " ");
        preorder(node.left);
        preorder(node.right);
    }

    public static void inorder(Node node) { // Left -> Root -> Right
        if (node == null) return;
        inorder(node.left);
        System.out.print(node.data + " ");
        inorder(node.right);
    }

    public static void postorder(Node node) { // Left -> Right -> Root
        if (node == null) return;
        postorder(node.left);
        postorder(node.right);
        System.out.print(node.data + " ");
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
        
        // --- Execute Traversal Functions ---
        
        // 1. Euler Traversal (your original function)
        System.out.println("🚶 Euler Tour Traversal (Original `traversal` function):");
        traversal(root);
        System.out.println("-------------------------------------------------");

        // 2. Standard Traversal Sequences
        System.out.println("📋 Standard Recursive Traversal Sequences:");
        
        System.out.print("   - Preorder (Root-Left-Right): ");
        preorder(root);
        System.out.println();
        
        System.out.print("   - Inorder (Left-Root-Right): ");
        inorder(root);
        System.out.println();
        
        System.out.print("   - Postorder (Left-Right-Root): ");
        postorder(root);
        System.out.println();
        System.out.println("-------------------------------------------------");
    }
}