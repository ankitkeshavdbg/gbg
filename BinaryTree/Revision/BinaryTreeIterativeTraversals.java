import java.util.*;

public class BinaryTreeIterativeTraversals {
    public static class Node {
        int data;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
        }
    }

    public static class Pair {
        //you would need to write the pair class for state machine
    }

    // --- YOUR TASK: ITERATIVE TRAVERSALS (PRE, IN, POST) ---
    public static void iterativeTraversals(Node node) {
        //1. first write the pair class for state machine.
        if (node == null) return;
        String pre = "";
        String in = "";
        String post= "";


        

        System.out.println("Pre-order:  " + pre);
        System.out.println("In-order:   " + in);
        System.out.println("Post-order: " + post);
    }

    public static void main(String[] args) {
        Integer[] arr = {
            50, 25, 12, null, null, 37, 30, null, null, null, 
            75, 62, null, 70, null, null, 87, null, null
        };

        Node root = construct(arr);

        System.out.println("Running Iterative Traversals...");
        iterativeTraversals(root);
    }

    // --- Iterative Constructor (Required for full boilerplate) ---
    public static Node construct(Integer[] arr) {
        if (arr.length == 0 || arr[0] == null) return null;

        Stack<Pair> st = new Stack<>();
        Node root = new Node(arr[0]);
        st.push(new Pair(root, 1));

        int idx = 0;
        while (st.size() > 0) {
            Pair top = st.peek();
            if (top.state == 1) {
                idx++;
                if (idx < arr.length && arr[idx] != null) {
                    top.node.left = new Node(arr[idx]);
                    st.push(new Pair(top.node.left, 1));
                }
                top.state++;
            } else if (top.state == 2) {
                idx++;
                if (idx < arr.length && arr[idx] != null) {
                    top.node.right = new Node(arr[idx]);
                    st.push(new Pair(top.node.right, 1));
                }
                top.state++;
            } else {
                st.pop();
            }
        }
        return root;
    }
}