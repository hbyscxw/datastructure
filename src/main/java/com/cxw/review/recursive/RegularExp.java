package com.cxw.review.recursive;

/**
 * @author chengxuwei
 * @date 2020-12-02 11:08
 * @description 给定一个字符串 (s) 和一个字符模式 (p)。实现支持 '.' 和 '*' 的正则表达式匹配。
 */
public class RegularExp {
    public static void main(String[] args) {
        String str = "aab";
        String exp = "c*a*b";
        char[] strAry = str.toCharArray();
        char[] expAry = exp.toCharArray();
        boolean res = process(strAry,expAry,0,0);
        System.out.println(res);
        //res = dpMatch(strAry,expAry);
        System.out.println(res);
    }

    private static boolean process(char[] strAry, char[] expAry, int i, int j) {
        if(expAry.length == j){
            return strAry.length == i;
        }
        //还有一位
        if(j+1==expAry.length&&expAry[j+1]!='*'){
            if(i==strAry.length){
                return false;
            }
            if(strAry[i]!=expAry[j]&&expAry[j]!='.'){
                return false;
            }
            return process(strAry,expAry,i+1,j+1);
        }
        //exp j+1 是*
        while((i!=strAry.length)&&(strAry[i]==expAry[j]&&expAry[j]=='.')){
            if(process(strAry,expAry,i,j+2)){
                return true;
            }else{
                i++;
            }
        }
        return false;
    }
}