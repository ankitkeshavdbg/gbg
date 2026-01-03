import java.util.*;

public class HeapIntro {
    public static void main(String[] args) {
        // 1. DEFAULT: Min-Heap (Smallest comes out first)
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        // 2. MAX-HEAP: (Largest comes out first)
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        int[] ranks = {22, 1, 45, 12, 8, 99};

        for (int val : ranks) {
            minHeap.add(val); // nlogn
            maxHeap.add(val);
        }

        System.out.println("Min-Heap Peek: " + minHeap.peek()); // Output: 1
        System.out.println("Max-Heap Peek: " + maxHeap.peek()); // Output: 99

        // Removing elements
        System.out.print("Min-Heap elements in order: ");
        while (minHeap.size() > 0) {
            System.out.print(minHeap.remove() + " "); // Prints in ascending order
        }
    }
}