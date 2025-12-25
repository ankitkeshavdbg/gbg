import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.ArrayDeque;
public class genericTreelevelOrderZigZag {
    private Node root; 

    private class Node {
        int data;
        ArrayList<Node> children = new ArrayList<>();
    }

    public genericTreelevelOrderZigZag(int[] arr) {
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

/**
     * Performs a Zig-Zag (Spiral) Level Order Traversal on the N-ary Tree.
     * @param node The root node of the tree.
     */
    public void levelOrderZigZag(Node node) {
        // Main stack (ms) holds nodes to be processed in the current level.
        Stack<Node> ms = new Stack<>();
        // Child stack (cs) collects nodes for the next level in the reverse order.
        Stack<Node> cs = new Stack<>();

        ms.push(node);
        int level = 1;

        while (ms.size() > 0) {
            node = ms.pop();
            System.out.print(node.data + " ");

            // Odd levels (1, 3, 5, ...): Process L->R, so children are added R->L for the next level.
            if (level % 2 == 1) {
                // Add children from Left (0) to Right (size-1) onto cs.
                for (int i = 0; i < node.children.size(); i++) {
                    Node child = node.children.get(i);
                    cs.push(child);
                }
            } 
            // Even levels (2, 4, 6, ...): Process R->L, so children are added L->R for the next level.
            else {
                // Add children from Right (size-1) to Left (0) onto cs.
                for (int i = node.children.size() - 1; i >= 0; i--) {
                    Node child = node.children.get(i);
                    cs.push(child);
                }
            }

            // Check if the current level is complete.
            if (ms.size() == 0) {
                // Swap roles: cs becomes the new ms.
                ms = cs;
                // Create a new empty stack for the next cs.
                cs = new Stack<>();
                level++;
                System.out.println(); // Print newline to separate levels.
            }
        }
        
    }



    
    public static void main(String[] args) {
        int[] arr = {10, 20, 50, -1, 60, -1, -1, 30, 70, -1, 80, 110, -1, 120, -1, -1, 90, -1, -1, 40, 150, -1, -1, -1};

        // Create an instance of the class, which builds the tree
        genericTreelevelOrderZigZag gt = new genericTreelevelOrderZigZag(arr);

        System.out.println("--- Generic Tree Structure ---");
        System.out.println("------------------------------");
        gt.levelOrderZigZag(gt.root);
    }
}