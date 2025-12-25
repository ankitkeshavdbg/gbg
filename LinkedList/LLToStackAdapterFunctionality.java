import java.util.LinkedList;

public class LLToStackAdapterFunctionality {
    
    /**
     * An adapter class that uses a LinkedList internally to implement
     * the behavior of a Stack (LIFO structure).
     */
    public static class LLToStackAdapter {
        // The underlying data structure used to implement the stack.
        private LinkedList<Integer> list;

        // Constructor: Initializes the empty LinkedList.
        public LLToStackAdapter() {
            list = new LinkedList<>();
        }

        /**
         * Returns the number of elements in the stack.
         */
        public int size() {
            return list.size();
        }

        /**
         * Pushes a value onto the stack.
         * Stack: New element goes to the TOP (LIFO).
         * LinkedList: Uses addFirst() for O(1) insertion.
         */
        public void push(int val) {
            list.addFirst(val); // O(1) operation
        }

        /**
         * Removes and returns the element at the top of the stack.
         */
        public int pop() {
            if (size() == 0) {
                System.out.println("Stack underflow");
                return -1;
            } else {
                // Stack: Removes the TOP element.
                // LinkedList: Uses removeFirst() for O(1) removal.
                return list.removeFirst(); // O(1) operation
            }
        }

        /**
         * Returns the element at the top of the stack without removing it.
         */
        public int top() {
            if (size() == 0) {
                System.out.println("Stack underflow");
                return -1;
            } else {
                // Stack: Peeks at the TOP element.
                // LinkedList: Uses getFirst() for O(1) access.
                return list.getFirst(); // O(1) operation
            }
        }
    }

    // --- Main method for demonstration ---
    public static void main(String[] args) {
        LLToStackAdapter stack = new LLToStackAdapter();

        System.out.println("Pushing 10, 20, 30 onto the stack...");
        stack.push(10); // 10 is the new TOP
        stack.push(20); // 20 is the new TOP
        stack.push(30); // 30 is the new TOP
        
        // Stack state: [30 (TOP), 20, 10]

        System.out.println("Current size: " + stack.size()); // Output: 3
        System.out.println("Top element (peek): " + stack.top()); // Output: 30

        System.out.println("Popping element: " + stack.pop()); // Output: 30
        System.out.println("Popping element: " + stack.pop()); // Output: 20
        
        // Stack state: [10 (TOP)]
        
        System.out.println("Top element after pops: " + stack.top()); // Output: 10
        System.out.println("Popping last element: " + stack.pop()); // Output: 10
        
        System.out.println("Popping empty stack:");
        stack.pop(); // Output: Stack underflow
    }
}