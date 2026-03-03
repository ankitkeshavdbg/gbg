import java.util.*;

public class PowerLogarithmic {

    public static void main(String[] args) {
        int x = 2;
        int n = 10;
        int result = power(x, n);
        System.out.println(result);
    }

    public static int power(int x, int n) {
        // BASE CASE: Still the same exit point
        if (n == 0) {
            return 1;
        }

        // RECURSIVE CALL (The Giant Leap):
        // We calculate x^(n/2) only ONCE and store it.
        int xpnb2 = power(x, n / 2);

        // SELF WORK: Square the result
        int xn = xpnb2 * xpnb2;

        // ADJUSTMENT: If n was odd, multiply by x one more time
        if (n % 2 == 1) {
            xn = xn * x;
        }

        return xn;
    }
}