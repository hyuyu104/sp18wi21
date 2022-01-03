public class LinkedListDeque<T>{

	public class IntNode{

		public T item;
		public IntNode prev;
		public IntNode next;

		/**
		 * This is the constructor of IntNode.
		 * @i: item in each IntNode with type of T
		 * @p: reference to the previous IntNode
		 * @p: reference to the next IntNode
		 */
		public IntNode(IntNode p, T i, IntNode n) {
			prev = p;
			item = i;
			next = n;
		}
	}

	private int size;
	private IntNode sentinel;

	public LinkedListDeque() {
		size = 0;
		sentinel = new IntNode(null, null, null);
		sentinel.prev = sentinel;
		sentinel.next = sentinel;
	}

	public void addFirst(T item) {
		sentinel.next = new IntNode(sentinel, item, sentinel.next);
		sentinel.next.next.prev = sentinel.next;
		size = size + 1;
	}

	public void addLast(T item) {
		sentinel.prev = new IntNode(sentinel.prev, item, sentinel);
		sentinel.prev.prev.next = sentinel.prev;
		size = size + 1;
	}

	public T removeFirst() {
		T returnItem = sentinel.next.item;
		sentinel.next = sentinel.next.next;
		sentinel.next.prev = sentinel;
		size = size - 1;
		return returnItem;
	}

	public T removeLast() {
		T returnItem = sentinel.prev.item;
		sentinel.prev = sentinel.prev.prev;
		sentinel.prev.next = sentinel;
		size = size - 1;
		return returnItem;
	}

	public boolean isEmpty() {
		return size==0;
	}

	public int size() {
		return size;
	}

	public T get(int index) {
		IntNode a = sentinel;
		while (index >= 0) {
			a = a.next;
			index = index - 1;
		}
		return a.item;
	}

	private T getRecursive(int index, IntNode n) {
		if (index < 0) return n.item;
		return getRecursive(index - 1, n.next);
	}

	public T getRecursive(int index) {
		return getRecursive(index, sentinel);
	}

	public void printDeque() {
		IntNode a = sentinel;
		for (int i = 0; i < size; i ++) {
			System.out.print(a.next.item + " ");
			a = a.next;
		}
		System.out.println();
	}

	public LinkedListDeque(LinkedListDeque other) {
		sentinel = new IntNode();
	}

	public static void main(String[] args) {
		LinkedListDeque<Integer> L = new LinkedListDeque<>();
		L.addFirst(15);
		L.addFirst(10);
		L.addFirst(5);
		L.addFirst(0);
		L.addLast(20);
		L.addLast(25);
		L.addLast(30);
		L.printDeque();
		System.out.println(L.removeLast());
		System.out.println(L.removeLast());
		System.out.println(L.removeFirst());
		L.printDeque();
		System.out.println("size: " + L.size());
		System.out.println("item: " + L.get(3));
		System.out.println("item: " + L.getRecursive(3));
	}
}