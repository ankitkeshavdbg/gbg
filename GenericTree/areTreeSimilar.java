import java.util.*;

public class areTreeSimilar {
    public static class Node {
        int data;
        ArrayList<Node> children = new ArrayList<>();

        Node(int data) {
            this.data = data;
        }
    }

    // Main Algorithm: Check if two generic trees are similar in shape
    public static boolean areSimilar(Node n1, Node n2) {
        // 1. Check if the number of children at the current level is different
        if (n1.children.size() != n2.children.size()) {
            return false;
        }

        // 2. Iterate through children of both nodes simultaneously
        for (int i = 0; i < n1.children.size(); i++) {
            Node c1 = n1.children.get(i);
            Node c2 = n2.children.get(i);

            // 3. Recursive call to check the subtrees of corresponding children
            if (areSimilar(c1, c2) == false) {
                return false;
            }
        }

        // 4. If all checks pass, the shapes are identical
        return true;
    }

    public static void main(String[] args) {
        // Constructing Tree 1
        Node r1 = new Node(10);
        r1.children.add(new Node(20));
        r1.children.add(new Node(30));
        r1.children.get(0).children.add(new Node(40));

        // Constructing Tree 2 (Different data, same shape)
        Node r2 = new Node(100);
        r2.children.add(new Node(200));
        r2.children.add(new Node(300));
        r2.children.get(0).children.add(new Node(400));

        // Constructing Tree 3 (Different shape)
        Node r3 = new Node(10);
        r3.children.add(new Node(20));
        r3.children.get(0).children.add(new Node(30));
        r3.children.get(0).children.add(new Node(40));

        System.out.println("Are r1 and r2 similar? " + areSimilar(r1, r2)); // true
        System.out.println("Are r1 and r3 similar? " + areSimilar(r1, r3)); // false
    }
}