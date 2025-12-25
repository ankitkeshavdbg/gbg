import java.util.*;

public class BinaryTreeFind {
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

    public static boolean find(Node node, int data){
        // code is in 5 parts.

        // 1. Base Case: If the current node is null, 
        if(node == null){
            return false;
        }

        // 2. Self Check: If the current node holds the data, 
        //    we have found it. Stop immediately and return true.
        if(node.data == data){
            return true;
        }

        // 3. Search Left: Recursively call find on the left child.
        boolean filc = find(node.left, data);
        
        // 4. Check Left Result: If the data was found anywhere in the left subtree, 
        //    propagate the 'true' result up immediately.
        if(filc){
            return true;
        }

        // 5. Search Right: Recursively call find on the right child.
        boolean firc = find(node.right, data);
        
        // 6. Check Right Result: If the data was found anywhere in the right subtree, 
        //    propagate the 'true' result up immediately.
        if(firc){
            return true;
        }

        // 7. Final Return: If the data was not found in the current node, 
        //    nor in the left, nor in the right subtrees, return false.
        return false;
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
        
        if(find(root, 30)){
            System.out.println("30 is found");
        }
        if(!find(root, 130)){
            System.out.println("130 is not found");
        }
    }
}