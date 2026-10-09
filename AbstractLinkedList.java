public abstract class AbstractLinkedList implements MyList {
    protected int size;

    //abstract classes of the interface MyList
    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public abstract String getListType();
}
