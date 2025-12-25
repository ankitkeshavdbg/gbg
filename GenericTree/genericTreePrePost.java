import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
public class genericTreePrePost{
    private Node root; 

    private class Node {
        int data;
        ArrayList<Node> children = new ArrayList<>();
    }

    public genericTreePrePost(int[] arr) {
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

    public int height(Node currentNode) {
        int maxHeight = -1; 
        for (Node child : currentNode.children) {
            int childHeight = height(child);
            maxHeight = Math.max(childHeight, maxHeight);
        }
        int treeHeight = maxHeight + 1;
        return treeHeight;
    }

    public void preOrderPostOrder(Node node){
        System.out.println("Pre"+node.data);
        for(Node child: node.children){
            preOrderPostOrder(child);
        }
        System.out.println("Post"+node.data);
    }
    
    public static void main(String[] args) {
        int[] arr = {10, 20, 50, -1, 60, -1, -1, 30, 70, -1, 80, 110, -1, 120, -1, -1, 90, -1, -1, 40, 150, -1, -1, -1};

        // Create an instance of the class, which builds the tree
        genericTreePrePost gt = new genericTreePrePost(arr);

        System.out.println("--- Generic Tree Structure ---");
        System.out.println("------------------------------");
        gt.preOrderPostOrder(gt.root);
    }
}