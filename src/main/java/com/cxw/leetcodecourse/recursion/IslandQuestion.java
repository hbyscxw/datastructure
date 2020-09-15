package com.cxw.leetcodecourse.recursion;

/**
 * 岛问题
 * 一个二维数组，上下左右为1连成一片时为一个岛，计算有多少个岛
 1 0 0 0 1 0
 1 0 1 0 1 0
 0 0 0 0 1 0
 1 0 0 0 0 0
 1 0 0 0 0 1
 */
public class IslandQuestion {
    public static void main(String[] args) {
        int[][] ary = {{1,0,0,0,1,0},
                {1,0,1,0,1,0},
                {0,0,0,0,1,0},
                {1,0,0,0,0,1}};
        int count = cal(ary);
        System.out.println(count);
    }

    public static int cal(int[][] ary){
        int count = 0;
        for (int i = 0; i < ary.length ; i++) {
            for (int j = 0; j <ary[0].length ; j++) {
                if(ary[i][j]==1) {
                    count++;
                    infect(ary, i, j);
                }
            }
        }
        return count;
    }

    private static void infect(int[][] ary, int i, int j) {
        int row = ary.length;
        int col = ary[0].length;
        if(i<0||i>row-1||j>col-1||j<0||ary[i][j]!=1){
            return;
        }
        ary[i][j] = 2;
        infect(ary,i+1,j);
        infect(ary,i-1,j);
        infect(ary,i,j+1);
        infect(ary,i,j-1);
    }
}
