import java.util.*;

public class keypadCombination {

    static String[] codes = {".;", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tu", "vwx", "yz"};

    public static ArrayList<String> combination(String digits) {
        // BASE CASE: The "Empty String" represents one valid path (doing nothing)
        if (digits.length() == 0) {
            ArrayList<String> bres = new ArrayList<>();
            bres.add("");
            return bres;
        }

        char ch = digits.charAt(0);          // '7'
        String ros = digits.substring(1);    // '8'

        // FAITH: Get all combinations for "8" -> [v, w, x]
        ArrayList<String> rosCombination = combination(ros); 
        
        ArrayList<String> finalRes = new ArrayList<>();

        // SELF WORK: Get the string for '7' -> "tu"
        String codeForCh = codes[ch - '0']; // '7' - '0' = 7
        
        // Muscle Tip: Always loop through the current choices first, 
        // then the results from the recursive call.
        for (int i = 0; i < codeForCh.length(); i++) {
            char chCode = codeForCh.charAt(i);
            for (String str : rosCombination) {
                finalRes.add(chCode + str);
            }
        }
        
        return finalRes;
    }

    public static void main(String[] args) {
        ArrayList<String> res = combination("78");
        System.out.println(res);
    }
}