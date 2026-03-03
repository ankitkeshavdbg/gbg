import java.util.*;

public class PrintZigzag {

    public static void main(String[] args) {
        int n = 2; // Try with 2 first, then 3
        pzz(n);
    }

    public static void pzz(int n) {
        // BASE CASE: The end of the branches
        if (n == 0) {
            return;
        }

        // PRE-ORDER: Print before the first call
        System.out.print(n + " "); 

        pzz(n - 1); // LEFT CALL

        // IN-ORDER: Print between the two calls
        System.out.print(n + " "); 

        pzz(n - 1); // RIGHT CALL

        // POST-ORDER: Print after both calls are done
        System.out.print(n + " "); 
    }
}