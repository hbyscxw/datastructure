package com.cxw.leetcode.string;

import java.util.HashSet;
import java.util.Set;

/**
 * @author chengxuwei
 * @date 2020-12-23 15:57
 * @description
 * 字符串中的第一个唯一字符
 * 给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。
 *
 *
 *
 * 示例：
 *
 * s = "leetcode"
 * 返回 0
 *
 * s = "loveleetcode"
 * 返回 2
 *
 *
 * 提示：你可以假定该字符串只包含小写字母。
 * https://leetcode-cn.com/problems/first-unique-character-in-a-string/
 */
public class UniqueChar {
    public static void main(String[] args) {
        UniqueChar uc = new UniqueChar();
        int r = uc.firstUniqChar("cc");
        System.out.println(r);
    }
    public int firstUniqChar(String s) {
        char[] chars = s.toCharArray();
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < chars.length; i++) {
            if(set.contains(chars[i])){
                continue;
            }
            int j = i+1;
            for (; j < chars.length; j++) {
                if(chars[i]==chars[j]){
                    set.add(chars[i]);
                    break;
                }
            }
            if(j==chars.length){
                return i;
            }
        }
        return -1;
    }
}