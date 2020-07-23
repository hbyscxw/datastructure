package com.cxw.algorithm.kmp;

public class KMPMatch {
    public static void main(String[] args) {
        String str1 = "12314123";
        String str2 = "231";
        int[] next = kmpNext(str2);
        int index = kmpMatch(str1,str2,next);
        System.out.println(index);
    }

    private static int[] kmpNext(String str){
        char[] chars = str.toCharArray();
        int[] kmpAry = new int[str.length()];
        kmpAry[0] = 0;
        for (int i = 1, j = 0; i < str.length(); i++) {
            //kmp 算法核心
            while(j>0&&chars[i]!=chars[j]){
                j=kmpAry[j-1];
            }
            if(chars[i]==chars[j]){
                j++;
            }
            kmpAry[i]=j;
        }
        return kmpAry;
    }


    private static int kmpMatch(String str1, String str2,int[] next) {
        char[] ary1 = str1.toCharArray();
        char[] ary2 = str2.toCharArray();
        for (int i = 0,j = 0; i < ary1.length; i++) {
            while(j>0&&ary1[i]!=ary2[j]){
                j = next[j-1];
            }
            if(ary1[i]==ary2[j]){
                j++;
            }
            if(j==ary2.length){
                return i-j+1;
            }
        }
        return -1;
    }
}
