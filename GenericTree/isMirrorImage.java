import java.util.*;

public class isMirrorImage {
    public static class Node {
        int data;
        ArrayList<Node> children = new ArrayList<>();

        Node(int data) {
            this.data = data;
        }
    }

    public static boolean areMirror(Node n1, Node n2) {
        // 1. Same number of children is a requirement
        if (n1.children.size() != n2.children.size()) {
            return false;
        }

        int totalChildren = n1.children.size();

        // 2. Loop through n1's children forward and n2's children backward
        for (int i = 0; i < totalChildren; i++) {
            Node c1 = n1.children.get(i);
            // Get the mirror index from the second tree
            Node c2 = n2.children.get(totalChildren - 1 - i);

            // 3. Recursively check if these corresponding subtrees are mirrors
            if (areMirror(c1, c2) == false) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        // Constructing Tree 1
        Node r1 = new Node(10);
        r1.children.add(new Node(20)); // Left child
        r1.children.add(new Node(30)); // Right child
        r1.children.get(0).children.add(new Node(40)); // Child of 20

        // Constructing Tree 2 (Mirror of Tree 1)
        Node r2 = new Node(100);
        r2.children.add(new Node(300)); // Left child (mirrors 30)
        r2.children.add(new Node(200)); // Right child (mirrors 20)
        r2.children.get(1).children.add(new Node(400)); // Child of 200

        System.out.println("Are r1 and r2 mirrors? " + areMirror(r1, r2)); // true
    }
}