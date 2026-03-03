import java.util.*;

public class NQueens {

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = 4; // 4x4 is the smallest board with a solution
        int[][] chess = new int[n][n];
        printNQueens(chess, "", 0);
    }

    public static void printNQueens(int[][] chess, String qsf, int row) {
        if (row == chess.length) {
            System.out.println(qsf + ".");
            return;
        }

        for (int col = 0; col < chess.length; col++) {
            // THE SHIELD: Only proceed if this spot is safe
            if (isQueenSafe(chess, row, col)) {
                chess[row][col] = 1; // DO
                printNQueens(chess, qsf + row + "-" + col + ", ", row + 1); // FAITH
                chess[row][col] = 0; // UNDO (Backtrack)
            }
        }
    }

    public static boolean isQueenSafe(int[][] chess, int row, int col) {
        // 1. Check Vertical Up (Same Column)
        for (int i = row - 1, j = col; i >= 0; i--) {
            if (chess[i][j] == 1) return false;
        }

        // 2. Check Diagonal Left-Up
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (chess[i][j] == 1) return false;
        }

        // 3. Check Diagonal Right-Up
        for (int i = row - 1, j = col + 1; i >= 0 && j < chess.length; i--, j++) {
            if (chess[i][j] == 1) return false;
        }

        return true;
    }
}