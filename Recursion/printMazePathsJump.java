import java.util.*;

public class printMazePathsJump {

    public static void main(String[] args) {
        // From (0,0) to (2,2)
        printMazePathsJumps(0, 0, 2, 2, "");
    }

    public static void printMazePathsJumps(int sr, int sc, int dr, int dc, String psf) {
        // 1. SUCCESS BASE CASE
        if (sr == dr && sc == dc) {
            System.out.println(psf);
            return;
        }

        // 2. FAILURE BASE CASE (Reactive)
        // If we jump out of the grid, we just stop this branch
        if (sr > dr || sc > dc) {
            return;
        }

        // 3. HORIZONTAL JUMPS
        // ms stands for move size
        for (int ms = 1; ms <= dc; ms++) {
            printMazePathsJumps(sr, sc + ms, dr, dc, psf + "h" + ms);
        }

        // 4. VERTICAL JUMPS
        for (int ms = 1; ms <= dr; ms++) {
            printMazePathsJumps(sr + ms, sc, dr, dc, psf + "v" + ms);
        }

        // 5. DIAGONAL JUMPS
        for (int ms = 1; ms <= dr && ms <= dc; ms++) {
            printMazePathsJumps(sr + ms, sc + ms, dr, dc, psf + "d" + ms);
        }
    }
}