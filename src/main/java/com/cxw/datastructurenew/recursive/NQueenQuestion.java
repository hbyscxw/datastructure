package com.cxw.datastructurenew.recursive;

import java.util.Arrays;

/**
 * @author chengxuwei
 * @create 2022/3/17 17:03
 * @desc n皇后问题
 */
public class NQueenQuestion {
    public static void main(String[] args) {
        int n = 8;
        int[] records = new int[n];
        Arrays.fill(records,0);
        int num = process(records,0,n);
        System.out.println(num);
    }

    /**
     *
     * @param records 之前的皇后摆法
     * @param i 到了第i行
     * @param n 整体n行
     * @return 合法的摆法个数
     */
    private static int process(int[] records, int i, int n) {
        if(i == n){
            return 1;
        }
        int num = 0;
        for (int j = 0; j < n; j++) {//尝试i行所有列
            if(valid(records,i,j)){
                records[i] = j;  //recode[i] 第i行的皇后放在j位置上
                num+=process(records,i+1,n);
            }
        }
        return num;
    }

    private static boolean valid(int[] records, int i, int j) {
        for (int k = 0; k < i; k++) {
            //共列或者共斜线
            if(records[k]==j||Math.abs(records[k]-j)==Math.abs(i-k)){
                return false;
            }
        }
        return true;
    }
}
