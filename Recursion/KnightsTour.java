import java.util.*;

public class KnightsTour {

    public static void main(String[] args) {
        int n = 5; // Usually done on 5x5; 8x8 takes a very long time!
        int[][] chess = new int[n][n];
        
        // Start at row 0, col 0, as the 1st move
        printKnightsTour(chess, 0, 0, 1);
    }

    public static void printKnightsTour(int[][] chess, int r, int c, int move) {
        // BASE CASE 1: Out of bounds or cell already visited
        if (r < 0 || c < 0 || r >= chess.length || c >= chess.length || chess[r][c] > 0) {
            return;
        }
        
        // BASE CASE 2: Success! All cells visited
        if (move == chess.length * chess.length) {
            chess[r][c] = move; // Place the final move
            displayBoard(chess);
            chess[r][c] = 0; // Backtrack
            return;
        }

        chess[r][c] = move; // DO: Mark the cell with the current move number
        
        // 8 Possible Knight Moves
        printKnightsTour(chess, r - 2, c + 1, move + 1);
        printKnightsTour(chess, r - 1, c + 2, move + 1);
        printKnightsTour(chess, r + 1, c + 2, move + 1);
        printKnightsTour(chess, r + 2, c + 1, move + 1);
        printKnightsTour(chess, r + 2, c - 1, move + 1);
        printKnightsTour(chess, r + 1, c - 2, move + 1);
        printKnightsTour(chess, r - 1, c - 2, move + 1);
        printKnightsTour(chess, r - 2, c - 1, move + 1);
        
        chess[r][c] = 0; // UNDO: Backtrack
    }

    public static void displayBoard(int[][] chess) {
        for (int[] row : chess) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}