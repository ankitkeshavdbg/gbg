public class addLastTemplate {
    public static class Node{
        int data;
        Node next;
    }

    public static class LinkedList{
        Node head;
        Node tail;
        int size;

        void addLast(int val){

        }

        public void display() {

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
        
        System.out.println("\n✅ Test complete.");
    }
}
