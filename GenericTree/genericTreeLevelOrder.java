import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.ArrayDeque;
public class genericTreeLevelOrder {
    private Node root; 

    private class Node {
        int data;
        ArrayList<Node> children = new ArrayList<>();
    }

    public genericTreeLevelOrder(int[] arr) {
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

    public void levelOrder(Node node) {
        Queue<Node> q = new ArrayDeque<>(); 
        //or Queue<Node> q = new LinkedList<>(); 
    
        // 2. Correct Initial Push: Use add()
        q.add(node);

        while (q.size()>0) {
            
            // 4. Correct Dequeue: Use remove() 
            Node front = q.remove(); 
            
            System.out.print(front.data + " ");
            
            // 5. Iterate over the children of the 'front' node
            for (Node child : front.children) { 
                // Add the child to the queue
                q.add(child);
            }
        }
        // Print a newline or separator after traversal is complete
        System.out.println("."); 
    }



    
    public static void main(String[] args) {
        int[] arr = {10, 20, 50, -1, 60, -1, -1, 30, 70, -1, 80, 110, -1, 120, -1, -1, 90, -1, -1, 40, 150, -1, -1, -1};

        // Create an instance of the class, which builds the tree
        genericTreeLevelOrder gt = new genericTreeLevelOrder(arr);

        System.out.println("--- Generic Tree Structure ---");
        gt.display();
        System.out.println("------------------------------");
        gt.levelOrder(gt.root);
    }
}