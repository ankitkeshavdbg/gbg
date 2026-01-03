import java.util.*;

public class MedianPriorityQueue {
    PriorityQueue<Integer> left;  // Max-Heap for smaller half
    PriorityQueue<Integer> right; // Min-Heap for larger half

    public MedianPriorityQueue() {
        // Default is Min-Heap, so we use Collections.reverseOrder() for Max-Heap
        left = new PriorityQueue<>(Collections.reverseOrder());
        right = new PriorityQueue<>();
    }

    public void add(int val) {
        // Step 1: Decide which heap to put the value in
        if (right.size() > 0 && val > right.peek()) {
            right.add(val);
        } else {
            left.add(val);
        }

        // Step 2: Balance the heaps so size difference is max 1
        handleBalance();
    }

    public int remove() {
        if (this.size() == 0) {
            System.out.println("Underflow");
            return -1;
        }

        // Logic: Median is at the top of the larger heap. 
        // If sizes are equal, 'left' has the smaller of the two middle elements.
        if (left.size() >= right.size()) {
            return left.remove();
        } else {
            return right.remove();
        }
    }

    public int peek() {
        if (this.size() == 0) {
            System.out.println("Underflow");
            return -1;
        }

        // Same logic as remove: return from the larger heap
        if (left.size() >= right.size()) {
            return left.peek();
        } else {
            return right.peek();
        }
    }

    private void handleBalance() {
        if (left.size() - right.size() == 2) {
            right.add(left.remove());
        } else if (right.size() - left.size() == 2) {
            left.add(right.remove());
        }
    }

    public int size() {
        return left.size() + right.size();
    }

    public static void main(String[] args) {
        MedianPriorityQueue mpq = new MedianPriorityQueue();
        
        mpq.add(10);
        mpq.add(20);
        mpq.add(30);
        
        System.out.println("Median: " + mpq.peek()); // 20
        mpq.remove();
        
        mpq.add(40);
        // Elements are now 10, 30, 40. Even count.
        // Middle elements: 10, 30. Smaller is 10.
        System.out.println("Median after removing 20 and adding 40: " + mpq.peek()); 
    }
}