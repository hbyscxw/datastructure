package com.cxw.leetcodecourse.dynamic;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chengxuwei
 * @date 2020-10-30 09:46
 * @description 换钱的方法数
 * [题目]
 * 给定数组arr, arr中所有的值都为正数且不重复。每个值代表
 * 一种面值的货币，每种面值的货币可以使用任意张，再给定一
 * 个整数aim代表要找的钱数，求换钱有多少种方法。
 * [举例]
 * arr=[5, 10, 25, 1]，aim=0。
 * 组成0元的方法有1种，就是所有面值的货币都不用。所以返回1。
 * arr=[5, 10, 25, 1]，aim=15。
 * 组成15元的方法有6种，分别为3张5元、1张10元+1张5元、1张
 * 10元+5张1元、10张1元+1张5元、2张5元+5张1元和15张1元。所
 * 以返回6。
 * arr=[3,5]，aim=2。
 * 任何方法都无法组成2元。所以返回0。
 */
public class ChangeMoney {
    public static void main(String[] args) {
        int[] ary = {5, 10, 25, 1};
        int aim = 500;
        int res = cost1(ary,aim);
        System.out.println(res);
        int res2 = cost4(ary,aim);
        System.out.println(res2);
    }

    private static int cost1(int[] ary, int aim) {
        if(aim<0||ary==null||ary.length==0) {
            return 0;
        }else{
            return process2(ary,0,aim);
        }
    }

    private static int process1(int[] ary, int index, int aim) {
        int res = 0;
        if(index==ary.length){
            res = (aim==0)?1:0;
        }else{
            for (int i = 0; ary[index]*i <= aim ; i++) {
                res+=process1(ary,index+1,aim-ary[index]*i);
            }
        }
        return res;
    }

    static Map<String,Integer> map = new HashMap<>();
    private static int process2(int[] ary, int index, int aim) {
        int res = 0;
        if(index==ary.length){
            res = (aim==0?1:0);
        }else{

            for (int i = 0; aim - ary[index] * i >= 0; i++) {
                if(map.containsKey((index)+"_"+aim)){
                    res += map.get((index+1)+"_"+(aim - ary[index] * i));
                }else {
                    int r = process2(ary, index + 1, aim - ary[index] * i);
                    map.put((index+1)+"_"+(aim - ary[index] * i),r);
                    res+=r;
                }
            }
        }
        return res;
    }

    private static int cost3(int[] ary, int aim) {
        if(aim<0||ary==null||ary.length==0) {
            return 0;
        }
        int[][] dp = new int[ary.length][aim+1];
        //组成0元的方法有1种，就是所有面值的货币都不用。所以返回1。
        for (int i = 0; i < ary.length; i++) {
            dp[i][0]=1;
        }
        //例如第一行是5，那么5、10、15...位置肯定为1
        for (int i = 1; ary[0]*i <= aim; i++) {
            dp[0][ary[0]*i] = 1;
        }
        int num = 0;
        for (int i = 1; i < ary.length; i++) {
            for (int j = 0; j <= aim ; j++) {
                num = 0;
                for (int k = 0; j-ary[i]*k>=0; k++) {
                    num += dp[i-1][j-ary[i]*k];
                }
                dp[i][j] = num;
            }
        }
        return dp[ary.length-1][aim];
    }


    private static int cost4(int[] ary, int aim) {
        if (aim < 0 || ary == null || ary.length == 0) {
            return 0;
        }
        int[][] dp = new int[ary.length][aim + 1];
        //组成0元的方法有1种，就是所有面值的货币都不用。所以返回1。
        for (int i = 0; i < ary.length; i++) {
            dp[i][0] = 1;
        }
        for (int i = 0; aim-ary[0]*i>=0; i++) {
            dp[0][ary[0]*i] = 1;
        }
        for (int i = 1; i < ary.length; i++) {
            for (int j = 1; j<=aim; j++) {
                dp[i][j]=dp[i-1][j];
                if(j-ary[i]>=0){
                    dp[i][j] += dp[i][j-ary[i]];
                }
            }
        }
        return dp[ary.length-1][aim];
    }
}