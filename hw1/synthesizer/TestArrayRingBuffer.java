package synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the synthesizer.ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        synthesizer.ArrayRingBuffer<Integer> arb = new synthesizer.ArrayRingBuffer<>(10);
        arb.enqueue(10);
        arb.enqueue(5);
        arb.enqueue(15);
        assertFalse(arb.isEmpty());
    }

    /** Calls tests for synthesizer.ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 
