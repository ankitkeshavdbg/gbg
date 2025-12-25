import java.util.LinkedList;

public class LLToQueueAdapterFunctionality {
    
    /**
     * An adapter class that uses a LinkedList internally to implement
     * the behavior of a Queue (FIFO structure).
     */
    public static class LLToQueueAdapter {
        // The underlying data structure used to implement the queue.
        private LinkedList<Integer> list;

        // Constructor: Initializes the empty LinkedList.
        public LLToQueueAdapter() {
            list = new LinkedList<>();
        }

        /**
         * Returns the number of elements in the queue.
         */
        public int size() {
            return list.size();
        }

        /**
         * Adds an element to the back (tail) of the queue. (Enqueue operation)
         * Queue: New element goes to the back.
         * LinkedList: Uses addLast() for O(1) insertion at the tail.
         */
        public void add(int val) {
            list.addLast(val); // O(1) operation
        }

        /**
         * Removes and returns the element from the front (head) of the queue. (Dequeue operation)
         */
        public int remove() {
            if (size() == 0) {
                System.out.println("Queue underflow");
                return -1;
            } else {
                // Queue: Removes the FRONT element (the oldest one).
                // LinkedList: Uses removeFirst() for O(1) removal from the head.
                return list.removeFirst(); // O(1) operation
            }
        }

        /**
         * Returns the element at the front (head) of the queue without removing it. (Peek operation)
         */
        public int peek() {
            if (size() == 0) {
                System.out.println("Queue underflow");
                return -1;
            } else {
                // Queue: Peeks at the FRONT element.
                // LinkedList: Uses getFirst() for O(1) access.
                return list.getFirst(); // O(1) operation
            }
        }
    }

    // --- Main method for demonstration ---
    public static void main(String[] args) {
        LLToQueueAdapter queue = new LLToQueueAdapter();

        System.out.println("Adding 10, 20, 30 to the queue...");
        queue.add(10); // 10 is FIRST IN
        queue.add(20);
        queue.add(30); // 30 is LAST IN
        
        // Queue state (Front to Back): [10, 20, 30]

        System.out.println("Current size: " + queue.size()); // Output: 3
        System.out.println("Front element (peek): " + queue.peek()); // Output: 10

        System.out.println("Removing element (FIFO): " + queue.remove()); // Output: 10
        System.out.println("Removing element (FIFO): " + queue.remove()); // Output: 20
        
        // Queue state: [30]
        
        System.out.println("Front element after removals: " + queue.peek()); // Output: 30
        System.out.println("Removing last element: " + queue.remove()); // Output: 30
        
        System.out.println("Removing from empty queue:");
        queue.remove(); // Output: Queue underflow
    }
}