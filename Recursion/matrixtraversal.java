import java.util.*;

public class matrixtraversal {

    public static void main(String[] args) {
        int n = 3; // Size of the n*n matrix
        System.out.println("Starting traversal of " + n + "x" + n + " matrix:");
        
        // Start the recursion from the top-left corner (0, 0)
        traverse(0, 0, n);
    }

    public static void traverse(int row, int col, int n) {
        // 1. BASE CASE: Success!
        // When 'row' hits 'n', we have moved off the bottom of the board.
        if (row == n) {
            System.out.println("Finished!");
            return; 
        }

        // 2. SMART LOGIC: Calculate where to go next
        int nextRow = 0;
        int nextCol = 0;

        // If we are at the last column, jump to the start of the next row
        if (col == n - 1) {
            nextRow = row + 1;
            nextCol = 0;
        } else {
            // Otherwise, just move to the next column in the same row
            nextRow = row;
            nextCol = col + 1;
        }
        

        // 3. THE WORK: Print the current coordinate
        System.out.println("Visiting: (" + row + ", " + col + ")");
        
        // 4. THE FAITH: Recursive call to visit the next calculated cell
        traverse(nextRow, nextCol, n);
    }
}