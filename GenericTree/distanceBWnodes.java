import java.util.*;

public class distanceBWnodes {
    public static class Node {
        int data;
        ArrayList<Node> children = new ArrayList<>();

        Node(int data) {
            this.data = data;
        }
    }

    // Helper: Find path from node back to root
    public static ArrayList<Integer> nodeToRootPath(Node node, int x) {
        if (node.data == x) {
            ArrayList<Integer> path = new ArrayList<>();
            path.add(node.data);
            return path;
        }

        for (Node child : node.children) {
            ArrayList<Integer> childPath = nodeToRootPath(child, x);
            if (childPath.size() > 0) {
                childPath.add(node.data);
                return childPath;
            }
        }
        return new ArrayList<>();
    }

    // Main Algorithm: Distance between two nodes
    public static int distanceBetweenNodes(Node node, int d1, int d2) {
        // 1. Get paths for both data points
        ArrayList<Integer> p1 = nodeToRootPath(node, d1);
        ArrayList<Integer> p2 = nodeToRootPath(node, d2);

        // 2. Set pointers to the end (the root)
        int i = p1.size() - 1;
        int j = p2.size() - 1;

        // 3. Backtrack until they diverge
        // Use .equals() for Integer objects to avoid caching issues
        while (i >= 0 && j >= 0 && p1.get(i).equals(p2.get(j))) {
            i--;
            j--;
        }

        // 4. Distance Formula: (remaining length of path 1) + (remaining length of path 2)
        // Adding 1 because i and j are currently at the first non-matching index
        return (i + 1) + (j + 1);
    }

    public static void main(String[] args) {
        // Tree Construction (from your provided test case)
        Node root = new Node(10);
        Node n20 = new Node(20);
        Node n30 = new Node(30);
        Node n40 = new Node(40);
        root.children.addAll(Arrays.asList(n20, n30, n40));
        
        n20.children.addAll(Arrays.asList(new Node(50), new Node(60)));
        
        Node n80 = new Node(80);
        n30.children.addAll(Arrays.asList(new Node(70), n80, new Node(90)));
        n80.children.addAll(Arrays.asList(new Node(110), new Node(120)));

        // Test: Distance between 110 and 90
        int dist = distanceBetweenNodes(root, 110, 90);
        System.out.println("Distance between 110 and 90: " + dist); 
        // Logic: 110 -> 80 -> 30 -> 90 (3 edges)
    }
}