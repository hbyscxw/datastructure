package com.cxw.labuladong.dp;

/**
 * @author chengxuwei
 * @date 2021-03-01 16:02
 * @description 5. 最长回文子串
 * https://leetcode-cn.com/problems/longest-palindromic-substring/
 *
 * labuladong解析
 * https://mp.weixin.qq.com/s?__biz=MzAxODQxMDM0Mw==&mid=2247484471&idx=1&sn=7c26d04a1f035770920d31377a1ebd42&chksm=9bd7fa3faca07329189e9e8b51e1a665166946b66b8e8978299ba96d5f2c0d3eafa7db08b681&scene=21#wechat_redirect
 */
public class LongestPalindromicSubstring {
    public static void main(String[] args) {
        String str = "babad";
        LongestPalindromicSubstring lps = new LongestPalindromicSubstring();
        String s = lps.longestPalindrome(str);
        System.out.println(s);
    }
    public String longestPalindrome(String s) {
        String longestStr = "";
        for(int i=0;i<s.length();i++){
            String subStr1 = palindrome(s,i);
            String subStr2 = palindrome(s,i,i+1);
            longestStr = longestStr.length()>=subStr1.length()?longestStr:subStr1;
            longestStr = longestStr.length()>=subStr2.length()?longestStr:subStr2;
        }
        return longestStr;
    }
    public String palindrome(String s,int i) {
        int l = i-1;
        int r = i+1;
        while(l>=0&&r<=s.length()-1&&s.charAt(l)==s.charAt(r)){
            l--;
            r++;
        }
        if(l == i-1){
            return String.valueOf(s.charAt(i));
        }else{
            return s.substring(l+1,r);
        }
    }
    public String palindrome(String s,int left,int right) {
        int l = left;
        int r = right;
        while(l>=0&&r<=s.length()-1&&s.charAt(l)==s.charAt(r)){
            l--;
            r++;
        }
        if(l == left){
            return "";
        }else{
            return s.substring(l+1,r);
        }
    }
}