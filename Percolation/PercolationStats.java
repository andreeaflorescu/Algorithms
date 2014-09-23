public class PercolationStats {
    
    private double[] openSites;
    private int T;
    private final double DEVIATION_CONST; 
    // perform T independent computational experiments on an N-by-N grid
    public PercolationStats(int N, int T) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException(
                        "N/T should be a number greater than 0");
        }
        
        openSites = new double[T];
        this.T = T;
        DEVIATION_CONST = 1.96;
        
        // used variables
        boolean free;
        int row = -1, column = -1, nr = 0;
        
        for (int i = 0; i < T; i++) {
            Percolation percolation = new Percolation(N);
            nr = 0;
            while (!percolation.percolates()) {
                
                // check if site is already opened
                free = false;
                while (!free) {
                    // Choose a site (row i, column j) 
                    // uniformly at random among all blocked sites
                    row = StdRandom.uniform(1, N + 1);
                    column = StdRandom.uniform(1, N + 1);
                    free = !percolation.isOpen(row, column);
                    
                }
                // Open the site (row, column)
                percolation.open(row, column);
                // increase number of open sites
                nr++;
            }
            openSites[i] = ((double) nr) / (N * N);
        }
    }
    
    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(openSites);
    }
    
    // sample standard deviation of percolation threshold
    public double stddev() {
        if (T <= 1) {
            return Double.NaN;
        }
        return StdStats.stddev(openSites);
    }
    
    // returns lower bound of the 95% confidence interval
    public double confidenceLo() {
        return mean() - (DEVIATION_CONST * stddev()) / Math.sqrt(T);
    }
    
    // returns upper bound of the 95% confidence interval
    public double confidenceHi() {
        return mean() + (DEVIATION_CONST * stddev()) / Math.sqrt(T);
    }
    
    // test client, described below
    public static void main(String[] args) {
        // mean                    = 0.5929934999999997
        // stddev                  = 0.00876990421552567
        //95% confidence interval = 0.5912745987737567, 0.5947124012262428
        int N = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);

        PercolationStats ps = new PercolationStats(N, T);
        StdOut.println("mean                    = " + ps.mean());
        StdOut.println("stddev                  = " + ps.stddev());
        StdOut.println("95% confidence interval = "
                       + ps.confidenceLo() + ", " + ps.confidenceHi());
        
    }
}