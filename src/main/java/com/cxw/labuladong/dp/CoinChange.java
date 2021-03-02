package com.cxw.labuladong.dp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author chengxuwei
 * @date 2021-03-01 10:31
 * @description 零钱兑换
 * https://leetcode-cn.com/problems/coin-change/
 */
public class CoinChange {
    public static void main(String[] args) {
        int[] coins = {2};
        int amount = 3;
        CoinChange cc = new CoinChange();
        int res = cc.violenceRecursive(coins, amount);
        System.out.println(res);
    }
    /**
     * 暴力递归
     * @param coins
     * @param amount
     * @return
     */
    public int violenceRecursive(int[] coins,int amount){
        if(amount<0){
            return -1;
        }
        if(amount==0){
            return 0;
        }
        int count = Integer.MAX_VALUE;
        for (int coin : coins) {
            int res = violenceRecursive(coins,amount-coin);
            if(res==-1){
                continue;
            }
            count = Math.min(res + 1, count);
        }
        if(count==Integer.MAX_VALUE){
            return -1;
        }
        return count;
    }

    /**
     * 带备忘录的递归
     */
    Map<Integer,Integer> memo = new HashMap<>();
    public int memoRecursive(int[] coins,int amount){
        if(amount<0){
            return -1;
        }
        if(amount==0){
            return 0;
        }
        int count = Integer.MAX_VALUE;
        for (int coin : coins) {
            int res = -1;
            Integer v = memo.get(amount - coin);
            if(v==null){
                res = violenceRecursive(coins,amount-coin);
                memo.put(amount-coin,res);
            }else{
                res = v;
            }
            if(res==-1){
                continue;
            }
            count = Math.min(res + 1, count);
        }
        if(count==Integer.MAX_VALUE){
            return -1;
        }
        return count;
    }

    public int dpRecursive(int[] coins,int amount){
        int[] dp = new int[amount+1];
        Arrays.fill(dp,Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 0; i <= dp.length; i++) {
            for (int coin : coins) {
                if(i-coin<0){
                    continue;
                }
                dp[i] = Math.min(dp[i-coin]+1, dp[i]);
            }
        }
        if(dp[amount]==Integer.MAX_VALUE){
            return -1;
        }
        return dp[amount];
    }
}