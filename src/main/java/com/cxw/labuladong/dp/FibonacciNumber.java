package com.cxw.labuladong.dp;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chengxuwei
 * @date 2021-03-01 09:44
 * @description 动态规划 斐波那契数列
 * https://leetcode-cn.com/problems/fibonacci-number/
 */
public class FibonacciNumber {
    public static void main(String[] args) {
        FibonacciNumber fn = new FibonacciNumber();
        int v = fn.recursive(10);
        System.out.println(v);
        v = fn.recursiveMemo(10);
        System.out.println(v);
        v = fn.recursiveDp(10);
        System.out.println(v);
        v = fn.recursiveDpCompress(10);
        System.out.println(v);
    }

    /**
     * 暴力递归
     * @param n
     * @return
     */
    private int recursive(int n) {
        if(n==0){
            return 0;
        }
        if(n==1){
            return 1;
        }
        return recursive(n-1)+recursive(n-2);
    }
    /**
     * 带备忘录的递归
     * @param n
     * @return
     */
    Map<Integer,Integer> memo = new HashMap<>();
    private int recursiveMemo(int n) {
        Integer value = memo.get(n);
        if(value!=null){
            return value;
        }
        if(n==0){
            memo.put(0,0);
            return 0;
        }
        if(n==1){
            memo.put(1,1);
            return 1;
        }
        value = recursiveMemo(n-1)+recursiveMemo(n-2);
        memo.put(n,value);
        return value;
    }

    /**
     * 动态规划
     * @param n
     * @return
     */
    private int recursiveDp(int n) {
        if(n==0){
            return 0;
        }
        if(n==1){
            return 1;
        }
        int[] dp = new int[n+1];
        dp[1]=1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i-1]+dp[i-2];
        }
        return dp[n];
    }

    /**
     * 动态规划 压缩dp
     * @param n
     * @return
     */
    private int recursiveDpCompress(int n) {
        if(n==0){
            return 0;
        }
        if(n==1){
            return 1;
        }
        int pre = 0;
        int cur = 1;
        for (int i = 2; i <= n; i++) {
            int sum = pre+cur;
            pre = cur;
            cur = sum;
        }
        return cur;
    }
}