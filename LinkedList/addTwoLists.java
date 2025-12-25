public class addTwoLists {
    public static class Node {
        int data;
        Node next;
    }
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

        void addFirst(int val) {
            Node temp = new Node();
            temp.data = val;
            temp.next = head;
            head = temp;

            if (size == 0) {
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
            while (temp != null) {
                System.out.print(temp.data + " -> ");
                temp = temp.next;
            }
            System.out.println("null");
        }
    }

    // --- 3. Recursive Helper Function (Core Logic) ---
    /**
     * Recursive helper to add two linked lists, handling alignment and carry.
     * It uses the 'pv' (position value, equivalent to remaining length) to align the traversal.
     * * @param one Current node in the first list.
     * @param pv1 Remaining length/position of list one.
     * @param two Current node in the second list.
     * @param pv2 Remaining length/position of list two.
     * @param res The resulting linked list (built recursively using addFirst).
     * @return The carry (int oc - old carry) passed back up the recursion stack.
     */
    public static int addListHelper(Node one, int pv1, Node two, int pv2, LinkedList res) {
        
        // Base Case: Both lists are exhausted
        if (one == null && two == null) {
            return 0; // Return zero carry
        }

        int data = 0;
        int oc; // Old Carry from the deeper recursive call

        if (pv1 > pv2) {
            // Case 1: List 'one' is longer. Traverse 'one' until lengths match.
            // pv2 remains constant, pv1 decreases.
            oc = addListHelper(one.next, pv1 - 1, two, pv2, res);
            data = one.data + oc;
        } else if (pv1 < pv2) {
            // Case 2: List 'two' is longer. Traverse 'two' until lengths match.
            // pv1 remains constant, pv2 decreases.
            oc = addListHelper(one, pv1, two.next, pv2 - 1, res);
            data = two.data + oc;
        } else {
            // Case 3: Lists are of equal length (or now aligned). Traverse both simultaneously.
            oc = addListHelper(one.next, pv1 - 1, two.next, pv2 - 1, res);
            data = one.data + two.data + oc;
        }
        
        // --- Post-computation (executed on return from deeper calls) ---
        int nd = data % 10; // New Digit (the value for the current position)
        int nc = data / 10; // New Carry (to be passed up)
        
        // Build the result list by adding digits to the front (addFirst)
        // This is necessary because we process MSD first but calculate LSD first.
        res.addFirst(nd);
        
        return nc; // Return the new carry
    }
    
    // --- 4. Main Public Method ---
    /**
     * Public function to initiate the addition of two linked lists.
     * @param list1 The first linked list.
     * @param list2 The second linked list.
     * @return A new LinkedList representing the sum.
     */
    public static LinkedList addTwoLists(LinkedList list1, LinkedList list2) {
        
        // The result list where the sum will be stored
        LinkedList res = new LinkedList();
        
        // Get initial lengths (used as position values)
        int pv1 = list1.size;
        int pv2 = list2.size;

        // Call the recursive helper function
        int finalCarry = addListHelper(list1.head, pv1, list2.head, pv2, res);
        
        // Handle the final carry (e.g., 99 + 1 = 100)
        if (finalCarry > 0) {
            res.addFirst(finalCarry);
        }
        
        return res;
    }


    // --- 5. Main Execution Method ---
    public static void main(String[] args) {
        
        // --- Setup List 1 (L1) ---
        // L1: 9 -> 7 -> 6 (Value 976)
        LinkedList list1 = new LinkedList();
        list1.addLast(9);
        list1.addLast(7);
        list1.addLast(6);

        // --- Setup List 2 (L2) ---
        // L2: 8 -> 4 (Value 84)
        LinkedList list2 = new LinkedList();
        list2.addLast(8);
        list2.addLast(4);

        // --- Execute Addition (976 + 84 = 1060) ---
        LinkedList resultList = addTwoLists(list1, list2);
        
        // --- Display Results ---
        System.out.print("List 1 (976): ");
        list1.display(); 
        
        System.out.print("List 2 (84): ");
        list2.display();
        
        System.out.println("-------------------------");
        
        System.out.print("Sum (1060): ");
        resultList.display(); // Output: 1 -> 0 -> 6 -> 0 -> null
        
        System.out.println("-------------------------");

        // --- Example 2: Lists of equal length with carry ---
        // L3: 9 -> 9 (Value 99)
        LinkedList list3 = new LinkedList();
        list3.addLast(9);
        list3.addLast(9);
        
        // L4: 1 -> 1 (Value 11)
        LinkedList list4 = new LinkedList();
        list4.addLast(1);
        list4.addLast(1);
        
        // Calculate the sum (99 + 11 = 110)
        LinkedList resultList2 = addTwoLists(list3, list4);
        
        System.out.print("List 3 (99): ");
        list3.display(); 
        
        System.out.print("List 4 (11): ");
        list4.display();
        
        System.out.print("Sum (110): ");
        resultList2.display(); // Output: 1 -> 1 -> 0 -> null
    }
}