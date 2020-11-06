package com.cxw.leetcodecourse.dynamic;

/**
 * @author chengxuwei
 * @date 2020-11-06 10:46
 * @description
 * 给定一个字符串 (s) 和一个字符模式 (p)。实现支持 '.' 和 '*' 的正则表达式匹配。
 *
 * '.' 匹配任意单个字符。
 *
 * '*' 匹配零个或多个前面的元素。
 *
 * 匹配应该覆盖整个字符串 (s) ，而不是部分字符串。
 *
 * 说明:
 *
 * s 可能为空，且只包含从 a-z 的小写字母。
 * p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。
 *
 * https://www.cnblogs.com/mfrank/p/10472663.html
 */
public class RegularExpression {
    public static void main(String[] args) {
        String str = "aab";
        String exp = "c*a*b";
        char[] strAry = str.toCharArray();
        char[] expAry = exp.toCharArray();
        boolean res = process2(strAry,expAry,0,0);
        System.out.println(res);
        res = dpMatch(strAry,expAry);
        System.out.println(res);
    }

    private static boolean process(char[] strAry, char[] expAry, int i, int j) {
        //exp到结尾了
        if(expAry.length==j){
            return strAry.length==i;
        }
        //j后面还有字符
        if(j+1==expAry.length||expAry[j+1]!='*'){
            if(i==strAry.length){
                return false;
            }
            if(strAry[i]!=expAry[j]&&expAry[j]!='.'){
                return false;
            }
            return process(strAry, expAry, i+1, j+1);
        }
        //exp j+1的位置，不仅有字符而且还是'*'
        while(i!=strAry.length&&(strAry[i]==expAry[j]||expAry[j]=='.')){
            if(process(strAry,expAry,i,j+2)){
                return true;
            }
            i++;
        }
        return process(strAry,expAry,i,j+2);
    }
    public static boolean dpMatch(char[] strAry, char[] expAry){
        if(strAry==null||strAry.length==0||expAry==null||expAry.length==0){
            return false;
        }
        boolean[][] dp = initDp(strAry,expAry);
        for (int i = strAry.length-1; i >= 0 ; i--) {
            for (int j = expAry.length-2; j >=0 ; j--) {
                if(expAry[j+1]!='*'){
                    dp[i][j] = (strAry[i]==expAry[j]||expAry[j]=='.')&&dp[i+1][j+1];
                }else{
                    int si = i;
                    while(si!=strAry.length&&(strAry[si]==strAry[j]||expAry[j]=='.')){
                        if(dp[si][j+2]){
                            dp[i][j] = true;
                            break;
                        }
                        si++;
                    }
                    if (dp[i][j] != true) {
                        dp[i][j] = dp[si][j + 2];
                    }
                }
            }
        }
        return dp[0][0];
    }

    private static boolean[][] initDp(char[] strAry, char[] expAry) {
        boolean[][] dp = new boolean[strAry.length+1][expAry.length+1];
        dp[strAry.length][expAry.length] = true;
        for (int i = expAry.length-2; i >-1 ; i-=2) {
            if(expAry[i]!='*'&&expAry[i+1]=='*'){
                dp[strAry.length][i]=true;
            }else {
                break;
            }
        }
        if(strAry.length>0&&expAry.length>0){
            if(expAry[expAry.length-1]=='.'||strAry[strAry.length-1]==expAry[expAry.length-1]){
                dp[strAry.length-1][expAry.length-1] = true;
            }
        }
        return dp;
    }


    private static boolean process2(char[] strAry, char[] expAry, int i, int j) {
        //exp到结尾了
        if(expAry.length==j){
            return strAry.length==i;
        }
        boolean match = strAry.length>i&&(strAry[i]==expAry[j]||expAry[j]=='.');
        if(expAry.length>j+1&&expAry[j+1]=='*'){
            return process2(strAry,expAry,i,j+2)||(match&&process2(strAry,expAry,i+1,j));
        }else{
            return match&&process2(strAry,expAry,i+1,j+1);
        }
    }

    public static boolean dpMatch2(char[] strAry, char[] expAry){
        boolean[][] dp = new boolean[strAry.length+1][expAry.length+1];
        dp[strAry.length][expAry.length] = true;
        for (int i = strAry.length; i >=0 ; i--) {
            for (int j = expAry.length-1; j >=0 ; j--) {
                boolean match = strAry.length>i&&(strAry[i]==expAry[j]||expAry[j]=='.');
                if(expAry.length>j+1&&expAry[j+1]=='*'){
                    dp[i][j] =dp[i][j+2]||(match&&dp[i+1][j]);
                }else{
                    dp[i][j] = match&&dp[i+1][j+1];
                }
            }
        }
        return dp[0][0];
    }
}