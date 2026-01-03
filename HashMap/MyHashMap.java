import java.util.*;

public class MyHashMap<K, V> {
    // A Node represents one Key-Value pair
    private class Node {
        K key;
        V value;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private int size; // n (number of elements)
    private LinkedList<Node>[] buckets; // N (number of buckets)

    public MyHashMap() {
        initBuckets(4); // Start with a small capacity
        size = 0;
    }

    @SuppressWarnings("unchecked")
    private void initBuckets(int N) {
        buckets = new LinkedList[N];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new LinkedList<>();
        }
    }

    public void put(K key, V value) {
        int bi = hashFunction(key); // Bucket Index
        int di = getIndexInBucket(key, bi); // Data Index (inside the list)

        if (di != -1) {
            // Key already exists, update value
            Node node = buckets[bi].get(di);
            node.value = value;
        } else {
            // New key, add to the list
            Node node = new Node(key, value);
            buckets[bi].add(node);
            size++;
        }

        // Check Load Factor (lambda = n/N)
        double lambda = (double) size / buckets.length;
        if (lambda > 2.0) { // If lists are too long, rehash
            rehash();
        }
    }

    public V get(K key) {
        int bi = hashFunction(key);
        int di = getIndexInBucket(key, bi);

        if (di != -1) {
            return buckets[bi].get(di).value;
        } else {
            return null;
        }
    }

    public boolean containsKey(K key) {
        int bi = hashFunction(key);
        int di = getIndexInBucket(key, bi);
        return di != -1;
    }

    public V remove(K key) {
        int bi = hashFunction(key);
        int di = getIndexInBucket(key, bi);

        if (di != -1) {
            Node node = buckets[bi].remove(di);
            size--;
            return node.value;
        } else {
            return null;
        }
    }

    public ArrayList<K> keySet() {
        ArrayList<K> keys = new ArrayList<>();
        for (LinkedList<Node> bucket : buckets) {
            for (Node node : bucket) {
                keys.add(node.key);
            }
        }
        return keys;
    }

    public int size() {
        return size;
    }

    private int hashFunction(K key) {
        int hc = key.hashCode(); // Java's built-in hash
        return Math.abs(hc) % buckets.length; // Compress to bucket range
    }

    private int getIndexInBucket(K key, int bi) {
        int di = 0;
        for (Node node : buckets[bi]) {
            if (node.key.equals(key)) {
                return di;
            }
            di++;
        }
        return -1;
    }

    private void rehash() {
        LinkedList<Node>[] oldBuckets = buckets;
        initBuckets(oldBuckets.length * 2); // Double the array size
        size = 0; // Reset size as put() will increment it back

        for (LinkedList<Node> bucket : oldBuckets) {
            for (Node node : bucket) {
                put(node.key, node.value); // Re-insert into new larger array
            }
        }
    }

    public static void main(String[] args) {
        MyHashMap<String, Integer> map = new MyHashMap<>();
        
        // Testing put
        map.put("India", 140);
        map.put("USA", 33);
        map.put("China", 141);
        map.put("UK", 7);

        // Testing get and size
        System.out.println("India Population: " + map.get("India") + " Cr");
        System.out.println("Map size: " + map.size());

        // Testing update
        map.put("India", 145);
        System.out.println("Updated India Population: " + map.get("India") + " Cr");

        // Testing remove
        System.out.println("Removing USA: " + map.remove("USA"));
        System.out.println("Contains USA now? " + map.containsKey("USA"));

        // Testing keySet
        System.out.println("All remaining keys: " + map.keySet());
    }
}