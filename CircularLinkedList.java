public class CircularLinkedList extends AbstractLinkedList {

    private SNode head;

    //abstract class of the class AbstractLinkedList
    @Override
    public String getListType() {
        return "Circular Linked List";
    }

    // Helper class: locate the last node (the one whose next == head).
    private SNode findLast() {
        SNode curr = head;
        while (curr.next != head) {
            curr = curr.next;
        }
        return curr;
    }

    //abstract classes of the interface MyList
    @Override
    public void add(int value) {
        SNode newNode = new SNode(value);
        if (head == null) {
            head = newNode;
            newNode.next = head;
        } else {
            SNode last = findLast();
            last.next = newNode;
            newNode.next = head;
        }
        size++;
    }

    @Override
    public void addAt(int index, int value) {
        if (index < 0 || index > size) {
            System.out.println("Invalid index.");
            return;
        } else if (index == size) {
            add(value);
            return;
        }
        SNode newNode = new SNode(value);
        if (index == 0) {
            if (head == null) {
                head = newNode;
                newNode.next = head;
            } else {
                SNode last = findLast();
                newNode.next = head;
                head = newNode;
                last.next = head;
            }
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
            if (size == 1) {
                head = null;
            } else {
                SNode last = findLast();
                head = head.next;
                last.next = head;
            }
            size--;
            return true;
        }
        SNode prev = head;
        SNode curr = head.next;
        for (int i = 1; i < size; i++) {
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
        for (int i = 0; i < size; i++) {
            System.out.print(curr.data + " ");
            curr = curr.next;
        }
        System.out.println("] (circular)");
    }
}
