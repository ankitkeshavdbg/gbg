import java.util.*;

public class getMazePathWithJumps {

    public static ArrayList<String> getPath(int sr, int sc, int dr, int dc) {
        // 1. SUCCESS BASE CASE: You made it!
        if (sr == dr && sc == dc) {
            ArrayList<String> bres = new ArrayList<>();
            bres.add("");
            return bres;
        }

        // 2. FAILURE BASE CASE: You walked off the board!
        // This is the "Reactive" part—we catch the mistake after it happens.
        if (sr > dr || sc > dc) {
            return new ArrayList<>();
        }

        ArrayList<String> finalres = new ArrayList<>();

        // Horizontal Jumps
        // We loop through all possible jumps up to the max width of the board
        for (int ms = 1; ms <= dc; ms++) {
            ArrayList<String> hres = getPath(sr, sc + ms, dr, dc);
            for (String str : hres) {
                finalres.add("h" + ms + str);
            }
        }

        // Vertical Jumps
        for (int ms = 1; ms <= dr; ms++) {
            ArrayList<String> vres = getPath(sr + ms, sc, dr, dc);
            for (String str : vres) {
                finalres.add("v" + ms + str);
            }
        }

        // Diagonal Jumps
        for (int ms = 1; ms <= dr && ms <= dc; ms++) {
            ArrayList<String> dres = getPath(sr + ms, sc + ms, dr, dc);
            for (String str : dres) {
                finalres.add("d" + ms + str);
            }
        }

        return finalres;
    }

    public static void main(String[] args) {
        ArrayList<String> res = getPath(0, 0, 2, 2);
        System.out.println(res);
    }
}