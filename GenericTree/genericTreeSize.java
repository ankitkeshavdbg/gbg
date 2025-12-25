import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
public class genericTreeSize {
    private Node root; 

    private class Node {
        int data;
        ArrayList<Node> children = new ArrayList<>();
    }

    public genericTreeSize(int[] arr) {
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

    // --- The Intuitive Size Calculation Method ---
    /**
     * Calculates the total number of nodes in the subtree rooted at 'node'.
     * @param node The root of the subtree to measure.
     * @return The total number of nodes in the subtree.
     */
    public int size(Node node) {
        // 1. Initialize the count for this subtree to zero.
        int s = 0;

        // 2. Recursively get the size of every child's subtree.
        for (Node child : node.children) {
            int childSubtreeSize = size(child);
            // Add the size of the child's entire branch to our total.
            s += childSubtreeSize;
        }

        // 3. Add 1 for the current node itself (Self-Inclusion).
        s = s + 1;

        // 4. Return the total size of the subtree.
        return s;
    }
    
    public static void main(String[] args) {
        int[] arr = {10, 20, 50, -1, 60, -1, -1, 30, 70, -1, 80, 110, -1, 120, -1, -1, 90, -1, -1, 40, 150, -1, -1, -1};

        // Create an instance of the class, which builds the tree
        genericTreeSize gt = new genericTreeSize(arr);

        System.out.println("--- Generic Tree Structure ---");
        gt.display();
        System.out.println("------------------------------");
        System.out.println(gt.size(gt.root));
    }
}