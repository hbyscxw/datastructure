package com.cxw.algorithm.kmp;

/**
 * 字符串暴力匹配
 */
public class ViolenceMatch {
    public static void main(String[] args) {
        String str1 = "12314123";
        String str2 = "231";
        int index = violenceMatch(str1,str2);
        System.out.println(index);
    }

    private static int violenceMatch(String str1, String str2) {
        char[] ary1 = str1.toCharArray();
        char[] ary2 = str2.toCharArray();
        int i = 0;
        int j = 0;
        while(i<ary1.length&&j<ary2.length){
            if(ary1[i]==ary2[j]){
                i++;
                j++;
            }else{
                i = i-j+1;
                j=0;
            }
        }
        if(j==ary2.length){
            return i-j;
        }
        return -1;
    }
}
