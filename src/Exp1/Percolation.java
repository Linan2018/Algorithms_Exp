package Exp1;

import edu.princeton.cs.algs4.QuickFindUF;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Percolation {
    public int N;
    public int [][] grid;
    public boolean [][] grid_b;
    public QuickFindUF qf ;

    public Percolation(int N){
        grid = new int[N+1][N+1];
        grid_b = new boolean[N+1][N+1];

    } // create N-by-N grid, with all sites blocked
    public void open(int i, int j) // open site (row i, column j) if it is not already
    public boolean isOpen(int i, int j) // is site (row i, column j) open?
    public boolean isFull(int i, int j) // is site (row i, column j) full?
    public boolean percolates() // does the system percolate?
    public static void main(String[] args) // test client, optional
}


