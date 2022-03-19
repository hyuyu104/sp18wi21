package hw2;

import org.junit.Test;
import static org.junit.Assert.*;

public class PercolationTester {

    @Test
    public void testOpen() {
        Percolation per = new Percolation(3);
        per.open(1, 1);

        assertTrue(per.isOpen(1, 1));
        assertFalse(per.isOpen(0, 1));

        per.open(0, 1);
        assertTrue(per.isOpen(0, 1));

        assertEquals(per.numberOfOpenSites(), 2);
    }

    @Test
    public void testIsFull() {
        Percolation per = new Percolation(3);

        assertFalse(per.isFull(0, 0));
        assertFalse(per.isFull(0, 1));
        assertFalse(per.isFull(0, 2));

        per.open(0, 1);
        per.open(1, 1);
        assertTrue(per.isFull(1, 1));

        per.open(2, 2);
        assertFalse(per.isFull(2, 2));
        per.open(2, 1);
        assertTrue(per.isFull(2, 2));

        assertEquals(per.numberOfOpenSites(), 4);
    }

    @Test
    public void testPercolates() {
        Percolation per = new Percolation(3);

        assertFalse(per.percolates());

        per.open(0, 0);
        assertFalse(per.percolates());

        per.open(1, 0);
        per.open(2, 1);
        assertFalse(per.percolates());

        per.open(1, 1);
        assertTrue(per.percolates());
    }
}
