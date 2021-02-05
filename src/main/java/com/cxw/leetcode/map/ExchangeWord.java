package com.cxw.leetcode.map;

import java.util.*;

/**
 * @author chengxuwei
 * @date 2021-01-11 18:37
 * @description  交换字符串中的元素
 * 给你一个字符串 s，以及该字符串中的一些「索引对」数组 pairs，其中 pairs[i] = [a, b] 表示字符串中的两个索引（编号从 0 开始）。
 *
 * 你可以 任意多次交换 在 pairs 中任意一对索引处的字符。
 *
 * 返回在经过若干次交换后，s 可以变成的按字典序最小的字符串。
 *
 *  
 *
 * 示例 1:
 *
 * 输入：s = "dcab", pairs = [[0,3],[1,2]]
 * 输出："bacd"
 * 解释：
 * 交换 s[0] 和 s[3], s = "bcad"
 * 交换 s[1] 和 s[2], s = "bacd"
 * 示例 2：
 *
 * 输入：s = "dcab", pairs = [[0,3],[1,2],[0,2]]
 * 输出："abcd"
 * 解释：
 * 交换 s[0] 和 s[3], s = "bcad"
 * 交换 s[0] 和 s[2], s = "acbd"
 * 交换 s[1] 和 s[2], s = "abcd"
 * 示例 3：
 *
 * 输入：s = "cba", pairs = [[0,1],[1,2]]
 * 输出："abc"
 * 解释：
 * 交换 s[0] 和 s[1], s = "bca"
 * 交换 s[1] 和 s[2], s = "bac"
 * 交换 s[0] 和 s[1], s = "abc"
 *  
 *
 * 提示：
 *
 * 1 <= s.length <= 10^5
 * 0 <= pairs.length <= 10^5
 * 0 <= pairs[i][0], pairs[i][1] < s.length
 * s 中只含有小写英文字母
 *
 * 链接：https://leetcode-cn.com/problems/smallest-string-with-swaps
 */
public class ExchangeWord {
    public static void main(String[] args) {
        String s = "dcab";
        //"dcab"
        //[[0,3],[1,2],[0,2]]
        List<List<Integer>> pairs = new ArrayList<>();
        List<Integer> list1 = new ArrayList<>();
        list1.add(0);
        list1.add(3);
        pairs.add(list1);
        List<Integer> list2 = new ArrayList<>();
        list2.add(1);
        list2.add(2);
        pairs.add(list2);
        List<Integer> list3 = new ArrayList<>();
        list3.add(0);
        list3.add(2);
        pairs.add(list3);
        ExchangeWord ew = new ExchangeWord();
        String s1 = ew.smallestStringWithSwaps(s, pairs);
        System.out.println(s1);
    }
    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        Collections.sort(pairs,(o1,o2)->{
            if(o1.size()>o2.size()){
                return 1;
            }
            for (int i = 0; i < o1.size(); i++) {
                Integer v1 = o1.get(i);
                Integer v2 = o2.get(i);
                if(v1.equals(v2)){
                    continue;
                }else{
                    return v1.compareTo(v2);
                }
            }
            return 0;
        });
        for (List<Integer> pair : pairs) {
            if(pair.get(0).equals(pair.get(1))){
                pairs.remove(pair);
            }
        }
        Set<Set<Integer>> allSet = new HashSet<>();
        Map<Character, Set<Integer>> map = new HashMap<>();
        for (List<Integer> pair : pairs) {
            Set<Integer> set = null;
            for (Integer p : pair) {
                Character c = s.charAt(p);
                if(map.get(c)!=null){
                    set = map.get(c);
                }
            }
            if(set==null){
                set = new HashSet<>();
                allSet.add(set);
            }
            for (Integer p : pair) {
                Character c = s.charAt(p);
                set.add(p);
                map.put(c,set);
            }
        }
        char[] newStrAry = new char[s.length()];
        for (Set<Integer> set : allSet) {
            List<Integer> list = new ArrayList<>(set);
            List<Integer> indexList= new ArrayList<>();
            List<Character> charList= new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                Integer index = list.get(i);
                indexList.add(index);
                char c = s.charAt(index);
                charList.add(c);
            }
            Collections.sort(indexList);
            Collections.sort(charList);
            for (int i = 0; i < charList.size(); i++) {
                Integer index = indexList.get(i);
                char c = charList.get(i);
                newStrAry[index] = c;
            }
        }
        return String.valueOf(newStrAry);
    }
}