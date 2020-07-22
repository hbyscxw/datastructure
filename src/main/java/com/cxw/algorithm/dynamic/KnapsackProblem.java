package com.cxw.algorithm.dynamic;

import java.util.Arrays;

/**
 * @author chengxuwei
 * @date 2020-07-21 17:19
 * @description 动态规划  背包问题
          0,    1,    2     3     4
0        [0,    0,    0,    0,    0]
1500(1)  [0, 1500, 1500, 1500, 1500]
3000(4)  [0, 1500, 1500, 1500, 3000]
2000(2)  [0, 1500, 1500, 2000, 3500]
 */
public class KnapsackProblem {
    public static void main(String[] args) {
        int[] w = {1,4,3};//物品的重量
        int[] val = {1500,3000,2000};//物品的价值
        int m = 5;//背包容量
        int n = val.length;//物品个数
        int[][] v = new int[n+1][m+1]; //背包价值
        String[][] c = new String[n+1][m+1];//背包物品内容

        for (int i = 1; i < v.length ; i++) {
            for (int j = 1; j < v[0].length; j++) {
                if(w[i-1]>j){//当准备新增物品的重量>当前容量时，则使用上行的单元格的策略
                    v[i][j] = v[i-1][j];
                    c[i][j] = c[i-1][j];
                }else{
                    //比较 上行的单元格的价值 新增商品+剩余空间的最大价值
//                    v[i][j] = Math.max(v[i-1][j],val[i-1]+v[i-1][j-w[i-1]]);
                    if(v[i-1][j]>val[i-1]+v[i-1][j-w[i-1]]){
                        v[i][j] = v[i-1][j];
                        c[i][j] = c[i-1][j];
                    }else{
                        v[i][j] = val[i-1]+v[i-1][j-w[i-1]];
                        if(c[i-1][j-w[i-1]]!=null) {
                            c[i][j] = val[i - 1] + "+" + c[i - 1][j - w[i - 1]];
                        }else{
                            c[i][j] = String.valueOf(val[i - 1]);
                        }
                    }
                }
            }
        }
        print(v);
        print(c);
    }

    private static void print(int[][] ary) {
        for (int[] a : ary) {
            System.out.println(Arrays.toString(a));
        }
    }

    private static void print(String[][] ary) {
        for (String[] a : ary) {
            System.out.println(Arrays.toString(a));
        }
    }

}