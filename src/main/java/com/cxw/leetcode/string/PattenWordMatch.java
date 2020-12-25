package com.cxw.leetcode.string;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chengxuwei
 * @date 2020-12-16 16:53
 * @description 单词规律
 * 给定一种规律 pattern 和一个字符串 str ，判断 str 是否遵循相同的规律。
 *
 * 这里的 遵循 指完全匹配，例如， pattern 里的每个字母和字符串 str 中的每个非空单词之间存在着双向连接的对应规律。
 *
 * 示例1:
 *
 * 输入: pattern = "abba", str = "dog cat cat dog"
 * 输出: true
 * 示例 2:
 *
 * 输入:pattern = "abba", str = "dog cat cat fish"
 * 输出: false
 * 示例 3:
 *
 * 输入: pattern = "aaaa", str = "dog cat cat dog"
 * 输出: false
 * 示例 4:
 *
 * 输入: pattern = "abba", str = "dog dog dog dog"
 * 输出: false
 * 说明:
 * 你可以假设 pattern 只包含小写字母， str 包含了由单个空格分隔的小写字母。    
 *
 * 链接：https://leetcode-cn.com/problems/word-pattern
 */
public class PattenWordMatch {
    public static void main(String[] args) {
        PattenWordMatch p = new PattenWordMatch();
        boolean f = p.wordPattern("abba","dog dog dog dog");
        System.out.println(f);
    }
    public boolean wordPattern(String pattern, String s) {
        char[] pAry = pattern.toCharArray();
        String[] sAry = s.split(" ");
        if(pAry.length!=sAry.length){
            return false;
        }
        Map<String,String> map = new HashMap<>();
        Map<String,String> map2 = new HashMap<>();
        for(int i=0;i<sAry.length;i++){
            if(map.containsKey(String.valueOf(pAry[i]))){
                if(!map.get(String.valueOf(pAry[i])).equals(sAry[i])){
                    return false;
                }
            }else{
                if(map2.containsKey(sAry[i])){
                    return false;
                }
                map.put(String.valueOf(pAry[i]),sAry[i]);
                map2.put(sAry[i],String.valueOf(pAry[i]));
            }
        }
        return true;
    }
}