public class addFirst {
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
    
        
        System.out.println("\n✅ Test complete.");
    }
}
