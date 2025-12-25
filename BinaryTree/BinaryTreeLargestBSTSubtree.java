import java.util.*;

public class BinaryTreeLargestBSTSubtree {

    // --- 1. Node Class ---
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

    // --- 2. Pair Class for Iterative Tree Construction ---
    public static class Pair {
        Node node;
        int state; 

        Pair(Node node, int state) {
            this.node = node;
            this.state = state;
        }
    }

    // --- 3. Result Class for Post-Order BST Check (BSTPair) ---
    /**
     * Stores the necessary information returned from a subtree:
     * 1. isBST: Is the current subtree a BST?
     * 2. min: The absolute minimum value in the current subtree.
     * 3. max: The absolute maximum value in the current subtree.
     * 4. root: The root of the largest BST found *so far* in this subtree.
     * 5. size: The size (number of nodes) of the largest BST found *so far*.
     */
    public static class BSTPair {
        boolean isBST;
        int min;
        int max;
        Node root; 
        int size; 
    }

    // --- 4. Display Function (Preorder Visualization) ---
    public static void display(Node node) {
        if (node == null) {
            return;
        }

        String str = "";
        str += (node.left == null) ? ". " : node.left.data + "";
        str += " <- " + node.data + " -> ";
        str += (node.right == null) ? " ." : node.right.data + "";

        System.out.println(str);

        display(node.left);
        display(node.right);
    }

    // --- 5. The Largest BST Subtree Logic (O(N) Time Complexity) ---
    public static BSTPair largestBSTSubtree(Node node) {
        // Base Case: A null node returns a pair that won't violate the parent's check.
        if (node == null) {
            BSTPair bp = new BSTPair();
            bp.isBST = true;
            
            // Min must be Integer.MAX_VALUE and Max must be Integer.MIN_VALUE 
            // so they don't affect the Math.min/Math.max comparisons of the parent node.
            bp.min = Integer.MAX_VALUE; 
            bp.max = Integer.MIN_VALUE;
            bp.root = null;
            bp.size = 0;
            
            return bp;
        }

        // 1. Post-Order Traversal: Get results from children first
        BSTPair lp = largestBSTSubtree(node.left);
        BSTPair rp = largestBSTSubtree(node.right);

        // 2. Process Current Node
        BSTPair mp = new BSTPair();
        
        // A. Check the BST property for the current node's subtree:
        // Current node's subtree is a BST only if:
        // 1. Both subtrees are BSTs (lp.isBST && rp.isBST)
        // 2. Current node's data is greater than the max in the left subtree (node.data > lp.max)
        // 3. Current node's data is less than the min in the right subtree (node.data < rp.min)
        // NOTE: The user's code uses >= and <= which allows for duplicates. We'll stick to 
        // the strict definition for standard BST, or use the user's logic for non-strict BST.
        // Sticking to user's non-strict logic:
        mp.isBST = lp.isBST && rp.isBST && 
                    (node.data >= lp.max && node.data <= rp.min);

        // B. Update min/max for the current subtree (mp)
        // The overall min/max is the smallest/largest among the node's data and its children's min/max.
        mp.min = Math.min(node.data, Math.min(lp.min, rp.min));
        mp.max = Math.max(node.data, Math.max(lp.max, rp.max));

        // C. Determine the Largest BST Subtree:
        if (mp.isBST) {
            // The current subtree (rooted at 'node') is a BST. 
            // It is the largest BST found so far in this region.
            mp.root = node;
            mp.size = lp.size + rp.size + 1;
        } else if (lp.size > rp.size) {
            // Current subtree is NOT a BST, so the largest BST must be either in 
            // the left or right child. Choose the one with the larger size.
            mp.root = lp.root;
            mp.size = lp.size;
        } else {
            mp.root = rp.root;
            mp.size = rp.size;
        }

        return mp;
    }

    // --- Helper function to build the tree from the array (Iterative Construction) ---
    // (Retained from user's input)
    public static Node buildTree(Integer[] arr) {
        if (arr == null || arr.length == 0 || arr[0] == null) return null;

        Stack<Pair> st = new Stack<>();
        Node root = new Node(arr[0]);
        Pair rp = new Pair(root, 1);
        st.push(rp);

        int idx = 0;
        while (st.size() > 0) {
            Pair top = st.peek();
            
            if (top.state == 1) { // State 1: Left Child
                idx++;
                if (idx < arr.length && arr[idx] != null) {
                    Node ln = new Node(arr[idx]);
                    top.node.left = ln;
                    st.push(new Pair(ln, 1)); 
                } else {
                    top.node.left = null;
                }
                top.state++; 
            } 
            
            else if (top.state == 2) { // State 2: Right Child
                idx++;
                if (idx < arr.length && arr[idx] != null) {
                    Node rn = new Node(arr[idx]);
                    top.node.right = rn;
                    st.push(new Pair(rn, 1));
                } else {
                    top.node.right = null;
                }
                top.state++; 
            } 
            
            else { // State 3: Pop
                st.pop();
            }
        }
        return root;
    }


    // --- 6. Main Method for Testing ---
    public static void main(String[] args) {
        // Example Tree: Largest BST is the subtree rooted at 50 (size 3)
        // Tree structure:
        //         10
        //        /  \
        //       20   30
        //      / \   / \
        //     40 50 60  70
        //    / \
        //   80  90 (The subtree rooted at 50 is a valid BST: 50 -> 60, 50 -> 70 is wrong)
        
        // Let's use a clear example where the largest BST is not the entire tree.
        // Example: 
        //            60 (Not BST: 50 is on the right)
        //           /  \
        //          70  50 (Root of largest BST)
        //         / \  / \
        //        10 40 45 55 (Size 3: 50, 45, 55 is a valid BST)
        
        Integer[] arr = {60, 70, 50, 10, 40, 45, 55, null, null, null, null, null, null, null, null};
        Node root = buildTree(arr);

        System.out.println("Input Binary Tree:");
        display(root);
        System.out.println("-".repeat(30));

        // Call the largestBSTSubtree function
        BSTPair result = largestBSTSubtree(root);

        System.out.println("✨ Largest BST Subtree Result:");
        System.out.println("Size: **" + result.size + "** nodes");
        
        if (result.root != null) {
            System.out.println("Root Node Value: **" + result.root.data + "**");
            System.out.println("\nVisualization of the Largest BST:");
            // Since we only have the root, we display the subtree rooted at the result root.
            display(result.root);
        } else {
            System.out.println("Root Node Value: **None** (Tree is empty)");
        }
    }
}