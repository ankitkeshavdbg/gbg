import java.util.*;

public class MyPriorityQueue {
    ArrayList<Integer> data;

    public MyPriorityQueue() {
        data = new ArrayList<>();
    }

    // 1. ADD: Add to end, then move UP to restore order
    public void add(int val) {
        data.add(val); // Add at the last position
        upheapify(data.size() - 1);
    }

    private void upheapify(int i) {
        if (i == 0) return;

        int pi = (i - 1) / 2; // Parent Index
        if (data.get(i) < data.get(pi)) { // If child is smaller than parent (Min-Heap)
            swap(i, pi);
            upheapify(pi);
        }
    }

    private void swap(int i, int j) {
        int ith = data.get(i);
        int jth = data.get(j);
        data.set(i, jth);
        data.set(j, ith);
    }

    // 2. REMOVE: Swap root with last, remove last, then move new root DOWN
    public int remove() {
        if (this.size() == 0) {
            System.out.println("Underflow");
            return -1;
        }

        swap(0, data.size() - 1); // Swap root with the last element
        int val = data.remove(data.size() - 1); // Remove the minimum
        
        downheapify(0);
        return val;
    }

    private void downheapify(int pi) {
        int mini = pi; // Assume parent is smallest

        int li = 2 * pi + 1; // Left child index
        if (li < data.size() && data.get(li) < data.get(mini)) {
            mini = li;
        }

        int ri = 2 * pi + 2; // Right child index
        if (ri < data.size() && data.get(ri) < data.get(mini)) {
            mini = ri;
        }

        if (mini != pi) {
            swap(pi, mini);
            downheapify(mini);
        }
    }

    public int peek() {
        if (this.size() == 0) {
            System.out.println("Underflow");
            return -1;
        }
        return data.get(0); // Root is always the minimum
    }

    public int size() {
        return data.size();
    }

    public static void main(String[] args) {
        MyPriorityQueue pq = new MyPriorityQueue();

        // 1. Test Adding elements in random order
        System.out.println("Adding: 20, 10, 30, 5, 15");
        pq.add(20);
        pq.add(10);
        pq.add(30);
        pq.add(5);
        pq.add(15);

        // 2. Test Peek (Should show the smallest: 5)
        System.out.println("Peek (Smallest): " + pq.peek()); // Expected: 5
        System.out.println("Current Size: " + pq.size());    // Expected: 5

        System.out.println("--- Removing elements one by one ---");

        // 3. Test Remove (Should remove in sorted order: 5, 10, 15, 20, 30)
        while (pq.size() > 0) {
            System.out.println("Removed: " + pq.remove() + " | New Peek: " + (pq.size() > 0 ? pq.peek() : "None"));
        }

        // 4. Test Underflow
        System.out.println("--- Testing Underflow ---");
        pq.remove(); // Expected: "Underflow" message
    }

}