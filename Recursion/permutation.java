import java.util.*;

public class permutation {

    public static void permu(String ques, String ans) {
        // BASE CASE: No more characters left to pick
        if (ques.length() == 0) {
            System.out.println(ans);
            return;
        }

        // We loop through all available characters in the current question
        for (int i = 0; i < ques.length(); i++) {
            char ch = ques.charAt(i); // Pick the i-th character

            // STITCHING: Remove the picked character to get the 'Rest of Question'
            String leftPart = ques.substring(0, i);
            String rightPart = ques.substring(i + 1);
            String roq = leftPart + rightPart;

            // FAITH: Find all permutations of the remaining characters
            // Adding 'ch' to the END of 'ans' keeps the sequence in order
            permu(roq, ans + ch);
        }
    }

    public static void main(String[] args) {
        permu("abc", "");
    }
}