import java.util.*;

public class HashMapIntro {
    
    public static void main(String[] args){
        // 1. Initialization
        HashMap<String, Integer> hm = new HashMap<>();

        // 2. PUT (Insertion)
        hm.put("India", 135);
        hm.put("China", 200);
        hm.put("US", 20);
        
        System.out.println("Initial Map: " + hm);

        // 3. PUT (Overwrite Behavior)
        // If China is already there, this updates 200 to 195
        hm.put("China", 195); 
        System.out.println("After updating China: " + hm);

        // 4. GET Behavior
        // Case: Key exists
        Integer indiaPop = hm.get("India");
        System.out.println("Value for 'India': " + indiaPop); // Output: 135

        // Case: Key does NOT exist
        Integer utopiaPop = hm.get("Utopia"); 
        System.out.println("Value for 'Utopia': " + utopiaPop); // Output: null

        // 5. CONTAINS KEY Behavior
        System.out.println("Is India present? " + hm.containsKey("India")); // true
        System.out.println("Is Utopia present? " + hm.containsKey("Utopia")); // false

        // 6. REMOVE Behavior
        hm.remove("US");
        System.out.println("After removing US: " + hm);
        
        // 7. SIZE
        System.out.println("Total entries: " + hm.size());
    }
}