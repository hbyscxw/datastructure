package com.cxw.labuladong.dp;

/**
 * @author chengxuwei
 * @date 2021-03-02 16:15
 * @description 编辑距离
 * https://leetcode-cn.com/problems/edit-distance/
 * 解析
 * https://labuladong.gitbook.io/algo-en/v/master/dong-tai-gui-hua-xi-lie/zi-xu-lie-lei-xing-wen-ti/bian-ji-ju-li
 * if(s1[i]==s2[j]){
 *     1.啥都不做
 *     2.i j同时移动一个字符
 * }else{
 *     1.插入一个字符
 *     2.删除一个字符
 *     3.替换一个字符
 * }
 */
public class EditDistance {
    public static void main(String[] args) {
        String word1 = "horse", word2 = "ros";
        EditDistance ed = new EditDistance();
        int res = ed.minDistance2(word1, word2);
        System.out.println(res);
    }
    public int minDistance(String word1, String word2) {
        return violenceRecursive(word1,word2,word1.length()-1,word2.length()-1);
    }

    /**
     * 暴力递归
     * @param word1
     * @param word2
     * @param i
     * @param j
     * @return
     */
    public int violenceRecursive(String word1, String word2,int i,int j) {
        if(i<0){
            return j+1;
        }
        if(j<0){
            return i+1;
        }
        int res = 0;
        if(word1.charAt(i)==word2.charAt(j)){
//            1.啥都不做
//            2.i j同时移动一个字符
            res = violenceRecursive(word1,word2,--i,--j);
        }else{
//            1.插入一个字符
            int iRes = violenceRecursive(word1,word2,i,--j)+1;
//            2.删除一个字符
            int dRes = violenceRecursive(word1,word2,--i,j)+1;
//            3.替换一个字符
            int cRes = violenceRecursive(word1,word2,--i,--j)+1;
            res = min(iRes,dRes,cRes);
        }
        return res;
    }

    /**
     * 动态规划
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance2(String word1, String word2) {
        int[][] dp = new int[word1.length()+1][word2.length()+1];
        for (int i = 1; i <= word1.length(); i++) {
            dp[i][0]=i;
        }
        for (int i = 1; i <= word2.length(); i++) {
            dp[0][i]=i;
        }
        for (int i = 1; i <= word1.length(); i++) {
            for (int j = 1; j <= word2.length(); j++) {
                if(word1.charAt(i-1)==word2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1];
                }else{
//                  1.插入一个字符
                    int iRes = dp[i][j-1]+1;
//                  2.删除一个字符
                    int dRes = dp[i-1][j]+1;
//                  3.替换一个字符
                    int cRes = dp[i-1][j-1]+1;
                    dp[i][j] = min(iRes,dRes,cRes);
                }
            }
        }
        return dp[word1.length()][word2.length()];
    }


    private int min(int a, int b, int c) {
        return Math.min(a,Math.min(b,c));
    }

}