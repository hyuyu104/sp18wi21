public class LinkedListDeque<T> {

    private class Node{

        public T item;
        public Node prev;
        public Node next;

        /**
         * This is the constructor of Node.
         * @i: item in each Node with type of T
         * @p: reference to the previous Node
         * @n: reference to the next Node
         */
        public Node(Node p, T i, Node n) {
            prev = p;
            item = i;
            next = n;
        }
    }

    private int size;
    private Node sentinel;

    /* The constructor of LinkedListDeque, will create an empty list.*/
    public LinkedListDeque() {
        size = 0;
        sentinel = new Node(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
    }

    /**
     * The constructor of LinkedListDeque, will do a deep copy of other.
     * @other: the LinkedListDeque to copy from*/
    // public LinkedListDeque(LinkedListDeque other) {
    //     size = 0;
    //     sentinel = new Node(null, null, null);
    //     sentinel.prev = sentinel;
    //     sentinel.next = sentinel;

    //     for (int i = 0; i < other.size(); i++) {
    //         addLast((T) other.get(i));
    //     }
    // }

    /**
     * addFirst will add an item of type T to the first postion of the list.
     * @item: item to be added of type T*/
    public void addFirst(T item) {
        sentinel.next = new Node(sentinel, item, sentinel.next);
        sentinel.next.next.prev = sentinel.next;
        size = size + 1;
    }

    /**
     * addLast will add an item of type T to the last postion of the list.
     * @item: item to be added of type T*/
    public void addLast(T item) {
        sentinel.prev = new Node(sentinel.prev, item, sentinel);
        sentinel.prev.prev.next = sentinel.prev;
        size = size + 1;
    }

    /**
     * removeFirst will remove the first item from the list and return it.
     * @return: item removed of type T*/
    public T removeFirst() {
        T returnItem = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size = size - 1;
        return returnItem;
    }

    /**
     * removeLast will remove the last item from the list and return it.
     * @return: item removed of type T*/
    public T removeLast() {
        T returnItem = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size = size - 1;
        return returnItem;
    }

    /**
     * isEmpty will determine whether the list is empty.
     * @return: boolean value based on whether the list is empty.*/
    public boolean isEmpty() {
        return size <= 0;
    }

    /**
     * size will return the current size of the list.
     * @return: an integer of the size of the list*/
    public int size() {
        if (size <= 0) {
            return 0;
        }
        return size;
    }

    /**
     * get method will return the value of ith item using iteration.
     * @index: the index of the item, integer
     * @return: the value of the ith item with a type of T*/
    public T get(int index) {
        Node a = sentinel;
        while (index >= 0) {
            a = a.next;
            index = index - 1;
        }
        return a.item;
    }

    /**
     * This is a helper function of the getRecursive method.
     * @index: the index of the item, integer
     * @Node: Node where getRecursive will find item
     * @return: the value of the ith item with a type of T*/
    private T getRecursive(int index, Node n) {
        if (index < 0) {
            return n.item;
        }
        return getRecursive(index - 1, n.next);
    }

    /**
     * getRecursive method will return the value of ith item using recursion.
     * @index: the index of the item, integer
     * @return: the value of the ith item with a type of T*/
    public T getRecursive(int index) {
        return getRecursive(index, sentinel);
    }

    /* printDeque will print each item in the list, separating by space*/
    public void printDeque() {
        Node a = sentinel;
        for (int i = 0; i < size; i++) {
            System.out.print(a.next.item + " ");
            a = a.next;
        }
        System.out.println();
    }
}
