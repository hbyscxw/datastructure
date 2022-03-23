package com.cxw.datastructurenew.recursive;

import java.util.Arrays;

/**
 * @author chengxuwei
 * @create 2022/3/17 17:03
 * @desc n皇后问题
 */
public class NQueenQuestion2 {
    public static void main(String[] args) {
        int n = 8;
        int[][] records = new int[n][n];
        for (int[] record : records) {
            Arrays.fill(record,0);
        }
        int num = process(records,0,n);
        System.out.println(num);
    }


    private static int process(int[][] records, int i, int n) {
        if(i == n){
            return 1;
        }
        int num = 0;
        for (int j = 0; j < n; j++) {
            if(valid(records,i,j)){
                records[i][j] = 1;
                num+=process(records,i+1,n);
            }
        }
        return num;
    }

    private static boolean valid(int[][] records, int i, int j) {
        for (int k = 0; k < i; k++) {
            if(k==j||Math.abs(i-k)==Math.abs(j-k)){
                return false;
            }
        }
        return true;
    }
}
