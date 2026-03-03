import java.util.*;

public class multisolver {
    public static class Node {
        int data;
        ArrayList<Node> children = new ArrayList<>();

        Node(int data) {
            this.data = data;
        }
    }

    // Global variables to store results
    static int size;
    static int max;
    static int min;
    static int height;

    public static void multisolver(Node node, int depth) {
        // 1. Update Size
        size++;

        // 2. Update Min and Max
        min = Math.min(min, node.data);
        max = Math.max(max, node.data);

        // 3. Update Height (depth of the deepest node)
        height = Math.max(height, depth);

        // 4. Recursive calls for children
        for (Node child : node.children) {
            multisolver(child, depth + 1);
        }
    }

    public static void main(String[] args) {
        // Initialization for global variables
        size = 0;
        min = Integer.MAX_VALUE;
        max = Integer.MIN_VALUE;
        height = 0; // Usually defined as edges (root is 0)

        // Mock Tree Construction
        Node root = new Node(10);
        Node n20 = new Node(20);
        Node n30 = new Node(30);
        root.children.addAll(Arrays.asList(n20, n30));
        n20.children.add(new Node(50));
        n30.children.add(new Node(5));

        // Execute Multisolver
        multisolver(root, 0);

        // Output Results
        System.out.println("Size: " + size);     // 5
        System.out.println("Max: " + max);       // 50
        System.out.println("Min: " + min);       // 5
        System.out.println("Height: " + height); // 2
    }
}