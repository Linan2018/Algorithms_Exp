package Exp1;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Percolation {

    public int N;
    public int [][] grid;
    public boolean [][] grid_b;
    public QuickFindUF qf;
    // public int[] rds;

    public Percolation(int N)     // create N-by-N grid, with all sites b;locked
    {
        grid = new int[N+1][N+1];
        grid_b = new boolean[N+1][N+1];

        qf = new QuickFindUF(N*N+2);     //0~n*n+1
        // rds = new int[N*N];//随机数数组
        this.N = N;

        for (int i=1;i <= N;i++) {
            for (int j=1; j <= N; j++) {
                grid[i][j] = (i - 1) * N + j;
                grid_b[i][j] = false; // the full grib init is closed
            }
        }
        grid_b[0][0] = true;          //首
        grid_b[0][1] = true;          //尾
        grid[0][0] = 0;               //首
        grid[0][1] = N*N+1;           //尾
    }
    public void open(int i, int j)         // open site (row i, column j) if it is not already
    {
        if(isFull(i,j)){
            grid_b[i][j] = true;
        }

    }
    public boolean isOpen(int i, int j)  // is site (row i, column j) open?
    {
        return grid_b[i][j];
    }
    public boolean isFull(int i, int j) // is site (row i, column j) full?
    {
        return !grid_b[i][j];
    }
    public boolean range(int x, int y){
        return x >= 1 && x <= N && y >= 1 && y <= N;
    }
//    private void init(){
//        for(int i = 1, j = 1; j <= N; j++){
//            if(isOpen(i, j))
//                qf.union(grid[0][0], grid[i][j]);       //grid[0][0] 是顶点（P）
//        }
//        for(int i = N, j = 1; j <= N; j++){
//            if(isOpen(i, j))
//                qf.union(grid[0][1], grid[i][j]);       //grid[0][1] 是底点（Q）
//        }
//    }
    public boolean percolates() // does the system percolate?
    {
//        init();
        return (qf.connected(grid[0][0], grid[0][1]));
    }

    public void generate(double p){
        Random r = new Random();
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                grid_b[i][j] = r.nextDouble() < p;
            }
        }

        for(int j = 1; j <= N; j++){
            if(isOpen(1, j))
                qf.union(grid[0][0], grid[1][j]);       //grid[0][0] 是顶点（P）
        }
        for(int j = 1; j <= N; j++){
            if(isOpen(N, j))
                qf.union(grid[0][1], grid[N][j]);       //grid[0][1] 是底点（Q）
        }
    }
//    public void reset(){
//        qf.reset();
//        for (int i=1;i <= N;i++) {
//            for (int j=1; j <= N; j++) {
//                grid[i][j] = (i - 1) * N + j;
//                grid_b[i][j] = false; // the full grib init is closed
//            }
//        }
//    }

    public static void main(String[] args){           // test client, optional
        Percolation x = new Percolation(5);
        //x.rand();

    }
}


