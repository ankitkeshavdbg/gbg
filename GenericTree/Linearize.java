import java.util.*;

public class Linearize {
    public static class Node {
        int data;
        ArrayList<Node> children = new ArrayList<>();

        Node(int data) {
            this.data = data;
        }
    }

    // O(N^2) Approach using getTail
    public static void linearize(Node node) {
        // Step 1: Recursively linearize all children first (Post-order)
        for (Node child : node.children) {
            linearize(child);
        }

        // Step 2: Stitch children together from right to left
        while (node.children.size() > 1) {
            // Remove the last child (lc)
            Node lc = node.children.remove(node.children.size() - 1);
            // Get the child that is now at the end (second last)
            Node sl = node.children.get(node.children.size() - 1);
            
            // Find the tail of the second-last child's chain
            Node slTail = getTail(sl);
            
            // Stitch the removed child to that tail
            slTail.children.add(lc);
        }
    }

    private static Node getTail(Node node) {
        while (node.children.size() == 1) {
            node = node.children.get(0);
        }
        return node;
    }

    // Helper to display the tree (will show as a single line after linearization)
    public static void display(Node node) {
        String str = node.data + " -> ";
        for (Node child : node.children) {
            str += child.data + ", ";
        }
        System.out.println(str + ".");

        for (Node child : node.children) {
            display(child);
        }
    }

    public static void main(String[] args) {
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

        System.out.println("--- Original Tree Structure ---");
        display(root);

        linearize(root);

        System.out.println("\n--- After Linearization ---");
        display(root);
    }
}