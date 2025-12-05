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

    Node node1 = null, node2 = null, current = head;
    int index = 0;

    while (current != null) {
        if (index == index1) node1 = current;
        if (index == index2) node2 = current;
        current = current.next;
        index++;
    }

    if (node1 != null && node2 != null) {
        Design temp = node1.data;
        node1.data = node2.data;
        node2.data = temp;
    }
}
}
