import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.ArrayDeque;
public class genericTreeLevelOrderLW {
    private Node root; 

    private class Node {
        int data;
        ArrayList<Node> children = new ArrayList<>();
    }

    public genericTreeLevelOrderLW(int[] arr) {
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

    public void levelOrderLW(Node node) {
        // 1. Declare two queues: mq for current level, cq for next level's children
        Queue<Node> mq = new ArrayDeque<>(); 
        Queue<Node> cq = new ArrayDeque<>();


        mq.add(node); // Add the root node to start

        // Loop continues as long as there are levels to process
        while(!mq.isEmpty()){ 
            
            // A. Dequeue the node from the current level (mq)
            Node front = mq.remove();
            
            // B. Print the data. Use print() to keep level nodes on the same line.
            System.out.print(front.data + " "); 

            // C. Enqueue all children of the processed node into the child queue (cq)
            for(Node child : front.children){
                cq.add(child);
            }

            // D. Level Complete Check & Swap
            if(mq.isEmpty()){
                // The current level (mq) is fully processed.
                
                mq = cq; // Swap: The children (cq) become the new current level (mq)
                cq = new ArrayDeque<>(); // Reset cq to collect the *next* level's children
                
                System.out.println(); // Print newline to separate the levels in the output
            }
        }
    }



    
    public static void main(String[] args) {
        int[] arr = {10, 20, 50, -1, 60, -1, -1, 30, 70, -1, 80, 110, -1, 120, -1, -1, 90, -1, -1, 40, 150, -1, -1, -1};

        // Create an instance of the class, which builds the tree
        genericTreeLevelOrderLW gt = new genericTreeLevelOrderLW(arr);

        System.out.println("--- Generic Tree Structure ---");
        System.out.println("------------------------------");
        gt.levelOrderLW(gt.root);
    }
}