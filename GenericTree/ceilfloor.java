import java.util.*;

public class ceilfloor {
    public static class Node {
        int data;
        ArrayList<Node> children = new ArrayList<>();

        Node(int data) {
            this.data = data;
        }
    }

    // Global variables to store results
    static int ceil;
    static int floor;

    public static void ceilAndFloor(Node node, int data) {
        // Checking for Ceil (Smallest among the larger values)
        if (node.data > data) {
            if (node.data < ceil) {
                ceil = node.data;
            }
        } 
        // Checking for Floor (Largest among the smaller values)
        else if (node.data < data) {
            if (node.data > floor) {
                floor = node.data;
            }
        }

        for (Node child : node.children) {
            ceilAndFloor(child, data);
        }
    }

    public static void main(String[] args) {
        // Constructing the tree
        Node root = new Node(10);
        Node n20 = new Node(20);
        Node n30 = new Node(30);
        root.children.addAll(Arrays.asList(n20, n30));
        n20.children.addAll(Arrays.asList(new Node(50), new Node(8)));
        n30.children.addAll(Arrays.asList(new Node(40), new Node(60)));

        // Reset globals
        ceil = Integer.MAX_VALUE;
        floor = Integer.MIN_VALUE;
        int target = 35;

        ceilAndFloor(root, target);

        System.out.println("Target: " + target);
        System.out.println("Floor: " + (floor == Integer.MIN_VALUE ? "None" : floor)); // Expected: 30
        System.out.println("Ceil: " + (ceil == Integer.MAX_VALUE ? "None" : ceil));    // Expected: 40
    }
}