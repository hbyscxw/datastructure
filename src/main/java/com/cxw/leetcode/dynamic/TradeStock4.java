package com.cxw.leetcode.dynamic;

import java.util.Arrays;

/**
 * @author chengxuwei
 * @date 2020-12-17 15:37
 * @description
 * 188. 买卖股票的最佳时机 IV
 * 给定一个整数数组 prices ，它的第 i 个元素 prices[i] 是一支给定的股票在第 i 天的价格。
 *
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。
 *
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 *
 *
 *
 * 示例 1：
 *
 * 输入：k = 2, prices = [2,4,1]
 * 输出：2
 * 解释：在第 1 天 (股票价格 = 2) 的时候买入，在第 2 天 (股票价格 = 4) 的时候卖出，这笔交易所能获得利润 = 4-2 = 2 。
 * 示例 2：
 *
 * 输入：k = 2, prices = [3,2,6,5,0,3]
 * 输出：7
 * 解释：在第 2 天 (股票价格 = 2) 的时候买入，在第 3 天 (股票价格 = 6) 的时候卖出, 这笔交易所能获得利润 = 6-2 = 4 。
 *      随后，在第 5 天 (股票价格 = 0) 的时候买入，在第 6 天 (股票价格 = 3) 的时候卖出, 这笔交易所能获得利润 = 3-0 = 3 。
 *
 *
 * 提示：
 *
 * 0 <= k <= 109
 * 0 <= prices.length <= 1000
 * 0 <= prices[i] <= 1000
 *
 * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iv/
 */
public class TradeStock4 {
//    public int maxProfit(int k, int[] prices) {
//        if(prices.length<2){
//            return 0;
//        }
//        int n = prices.length;
//        //最多能交易k次
//        k = Math.max(k,n/2);
//        //buy[i][j]=max{buy[i−1][j],sell[i−1][j]−price[i]}
//        int[][] buy = new int[n][k+1];
//        //sell[i][j]=max{buy[i−1][j]+price[i],sell[i−1][j]}
//        int[][] sell = new int[n][k+1];
//        buy[0][0] = -prices[0];
//        sell[0][0] = 0;
//        for (int i = 1; i <= k; ++i) {
//            buy[0][i] = sell[0][i] = Integer.MIN_VALUE/2;
//        }
//        for (int i = 1; i < n; ++i) {
//            buy[i][0] = Math.max(buy[i-1][0],sell[i-1][0]-prices[i]);
//            for (int j = 1; j <= k; ++j) {
//                buy[i][j] = Math.max(buy[i-1][j],sell[i-1][j]-prices[i]);
//                sell[i][j] = Math.max(sell[i-1][j],buy[i-1][j-1]+prices[i]);
//            }
//        }
//        return Arrays.stream(sell[n-1]).max().getAsInt();
//    }
    public int maxProfit(int k, int[] prices) {
        if(prices.length<2){
            return 0;
        }
        int n = prices.length;
        //最多能交易k次
        k = Math.max(k,n/2);
        // 状态表示数组，三个维度分别代表：第几天，手上是否有股票 0没有 1有，还剩下多少次买卖机会
        // 数组值表示当前还有多少钱
        int[][][] dp = new int[n][2][k+1];
        // 设置初始值，第一天手上有股票的状态，就是买入第一天价格的值
        for (int i = 0; i <= k; i++) {
            dp[0][1][i] = -prices[0];
        }
        // 状态转移，遍历天数
        for (int i = 1; i < n; i++) {
            // 枚举当天所有可能买卖次数的状态
            for (int j = 0; j <= k; j++) {
                //当前不持有股票的状态转移
                if(j<k){
                    //当前不持有股票 1.昨天也没有股票  2.昨天有股票今天卖出了
                    //如果今天卖出了今天还剩j次，那么昨天就是有j+1次机会
                    dp[i][0][j] = Math.max(dp[i-1][0][j],dp[i-1][1][j+1]+prices[i]);
                }else{
                    //没有机会了
                    dp[i][0][j] = dp[i-1][0][j];
                }
                //当前持有股票 1.昨天也持有股票  2.昨天没有股票今天买入了
                dp[i][1][j] = Math.max(dp[i-1][1][j],dp[i-1][0][j]-prices[i]);
            }
        }
        return Arrays.stream(dp[n - 1][0]).max().getAsInt();
    }
}


