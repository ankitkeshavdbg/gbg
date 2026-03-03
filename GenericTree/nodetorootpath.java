import java.util.*;

public class nodetorootpath {
    public static class Node {
        int data;
        ArrayList<Node> children = new ArrayList<>();

        Node(int data) {
            this.data = data;
        }
    }

    public static ArrayList<Integer> nodeToRootPath(Node node, int x) {
        // Step 1: Base Case - If current node is the target
        if (node.data == x) {
            ArrayList<Integer> path = new ArrayList<>();
            path.add(node.data);
            return path;
        }

        // Step 2: Search in children
        for (Node child : node.children) {
            ArrayList<Integer> childPath = nodeToRootPath(child, x);
            
            // Step 3: If childPath has something, it means the target was found
            if (childPath.size() > 0) {
                childPath.add(node.data); // Add myself to the path
                return childPath; // Pass it up the chain
            }
        }

        // Step 4: Not found anywhere
        return new ArrayList<>();
    }

    public static void main(String[] args) {
        // Constructing your specific tree
        Node root = new Node(10);
        Node n20 = new Node(20);
        Node n30 = new Node(30);
        Node n40 = new Node(40);
        root.children.addAll(Arrays.asList(n20, n30, n40));
        n20.children.addAll(Arrays.asList(new Node(50), new Node(60)));
        
        Node n80 = new Node(80);
        n30.children.addAll(Arrays.asList(new Node(70), n80, new Node(90)));
        n80.children.addAll(Arrays.asList(new Node(110), new Node(120)));

        // Test: Find path for 110
        ArrayList<Integer> path = nodeToRootPath(root, 110);
        System.out.println("Path to Root for 110: " + path); 
        // Expected Output: [110, 80, 30, 10]
    }
}