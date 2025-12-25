public class mergeSort {
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

        public static Node midNode(Node head, Node tail){
            Node s= head;
            Node f= head;

            while(f!=tail && f.next!=tail){
                s=s.next;
                f=f.next.next;
            }
            return s;
        }

        public static LinkedList mergeTwoSortedLists(LinkedList l1, LinkedList l2){
            Node one = l1.head;
            Node two = l2.head;

            LinkedList l3= new LinkedList();

            while(one!=null && two!=null)
            {
                if(one.data<two.data){
                    l3.addLast(one.data);
                    one=one.next;
                } else{
                    l3.addLast(two.data);
                    two=two.next;
                }
            }

            while(one!=null){
                l3.addLast(one.data);
                one=one.next;
            }
            while(two!=null){
                l3.addLast(two.data);
                two=two.next;
            }
            return l3;
        }

        public static LinkedList mergeSort(Node head, Node tail){
            if(head == tail){
                LinkedList br = new LinkedList();
                br.addLast(head.data);
                return br;
            }


            Node mid = midNode(head, tail);
            LinkedList fsh = mergeSort(head, mid);
            LinkedList ssh = mergeSort(mid.next, tail);
            LinkedList cl = LinkedList.mergeTwoSortedLists(fsh, ssh);
            return cl;
        }
    }


    public static void main(String[] args) {

        LinkedList list1 = new LinkedList();
    
        list1.addLast(10);
        list1.addLast(20);
        list1.addLast(30);
        list1.addLast(40);
        list1.display();

        LinkedList list2 = new LinkedList();

        list2.addLast(5);
        list2.addLast(12);
        list2.addLast(24);
        list2.addLast(37);
        list2.display();

        LinkedList list3 = LinkedList.mergeTwoSortedLists(list1, list2);
        list3.addLast(1);
        list3.addLast(11);
        list3.display();

        LinkedList sorted= LinkedList.mergeSort(list3.head, list3.tail);
        sorted.display();
    }
}