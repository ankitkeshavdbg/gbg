import java.util.*;

public class HashMapIntroKeySet {
    
    public static void main(String[] args){
        HashMap<String, Integer> hm = new HashMap<>();
        
        hm.put("India", 140);
        hm.put("China", 200);
        hm.put("Pak", 40);
        hm.put("US", 20);
        hm.put("UK", 15);

        // 1. Get the KeySet
        Set<String> keys = hm.keySet();
        System.out.println("All Keys: " + keys);

        // 2. Loop through the keys to get values
        System.out.println("\n--- Iterating using KeySet ---");
        for (String key : keys) {
            Integer value = hm.get(key);
            System.out.println("Country: " + key + ", Population: " + value);
        }
    }
}