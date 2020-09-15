package com.cxw.leetcodecourse.greedy;

import java.util.Arrays;

/**
 * 将一个字符串数组连接起来字典序最小
 * ["ba","b"] -> bab
 */
public class DictSort {
    public static void main(String[] args) {
        String[] ary = {"ba","b"};
        Arrays.sort(ary,(String s1,String s2)->(s1+s2).compareTo(s2+s1));
        System.out.println(Arrays.toString(ary));
    }
}
