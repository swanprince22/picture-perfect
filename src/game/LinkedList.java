package game;
import utils.Design;

public class LinkedList {
Node head;
public LinkedList(){
    this.head = null;
}

public void add(Design data){
    Node newNode = new Node(data);

    if (head == null){
        head = newNode;
        return;
    }

    Node current = head;
    while(current.next != null){
        current = current.next;
    }

    current.next = newNode;
}

 public int indexOf(Design data) {
        Node current = head;
        int index = 0;

        while (current != null) {
            if (current.data == data)
                return index;

            current = current.next;
            index++;
        }

        return -1;
    }

    public void swap(int index1, int index2) {
        if (index1 == index2) return;

        Node prev1 = null, prev2 = null;
        Node node1 = head, node2 = head;

        int i = 0;

        while (node1 != null && i < index1) {
            prev1 = node1;
            node1 = node1.next;
            i++;
        }

        i = 0;
        while (node2 != null && i < index2) {
            prev2 = node2;
            node2 = node2.next;
            i++;
        }

        if (node1 == null || node2 == null) return;

        if (prev1 != null) prev1.next = node2;
        else head = node2;

        if (prev2 != null) prev2.next = node1;
        else head = node1;

        Node temp = node1.next;
        node1.next = node2.next;
        node2.next = temp;
    }

    public void clear() {
        head = null;
    }
}
