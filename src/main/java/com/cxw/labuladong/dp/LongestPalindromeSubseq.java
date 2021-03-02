package com.cxw.labuladong.dp;

/**
 * @author chengxuwei
 * @date 2021-03-02 10:43
 * @description 最长回文子序列
 * https://leetcode-cn.com/problems/longest-palindromic-subsequence/
 * labuladong解析
 * https://mp.weixin.qq.com/s?__biz=MzAxODQxMDM0Mw==&mid=2247484666&idx=1&sn=e3305be9513eaa16f7f1568c0892a468&chksm=9bd7faf2aca073e4f08332a706b7c10af877fee3993aac4dae86d05783d3d0df31844287104e&scene=21#wechat_redirect
 */
public class LongestPalindromeSubseq {
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }
        // 反着遍历保证正确的状态转移
        for (int i = n-1; i >=0 ; i--) {
            for (int j = i+1; j < n; j++) {
                if(s.charAt(i)==s.charAt(j)){
                    //如果它俩相等，那么它俩加上s[i+1..j-1]中的最长回文子序列就是s[i..j]的最长回文子序列：
                    dp[i][j] = dp[i+1][j-1]+2;
                }else{
                    //如果它俩不相等，说明它俩不可能同时出现在s[i..j]的最长回文子序列中，那么把它俩分别加入s[i+1..j-1]中，看看哪个子串产生的回文子序列更长即可：
                    dp[i][j] =  Math.max(dp[i+1][j],dp[i][j-1]);
                }
            }
        }
        return dp[0][n-1];
    }
}