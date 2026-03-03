import java.util.*;

public class powerLinear {

    public static void main(String[] args) {
        // Variables needed to be defined inside main
        int x = 2; 
        int n = 5;
        
        int result = power(x, n);
        System.out.println(x + " raised to power " + n + " is: " + result);
    }

    /**
     * Algorithm: Linear Recursion
     * Time Complexity: O(n) - We make 'n' recursive calls.
     * Expectation: power(x, n) returns x multiplied by itself n times.
     * Faith: power(x, n-1) returns x multiplied by itself (n-1) times.
     */
    public static int power(int x, int n) {
        // BASE CASE: Any number raised to 0 is 1
        if (n == 0) {
            return 1;
        }

        // RECURSIVE CALL (Faith): Ask for the result of (n-1)
        int resn1 = power(x, n - 1);

        // SELF WORK: Multiply the result of the faith call by x
        int res = x * resn1;

        return res;
    }
}