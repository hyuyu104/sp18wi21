public class ArrayDeque<T>{
	private T[] items;
	private int size;
	private int fsize;
	private int bsize;

	public ArrayDeque() {
		items = (T[]) new Object[8];
		fsize = 0;
		bsize = 0;
	}

	public ArrayDeque(ArrayDeque other) {
		items = (T[]) new Object[8];
		fsize = 0;
		bsize = 0;

		for (int i = 0; i < other.size(); i++) {
			addLast((T) other.get(i));
		}
	}

	private void resize(int capacity) {
		T[] a = (T[]) new Object[capacity];
		System.arraycopy(items, 0, a, 0, bsize);
		System.arraycopy(items, items.length-fsize, a, a.length-fsize, fsize);
		items = a;
	}

	public void addFirst(T item) {
		size = bsize + fsize;
		if (size >= items.length) {
			resize(size * 2);
		}
		items[items.length - fsize - 1] = item;
		fsize = fsize + 1;
	}

	public void addLast(T item) {
		size = bsize + fsize;
		if (size >= items.length) {
			resize(size * 2);
		}
		items[bsize] = item;
		bsize = bsize + 1;
	}

	public T removeFirst() {
		T returnItem = items[items.length - fsize];
		items[items.length - fsize] = null;
		fsize = fsize - 1;
		return returnItem;
	}

	public T removeLast() {
		T returnItem = items[bsize - 1];
		items[bsize - 1] = null;
		bsize = bsize - 1;
		return returnItem;
	}

	public boolean isEmpty() {
		return (fsize + bsize) == 0;
	}

	public int size() {
		return fsize + bsize;
	}

	public T get(int index) {
		if (index < fsize) {
			return items[items.length - fsize + index];
		}
		return items[index - fsize];
	}

	public void printDeque() {
		for (int i = items.length - fsize; i < items.length; i++) {
			System.out.print(items[i] + " ");
		}
		for (int i = 0; i < bsize; i++) {
			System.out.print(items[i] + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		ArrayDeque<Integer> L = new ArrayDeque<>();
		L.addFirst(15);
		L.addFirst(10);
		L.addFirst(5);
		L.addFirst(0);
		L.addLast(20);
		L.addLast(25);
		L.addLast(30);
		L.printDeque();
		System.out.println("remove first: " + L.removeFirst());
		System.out.println("remove first: " + L.removeFirst());
		L.printDeque();
		System.out.println("remove last: " + L.removeLast());
		System.out.println("remove last: " + L.removeLast());
		L.printDeque();
		System.out.println(L.get(2));
		ArrayDeque<Integer> L2 = new ArrayDeque<>(L);
		L2.printDeque();
	}
}