package com.cxw.leetcodecourse.dynamic;


/**
 * @author chengxuwei
 * @date 2020-09-17 10:34
 * @description 打印全排列
 */
public class StrTotalOrdering {
    public static void main(String[] args) {
        String str = "abc";
        printTotalOrdering(str);
    }

    private static void printTotalOrdering(String str) {
        doPrintTotalOrdering("",str);
    }

    private static void doPrintTotalOrdering(String finishStr, String remainStr) {
        if(remainStr==null||remainStr.length()==0){
            System.out.println(finishStr);
            return;
        }
        for (int j = 0; j < remainStr.length(); j++) {
            char c = remainStr.charAt(j);
            String newRemainStr = null;
            if(j==0) {
                newRemainStr = remainStr.substring(1);
            }else if(j==remainStr.length()-1){
                newRemainStr = remainStr.substring(0,remainStr.length()-1);
            }else{
                newRemainStr = remainStr.substring(0,j)+remainStr.substring(j+1);
            }
            doPrintTotalOrdering(finishStr+c,newRemainStr);
        }
    }
}