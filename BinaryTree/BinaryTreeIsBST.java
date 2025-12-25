import java.util.*;

public class BinaryTreeIsBST {

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

    // --- 3. Result Class for Post-Order BST Check  ---
    public static class BSTPair {
        boolean isBST;
        int min;
        int max;
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

    // --- 5. The isBST Logic  ---
    public static BSTPair isBST(Node node) {
        // Base Case: A null node returns a pair that won't violate the parent's check.
        if (node == null) {
            BSTPair bp = new BSTPair();
            bp.isBST = true;
            
            // Min must be very large so it doesn't affect the Math.min of the parent.
            bp.min = Integer.MAX_VALUE; 
            
            // Max must be very small so it doesn't affect the Math.max of the parent.
            bp.max = Integer.MIN_VALUE;
            
            return bp;
        }

        // 1. Post-Order Traversal: Get results from children first
        BSTPair lp = isBST(node.left);
        BSTPair rp = isBST(node.right);

        // 2. Process Current Node
        BSTPair mp = new BSTPair();
        
        // A. Check the BST property:
        // - Both subtrees must be BSTs (lp.isBST && rp.isBST).
        // - Current node must be >= max of left subtree (lp.max).
        // - Current node must be <= min of right subtree (rp.min).
        // Note: Using >= and <= allows for duplicate handling where they're consistent.
        mp.isBST = lp.isBST && rp.isBST && 
                   (node.data >= lp.max && node.data <= rp.min);

        // B. Update min/max for the current subtree (mp)
        // Find the absolute minimum and maximum value in this entire subtree.
        mp.min = Math.min(node.data, Math.min(lp.min, rp.min));
        mp.max = Math.max(node.data, Math.max(lp.max, rp.max));

        return mp;
    }

    // --- Helper function to build the tree from the array (Iterative Construction) ---
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

        // --- TEST 1: A VALID BST ---
        // {50} -> L:{25}, R:{75}
        Integer[] validBSTArr = {
            50, 
            25, 12, null, null, 37, 30, null, null, 40, null, null,
            75, 62, 60, null, null, 70, null, null, 87, null, null
        };

        // --- TEST 2: AN INVALID BST ---
        // 30 is placed in the right subtree of 50, but it is less than 50.
        // Node 30 should not be reachable via the right pointer of 50.
        Integer[] invalidBSTArr = {
            50, 
            25, 12, null, null, 37, 30, null, null, 40, null, null,
            75, 62, 30, null, null, 70, null, null, 87, null, null // 30 (L of 62) is < 50. Fails global check.
        };


        // Test 1: Valid BST
        System.out.println("--- Test 1: Valid BST ---");
        Node root1 = buildTree(validBSTArr);
        display(root1);
        BSTPair result1 = isBST(root1);
        System.out.println("Result: Is Tree a BST? " + result1.isBST);
        System.out.println("-----------------------------------------\n");


        // Test 2: Invalid BST
        System.out.println("--- Test 2: Invalid BST ---");
        Node root2 = buildTree(invalidBSTArr);
        display(root2);
        BSTPair result2 = isBST(root2);
        System.out.println("Result: Is Tree a BST? " + result2.isBST); // Expected: false
        System.out.println("-----------------------------------------");
    }
}