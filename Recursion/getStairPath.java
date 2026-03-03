import java.util.*;

public class getStairPath {

    public static void main(String[] args) {
        int n = 3;
        ArrayList<String> res = getPath(n);
        System.out.println(res);
    }

    public static ArrayList<String> getPath(int n) {
        // SUCCESS: We reached the ground exactly.
        if (n == 0) {
            ArrayList<String> bres = new ArrayList<>();
            bres.add("");
            return bres;
        }
        
        // FAILURE: We jumped "past" the ground. 
        // Returning an empty list means no paths are added for this branch.
        if (n < 0) {
            return new ArrayList<>();
        }

        ArrayList<String> finalres = new ArrayList<>();

        // Branch 1: Take a 1-step jump
        ArrayList<String> paths1 = getPath(n - 1);
        for (String path : paths1) {
            finalres.add("1" + path);
        }

        // Branch 2: Take a 2-step jump
        ArrayList<String> paths2 = getPath(n - 2);
        for (String path : paths2) {
            finalres.add("2" + path);
        }

        // Branch 3: Take a 3-step jump
        ArrayList<String> paths3 = getPath(n - 3);
        for (String path : paths3) {
            finalres.add("3" + path);
        }

        return finalres;
    }
}