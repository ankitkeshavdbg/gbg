import java.util.Stack;

public class BSTTargetSumOptimized {

    // ------------------------------------------------------------------
    // 1. Node Class
    // ------------------------------------------------------------------
    public static class Node {
        int data;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }
    
    // ------------------------------------------------------------------
    // 2. Traversal Helper Class (ITPair)
    // ------------------------------------------------------------------
    /**
     * ITPair (Iterative Traversal Pair) stores a Node and its 'state'.
     * The state tracks where the traversal is relative to the node.
     * State 0: Pre-order work done, next is left child.
     * State 1: In-order work done (this node's data is yielded), next is right child.
     * State 2: Post-order work done, node is popped.
     */
    public static class ITPair {
        Node node;
        int state;

        ITPair(Node node, int state) {
            this.node = node;
            this.state = state;
        }
    }

    // ------------------------------------------------------------------
    // 3. Helper Functions for Iterative Traversal
    // ------------------------------------------------------------------

    /**
     * Finds and returns the next smallest element (Ascending Order).
     * Simulates a standard iterative Inorder Traversal (L -> Root -> R).
     * @param ls The Stack for the Left-side traversal.
     * @return The data of the next smallest node.
     */
    public static int getNextFromNormalInorder(Stack<ITPair> ls) {
        while (ls.size() > 0) {
            ITPair top = ls.peek();
            
            if (top.state == 0) {
                // State 0: Move to the left child to find the smallest element.
                top.state++;
                if (top.node.left != null) {
                    ls.push(new ITPair(top.node.left, 0));
                }
            } else if (top.state == 1) {
                // State 1: In-order work (yield data). Move to the right child next.
                top.state++;
                if (top.node.right != null) {
                    ls.push(new ITPair(top.node.right, 0));
                }
                // Return the current node's data (the next smallest).
                return top.node.data; 
            } else { // State 2
                // State 2: Post-order work. Pop and continue.
                ls.pop();
            }
        }
        return -1; // Should not be reached if the loop condition is correct
    }

    /**
     * Finds and returns the next largest element (Descending Order).
     * Simulates a Reverse Inorder Traversal (R -> Root -> L).
     * @param rs The Stack for the Right-side traversal.
     * @return The data of the next largest node.
     */
    public static int getNextFromReverseInorder(Stack<ITPair> rs) {
        while (rs.size() > 0) {
            ITPair top = rs.peek();
            
            if (top.state == 0) {
                // State 0: Move to the right child to find the largest element.
                top.state++;
                if (top.node.right != null) {
                    rs.push(new ITPair(top.node.right, 0));
                }
            } else if (top.state == 1) {
                // State 1: Reverse In-order work (yield data). Move to the left child next.
                top.state++;
                if (top.node.left != null) {
                    rs.push(new ITPair(top.node.left, 0));
                }
                // Return the current node's data (the next largest).
                return top.node.data;
            } else { // State 2
                // State 2: Post-order work. Pop and continue.
                rs.pop();
            }
        }
        return -1; // Should not be reached if the loop condition is correct
    }


    // ------------------------------------------------------------------
    // 4. Main Algorithm (Best Approach)
    // ------------------------------------------------------------------
    
    /**
     * Finds pairs that sum to 'tar' using two iterative traversals (O(N) Time, O(log N) Space).
     * @param node The root of the BST.
     * @param tar The target sum.
     */
    public static void bestApproach(Node node, int tar) {
        if (node == null) return;
        
        // Stacks to maintain the state of the two simultaneous traversals
        Stack<ITPair> ls = new Stack<>(); // Normal Inorder (Ascending)
        Stack<ITPair> rs = new Stack<>(); // Reverse Inorder (Descending)

        // Initialize both stacks with the root node.
        ls.push(new ITPair(node, 0));
        rs.push(new ITPair(node, 0));

        // Get the initial pair: the smallest (left) and the largest (right) elements.
        int left = getNextFromNormalInorder(ls);
        int right = getNextFromReverseInorder(rs);

        System.out.println("Finding pairs for target sum: " + tar);
        
        // Two-Pointer logic loop
        while (left < right) {
            int sum = left + right;

            if (sum < tar) {
                // Sum is too small, advance the left pointer (next smallest element).
                left = getNextFromNormalInorder(ls);
            } else if (sum > tar) {
                // Sum is too large, advance the right pointer (next largest element).
                right = getNextFromReverseInorder(rs);
            } else {
                // Pair found!
                System.out.println(left + " " + right);
                
                // Move both pointers inward to look for the next distinct pair.
                left = getNextFromNormalInorder(ls);
                right = getNextFromReverseInorder(rs);
            }
        }
    }
    
    // ------------------------------------------------------------------
    // 5. Helper Functions for BST Construction and Main Method
    // ------------------------------------------------------------------

    // Constructs a balanced BST from a sorted array.
    public static Node construct(Integer[] arr, int lo, int hi) {
        if (lo > hi) {
            return null;
        }
        int mid = (lo + hi) / 2;
        Node node = new Node(arr[mid]); 
        node.left = construct(arr, lo, mid - 1); 
        node.right = construct(arr, mid + 1, hi);
        return node;
    }

    // Prints the BST structure (Node <- Root -> Node).
    public static void display(Node node) {
        if (node == null) return;
        String str = (node.left == null ? "." : node.left.data) + 
                     " <- " + node.data + " -> " + 
                     (node.right == null ? "." : node.right.data);
        System.out.println(str);
        display(node.left);
        display(node.right);
    }

    public static void main(String[] args) {
        // Sorted array for a balanced BST: {10, 20, 30, 40, 50, 60, 70} -> Root: 40
        Integer[] arr = { 10, 20, 30, 40, 50, 60, 70 };
        Node root = construct(arr, 0, arr.length - 1);
        int targetSum = 80;

        System.out.println("--- Constructed BST ---");
        display(root);
        System.out.println("-----------------------");

        bestApproach(root, targetSum);
        // Expected Output:
        // 10 70
        // 20 60
        // 30 50
    }
}