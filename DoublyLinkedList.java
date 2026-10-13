public class DoublyLinkedList extends AbstractLinkedList implements Reversible {

    private DNode head;
    private DNode tail;

    //abstract method of class AbstractLinkedList
    @Override
    public String getListType() {
        return "Doubly Linked List";
    }

    //abstract method of interface MyList
    @Override
    public void add(int value) {
        DNode newNode = new DNode(value);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }

    @Override
    public void addAt(int index, int value) {
        if (index < 0 || index > size) {
            System.out.println("Invalid index.");
            return;
        }
        if (index == size) {
            add(value);
            return;
        }
        if (index == 0) {
            DNode newNode = new DNode(value);
            newNode.next = head;
            if (head != null) {
                head.prev = newNode;
            }
            head = newNode;
            if (tail == null) {
                tail = newNode;
            }
            size++;
            return;
        }
        DNode curr = head;
        for (int i = 0; i < index; i++) {
            curr = curr.next;
        }
        DNode newNode = new DNode(value);
        DNode before = curr.prev;
        newNode.prev = before;
        newNode.next = curr;
        before.next = newNode;
        curr.prev = newNode;
        size++;
    }

    @Override
    public int get(int index) {
        if (index < 0 || index >= size) {
            return -1;
        }
        DNode curr = head;
        for (int i = 0; i < index; i++) {
            curr = curr.next;
        }
        return curr.data;
    }

    @Override
    public boolean remove(int value) {
        DNode curr = head;
        while (curr != null) {
            if (curr.data == value) {
                if (curr.prev != null) {
                    curr.prev.next = curr.next;
                } else {
                    head = curr.next;
                }
                if (curr.next != null) {
                    curr.next.prev = curr.prev;
                } else {
                    tail = curr.prev;
                }
                size--;
                return true;
            }
            curr = curr.next;
        }
        return false;
    }

    @Override
    public void display() {
        System.out.print("[ ");
        DNode curr = head;
        while (curr != null) {
            System.out.print(curr.data + " ");
            curr = curr.next;
        }
        System.out.println(" ]");
    }

    //abstract method of reversible interface
    @Override
    public void reverse() {
        DNode curr = head;
        DNode temp = null;
        while (curr != null) {
            temp = curr.prev;
            curr.prev = curr.next;
            curr.next = temp;
            curr = curr.prev; // move to original "next" (now stored in prev)
        }
        temp = head;
        head = tail;
        tail = temp;
    }
}
