package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {
    private double[] fractions;
    private int T;

    /**
     * Constructor of PercolationStats class
     *
     * @param N the dimension of the grid
     * @param T the number of repetition
     * @param pf PercolationFactory object
     */
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException("N and T must all be positive");
        }
        this.T = T;
        fractions = new double[T];
        for (int i = 0; i < T; i++) {
            Percolation per = pf.make(N);
            while (!per.percolates()) {
                per.open(StdRandom.uniform(N), StdRandom.uniform(N));
            }
            fractions[i] = Double.valueOf(per.numberOfOpenSites()) / Double.valueOf(N * N);
        }
    }

    /**
     * Compute the mean of T repetitions
     *
     * @return mean
     */
    public double mean() {
        return StdStats.mean(fractions);
    }

    /**
     * Compute the standard deviation of T repetitions
     *
     * @return standard deviation
     */
    public double stddev() {
        return StdStats.stddev(fractions);
    }

    /**
     * Compute the lower bound of the confidence interval
     *
     * @return the lower bound of the confidence interval
     */
    public double confidenceLow() {
        return mean() - 1.96 * stddev() / Math.sqrt(T);
    }

    /**
     * Compute the upper bound of the confidence interval
     *
     * @return the upper bound of the confidence interval
     */
    public double confidenceHigh() {
        return mean() + 1.96 * stddev() / Math.sqrt(T);
    }
}
