package com.cxw.leetcodecourse.kmp;

/**
 * @author chengxuwei
 * @date 2020-09-22 09:33
 * @description kmp算法
 */
public class KMPAlgorithm {
    public static void main(String[] args) {
        String str1 = "abcabd";
        String str2 = "abd";
        int index = kmp(str1,str2);
        System.out.println(index);
    }

    private static int kmp(String str1, String str2) {
        int[] nextAry = getNextAry(str2);
        for (int i = 0,j=0; i < str1.length(); i++) {
            while(j>0&&str1.charAt(i)!=str2.charAt(j)){
                j = nextAry[j-1];
            }
            if(str1.charAt(i)==str2.charAt(j)){
                j++;
            }
            if(j==str2.length()){
                return i-j+1;
            }
        }
        return -1;
    }
    private static int[] getNextAry(String str) {
        int[] next = new int[str.length()];
        for (int i = 1,j=0; i < str.length(); i++) {
            while(j>0&&str.charAt(i)!=str.charAt(j)){
                j = next[j-1];
            }
            if(str.charAt(i)==str.charAt(j)){
                j++;
            }
            next[i] = j;
        }
        return next;
    }
}