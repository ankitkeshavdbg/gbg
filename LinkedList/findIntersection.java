public class findIntersection {
    // 1. Node Class
    public static class Node {
        int data;
        Node next;
    }

    // 2. LinkedList Class
    public static class LinkedList {
        Node head;
        Node tail;
        int size;

        void addLast(int val) {
            Node temp = new Node();
            temp.data = val;
            temp.next = null; 

            if (size == 0) {
                head = tail = temp;
            } else {
                tail.next = temp;
                tail = temp;
            }
            size++;
        }

        public void display() {
            if (size == 0) {
                System.out.println("List is empty.");
                return;
            }
            Node temp = head;
            System.out.print("List (Size " + size + "): ");
            while (temp != null) {
                System.out.print(temp.data + " -> ");
                temp = temp.next;
            }
            System.out.println("null");
        }
        
        /**
         * Attaches a segment of nodes and correctly updates both 'tail' and 'size'.
         * @param node The starting node of the segment to attach.
         */
        public void addNode(Node node) {
            if (node == null) return;
            
            // --- Step 1: Link the segment ---
            if (size == 0) {
                head = node;
            } else {
                tail.next = node;
            }

            // --- Step 2: Traverse to find the true end (new tail) and calculate size increase ---
            Node current = node;
            int segmentLength = 0;
            
            // Traverse until the end of the attached segment
            while (current != null) {
                segmentLength++;
                // If the next node is null, we've found the new tail
                if (current.next == null) {
                    tail = current; 
                }
                current = current.next;
            }
            
            // --- Step 3: Update the overall list size ---
            size += segmentLength;
        }
    }
    
    // 3. Main Intersection Logic (Corrected using Difference in Length)
    /**
     * Finds the intersection node using the Difference in Length method.
     * Time Complexity: O(N + M) | Space Complexity: O(1)
     * * @param list1 The first linked list.
     * @param list2 The second linked list.
     * @return The intersecting Node, or null.
     */
    public static Node findIntersection(LinkedList list1, LinkedList list2) {
        
        int len1 = list1.size;
        int len2 = list2.size;
        
        Node head1 = list1.head;
        Node head2 = list2.head;

        // Step 1: Calculate the difference in length.
        int diff = Math.abs(len1 - len2);

        // Step 2: Determine the longer list and advance its pointer by the difference.
        if (len1 > len2) {
            head1 = moveHead(head1, diff);
        } else if (len2 > len1) {
            head2 = moveHead(head2, diff);
        }
        
        // Step 3: Traverse both lists simultaneously until the pointers meet.
        while (head1 != null && head2 != null) {
            if (head1 == head2) {
                return head1; // Intersection found
            }
            head1 = head1.next;
            head2 = head2.next;
        }

        // If the loop finishes, the lists do not intersect.
        return null;
    }

    /** Helper method to advance a list head by 'k' steps. */
    private static Node moveHead(Node head, int k) {
        Node current = head;
        for (int i = 0; i < k; i++) {
            if (current == null) {
                return null; 
            }
            current = current.next;
        }
        return current;
    }


    // 4. Main Method with Test Case
    public static void main(String[] args) {
        
        // --- Setup Intersection Segment (8 -> 4 -> 5 -> null) ---
        // Length = 3
        Node commonStart = new Node();
        commonStart.data = 8;
        
        commonStart.next = new Node();
        commonStart.next.data = 4;
        
        commonStart.next.next = new Node();
        commonStart.next.next.data = 5;
        
        

        // --- Setup List 1 (4 -> 1 | Length 2) ---
        LinkedList list1 = new LinkedList();
        list1.addLast(4);
        list1.addLast(1);
        list1.addNode(commonStart); // Appends the segment (Total Length = 5)
        list1.display(); 

        // --- Setup List 2 (5 -> 6 -> 1 -> 7 | Length 4) ---
        LinkedList list2 = new LinkedList();
        list2.addLast(5);
        list2.addLast(6);
        list2.addLast(1);
        list2.addLast(7);
        list2.addNode(commonStart); // Appends the segment (Total Length = 7)
        list2.display(); 

        // --- Find Intersection ---
        Node intersectionNode = findIntersection(list1, list2);

        if (intersectionNode != null) {
            System.out.println("\n✅ Intersection found at node with value: " + intersectionNode.data); 
            // Output: 8
        } else {
            System.out.println("\n❌ The lists do not intersect.");
        }
    }
}