public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int findex;

    /* The constructor of ArrayDeque, will create an empty array of size 8.*/
    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        findex = 0;
    }

    /**
     * The constructor of ArrayDeque, will do a deep copy of other.
     * @other: the ArrayDeque to copy from*/
    // public ArrayDeque(ArrayDeque other) {
    //     items = (T[]) new Object[8];
    //     size = 0;
    //     findex = 0;

    //     for (int i = 0; i < other.size(); i++) {
    //         addLast((T) other.get(i));
    //     }
    // }

    /**
     * resize items according to the length specified
     * @capacity: the expected length after resizing*/
    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        for (int i = findex; i < findex + size; i++) {
            a[i - findex] = items[i % items.length];
        }
        items = a;
        findex = 0;
    }

    /**
     * addFirst will add an item of type T to the first postion of the list.
     * @item: item to be added of type T*/
    public void addFirst(T item) {
        if (size >= items.length) {
            resize(size * 2);
        }
        int index = (findex + items.length - 1) % items.length;
        items[index] = item;
        System.out.println("added index: " + ((findex + size) % items.length));
        System.out.println("findex: " + findex);
        findex = index;
        size = size + 1;
    }

    /**
     * addLast will add an item of type T to the last postion of the list.
     * @item: item to be added of type T*/
    public void addLast(T item) {
        if (size >= items.length) {
            resize(size * 2);
        }
        items[(findex + size) % items.length] = item;
        System.out.println("added index: " + ((findex + size) % items.length));
        System.out.println("findex: " + findex);
        size = size + 1;
    }

    /**
     * saveResize will resize the list when usage ratio is less than 0.25
     * and the length of the list is greater than or equal to 16 in order
     * to save memory*/
    private void saveResize() {
        if (size / items.length < 0.25 & items.length >= 16) {
            resize(size + 1);
        }
    }

    /**
     * removeFirst will remove the first item from the list and return it.
     * @return: item removed of type T*/
    public T removeFirst() {
        T returnItem = items[findex];
        items[findex] = null;
        findex = (findex + 1) % items.length;
        size = size - 1;
        saveResize();
        return returnItem;
    }

    /**
     * removeLast will remove the last item from the list and return it.
     * @return: item removed of type T*/
    public T removeLast() {
        int index = (findex + size - 1) % items.length;
        T returnItem = items[index];
        items[index] = null;
        size = size - 1;
        saveResize();
        return returnItem;
    }

    /**
     * isEmpty will determine whether the list is empty.
     * @return: boolean value based on whether the list is empty.*/
    public boolean isEmpty() {
        return size == 0;
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
     * get method will return the value of ith item.
     * @index: the index of the item, integer
     * @return: the value of the ith item with a type of T*/
    public T get(int index) {
        return items[(findex + index) % items.length];
    }

    /* printDeque will print each item in the list, separating by space*/
    public void printDeque() {
        for (int i = findex; i < findex + size; i++) {
            System.out.print(items[i % items.length] + " ");
        }
        System.out.println();
    }
}
