import java.util.*;

public class BinaryTreeConstructor {
    public static class Node {
        int data;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
        }

        Node(int data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

    public static class Pair {
        //write how your pair class will look like
    }

    public static void display(Node node) {
        if (node == null) return;
        String str = (node.left == null) ? ". " : node.left.data + "";
        str += " <- " + node.data + " -> ";
        str += (node.right == null) ? " ." : node.right.data + "";
        System.out.println(str);
        display(node.left);
        display(node.right);
    }

    public static void main(String[] args) {
        Integer[] arr = {
            50, 25, 12, null, null, 37, 30, null, null, null, 
            75, 62, null, 70, null, null, 87, null, null
        };

        Stack<Pair> st = new Stack<>();
        Node root = new Node(arr[0]);
        st.push(new Pair(root, 1));

        int idx = 0;
        
        // ---------------------------------------------------------
        // TODO: WRITE YOUR ITERATIVE CONSTRUCTION LOGIC HERE
        // Hint: 
        // State 1: Create/Attach Left Child, increment state, push new Pair
        // State 2: Create/Attach Right Child, increment state, push new Pair
        // State 3: Pop from stack
        // ---------------------------------------------------------

        while (st.size() > 0) {
            // Your Code Here
        }

        // ---------------------------------------------------------

        System.out.println("--- Tree Structure ---");
        display(root);
    }
}