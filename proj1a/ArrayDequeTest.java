public class ArrayDequeTest {
    public static ArrayDeque regularAdd() {
        ArrayDeque<Integer> input = new ArrayDeque<>();
        input.addFirst(10);
        input.addLast(15);
        input.addFirst(5);
        input.addLast(20);
        input.addFirst(0);
        input.addLast(25);
        return input;
    }

    public static ArrayDeque regularRemove(ArrayDeque input) {
        System.out.println("remove first: " + input.removeFirst());
        System.out.println("remove first: " + input.removeFirst());
        System.out.println("remove last: " + input.removeLast());
        System.out.println("remove last: " + input.removeLast());
        return input;
    }

    public static ArrayDeque randomAR(ArrayDeque input) {
        input.removeLast();
        input.removeLast();
        input.removeLast();
        input.removeLast();
        input.removeLast();
        input.removeLast();
        input.addFirst(16);
        input.addFirst(16);
        input.addFirst(16);
        return input;
    }

    public static void main(String[] args) {
        ArrayDeque<Integer> testDeque = regularAdd();
        testDeque.printDeque();
//        testDeque = regularRemove(testDeque);
//        testDeque.printDeque();
        randomAR(testDeque).printDeque();
    }
}
