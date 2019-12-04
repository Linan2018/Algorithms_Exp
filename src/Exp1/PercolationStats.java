package Exp1;

import java.util.Random;

public class PercolationStats {

    private double[] array;
    private int T, N;
    public Percolation per;
    private double sum=0,avg=0,var=0;

    private void simulate(int N, int T) {
        this.T = T;
        this.N = N;

        array = new double[T];
        for (int i = 0 ; i < T ; i ++){
            Session s = new Session();
            array[i] = s.one(N);
        }
    }   // perform T independent computational experiments on an N-by-N grid
    private double mean(){
        double sum = 0;
        System.out.println("   ");
        for (int i = 0 ; i < this.T ; i++){
            sum += array[i];
        }
        double avg = sum/this.T;
        System.out.println("均值为:");
        System.out.println(avg);
        return avg;
    }         // sample mean of percolation threshold

    private double stddev(){
        for ( int i = 0 ; i < T ; i ++){
            var += (array[i]-avg)*(array[i]-avg);
        }
        var /= T;
        var =Math.sqrt(var);
        System.out.println("标准差为:");
        System.out.println(var);
        return var;
    }          // sample standard deviation of percolation threshold
    private double confidenceLo()  {
        System.out.println("置信区间下限：");
        return avg-1.96*var;
    }       // returns lower bound of the 95% confidence interval
    private double confidenceHi()   {
        System.out.println("置信区间上限：");
        return avg+1.96*var;
    }      // returns upper bound of the 95% confidence interval
//    private void runOnce(double p){
//        Random r = new Random();
//        per.generate(p);
//        for (int i = 2; i <= per.N; i++) {
//            for (int j = 1; j <= per.N; j++) {
//                if(per.isOpen(i, j)){
//                    // 向上检查
//                    if(per.isOpen(i, j-1))
//                        per.qf.union(per.grid[i][j], per.grid[i][j-1]);
//                    // 向左检查
//                    if(j != 1 && per.isOpen(i-1, j))
//                        per.qf.union(per.grid[i][j], per.grid[i-1][j]);
//                }
//            }
//        }
//
//    }

//    private void simulate(){
//        for(int i = 0; i < T; i++){
//
//            double p = 0.1;
//
//            while(true){
//                runOnce(p);
//                if(per.percolates()){
//                    array[i] = p;
//                    break;
//                }
//                per.qf.reset();
//                p += 0.1;
//            }
//        }
//    }

    public static void main(String[] args)   {
        long starttime = System.currentTimeMillis();
        PercolationStats q = new PercolationStats();

//        q.PercolationStats_x(N, T);

        q.simulate(200,100);

        long endtime = System.currentTimeMillis();
        System.out.println("运行时间为:"+(endtime-starttime)+"ms");

        q.mean();
        q.stddev();
        System.out.println(q.confidenceLo());
        System.out.println(q.confidenceHi());
    } // test client, described below
}