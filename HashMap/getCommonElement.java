import java.util.*;

public class getCommonElement {
    
    public static void main(String[] args) {
        int[] a1 = {1, 1, 2, 2, 2, 3, 5};
        int[] a2 = {1, 1, 1, 2, 2, 4, 5};

        // STEP 1: Create a frequency map of elements in array 1
        HashMap<Integer, Integer> hma1 = new HashMap<>();
        for(int val : a1) {
            if(hma1.containsKey(val)) {
                int oldFreq = hma1.get(val);
                int newFreq = oldFreq + 1;
                hma1.put(val, newFreq);
            } else {
                hma1.put(val, 1);
            }
        }

        // STEP 2: Iterate through array 2 to find common elements
        System.out.println("Common Elements (Unique):");
        for(int val : a2) {
            // If the current element of a2 exists in our map
            if(hma1.containsKey(val)) {
                // Print it
                System.out.print(val + " ");
                
                // CRITICAL STEP: Remove the key from the map.
                // This prevents duplicates in a2 from triggering another print.
                hma1.remove(val);
            }
        }
    }
}