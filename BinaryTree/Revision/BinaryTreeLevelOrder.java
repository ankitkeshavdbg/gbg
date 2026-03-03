import java.util.*;

public class BinaryTreeLevelOrder {
    public static class Node {
        int data;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
        }
    }

    public static class Pair {
        Node node;
        int state;
        Pair(Node node, int state) {
            this.node = node;
            this.state = state;
        }
    }

    // --- YOUR TASK: LEVEL ORDER TRAVERSAL ---
    public static void levelOrder(Node node) {
        if (node == null) return;

        // Use a Queue (FIFO)
        
        
        // TODO: Implement the RPA (Remove, Print, Add) logic here
        // 1. Add root
        // 2. While queue is not empty:
        //    a. Remove node
        //    b. Print node.data
        //    c. Add children (Left then Right)
    }

    // --- YOUR TASK: LEVEL ORDER LINE-BY-LINE ---
    public static void levelOrderLineByLine(Node node) {
        if (node == null) return;

        // TODO: Implement the nested loop approach
        // Hint: Use the size of the queue at the start of each level

    }

    public static void main(String[] args) {
        Integer[] arr = {
            50, 25, 12, null, null, 37, 30, null, null, null, 
            75, 62, null, 70, null, null, 87, null, null
        };

        Node root = construct(arr);

        System.out.println("Standard Level Order:");
        levelOrder(root);
        
        System.out.println("\n\nLine-by-Line Level Order:");
        levelOrderLineByLine(root);
    }

    // --- Iterative Constructor (The one you completed) ---
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
                if (arr[idx] != null) {
                    top.node.left = new Node(arr[idx]);
                    st.push(new Pair(top.node.left, 1));
                }
                top.state++;
            } else if (top.state == 2) {
                idx++;
                if (arr[idx] != null) {
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