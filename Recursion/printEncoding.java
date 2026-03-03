import java.util.*;

public class printEncoding {

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        // Test with inputs like "123", "655196", or "103"
        String str = "123";
        printEncodings(str, "");
    }

    public static void printEncodings(String ques, String asf) {
        // 1. BASE CASE: Success!
        // When the question is empty, it means we have successfully decoded the entire string.
        if (ques.length() == 0) {
            System.out.println(asf);
            return;
        }

        // 2. INVALID CASE: Strings starting with '0' cannot be decoded.
        // There is no mapping for '0', '01', etc. 
        // We return to kill this branch (Backtracking/Pruning).
        if (ques.charAt(0) == '0') {
            return;
        }

        // CHOICE 1: Take the first single digit
        char ch1 = ques.charAt(0);
        String roq1 = ques.substring(1);
        
        // Convert char '1' to int 1, then map to 'a'
        int v1 = ch1 - '0'; 
        char code1 = (char)('a' + v1 - 1);
        printEncodings(roq1, asf + code1);

        // CHOICE 2: Take the first two digits (if they exist)
        if (ques.length() >= 2) {
            String ch12 = ques.substring(0, 2);
            String roq12 = ques.substring(2);

            // Convert String "12" to int 12
            int v12 = Integer.parseInt(ch12);
            
            // Only recurse if the two-digit number is valid (10 to 26)
            if (v12 <= 26) {
                char code12 = (char)('a' + v12 - 1);
                printEncodings(roq12, asf + code12);
            }
        }
    }
}