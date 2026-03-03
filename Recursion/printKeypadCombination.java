import java.util.*;

public class printKeypadCombination {
    // Our Global Weapon: The mapping of numbers to characters
    static String[] codes = {".;", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tu", "vwx", "yz"};

    public static void main(String[] args) {
        // We start with the full number string and an empty Answer So Far
        printKPC("78", "");
    }

    public static void printKPC(String ques, String ans) {
        // 1. BASE CASE: If the question is empty, the 'ans' bag is full.
        if (ques.length() == 0) {
            System.out.println(ans);
            return;
        }

        // 2. SELF WORK: Get the first digit (the current choice)
        char ch = ques.charAt(0); // e.g., '7'
        String roq = ques.substring(1); // the rest of the question, e.g., "8"

        // 3. MULTI-BRANCHING: Get the string for that digit and loop through it
        String codeForCh = codes[ch - '0']; // Convert '7' to int 7 to get "tu"

        for (int i = 0; i < codeForCh.length(); i++) {
            char chCode = codeForCh.charAt(i); // e.g., 't', then 'u'
            
            // FAITH: Pass the remaining question and the updated answer down
            // For '78', we first pass ('8', "t") and then later ('8', "u")
            printKPC(roq, ans + chCode);
        }
    }
}