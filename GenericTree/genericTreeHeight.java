import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
public class genericTreeHeight {
    private Node root; 

    private class Node {
        int data;
        ArrayList<Node> children = new ArrayList<>();
    }

    public genericTreeHeight(int[] arr) {
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
     * Calculates the height of the subtree rooted at 'currentNode'.
     * Height is defined as the number of edges from the current node to the deepest leaf.
     * * @param currentNode The root of the subtree to measure.
     * @return The height (in edges) of the subtree.
     */
    public int height(Node currentNode) {
        // 1. Initialization: The maximum height found among children starts at -1.
        // This value is chosen so that when 1 is added (for the current node's edge), 
        // a leaf node (with no children) correctly returns 0.
        int maxHeight = -1; 
        
        // 2. Recursive Step (Ask Children)
        for (Node child : currentNode.children) {
            
            // Recursive Faith: Get the height of the child's entire subtree.
            int childHeight = height(child);
            
            // Meeting Point: Find the maximum height among all child subtrees.
            maxHeight = Math.max(childHeight, maxHeight);
        }
        
        // 3. Self-Work and Return
        // The height of the current node is the 'max height of children' PLUS 1 
        // (for the edge connecting the current node to its deepest child).
        int treeHeight = maxHeight + 1;
        
        return treeHeight;
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
        genericTreeHeight gt = new genericTreeHeight(arr);

        System.out.println("--- Generic Tree Structure ---");
        gt.display();
        System.out.println("------------------------------");
        System.out.println(gt.height(gt.root));
    }
}