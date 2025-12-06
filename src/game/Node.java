package game;
import utils.Design;

public class Node {
    Design data;
    Node next;

    public Node(Design data) {
        this.data = data;
        this.next = null;
    }
}
