/* @source StudentArrayDequeLauncher class */
import static org.junit.Assert.*;
import org.junit.Test;
public class TestArrayDequeGold {
    /* This method will randomly call StudentArrayDeque and
    ArrayDequeSolution methods until they disagree on an output*/
    @Test
    public void testArrayDeque() {
        StudentArrayDeque<Integer> sad1 = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> ads1 = new ArrayDequeSolution<>();
        String message = "\n";
        for (int i = 0; i < 200; i++) {
            double numberBetweenZeroAndOne = StdRandom.uniform();
            if ((numberBetweenZeroAndOne < 0.25) && (!sad1.isEmpty()) && (!ads1.isEmpty())) {
                Integer x = sad1.removeFirst();
                Integer y = ads1.removeFirst();
                message = message + "removeFirst()\n";
                assertEquals(message, y, x);
            } else if ((numberBetweenZeroAndOne < 0.5) && (!sad1.isEmpty()) && (!ads1.isEmpty())) {
                Integer x = sad1.removeLast();
                Integer y = ads1.removeLast();
                message = message + "removeLast()\n";
                assertEquals(message, y, x);
            } else if ((numberBetweenZeroAndOne < 0.75)) {
                int add = StdRandom.uniform(10);
                sad1.addFirst(add);
                ads1.addFirst(add);
                message = message + "addFirst(" + add + ")\n";
            } else {
                int add = StdRandom.uniform(10);
                sad1.addLast(add);
                ads1.addLast(add);
                message = message + "addLast(" + add + ")\n";
            }
        }
    }
}
