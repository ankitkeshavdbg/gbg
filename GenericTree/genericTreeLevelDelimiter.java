import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.ArrayDeque;
public class genericTreeLevelDelimiter {
    private Node root; 

    private class Node {
        int data;
        ArrayList<Node> children = new ArrayList<>();
    }

    public genericTreeLevelDelimiter(int[] arr) {
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

    public void levelOrderdelimiter(Node node) {
        // LinkedList allows nulls; ArrayDeque does not!
        Queue<Node> q = new LinkedList<>(); 
        
        q.add(node);
        q.add(null); // The "Bookmark" for the end of level 0

        while(q.size() > 0){
            Node front = q.remove();
            
            if(front != null){
                // 1. Process the current node
                System.out.print(front.data + " ");
                
                // 2. Add all children to the queue for the next level
                for(Node child : front.children){
                    q.add(child);
                }
            } else {
                // 3. We hit a bookmark! Level is finished.
                System.out.println();

                // 4. If there are nodes in the queue, they belong to the NEXT level.
                // Add a new bookmark for them.
                if(q.size() > 0){
                    q.add(null);
                }
            }
        }
    }



    
    public static void main(String[] args) {
        int[] arr = {10, 20, 50, -1, 60, -1, -1, 30, 70, -1, 80, 110, -1, 120, -1, -1, 90, -1, -1, 40, 150, -1, -1, -1};

        // Create an instance of the class, which builds the tree
        genericTreeLevelDelimiter gt = new genericTreeLevelDelimiter(arr);

        System.out.println("--- Generic Tree Structure ---");
        gt.display();
        System.out.println("------------------------------");
        gt.levelOrderdelimiter(gt.root);
    }
}