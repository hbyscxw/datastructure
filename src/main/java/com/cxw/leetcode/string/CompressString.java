package com.cxw.leetcode.string;

/**
 * @author chengxuwei
 * @date 2020-08-07 19:29
 * @description 压缩字符串
 * https://leetcode-cn.com/problems/string-compression-ii/
 */
public class CompressString {
    public static void main(String[] args) {
        String str = getLengthOfOptimalCompression("aaabcccd", 0);
        System.out.println(str);
    }
    public static String getLengthOfOptimalCompression(String s, int k) {
        char c = s.charAt(0);
        StringBuilder newString = new StringBuilder(c);
        int index = 1;
        int count = 1;
        while(index<s.length()){
            if(c==s.charAt(index)){
                count++;
            }else{
                newString.append(c);
                if(count>1){
                    newString.append(count);
                }
                c = s.charAt(index);
                count = 1;
            }
            index++;
        }
        return newString.toString();
    }
}