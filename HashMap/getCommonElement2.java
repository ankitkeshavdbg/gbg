import java.util.*;

public class getCommonElement2 {
    
    public static void main(String[] args) {
        int[] a1 = {1, 1, 2, 2, 2, 3, 5};
        int[] a2 = {1, 1, 1, 2, 2, 4, 5};

        // STEP 1: Create a frequency map of elements in array 1
        HashMap<Integer, Integer> hm = new HashMap<>();
        for(int val : a1) {
            if(hm.containsKey(val)) {
                int oldFreq = hm.get(val);
                int newFreq = oldFreq + 1;
                hm.put(val, newFreq);
            } else {
                hm.put(val, 1);
            }
        }

        // STEP 2: Iterate through a2 and match frequencies
        for(int val : a2) {
            // Check if the value exists and has a remaining count
            if(hm.containsKey(val) && hm.get(val) > 0) {
                // Print the element
                System.out.print(val + " ");
                
                // STEP 3: Decrement the frequency in the map
                int oldFreq = hm.get(val);
                int newFreq = oldFreq - 1;
                hm.put(val, newFreq);
                
                // Note: We do NOT use .remove() here because we might need 
                // the same key again if it appeared multiple times in a1.
            }
        }
    }
}