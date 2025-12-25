import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
public class genericTreeMax {
    private Node root; 

    private class Node {
        int data;
        ArrayList<Node> children = new ArrayList<>();
    }

    public genericTreeMax(int[] arr) {
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
     * 
     * @param currentNode The root of the subtree for which max will be found
     * @return the maximum of the tree
     */

    public int findMaximumValue(Node currentNode){

        // 1. Initialization (Find Max in Children's Subtrees)
        // We initialize the maximum found so far to the smallest possible integer value.
        int maxFromChildren = Integer.MIN_VALUE;
        
        // 2. Recursive Step (Ask Children)
        // Iterate over every child of the current node.
        for(Node child : currentNode.children){
            
            // Recursive Faith: Assume the 'findMaximumValue' call correctly returns 
            // the maximum value found in the entire subtree rooted at the 'child'.
            int childMax = findMaximumValue(child);
            
            // Meeting Point: Compare the maximum returned by the child's subtree (childMax) 
            // with the overall maximum found so far in all children's subtrees (maxFromChildren).
            maxFromChildren = Math.max(childMax, maxFromChildren);
        }
        
        // 3. Self-Work and Return
        // After checking all children, compare the maximum found among the children 
        // (maxFromChildren) with the current node's own data (currentNode.data).
        // The largest of these two values is the true maximum for this entire subtree.
        return Math.max(maxFromChildren, currentNode.data);
    }

    /**
     * Calculates the total number of nodes in the subtree rooted at 'node'.
     * @param node The root of the subtree to measure.
     * @return The total number of nodes in the subtree.
     */
    public int size(Node node) {
        int s = 0;
        for (Node child : node.children) {
            int childSubtreeSize = size(child);
            s += childSubtreeSize;
        }
        s = s + 1;
        return s;
    }
    
    public static void main(String[] args) {
        int[] arr = {10, 20, 50, -1, 60, -1, -1, 30, 70, -1, 80, 110, -1, 120, -1, -1, 90, -1, -1, 40, 150, -1, -1, -1};

        // Create an instance of the class, which builds the tree
        genericTreeMax gt = new genericTreeMax(arr);

        System.out.println("--- Generic Tree Structure ---");
        gt.display();
        System.out.println("------------------------------");
        System.out.println(gt.findMaximumValue(gt.root));
    }
}