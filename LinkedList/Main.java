public class Main {
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
            temp.data = val;
            temp.next = null;

            if(size==0){
                head=tail=temp;
            } else {
                tail.next =temp;
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
            System.out.print("List: ");
            while (temp != null) {
                System.out.print(temp.data + " -> ");
                temp = temp.next;
            }
            System.out.println("null");
        }
    }


    public static void main(String[] args) {

        LinkedList list = new LinkedList();
    
        list.addLast(10);
        list.addLast(20);
        list.addLast(30);
        list.addLast(40);
        list.display();
        
    }
}