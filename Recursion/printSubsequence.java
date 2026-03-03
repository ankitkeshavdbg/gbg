import java.util.*;

public class printSubsequence {

    public static void printsubseq(String ques, String ans) {
        // BASE CASE: When the question is empty, it means all characters 
        // have made their "Yes/No" choice.
        if (ques.length() == 0) {
            // Instead of returning a list, we just print the 'ans' we've built.
            System.out.println(ans);
            return;
        }

        // SELF WORK: 
        // 1. Pick the first character to make a decision
        char ch = ques.charAt(0); 
        
        // 2. Identify the rest of the question for the next level
        String roq = ques.substring(1); 

        // CHOICE 1 (YES): The character 'ch' decides to join the answer.
        // We add 'ch' to our 'ans' string and pass it down.
        printsubseq(roq, ans + ch);

        // CHOICE 2 (NO): The character 'ch' decides NOT to join the answer.
        // We pass the 'ans' as it is, without adding 'ch'.
        printsubseq(roq, ans + "");
    }

    public static void main(String[] args) {
        String str = "abc";

        // We start with the full string as the question 
        // and an empty string as our "Answer So Far".
        printsubseq(str, "");
    }
}