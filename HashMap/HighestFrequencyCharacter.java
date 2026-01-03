import java.util.*;

public class HighestFrequencyCharacter {
    
    /*
    go to terminal on vs code
    
    PS C:\Users\ankit\Desktop\0or1\HashMap> javac HighestFrequencyCharacter.java
    PS C:\Users\ankit\Desktop\0or1\HashMap> java HighestFrequencyCharacter
    Enter string: abracadabra
    Character with highest frequency: a
    Frequency: 5
    
    */
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        System.out.print("Enter string: ");
        String str = scn.nextLine();

        HashMap<Character, Integer> hm = new HashMap<>();

        // Building the frequency map
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            
            // Pro Tip: hm.getOrDefault(ch, 0) + 1 replaces your entire if-else block
            if (hm.containsKey(ch)) {
                int of = hm.get(ch);
                int nf = of + 1;
                hm.put(ch, nf);
            } else {
                hm.put(ch, 1);
            }
        }

        // Finding the Maximum Frequency Character
        char mfc = str.charAt(0); // Assume first char is max
        for (Character key : hm.keySet()) {
            if (hm.get(key) > hm.get(mfc)) {
                mfc = key;
            }
        }

        System.out.println("Character with highest frequency: " + mfc);
        System.out.println("Frequenc: " + hm.get(mfc));
    }
}