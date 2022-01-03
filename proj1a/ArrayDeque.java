public class ArrayDeque<T>{
	private T[] items;
	private int size;
	private int fsize;
	private int bsize;

	/* The constructor of ArrayDeque, will create an empty array of size 8.*/
	public ArrayDeque() {
		items = (T[]) new Object[8];
		fsize = 0;
		bsize = 0;
	}

	/**
	 * The constructor of ArrayDeque, will do a deep copy of other.
	 * @other: the ArrayDeque to copy from*/
	public ArrayDeque(ArrayDeque other) {
		items = (T[]) new Object[8];
		fsize = 0;
		bsize = 0;

		for (int i = 0; i < other.size(); i++) {
			addLast((T) other.get(i));
		}
	}

	/**
	 * resize items according to the length specified
	 * @capacity: the expected length after resizing*/
	private void resize(int capacity) {
		T[] a = (T[]) new Object[capacity];
		System.arraycopy(items, 0, a, 0, bsize);
		System.arraycopy(items, items.length-fsize, a, a.length-fsize, fsize);
		items = a;
	}

	/**
	 * addFirst will add an item of type T to the first postion of the list.
	 * @item: item to be added of type T*/
	public void addFirst(T item) {
		size = bsize + fsize;
		if (size >= items.length) {
			resize(size * 2);
		}
		items[items.length - fsize - 1] = item;
		fsize = fsize + 1;
	}

	/**
	 * addLast will add an item of type T to the last postion of the list.
	 * @item: item to be added of type T*/
	public void addLast(T item) {
		size = bsize + fsize;
		if (size >= items.length) {
			resize(size * 2);
		}
		items[bsize] = item;
		bsize = bsize + 1;
	}

	/**
	 * saveResize will resize the list when usage ratio is less than 0.25
	 * and the length of the list is greater than or equal to 16 in order
	 * to save memory*/
	private void saveResize() {
		size = bsize + fsize;
		if (size/items.length < 0.25 & items.length >= 16) {
			resize(size + 1);
		}
	}

	/**
	 * removeFirst will remove the first item from the list and return it.
	 * @return: item removed of type T*/
	public T removeFirst() {
		T returnItem = items[items.length - fsize];
		items[items.length - fsize] = null;
		fsize = fsize - 1;
		saveResize();
		return returnItem;
	}

	/**
	 * removeLast will remove the last item from the list and return it.
	 * @return: item removed of type T*/
	public T removeLast() {
		T returnItem = items[bsize - 1];
		items[bsize - 1] = null;
		bsize = bsize - 1;
		saveResize();
		return returnItem;
	}

	/**
	 * isEmpty will determine whether the list is empty.
	 * @return: boolean value based on whether the list is empty.*/
	public boolean isEmpty() {
		return (fsize + bsize) == 0;
	}

	/**
	 * size will return the current size of the list.
	 * @return: an integer of the size of the list*/
	public int size() {
		return fsize + bsize;
	}

	/**
	 * get method will return the value of ith item.
	 * @index: the index of the item, integer
	 * @return: the value of the ith item with a type of T*/
	public T get(int index) {
		if (index < fsize) {
			return items[items.length - fsize + index];
		}
		return items[index - fsize];
	}

	/* printDeque will print each item in the list, separating by space*/
	public void printDeque() {
		for (int i = items.length - fsize; i < items.length; i++) {
			System.out.print(items[i] + " ");
		}
		for (int i = 0; i < bsize; i++) {
			System.out.print(items[i] + " ");
		}
		System.out.println();
	}
}