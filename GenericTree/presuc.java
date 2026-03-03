import java.util.*;

public class presuc {
    public static class Node {
        int data;
        ArrayList<Node> children = new ArrayList<>();

        Node(int data) {
            this.data = data;
        }
    }

    // Global variables for the Multisolver pattern
    static Node pre;
    static Node suc;
    static int state;

    public static void presuc(Node node, int data) {
        if (state == 0) {
            if (node.data == data) {
                state = 1; // Target found, the next node visited will be 'suc'
            } else {
                pre = node; // Keep updating 'pre' until we hit the target
            }
        } else if (state == 1) {
            suc = node; // This is the node visited immediately after 'data'
            state = 2;  // Stop capturing further nodes
        }

        for (Node child : node.children) {
            // Optimization: If state is 2, we already have both pre and suc
            if (state < 2) {
                presuc(child, data);
            }
        }
    }

    public static void main(String[] args) {
        // Constructing a sample tree: 10 -> [20, 30], 20 -> [50, 60]
        Node root = new Node(10);
        Node n20 = new Node(20);
        Node n30 = new Node(30);
        root.children.add(n20);
        root.children.add(n30);

        n20.children.add(new Node(50));
        n20.children.add(new Node(60));

        // Initialization
        pre = null;
        suc = null;
        state = 0;
        int target = 50;

        presuc(root, target);

        // Display results
        System.out.println("Target: " + target);
        System.out.println("Predecessor: " + (pre != null ? pre.data : "None"));
        System.out.println("Successor: " + (suc != null ? suc.data : "None"));
    }
}