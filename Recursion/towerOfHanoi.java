import java.util.*;

public class towerOfHanoi {

    public static void main(String[] args) {
        int n = 3; // Number of disks
        // Using descriptive names for towers to build better mental mapping
        int source = 10;
        int destination = 11;
        int helper = 12;

        toh(n, source, destination, helper);
    }

    /**
     * Logic: To move N disks from SRC to DEST
     * 1. Faith: Move N-1 disks from SRC to HELPER (Dest acts as temporary storage)
     * 2. Self Work: Move the remaining Nth disk from SRC to DEST
     * 3. Faith: Move the N-1 disks from HELPER to DEST (Src acts as temporary storage)
     */
    public static void toh(int n, int src, int dest, int helper) {
        // BASE CASE: If there are no disks to move, we simply return (stop)
        // This is cleaner than n == 1 because it covers the case where the user inputs 0.
        if (n == 0) {
            return;
        }

        // STEP 1: Move (n-1) disks from 'src' to 'helper' using 'dest' as support
        // Note: The destination for this specific call is the 'helper' tower.
        toh(n - 1, src, helper, dest);

        // STEP 2: Move the nth (largest) disk from 'src' to 'dest'
        // This is the "In-order" work.
        System.out.println(n + "[" + src + " -> " + dest + "]");

        // STEP 3: Move (n-1) disks from 'helper' to 'dest' using 'src' as support
        // Note: The source for this specific call is now the 'helper' tower.
        toh(n - 1, helper, dest, src);
    }
}