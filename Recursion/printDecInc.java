public class printDecInc {

    public static void main(String[] args) {
        int n = 3;
        pdi(n);
    }

    /**
     * Logic:
     * n = 3 -> Output: 3, 2, 1, 1, 2, 3
     */
    public static void pdi(int n) {
        // BASE CASE: Stop at 0
        if (n == 0) {
            return;
        }

        // PRE-ORDER: Executes as we go DEEPER into the stack
        System.out.println(n);

        // RECURSIVE CALL: The "Wait" point
        pdi(n - 1);

        // POST-ORDER: Executes as we COLLAPSE back to main
        System.out.println(n);
    }
}