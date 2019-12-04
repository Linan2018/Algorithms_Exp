package Exp1;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Percolation {

    public int N;
    public int [][] grid;
    public boolean [][] grid_b;
    public QuickFindUF qf ;
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
        grid_b[0][0]=true;
        grid_b[0][1]=true;
        grid[0][0]=0;
        grid[0][1]=N*N+1;
    }
    public void open(int i, int j)         // open site (row i, column j) if it is not already
    {
        if(isFull(i,j)){
            grid_b[i][j] = true;
        }

    }
    public boolean isOpen(int i, int j)  // is site (row i, column j) open?
    {
        if(grid_b[i][j]) return true;
        else return false;
    }
    public boolean isFull(int i, int j) // is site (row i, column j) full?
    {
        if(!grid_b[i][j]) return true;
        else return false;
    }
    public boolean range(int x, int y){
        if(x<1||x>N||y<1||y>N) return false;
        else return true;
    }
    public void init(){
        for(int i=1,j=1;j<=N;j++){
            if(isOpen(i,j))
                qf.union(grid[0][0],grid[i][j]);       //grid[0][0] 是顶点（P）
        }
        for(int i=N,j=1;j<=N;j++){
            if(isOpen(i,j))
                qf.union(grid[0][1],grid[i][j]);       //grid[0][1] 是底点（Q）
        }
    }
    /*  public void rand() {
            int sz = N*N;
            Random rd = new Random();

            List<Integer> lst = new ArrayList<Integer>();//存放有序数字集合
            for (int i = 0; i < sz; i++) {
                lst.add(i);
            }
            //随机索引
            int index = 0;
            for (int i = 0; i < sz; i++) {
                index = rd.nextInt(sz - i);
                rds[i] = lst.get(index)+1;
                lst.remove(index);
            }
        *//*    for (int i = 0; i < sz; i++) {
            StdOut.println(rds[i]);
        }*//*
    }*/
    public boolean percolates() // does the system percolate?
    {
        init();
        return (qf.connected(grid[0][0],grid[0][1]));
    }
    public static void main(String[] args){           // test client, optional
        Percolation x=new Percolation(5);
        //x.rand();

    }
}


