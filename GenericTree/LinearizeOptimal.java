import java.util.*;

public class LinearizeOptimal {
    public static class Node {
        int data;
        ArrayList<Node> children = new ArrayList<>();

        Node(int data) {
            this.data = data;
        }
    }

    /**
     * Efficient O(N) Linearization
     * @return The tail (last node) of the linearized line starting at 'node'
     */
    public static Node linearize(Node node) {
        // Base case: If leaf node, it is its own tail
        if (node.children.size() == 0) {
            return node;
        }

        // Step 1: Recursively linearize the last child first.
        // This gives us the ultimate tail of this specific branch.
        Node overallTail = linearize(node.children.get(node.children.size() - 1));

        // Step 2: Process other children from right-to-left
        while (node.children.size() > 1) {
            // Remove the last child (which is now a line)
            Node last = node.children.remove(node.children.size() - 1);
            
            // Get the new last child (the one to the left of the one we just removed)
            Node secondLast = node.children.get(node.children.size() - 1);
            
            // Linearize the secondLast child and get its tail
            Node slTail = linearize(secondLast);
            
            // Stitch the 'last' line to the tail of the 'secondLast' line
            slTail.children.add(last);
        }

        return overallTail;
    }

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