public class SinglyLinkedList extends AbstractLinkedList {
    private SNode head;

    //abstract class of class AbstractLinkedList
    @Override
    public String getListType() {
        return "Singly Linked List";
    }
    
    //abstract classes of the interface MyList
    @Override
    public void add(int value) {
        SNode newNode = new SNode(value);
        if (head == null) {
            head = newNode;
        } else {
            SNode curr = head;
            while (curr.next != null) {
                curr = curr.next;
            }
            curr.next = newNode;
        }
        size++;
    }

    @Override
    public void addAt(int index, int value) {
        if (index < 0 || index > size) {
            System.out.println("Invalid index.");
            return;
        }
        SNode newNode = new SNode(value);
        if (index == 0) {
            newNode.next = head;
            head = newNode;
        } else {
            SNode prev = head;
            for (int i = 0; i < index - 1; i++) {
                prev = prev.next;
            }
            newNode.next = prev.next;
            prev.next = newNode;
        }
        size++;
    }

    @Override
    public int get(int index) {
        if (index < 0 || index >= size) {
            return -1;
        }
        SNode curr = head;
        for (int i = 0; i < index; i++) {
            curr = curr.next;
        }
        return curr.data;
    }

    @Override
    public boolean remove(int value) {
        if (head == null) {
            return false;
        }
        if (head.data == value) {
            head = head.next;
            size--;
            return true;
        }
        SNode prev = head;
        SNode curr = head.next;
        while (curr != null) {
            if (curr.data == value) {
                prev.next = curr.next;
                size--;
                return true;
            }
            prev = curr;
            curr = curr.next;
        }
        return false;
    }

    @Override
    public void display() {
        System.out.print("[ ");
        SNode curr = head;
        while (curr != null) {
            System.out.print(curr.data + " ");
            curr = curr.next;
        }
        System.out.println(" ]");
    }
}
