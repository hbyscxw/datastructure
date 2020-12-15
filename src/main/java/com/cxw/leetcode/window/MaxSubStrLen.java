package com.cxw.leetcode.window;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author chengxuwei
 * @date 2020-12-08 10:32
 * @description 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/
 */
public class MaxSubStrLen {
    public static void main(String[] args) {
//        String str = "pwwkew";
        String str = "abcabcab";
        int len = lengthOfLongestSubstring(str);
        System.out.println(len);
    }

    public static int lengthOfLongestSubstring(String s) {
        char[] chars = s.toCharArray();
        int maxlen = 0;
        for (int i = 0; i < chars.length; i++) {
            int len = charMaxSubAry(chars,i);
            maxlen = Math.max(maxlen,len);
        }
        return maxlen;
    }

    private static int charMaxSubAry(char[] chars, int i) {
        Set<Character> set = new HashSet<>();
        //set.add(chars[i]);
        int j = i;
        for (; j < chars.length; j++) {
            if(set.contains(chars[j])){
                break;
            }else{
                set.add(chars[j]);
            }
        }
        return j-i;
    }
}