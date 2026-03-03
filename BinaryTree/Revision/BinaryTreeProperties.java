import java.util.*;

public class BinaryTreeProperties {
    public static class Node {
        int data;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
        }
    }

    // 1. SIZE: Total number of nodes in the tree
    public static int size(Node node) {
        // TODO: Write logic
        return 0;
    }

    // 2. SUM: Sum of all node data values
    public static int sum(Node node) {
        // TODO: Write logic
        return 0;
    }

    // 3. MAX: The maximum value in the tree
    public static int max(Node node) {
        // TODO: Write logic
        // Hint: Use Integer.MIN_VALUE as the base case for null
        return 0;
    }

    // 4. HEIGHT: Distance from root to deepest leaf (in terms of edges)
    public static int height(Node node) {
        // TODO: Write logic
        // Hint: Base case for null is -1
        return 0;
    }

    public static void main(String[] args) {
        // Using the same tree as before
        Integer[] arr = {50, 25, 12, null, null, 37, 30, null, null, null, 75, 62, null, 70, null, null, 87, null, null};
        Node root = construct(arr);

        System.out.println("Size: " + size(root));
        System.out.println("Sum: " + sum(root));
        System.out.println("Max: " + max(root));
        System.out.println("Height: " + height(root));
    }

    // Helper to quickly build the tree for testing
    public static Node construct(Integer[] arr) {
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

    public static class Pair {
        Node node;
        int state;
        Pair(Node node, int state) {
            this.node = node;
            this.state = state;
        }
    }
}