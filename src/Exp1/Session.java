package Exp1;

import java.util.Random;

public class Session {

    public Percolation per;

    public Session(int N, double p) {
        int count = 0, sum = 0;
        per = new Percolation(N);
        Random r = new Random();
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if(r.nextDouble() < p)
                    per.grid_b[i][j] = true;
            }
        }

        for(int j = 1; j <= N; j++){
            if(per.isOpen(1, j))
                per.qf.union(per.grid[0][0], per.grid[i][j]);       //grid[0][0] 是顶点（P）
        }
        for(int j = 1; j <= N; j++){
            if(per.isOpen(N, j))
                per.qf.union(per.grid[0][1], per.grid[N][j]);       //grid[0][1] 是底点（Q）
        }


        //per.init();
        //per.rand();
//        for (int x = 0; x < per.rds.length; x++) {
        while(true){
//          int rand = per.rds[x];
//          StdOut.println("rand是" + rand + ' ');
            int rand = StdRandom.uniform(N*N+1);
            if(rand == 0)continue;
            int i = rand / N + 1, j = rand % N;
            if (j == 0) {
                j = N;
                i = rand / N;
            }//i,j是rand产生的坐标(i,j);

            if (per.isFull(i, j)) per.open(i, j);
            //连通rand所在的方格
            if (per.range(i - 1, j) && per.isOpen(i - 1, j)) {
                if (!per.qf.connected(per.grid[i - 1][j], per.grid[i][j]))
                    per.qf.union(per.grid[i - 1][j], per.grid[i][j]);
            }
            if (per.range(i + 1, j) && per.isOpen(i + 1, j)) {
                if (!per.qf.connected(per.grid[i + 1][j], per.grid[i][j]))
                    per.qf.union(per.grid[i + 1][j], per.grid[i][j]);
            }
            if (per.range(i, j - 1) && per.isOpen(i, j - 1)) {
                if (!per.qf.connected(per.grid[i][j - 1], per.grid[i][j]))
                    per.qf.union(per.grid[i][j - 1], per.grid[i][j]);
            }
            if (per.range(i, j + 1) && per.isOpen(i, j + 1)) {
                if (!per.qf.connected(per.grid[i][j + 1], per.grid[i][j]))
                    per.qf.union(per.grid[i][j + 1], per.grid[i][j]);
            }
            if (per.percolates()) break;
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (per.isOpen(i, j)) count++;
            }
        }
        return (double) count/N/N;
    }


    public void

    public static void main(String[] args) {
        int a = StdIn.readInt();
        OneExp ww = new OneExp();
        for (int i = 0;i<3;i++){
            StdOut.println(ww.OneExperiment(a));
        }

    }
}
