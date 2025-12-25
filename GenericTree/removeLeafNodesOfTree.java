import java.util.*;

public class removeLeafNodesOfTree {
    // 1. Made Node static so it doesn't need an instance of Main
    public static class Node {
        int data;
        ArrayList<Node> children = new ArrayList<>();

        public Node(int data) {
            this.data = data;
        }
    }

    public static class GenericTree {
        Node root;
        public GenericTree(Node root) {
            this.root = root;
        }
    }

    public static Node construct(int[] arr) {
        Node root = null;
        Stack<Node> st = new Stack<>();

        for (int val : arr) {
            if (val == -1) {
                if (st.size() > 0) {
                    st.pop();
                }
            } else {
                Node t = new Node(val); 
                
                if (st.size() > 0) {
                    st.peek().children.add(t);
                } else {
                    root = t;
                }
                st.push(t);
            }
        }
        return root;
    }
    
    public static void preOrder(Node node){
        System.out.print(node.data+" ");
        for(Node child: node.children){
            preOrder(child);
        }

    }

    public static void removeLeafNodes(Node node){

        // remove children in preorder, so that you don't end up deleting whole tree

        for(int i =node.children.size()-1; i>=0;  i--){
            Node child = node.children.get(i);
            if(child.children.size()==0)
                node.children.remove(child);
        }
        for(Node child: node.children){
            removeLeafNodes(child);
        }
    }

    public static void main(String[] args) {
        int[] arr = {10, 20, 50, -1, 60, -1, -1, 30, 70, -1, 80, 110, -1, 120, -1, -1, 90, -1, -1, 40, 150, -1, -1, -1};

        // Call the static construct method
        Node root = construct(arr);
        // Create an instance of the GenericTree
        GenericTree gt = new GenericTree(root);

        preOrder(root);
        removeLeafNodes(root);

        System.out.println();
        preOrder(root);

    }
}