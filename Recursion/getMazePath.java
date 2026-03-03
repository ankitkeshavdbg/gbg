import java.util.*;

public class getMazePath {
    public static ArrayList<String> getPath(int sr, int sc, int dr, int dc) {
        // SUCCESS BASE CASE
        if (sr == dr && sc == dc) {
            ArrayList<String> bres = new ArrayList<>();
            bres.add("");
            return bres; // <--- This return is the "Signal" that we reached the end!
        }
        
        // FAILURE BASE CASE
        if (sr > dr || sc > dc) {
            return new ArrayList<>();
        }

        ArrayList<String> finalres = new ArrayList<>();

        // Faith 1: Move Right (Horizontal)
        ArrayList<String> hpath = getPath(sr, sc + 1, dr, dc);
        for (String str : hpath) {
            finalres.add("h" + str);
        }

        // Faith 2: Move Down (Vertical)
        ArrayList<String> vpath = getPath(sr + 1, sc, dr, dc);
        for (String str : vpath) {
            finalres.add("v" + str);
        }
        
        return finalres;
    }

    public static void main(String[] args) {
        // A 3x3 maze (from 0,0 to 2,2)
        ArrayList<String> res = getPath(0, 0, 2, 2);
        System.out.println(res);
    }
}