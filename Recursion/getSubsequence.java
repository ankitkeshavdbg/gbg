import java.util.*;

public class getSubsequence {
    public static void main(String[] args) {
        System.out.println(gss("abc"));
    }

    public static ArrayList<String> gss(String str) {
        // BASE CASE: If string is empty, the only subsequence is an empty string
        if (str.length() == 0) {
            ArrayList<String> bres = new ArrayList<>();
            bres.add("");
            return bres;
        }

        char ch = str.charAt(0);          // 'a'
        String ros = str.substring(1);    // "bc"
        
        // FAITH: This call brings back [, c, b, bc]
        ArrayList<String> rres = gss(ros); 

        ArrayList<String> mres = new ArrayList<>();
        
        // Loop through faith results and apply choices
        for (String s : rres) {
            mres.add("" + s);    // Choice 1: 'a' stays out
        }
        for (String s : rres) {
            mres.add(ch + s);    // Choice 2: 'a' joins in
        }

        return mres;
    }
}