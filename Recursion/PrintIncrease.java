public class PrintIncrease {

    public static void main(String[] args) {
        int n = 5;
        printIncreasing(n);
    }

    public static void printIncreasing(int n) {
        // BASE CASE: Every recursive function needs an exit
        if (n == 0) {
            return;
        }

        // RECURSIVE CALL: We "wait" here. 
        // We don't print n yet because we want smaller numbers to print first.
        printIncreasing(n - 1);

        // POST-CALL AREA: This line only executes when the call above it FINISHES.
        // This is the "Stack Falling" phase.
        System.out.println(n);
    }
}