package com.cxw.datastructurenew.recursive;

import java.util.ArrayList;
import java.util.List;


/**
 * @author chengxuwei
 * @create 2022/3/21 14:46
 * @desc  打印字符串子序列（子集）
 */
public class AllSubSequenceString {
    public static void main(String[] args) {
        String str = "abc";
        process(str.toCharArray(),0,new ArrayList<>());
    }
    public static void process(char[] ary, int i, List<Character> res) {
        if(i == ary.length){
            printAry(res);
            return;
        }
        List<Character> r1 = new ArrayList<>(res);
        r1.add(ary[i]);
        process(ary,i+1,r1);//要
        List<Character> r2 = new ArrayList<>(res);
        process(ary,i+1,r2);//不要
    }

    private static void printAry(List<Character> res) {
        for (Character re : res) {
            System.out.print(re);
        }
        System.out.println();
    }
}
