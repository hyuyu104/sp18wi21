public class ArrayDequeTest {
    public static void main(String[] args) {
        ArrayDeque<Integer> L = new ArrayDeque<>();
        L.addFirst(10);
        L.addFirst(5);
        L.addFirst(0);
        L.addLast(15);
        L.addLast(20);
        L.addLast(25);
        L.removeLast();
        L.removeFirst();
        L.removeLast();
        L.removeFirst();
        L.removeLast();
        L.removeFirst();
        L.removeLast();
        L.removeFirst();
        L.printDeque();
    }
}
