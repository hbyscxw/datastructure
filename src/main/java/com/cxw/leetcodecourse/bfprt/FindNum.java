package com.cxw.leetcodecourse.bfprt;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author chengxuwei
 * @date 2020-09-25 16:43
 * @description 从n个元素中选出第k小或第k大的元素，同时也能选出前k小或前k大的所有元素。
 * 使用堆来做
 */
public class FindNum {
    public static void main(String[] args) {
        int[] ary = {1,32,4,32,56,33,12,35,44,13,15};
        int k = 5;
        int[] newAry = findMaxNum(ary,k);
        System.out.println(Arrays.toString(newAry));
    }

    private static int[] findMaxNum(int[] ary, int k) {
        if(k>=ary.length){
            return ary;
        }
        PriorityQueue<Integer> heap = new PriorityQueue<>((o1,o2)->o2-o1);
        for (int i = 0; i < ary.length; i++) {
            heap.add(ary[i]);
        }
        int[] newAry = new int[k];
        for (int i = 0; i < k; i++) {
            newAry[i]=heap.poll();
        }
        return newAry;
    }

}