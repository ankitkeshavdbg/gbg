import java.util.*;

public class printStairPaths {

    public static void main(String[] args) {
        // Find all ways to climb 3 stairs
        printPaths(3, "");
    }

    public static void printPaths(int n, String path) {
        // SUCCESS BASE CASE: We landed exactly on step 0
        if (n == 0) {
            // We print the path we built during the descent
            System.out.println(path);
            return;
        }

        // FAILURE BASE CASE: We jumped past step 0 (negative step)
        // In "Print" style, we just return to kill this branch
        if (n < 0) {
            return;
        }

        // CHOICE 1: Take a 1-step jump
        // We subtract 1 from stairs left, and add "1" to our path so far
        printPaths(n - 1, path + "1");

        // CHOICE 2: Take a 2-step jump
        printPaths(n - 2, path + "2");

        // CHOICE 3: Take a 3-step jump
        printPaths(n - 3, path + "3");
    }
}