import java.util.*;

public class LongestConsecutiveSequence {
    
    public static void main(String[] args) {
        int[] arr = {10, 5, 9, 1, 11, 8, 6, 15, 3, 12, 2};

        HashMap<Integer, Boolean> hm = new HashMap<>();

        // Step 1: Assume every element is a starting point
        for (int val : arr) {
            hm.put(val, true);
        }

        // Step 2: If (val - 1) exists, then 'val' cannot be a starting point
        for (int val : arr) {
            if (hm.containsKey(val - 1)) {
                hm.put(val, false);
            }
        }

        int maxLen = 0;
        int maxStartPoint = 0;

        // Step 3: Check sequences only for valid starting points
        for (int val : arr) {
            if (hm.get(val) == true) {
                int tempLen = 1;
                int tempStartPoint = val;

                // While the next consecutive number exists
                while (hm.containsKey(tempStartPoint + tempLen)) {
                    tempLen++;
                }

                if (tempLen > maxLen) {
                    maxLen = tempLen;
                    maxStartPoint = tempStartPoint;
                }
            }
        }

        System.out.println("Longest Sequence Length: " + maxLen);
        System.out.println("Starts at: " + maxStartPoint);
    }
}