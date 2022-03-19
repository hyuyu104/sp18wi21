package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int openSites;
    private int N;
    private boolean[] grid;
    private WeightedQuickUnionUF wquuf;
    private WeightedQuickUnionUF wquuf2;

    /**
     * Constructor of Percolation, all initial values set to false
     *
     * @param N the width and height of the grid
     */
    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException("N must be a positive number");
        }
        this.N = N;
        grid = new boolean[N * N];
        wquuf = new WeightedQuickUnionUF(N * N + 2);
        wquuf2 = new WeightedQuickUnionUF(N * N + 1);
        openSites = 0;
    }

    /**
     * Converts row number and column number to an index in grid
     *
     * @param r the row number
     * @param c the column number
     * @return integer representation of the corresponding position
     */
    private int xyTo1d(int r, int c) {
        return c + r * N;
    }

    /**
     * Helper method of open, check whether the neighbor position
     * is opened; if yes, connect it with the current position
     *
     * @param currIdx the index of the current position
     * @param neighborIdx the index of the neighbor position
     */
    private void neighborConnect(int currIdx, int neighborIdx) {
        if (neighborIdx >= 0 && neighborIdx < grid.length) {
            if (grid[neighborIdx]) {
                wquuf.union(currIdx, neighborIdx);
                wquuf2.union(currIdx, neighborIdx);
            }
        }
    }

    /**
     * Helper method to check whether the position is at the
     * bottom or the top; if yes, connect it with the flag
     * position, i.e. grid.length for the top, grid.length + 1
     * for the bottom
     *
     * @param currIdx the index of the current position
     */
    private void connectToTopOrBottom(int currIdx) {
        if (currIdx < N) {
            wquuf.union(currIdx, grid.length);
            wquuf2.union(currIdx, grid.length);
        }
        if (currIdx >= N * (N - 1)) {
            wquuf.union(currIdx, grid.length + 1);
        }
    }

    /**
     * Helps to check neighbors and connect all opened ones
     *
     * @param row the row index of the current site
     * @param col the column index of the current site
     */
    private void connectAllNeighbors(int row, int col) {
        int currIdx = xyTo1d(row, col);
        if (row - 1 >= 0) {
            neighborConnect(currIdx, xyTo1d(row - 1, col));
        }
        if (row + 1 < N) {
            neighborConnect(currIdx, xyTo1d(row + 1, col));
        }
        if (col - 1 >= 0) {
            neighborConnect(currIdx, xyTo1d(row, col - 1));
        }
        if (col + 1 < N) {
            neighborConnect(currIdx, xyTo1d(row, col + 1));
        }
    }

    /**
     * Open a site based on the position specified, connect
     * it to neighbor sites if neighbor sites are opened, and
     * check whether it's at the bottom or top row
     *
     * @param row the row index of the site
     * @param col the column index of the site
     */
    public void open(int row, int col) {
        int currIdx = xyTo1d(row, col);
        if (currIdx >= grid.length || currIdx < 0) {
            throw new IndexOutOfBoundsException("invalid row and column number");
        }
        if (isOpen(row, col)) {
            return;
        }
        grid[currIdx] = true;
        connectToTopOrBottom(currIdx);
        connectAllNeighbors(row, col);
        openSites += 1;
    }

    /**
     * Determines whether the site is opened
     *
     * @param row the row index of the site
     * @param col the column index of the site
     * @return true if it's opened, false otherwise
     */
    public boolean isOpen(int row, int col) {
        int idx = xyTo1d(row, col);
        if (idx >= grid.length || idx < 0) {
            throw new IndexOutOfBoundsException("invalid row and column number");
        }
        return grid[idx];
    }

    /**
     * Determines whether the site is full
     *
     * @param row the row index of the site
     * @param col the column index of the site
     * @return true if it's full, false otherwise
     */
    public boolean isFull(int row, int col) {
        int idx = xyTo1d(row, col);
        if (idx >= grid.length || idx < 0) {
            throw new IndexOutOfBoundsException("invalid row and column number");
        }
        return wquuf2.connected(idx, grid.length);
    }


    /**
     * The total number of current open sites
     *
     * @return the total number of current open sites
     */
    public int numberOfOpenSites() {
        return openSites;
    }

    /**
     * Determine whether the grid is percolated
     *
     * @return true if it's percolated, false otherwise
     */
    public boolean percolates() {
        return wquuf.connected(grid.length, grid.length + 1);
    }

    public static void main(String[] args) {

    }
}
