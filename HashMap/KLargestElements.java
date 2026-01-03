import java.util.*;

public class KLargestElements {
    public static void main(String[] args) {
        int[] arr = {2, 10, 5, 17, 7, 18, 6, 4};
        int k = 3;

        // 1. Initialize a Min-Heap (Default PriorityQueue)
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        // 2. Add first K elements
        for (int i = 0; i < k; i++) {
            pq.add(arr[i]);
        }

        // 3. Process the rest of the array
        for (int i = k; i < arr.length; i++) {
            if (arr[i] > pq.peek()) {
                pq.remove(); // Remove the smallest of the "Top K"
                pq.add(arr[i]); // Add the new contender
            }
        }

        // 4. Print the result
        System.out.println("The " + k + " largest elements are:");
        while (pq.size() > 0) {
            System.out.print(pq.remove() + " ");
        }
    }
}