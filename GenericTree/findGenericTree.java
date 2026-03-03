import java.util.*;

public class findGenericTree {
    public static class Node {
        int data;
        ArrayList<Node> children = new ArrayList<>();

        Node(int data) {
            this.data = data;
        }
    }

    public static boolean find(Node node, int x) {
        // Step 1: Check if current node is the target
        if (node.data == x) {
            return true;
        }

        // Step 2: Iterate through all children to look for the target
        for (Node child : node.children) {
            boolean foundInChild = find(child, x);
            
            // Step 3: If found in this subtree, return true immediately
            if (foundInChild) {
                return true;
            }
        }

        // Step 4: If not found in self or any subtree, return false
        return false;
    }

    public static void main(String[] args) {
        // Constructing the tree from your previous image
        Node root = new Node(10);
        
        Node n20 = new Node(20);
        Node n30 = new Node(30);
        Node n40 = new Node(40);
        root.children.addAll(Arrays.asList(n20, n30, n40));
        
        n20.children.add(new Node(50));
        n20.children.add(new Node(60));
        
        n30.children.add(new Node(70));
        Node n80 = new Node(80);
        n30.children.add(n80);
        n30.children.add(new Node(90));
        
        n80.children.add(new Node(110));
        n80.children.add(new Node(120));
        
        n40.children.add(new Node(100));

        // Test Cases
        int target1 = 110;
        int target2 = 500;

        System.out.println("Searching for " + target1 + ": " + find(root, target1)); // Should be true
        System.out.println("Searching for " + target2 + ": " + find(root, target2)); // Should be false
    }
}