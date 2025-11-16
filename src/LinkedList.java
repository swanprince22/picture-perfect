public class LinkedList {
    Node head;
    public LinkedList(){
        this.head=null;
    }

    public void add(Design data){
        Node newNode = new Node(data);
        if (head == null){
            head = newNode;
            return;
        }

        Node current = head;
        while(current.next!=null){
            current=current.next;
        }
        current.next = newNode;
    }
}
