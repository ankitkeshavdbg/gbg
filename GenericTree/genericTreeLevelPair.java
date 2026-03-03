import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.ArrayDeque;
public class genericTreeLevelPair {
    private Node root; 

    private class Node {
        int data;
        ArrayList<Node> children = new ArrayList<>();
    }

    public genericTreeLevelPair(int[] arr) {
        Stack<Node> st = new Stack<>();

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == -1) {
                if (st.size() > 0) {
                    st.pop();
                }
            } else {
                Node t = new Node();
                t.data = arr[i];

                if (st.size() > 0) {
                    st.peek().children.add(t);
                } else {
                    this.root = t; 
                }
                st.push(t);
            }
        }
    }

    // A sample method to display the tree structure
    // Public Wrapper, Private Helper
    public void display() {
        if (this.root != null) {
            display(this.root);
        }
    }

    private void display(Node node) {
        String str = node.data + " -> ";
        for (Node child : node.children) {
            str += child.data + ", ";
        }
        str += "."; 

        System.out.println(str);

        for (Node child : node.children) {
            display(child);
        }
    }
    public static class Pair {
        Node node;
        int level;

        Pair(Node node, int level) {
            this.node = node;
            this.level = level;
        }
    }

    public void levelOrderPair(Node node) {
        if (node == null) return;

        Queue<Pair> q = new ArrayDeque<>();
        
        // Start with the root at Level 1
        q.add(new Pair(node, 1));
        
        int currentLevel = 1;

        while (q.size() > 0) {
            Pair p = q.remove();

            // If the level of the removed node is higher than our current tracker,
            // it means we've officially stepped onto a new level.
            if (p.level > currentLevel) {
                currentLevel = p.level;
                System.out.println();
            }

            // Print the data
            System.out.print(p.node.data + " ");

            // Add children, incrementing the level badge for each
            for (Node child : p.node.children) {
                q.add(new Pair(child, p.level + 1));
            }
        }
        System.out.println(); // Final newline for cleanliness
    }



    
    public static void main(String[] args) {
        int[] arr = {10, 20, 50, -1, 60, -1, -1, 30, 70, -1, 80, 110, -1, 120, -1, -1, 90, -1, -1, 40, 150, -1, -1, -1};

        // Create an instance of the class, which builds the tree
        genericTreeLevelPair gt = new genericTreeLevelPair(arr);

        System.out.println("--- Generic Tree Structure ---");
        gt.display();
        System.out.println("------------------------------");
        gt.levelOrderPair(gt.root);
    }
}