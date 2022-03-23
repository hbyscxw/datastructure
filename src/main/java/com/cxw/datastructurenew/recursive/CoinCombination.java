package com.cxw.datastructurenew.recursive;

/**
 * @author chengxuwei
 * @create 2022/3/22 16:08
 * @desc 硬币组合
 * 1 2 5分 要组成一元钱 问有多少种组合方式
 *
 */
public class CoinCombination {
    public static void main(String[] args) {
        int[] ary = {1,2,5};
        int sum = 100;
        int num = process();

        //int[][] dp = new int[][];
    }

    private static int process() {
        int sum = 0;
        for (int i = 0; i <= 100/1; i++) {
            for (int j = 0; j <= 100/2 - i; j++) {
                for (int k = 0; k <= 100/5 - i -(j*2); k++) {
                    if(i*1+j*2+k*5==100){
                        sum++;
                        break;
                    }
                }
            }
        }
        return sum;
    }


}
