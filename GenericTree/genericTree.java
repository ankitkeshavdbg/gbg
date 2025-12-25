import java.util.ArrayList;
import java.util.List;
import java.util.Stack; // Must import Stack

public class genericTree {

    // 1. The root of the tree should be a field of the class
    private Node root; 

    private class Node {
        int data;
        ArrayList<Node> children = new ArrayList<>();
    }

    // 2. The constructor will build the tree
    public genericTree(int[] arr) {
        Stack<Node> st = new Stack<>();

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == -1) {
                // If -1 is encountered, it means we are done with the current
                // node's children, so pop it from the stack.
                if (st.size() > 0) {
                    st.pop();
                }
            } else {
                // Create a new node for the current data element
                Node t = new Node();
                t.data = arr[i];

                if (st.size() > 0) {
                    // If the stack is NOT empty, the node at the top is the parent.
                    // Add the new node 't' to its parent's children list.
                    st.peek().children.add(t);
                } else {
                    // If the stack IS empty, this is the first element (the Root).
                    this.root = t; // Assign the root of the tree
                }
                
                // Push the new node onto the stack to act as the parent for its children
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
        // Construct the string for the current node's data and children's data
        String str = node.data + " -> ";
        for (Node child : node.children) {
            str += child.data + ", ";
        }
        str += "."; // End the list of children

        System.out.println(str);

        // Recursive call for all children
        for (Node child : node.children) {
            display(child);
        }
    }
    
    public static void main(String[] args) {
        int[] arr = {10, 20, 50, -1, 60, -1, -1, 30, 70, -1, 80, 110, -1, 120, -1, -1, 90, -1, -1, 40, 150, -1, -1, -1};

        // Create an instance of the class, which builds the tree
        genericTree gt = new genericTree(arr);

        System.out.println("--- Generic Tree Structure ---");
        gt.display();
        System.out.println("------------------------------");
    }
}