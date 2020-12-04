package com.cxw.review.recursive;

/**
 * @author chengxuwei
 * @date 2020-12-02 15:30
 * @description 打印字符串全排列
 */
public class StrTotalOrder {
    public static void main(String[] args) {
        String str = "abc";
        process("",str);
    }

    private static void process(String finishStr, String remainStr) {
        if(remainStr==null||"".equals(remainStr)){
            System.out.println(finishStr);
            return;
        }
        for (int i = 0; i < remainStr.length(); i++) {
            char c = remainStr.charAt(i);
            String newRemainStr = null;
            if(i==0){
                newRemainStr = remainStr.substring(1);
            }else if(i==remainStr.length()-1){
                newRemainStr = remainStr.substring(0,remainStr.length()-1);
            }else{
                newRemainStr = remainStr.substring(0,i)+remainStr.substring(i+1);
            }
            process(finishStr+c,newRemainStr);
        }
    }


}