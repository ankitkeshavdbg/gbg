import java.util.*;

public class NQueenTraversal {

    public static void recurseonmatrix(int[][] chess, String qsf, int row) {
        // BASE CASE: We have successfully placed a queen in every row
        if (row == chess.length) {
            System.out.println(qsf + ".");
            return;
        }

        // DECISION: In the current 'row', I can pick any 'col'
        for (int col = 0; col < chess.length; col++) {
            // DO: Place queen
            chess[row][col] = 1; 
            
            // FAITH: Move to the next row (row + 1)
            recurseonmatrix(chess, qsf + row + "-" + col + ", ", row + 1);
            
            // UNDO: Backtrack (clean up the board for the next column choice)
            chess[row][col] = 0; 
        }
    }

    public static void main(String[] args) {
        int n = 3; // Let's try a 3x3 board
        int[][] chess = new int[n][n]; // Memory allocation
        
        // Start at row 0
        recurseonmatrix(chess, "", 0); 
    }
}