public class Percolation {
    
    private int N;
    private boolean[] sites;
    private final int VIRTUAL_TOP;
    private final int VIRTUAL_BOTTOM;
    private WeightedQuickUnionUF percolationUnion;
    private WeightedQuickUnionUF fullUnion;
    
    // create N-by-N grid, with all sites blocked
    public Percolation(int N) {
        
        if (N <= 0) {
            throw new IllegalArgumentException(
                        "N should be a number greater than 0");
        }
        
        this.N = N;
        
        VIRTUAL_TOP = 0;
        VIRTUAL_BOTTOM = N * N + 1;
        
        sites = new boolean[N * N + 2];
        percolationUnion = new WeightedQuickUnionUF(N * N + 2);
        fullUnion = new WeightedQuickUnionUF(N * N + 2);
        
        for (int i = 1; i <= N * N; i++) {
            sites[i] = false;
        }
        
        sites[0] = true;
        sites[N * N + 1] = true;
    }
    
    private boolean isValid(int i) {
        return (i >= 1 && i <= N);
    }
    
    private void createConnection(int index, int celli, int cellj) {
        if (isValid(celli) && isValid(cellj) && isOpen(celli, cellj)) {
            fullUnion.union(getIndex(celli, cellj), index);
            percolationUnion.union(getIndex(celli, cellj), index);
        }
    }

    
    // open site (row i, column j) if it is not already
    public void open(int i, int j) {
        boolean validEntry = isValid(i) && isValid(j);
        if (!validEntry) {
            throw new java.lang.IndexOutOfBoundsException("Index out of bounds");
        }
        int index = getIndex(i, j);
        sites[index] = true;
        
        // if index is on the first line create union with virtual top site
        if (i == 1) {
            percolationUnion.union(index, VIRTUAL_TOP);
            fullUnion.union(index, VIRTUAL_TOP);
        }
        
        // if index is on the last line create union with virtual bottom site
        if (i == N) {
            percolationUnion.union(index, VIRTUAL_BOTTOM);
        }
        
        // check for open neighbours: (i - 1, j), (i + 1, j), (i, j - 1), (i, j + 1)
        createConnection(index, i - 1, j);
        createConnection(index, i + 1, j);
        createConnection(index, i, j - 1);
        createConnection(index, i, j + 1);
    }
    
    private int getIndex(int i, int j) {
        return (i - 1) * N + j;
    }
    
    // is site (row i, column j) open?
    public boolean isOpen(int i, int j) {
        if (!isValid(i) || !isValid(j)) {
            throw new java.lang.IndexOutOfBoundsException("Index out of bounds");
        }
        return this.sites[getIndex(i, j)];
    }
    
    // is site (row i, column j) full? = is (i, j) connected to the top
    public boolean isFull(int i, int j) {
        
        if (!isValid(i) || !isValid(j)) {
            throw new java.lang.IndexOutOfBoundsException("Index out of bounds");
        }
        int index = getIndex(i, j);
        return fullUnion.connected(index, VIRTUAL_TOP);
    }
    
    // does the system percolate?
    public boolean percolates() {
        return percolationUnion.connected(VIRTUAL_TOP, VIRTUAL_BOTTOM);
    }
    
    public static void main(String[] args) {
    }
}