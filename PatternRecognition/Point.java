/*************************************************************************
 * Name: Andreea Florescu
 * Email: andreea.florescu15@gmail.com
 *
 * Compilation:  javac Point.java
 * Execution:
 * Dependencies: StdDraw.java
 *
 * Description: An immutable data type for points in the plane.
 *
 *************************************************************************/

import java.util.Comparator;

public class Point implements Comparable<Point> {

    // compare points by slope
    public final Comparator<Point> SLOPE_ORDER;       // YOUR DEFINITION HERE
    private final double POSITIVE_ZERO = 0.0;
    private final int x;                              // x coordinate
    private final int y;                              // y coordinate

    // create the point (x, y)
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    // plot this point to standard drawing
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    // draw line between this point and that point to standard drawing
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // slope between this point and that point
    public double slopeTo(Point that) {
        /* YOUR CODE HERE */
        
        // check for corner cases
        if (isHorizontalLine(that)) {
            return POSITIVE_ZERO;
        }
        if (isVerticalLine(that)) {
            return Double.POSITIVE_INFINITY;
        }
        if (isDegenarateLine(that)) {
            return Double.NEGATIVE_INFINITY;
        }
        
        return (that.y - this.y) / (that.x -this.x);
    }
    
    private boolean isHorizontalLine(Point that) {
        return this.y = that.y;
    }
    
    private boolean isVerticalLine(Point that) {
        return this.x == that.x;
    }
    
    private boolean isDegenarateLine(Point that) {
        return this.x == that.x && this.y == that.y;
    }

    // is this point lexicographically smaller than that one?
    // comparing y-coordinates and breaking ties by x-coordinates
    public int compareTo(Point that) {
        /* YOUR CODE HERE */
        // Formally, the invoking point (x0, y0) is less than the argument point (x1, y1) 
        // if and only if either y0 < y1 or if y0 = y1 and x0 < x1. 
        int y0 = this.y;
        int x0 = this.x;
        int y1 = that.y;
        int x1 = that.x;
        
        if (less(that)) {
            return -1;
        }
        
        if (equal(that)) {
            return 0;
        }
        
        return 1;
    }
    
    private boolean less(Point that) {
        int y0 = this.y;
        int x0 = this.x;
        int y1 = that.y;
        int x1 = that.x;
        
        return y0 < y1 || (y0 == y1 && x0 < x1); 
    }
    
    private boolean equal(Point that) {
        return this.x == that.x && this.y == that.y;
    }
    
    // return string representation of this point
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    // unit test
    public static void main(String[] args) {
        /* YOUR CODE HERE */
    }
}
