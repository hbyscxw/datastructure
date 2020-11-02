package com.cxw.leetcodecourse.dynamic;

/**
 * @author chengxuwei
 * @date 2020-11-02 10:53
 * @description
 * 一个长度为N的路，1~N
 *
 * 一个机器人在M位置，他可以走P步，如果在1只能走右，在N只能走左，请问机器人走P步后他停在K位置上的走法有多少种。
 */
public class Walk {
    public static void main(String[] args) {
        int N = 10;
        int M = 1;
        int P = 20;
        int K = 3;
        int res  = walk(N,M,P,K);
        System.out.println(res);
        int res2  = walk2(N,M,P,K);
        System.out.println(res2);
    }

    private static int walk(int n, int m, int p, int k) {
        if(n<2||m<1||m>n||p<0||k<0||k>n){
            return 0;
        }
        if(p == 0){
            return m==k?1:0;
        }
        int count = 0;
        if(m==1){
            count = walk(n,m+1,p-1,k);
        }else if(m==n){
            count = walk(n,m-1,p-1,k);
        }else{
            count = walk(n,m+1,p-1,k)+walk(n,m-1,p-1,k);
        }
        return count;
    }

    private static int walk2(int n, int m, int p, int k) {
        if(n<2||m<1||m>n||p<0||k<0||k>n){
            return 0;
        }
        int[][] dp = new int[p+1][n+1];
        dp[0][k] = 1;
        for (int i = 1; i < p+1; i++) {
            for (int j = 1; j < n+1; j++) {
                dp[i][j] += j-1<1 ? 0:dp[i-1][j-1];
                dp[i][j] += j+1>n ? 0:dp[i-1][j+1];
            }
        }
        return dp[p][m];
    }
}