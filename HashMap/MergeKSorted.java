import java.util.*;

public class MergeKSorted {

    // Using "Triplet" because we are tracking 3 distinct pieces of data
    public static class Triplet implements Comparable<Triplet> {
        int val; // The actual number
        int li;  // List Index (Which list did this come from?)
        int di;  // Data Index (What was its position in that list?)

        Triplet(int val, int li, int di) {
            this.val = val;
            this.li = li;
            this.di = di;
        }

        // This tells the PriorityQueue to sort by the 'val' field (Min-Heap)
        @Override
        public int compareTo(Triplet other) {
            return this.val - other.val;
        }
    }

    public static ArrayList<Integer> merge(ArrayList<ArrayList<Integer>> lists) {
        ArrayList<Integer> result = new ArrayList<>();
        PriorityQueue<Triplet> pq = new PriorityQueue<>();

        // 1. Initial Seeding: Add the first element (index 0) of every list
        for (int i = 0; i < lists.size(); i++) {
            // Only add if the list is not empty
            if (lists.get(i).size() > 0) {
                pq.add(new Triplet(lists.get(i).get(0), i, 0));
            }
        }

        // 2. The Tournament: Process until the heap is empty
        while (!pq.isEmpty()) {
            // Remove the smallest current value (The Winner)
            Triplet current = pq.poll();
            result.add(current.val);

            // Look for the replacement in the SAME list
            int nextDataIndex = current.di + 1;

            // If there's another element in that same list, add it to the heap
            if (nextDataIndex < lists.get(current.li).size()) {
                int nextVal = lists.get(current.li).get(nextDataIndex);
                pq.add(new Triplet(nextVal, current.li, nextDataIndex));
            }
        }

        return result;
    }

    public static void main(String[] args) {
        // Constructing the sample input
        ArrayList<ArrayList<Integer>> lists = new ArrayList<>();
        lists.add(new ArrayList<>(Arrays.asList(10, 20, 30)));
        lists.add(new ArrayList<>(Arrays.asList(5, 9, 12, 18)));
        lists.add(new ArrayList<>(Arrays.asList(11, 15)));

        ArrayList<Integer> mergedList = merge(lists);

        System.out.println("Final Merged List: " + mergedList);
    }
}