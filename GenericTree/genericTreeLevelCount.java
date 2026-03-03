import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.ArrayDeque;
public class genericTreeLevelCount {
    private Node root; 

    private class Node {
        int data;
        ArrayList<Node> children = new ArrayList<>();
    }

    public genericTreeLevelCount(int[] arr) {
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

    public static void levelOrderCount(Node node) {
        if (node == null) return;

        // Any Queue implementation works here!
        Queue<Node> q = new ArrayDeque<>(); 
        q.add(node);

        while (q.size() > 0) {
            // Step 1: Count how many nodes are currently in the queue
            int nodesAtCurrentLevel = q.size();

            // Step 2: Only process that specific number of nodes
            for (int i = 0; i < nodesAtCurrentLevel; i++) {
                Node curr = q.remove();
                
                System.out.print(curr.data + " ");

                // Step 3: Add children (these will be counted in the NEXT level)
                for (Node child : curr.children) {
                    q.add(child);
                }
            }

            // Step 4: After the inner loop, the level is officially over
            System.out.println();
        }
    }



    
    public static void main(String[] args) {
        int[] arr = {10, 20, 50, -1, 60, -1, -1, 30, 70, -1, 80, 110, -1, 120, -1, -1, 90, -1, -1, 40, 150, -1, -1, -1};

        // Create an instance of the class, which builds the tree
        genericTreeLevelCount gt = new genericTreeLevelCount(arr);

        System.out.println("--- Generic Tree Structure ---");
        gt.display();
        System.out.println("------------------------------");
        gt.levelOrderCount(gt.root);
    }
}