package com.cxw.leetcode.dynamic;

import java.util.Arrays;

/**
 * @author chengxuwei
 * @date 2021-03-08 18:48
 * @description 分隔回文串
 * https://leetcode-cn.com/problems/palindrome-partitioning-ii/
 */
public class PalindromePartitioning2 {
    public static void main(String[] args) {
        String s = "abcdefgh";
        PalindromePartitioning2 pp = new PalindromePartitioning2();
        pp.minCut(s);
    }

    public int minCut(String s) {
        int n = s.length();
        // i到j 是否是回文串
        boolean[][] db = new boolean[n][n];
        for (boolean[] b : db) {
            Arrays.fill(b,true);
        }
        for (int i = n-1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                if(i==j){
                    db[i][j] = true;
                    continue;
                }
                if(s.charAt(i)==s.charAt(j)){
                    db[i][j] = db[i+1][j-1];
                }else{
                    db[i][j] = false;
                }
            }
        }
        int[] f = new int[n];
        for (int j = 1; j < n; j++) {
            if(db[0][j]){
                f[j]= 0;
            }else{
                f[j] = f[j-1]+1;
                for (int i = 1; i < j; i++) {
                    if(db[i][j]){
                        f[j] = Math.min(f[j],f[i-1]+1);
                    }
                }
            }
        }
        return f[n-1];
    }
}