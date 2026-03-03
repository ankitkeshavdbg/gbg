import java.util.*;

public class vowelTransformation {
    
    // Helper to check for vowels
    public static boolean isVowel(char ch) {
        ch = Character.toLowerCase(ch); // Handle edge case if input is upper
        return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u';
    }

    public static void printsub(String ques, String asf) {
        // BASE CASE: No more characters to decide on
        if (ques.length() == 0) {
            System.out.println(asf);
            return;
        }

        char ch = ques.charAt(0); // Current character
        String roq = ques.substring(1); // Rest of the question

        if (isVowel(ch)) {
            // CHOICE 1: Capitalize the vowel
            printsub(roq, asf + Character.toUpperCase(ch));
            // CHOICE 2: Keep it lowercase
            printsub(roq, asf + Character.toLowerCase(ch));
        } else {
            // CONSONANT: Only one path possible
            printsub(roq, asf + ch);
        }
    }

    public static void main(String[] args) {
        String str = "ace";
        printsub(str, "");
    }
}