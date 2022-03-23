package com.cxw.datastructurenew.recursive;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author chengxuwei
 * @create 2022/3/21 15:24
 * @desc 字符串 全排列
 */
public class FullPermutationString {
    public static void main(String[] args) {
        String ary = "abc";
        process(ary.toCharArray(),0);
    }

    public static void process(char[] ary, int i) {
        if(i == ary.length){
            System.out.println(String.valueOf(ary));
            return;
        }
        boolean[] visited = new boolean[26];
        for (int j = i; j < ary.length; j++) {
            if(!visited[ary[j]-'a']) { //有重复的路，直接不走
                visited[ary[j]-'a'] = true;
                swap(ary, i, j); //将i位置的字符与i后面的每个位置(j)字符交换
                process(ary, i + 1);
                swap(ary, i, j); //还原
            }
        }
    }


    private static void swap(char[] arr, int m, int n) {
        char temp = arr[m];
        arr[m] = arr[n];
        arr[n] = temp;
    }

}
