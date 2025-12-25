import java.util.*;

public class BinaryTreeNodeToRootPath {
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

   static ArrayList<Integer> path;
    public static boolean nodeToRootPath(Node node, int data){
        // The algorithm finds the node and, upon success, traces back up to the root,
        // collecting the data of each node on the path.

        // 1. Base Case (Failure): If the current node is null, 
        //    the data cannot be found in this branch.
        if(node == null){
            return false;
        }

        // 2. Self Check (Success): If the current node is the target node (data), 
        //    add its data to the path and return true immediately.
        if(node.data == data){
            path.add(node.data);
            return true;
        }

        // 3. Recursive Call Left: Check if the target is present in the left subtree.
        boolean isFoundLeft = nodeToRootPath(node.left, data);
        
        // 4. Check Left Result (Path Building): If the target was found in the left subtree, 
        //    this node is part of the path. Add its data and propagate 'true' up.
        if(isFoundLeft){
            path.add(node.data);
            return true;
        }

        // 5. Recursive Call Right: Check if the target is present in the right subtree.
        boolean isFoundRight = nodeToRootPath(node.right, data);
        
        // 6. Check Right Result (Path Building): If the target was found in the right subtree, 
        //    this node is part of the path. Add its data and propagate 'true' up.
        if(isFoundRight){
            path.add(node.data);
            return true;
        }

        // 7. Final Return (Not Found): If the target was not found in the current node, 
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
        
        path = new ArrayList<>();
        nodeToRootPath(root, 70);
        System.out.println(path);
    }
}