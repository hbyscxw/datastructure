package com.cxw.leetcodecourse.dynamic;

/**
 * @author chengxuwei
 * @date 2020-09-17 10:34
 * @description 打印子序列
 */
public class SubStrSequence {
    public static void main(String[] args) {
        String str = "abc";
        printSubStr(str);
    }

    private static void printSubStr(String str) {
        doPrintSubStr(str.toCharArray(),0,"");
    }

    private static void doPrintSubStr(char[] strAry, int i, String s) {
        if(i==strAry.length){
            System.out.println(s);
        }else{
            doPrintSubStr(strAry,i+1,s);
            doPrintSubStr(strAry,i+1,s+strAry[i]);
        }
    }
}