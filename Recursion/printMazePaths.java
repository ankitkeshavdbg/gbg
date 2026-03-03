import java.util.*;

public class printMazePaths {

    public static void main(String[] args) {
        // From (0,0) to (2,2)
        printMaze(0, 0, 2, 2, "");
    }

    public static void printMaze(int sr, int sc, int dr, int dc, String psf) {
        // 1. SUCCESS BASE CASE: You reached the target cell!
        if (sr == dr && sc == dc) {
            System.out.println(psf);
            return;
        }

        // 2. FAILURE BASE CASE (REACTIVE): You walked out of the maze
        if (sr > dr || sc > dc) {
            return;
        }

        // CHOICE 1: Move Horizontal (Right)
        // We increment the column (sc) and add "h" to the path so far
        printMaze(sr, sc + 1, dr, dc, psf + "h");

        // CHOICE 2: Move Vertical (Down)
        // We increment the row (sr) and add "v" to the path so far
        printMaze(sr + 1, sc, dr, dc, psf + "v");
    }
}