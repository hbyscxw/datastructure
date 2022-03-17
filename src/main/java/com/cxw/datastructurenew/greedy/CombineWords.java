package com.cxw.datastructurenew.greedy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author chengxuwei
 * @create 2022/3/15 16:12
 * @desc 多个字符串结合，字典序最小
 */
public class CombineWords {
    public static void main(String[] args) {
        String[] ary = {"ab","a","ba","b"};
        Arrays.sort(ary,new WordCompare());
        System.out.println(Arrays.toString(ary));
    }
    static class WordCompare implements Comparator<String>{
        @Override
        public int compare(String o1, String o2) {
            return (o1+o2).compareTo(o2+o1);
        }
    }
}
