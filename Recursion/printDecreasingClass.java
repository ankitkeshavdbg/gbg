import java.util.*;

public class printDecreasingClass {

    public static void main(String[] args) {
        int n = 7;
        printDecreasing(n);
    }

    /**
     * Algorithm:
     * 1. Self Work: Print the current value of n.
     * 2. Recursive Call: Hand over the responsibility for (n-1) to the next call.
     * 3. Base Case: If n becomes 0, stop the execution to prevent StackOverflow.
     */
    public static void printDecreasing(int n) {
        // BASE CASE: The point where the "accordion" stops expanding
        if (n == 0) {
            return;
        }

        // SELF WORK: This happens in the "Calling Phase" (on the way UP the stack)
        System.out.println(n);

        // RECURSIVE CALL: Faith that this will handle (n-1, n-2 ... 1)
        printDecreasing(n - 1);
        
        // POST-CALL AREA: Anything written here would execute on the way BACK from the base case.
    }
}