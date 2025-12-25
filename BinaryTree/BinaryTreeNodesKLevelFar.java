import java.util.*;

public class BinaryTreeNodesKLevelFar {
    public static class Node {
        int data;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
        Node(int data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

    public static class Pair {
        Node node;
        int state; 
        Pair(Node node, int state) {
            this.node = node;
            this.state = state;
        }
    }

    public static void display(Node node) {
        if (node == null) return;
        String str = (node.left == null) ? ". " : node.left.data + "";
        str += " <- " + node.data + " -> ";
        str += (node.right == null) ? " ." : node.right.data + "";
        System.out.println(str);
        display(node.left);
        display(node.right);
    }
    
    // ⭐ Helper: Print K Levels Down (with a blocker)
    public static void printKLevelDown(Node node, int k, Node blocker){
        // Base Case 1: Stop if node is null, k is negative, or we hit the blocker node.
        if(node == null || k < 0 || node == blocker){
            return;
        }

        // Base Case 2: Target reached. Print and stop.
        if(k == 0){
            System.out.println(node.data);
            return;
        }

        // Recursive Steps: Move down and decrement k.
        printKLevelDown(node.left, k - 1, blocker);
        printKLevelDown(node.right, k - 1, blocker);
    }

    // ⭐ Main Function: Prints all nodes K distance away from the Target
    public static void printKDistanceNodes(Node root, Node target, int k){
        // 1. Find the path from root to target and store it in the static Path list.
        if (!nodeToRootPath(root, target.data)) {
            System.out.println("Target node not found.");
            return;
        }
        
        System.out.println("\n🎯 Nodes at distance K=" + k + " from Target=" + target.data + ":");

        // 2. Iterate through the path (from target up to root).
        // The Path array goes: [Target, Parent, Grandparent, ..., Root]
        for(int i = 0; i < Path.size() ; i++){
            Node current = Path.get(i);
            
            // i is the distance from the target to the current ancestor.
            int distance_needed = k - i; 
            
            // The blocker is the child node we just came from (the path element before 'current').
            // This prevents searching back down the path we came up.
            // For the target (i=0), the blocker is null.
            Node blocker = (i == 0) ? null : Path.get(i-1);

            // 3. Search downward from the current ancestor with the remaining distance.
            printKLevelDown(current, distance_needed, blocker);
        }
    }

    // ⭐ Helper: Find and store the path from node to root.
    // NOTE: Path contains Node references, not int data.
    static ArrayList<Node> Path; 
    public static boolean nodeToRootPath(Node node, int val){
        if(node == null){
            return false;
        }

        if(node.data == val){
            Path.add(node);
            return true;
        }

        // Check Left Subtree
        boolean filt = nodeToRootPath(node.left, val);
        if(filt){
            Path.add(node); // Add current node if path found on left
            return true;
        }

        // Check Right Subtree
        boolean firt = nodeToRootPath(node.right, val);
        if(firt){
            Path.add(node); // Add current node if path found on right
            return true;
        }
        
        return false;
    }

    public static void main(String[] args) {

        Integer[] arr = {
                50, 25, 12, null, null, 37, 30, null, null, null, 75, 62, null, 70, null, null, 87, null, null
        };

        // --- Iterative Tree Construction ---
        Stack<Pair> st = new Stack<>();
        Node root = new Node(arr[0]);
        st.push(new Pair(root, 1));
        int idx = 0;
        Node targetNode = null; // Will store the reference to the target node
        
        while (st.size() > 0) {
            Pair top = st.peek();
            // ... (Construction logic remains the same) ...
            if (top.state == 1) { 
                idx++;
                if (arr[idx] != null) {
                    Node ln = new Node(arr[idx]);
                    top.node.left = ln;
                    st.push(new Pair(ln, 1));
                    if (arr[idx] == 37) targetNode = ln; // Example: Set 37 as the target
                }
                top.state++;
            } else if (top.state == 2) { 
                idx++;
                if (arr[idx] != null) {
                    Node rn = new Node(arr[idx]);
                    top.node.right = rn;
                    st.push(new Pair(rn, 1));
                    if (arr[idx] == 37) targetNode = rn; // Example: Set 37 as the target
                }
                top.state++;
            } else { 
                st.pop();
            }
        }
        if (targetNode == null) targetNode = root.left.right; // Ensure target is 37
        // --- End of Construction ---
        
        System.out.println("--- Tree Structure Display (Preorder Format) ---");
        display(root);
        System.out.println("-------------------------------------------------");

        // 🛠️ EXECUTION
        int K = 3;
        Path = new ArrayList<>(); // Initialize the static list before use!
        
        printKDistanceNodes(root, targetNode, K);
        
        System.out.println("-------------------------------------------------");
    }
}