public class kthFromEnd {
    public static class Node{
        int data;
        Node next;
    }

    public static class LinkedList{
        Node head;
        Node tail;
        int size;

        void addLast(int val){
            Node temp = new Node();
            temp.data=val;
            temp.next=null;

            if(size==0){
                head=tail=temp;
            }else{
                tail.next =temp;
                tail=temp;
            }
            size++;
        }

        public void display() {
            Node temp=head;
            if(size==0){
                System.out.println("List is empty");
            } else{
                while(temp!=null){
                    System.out.print(temp.data);

                    if(temp.next!=null){
                        System.out.print("->");
                    }
                    temp=temp.next;
                }
                System.out.println();
            }
        }

        public void removeFirst(){
            if(size==0){
                System.out.println("List is empty");

            } else if(size==1){
                head=tail=null;
                size--;

            } else{
                head = head.next;
                size--;

            }
        }

        public int getAt(int idx){
            if(size==0){
                System.out.println("List is empty");
                return -1;
            } else if(idx<0 || idx>=size){
                System.out.println("Invalid argument");
                return -1;
            } else{
                Node temp= head;
                for(int i=0; i<idx; i++){
                    temp =temp.next;
                }
                return temp.data;
            }
        }

        public void addFirst(int val){
            Node temp = new Node();
            temp.data = val;
            temp.next = null;

            if(size == 0){
                head =tail =temp;
                
            }else{
                temp.next = head;
                head =temp;

            }
            size++;
        }

        public void addAt(int idx, int val){
            if(idx==0){
                addFirst(val);
            } else if(idx == size){
                addLast(val);
            } else if(idx<0 || idx>size){
                System.out.println("Invalid index");
            } else{
                Node temp = new Node();
                temp.data =val;

                Node traverse= head;
                for(int i=0;i<idx-1; i++){
                    traverse = traverse.next;
                }
                temp.next = traverse.next;
                traverse.next =temp;

                size++;
            }
        }

        public void removeLast(){
            if(size==0){
                System.out.println("List is empty");

            } else if(size ==1){
                head =tail =null;
                size--;

            } else {
                Node traverse = head;
                for(int i=0; i<size -2; i++){
                    traverse = traverse.next;
                }
                tail = traverse;
                traverse.next=null;
                size--;

            }
        }

        private Node getNodeAt(int idx){
            Node traverse= head;
            for(int i=0; i<idx; i++){
                traverse= traverse.next;
            }
            return traverse;
        }

        public void reverseDI(){
            int li = 0; 
            int ri = size -1; 

            while(li< ri){
                Node left = getNodeAt(li);
                Node right = getNodeAt(ri);

                int temp = left.data;
                left.data = right.data;
                right.data = temp;

                li++;
                ri--;
            }
        }

        public void pointerIterative(){
            Node prev = null;
            Node curr = head; 

            while(curr != null)
            {
                // 1. Save the rest of the list (before overwriting curr.next)
                Node after_curr = curr.next; 
                // 2. Reverse the link: Make curr point backward to prev
                curr.next = prev;
                // 3. Advance prev: curr (now reversed) becomes the new prev
                prev = curr;
                // 4. Advance curr: Move to the next saved node
                curr = after_curr;
            }

            // --- Final Head and Tail Swap (Crucial for list utility) ---
            Node temp = head;
            head = tail; 
            tail = temp; 
        }

        public void removeAt(int idx){
            if (size == 0) {
                System.out.println("List is empty");
                return;
            } 
            if (idx < 0 || idx >= size) { // Must use >= size for an invalid index
                System.out.println("Invalid index");
                return;
            } 
            // Special Case 1: Remove Head
            if (idx == 0) {
                removeFirst();
                return;
            }
            // Special Case 2: Remove Tail
            if (idx == size - 1) {
                removeLast(); // Assumes removeLast() handles the single-node case correctly
                return;
            } 
            // Main Logic: Remove Intermediate Node (0 < idx < size - 1)
            {
                // Find the node *just before* the one to be deleted (at idx-1)
                Node prev_node = head;
                // Loop runs idx - 1 times
                for(int i = 0; i < idx - 1; i++){ 
                    prev_node = prev_node.next;
                }
                
                // Let the preceding node bypass the current node (the one to be deleted).
                // prev_node.next is the node to delete.
                // prev_node.next.next is the node after the one to delete.
                prev_node.next = prev_node.next.next;
                
                size--;
            }
        }
    
        public Node kthFromEnd(int k) {
            if (k < 0) {
                System.out.println("K must be non-negative.");
                return null;
            }
            
            Node slow = head;
            Node fast = head;

            // --- 1. Establish the K-node gap ---
            for (int i = 0; i < k; i++) {
                if (fast == null) {
                    // Error: K is greater than or equal to the size of the list
                    System.out.println("Invalid K. List is too short.");
                    return null;
                }
                fast = fast.next;
            }

            // --- 2. Move both pointers together until 'fast' is null ---
            // Since k=0 means the last node, 
            //'fast' points to null when 'slow' points to the last node.
            while (fast != null) {
                slow = slow.next;
                fast = fast.next;
            }

            // --- 3. Return the result ---
            // 'slow' is now at the Kth node from the end.
            return slow;
        }
    }


    public static void main(String[] args) {

        System.out.println("🚀 Testing LinkedList with addLast(int val) 🚀");
        System.out.println("---");

        // Initialize the LinkedList
        LinkedList list = new LinkedList();
        
        System.out.println("\n*** Adding 10 (addLast(10)) ***");
        list.addLast(10);
        list.display();

        System.out.println("\n*** Adding 20 (addLast(20)) ***");
        list.addLast(20);
        list.display();

        System.out.println("\n*** Adding 30 (addLast(30)) ***");
        list.addLast(30);
        list.display();

        System.out.println("\n*** Adding 40 (addLast(40)) ***");
        list.addLast(40);
        list.display();

        System.out.println("data at idx 2="+ list.getAt(2));

        list.addFirst(5);
        list.display();

        list.addAt(2, 7);

        list.display();
        list.removeLast();
        list.display();

        list.reverseDI();

        list.display();

        list.pointerIterative();

        list.display();
    
        
        System.out.println("\n✅ Test complete.");
    }
}
