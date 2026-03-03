public class Factorial {
    public static void main(String[] args) {
        int n = 5;
        int result = fact(n);
        System.out.println("Factorial of " + n + " is: " + result);
    }

    public static int fact(int n) {
        // BASE CASE: The point where we stop asking questions and give an answer.
        if (n == 1) {
            return 1;
        }

        // RECURSIVE CALL (The Faith): 
        // We stop our execution here and wait for the result of (n-1).
        int factn1 = fact(n - 1);

        // SELF WORK: Use the result from the recursive call to build our own answer.
        int factn = n * factn1;

        // RETURN: Hand the result back to the layer ABOVE us in the stack.
        return factn;
    }
}