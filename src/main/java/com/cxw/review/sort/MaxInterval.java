package com.cxw.review.sort;

import java.util.Arrays;

/**
 * @author chengxuwei
 * @date 2020-08-20 09:21
 * @description
 *  有一个数组 用非比较排序的方法求数组的最大间隔值 要求时间O(n) 桶排序
 */
public class MaxInterval {
    public static void main(String[] args) {
        int[] ary = new int[]{21,1,22,32,31,4,43};
        int[] minAry = new int[ary.length+1];
        Arrays.fill(minAry,Integer.MIN_VALUE);
        int[] maxAry = new int[ary.length+1];
        Arrays.fill(maxAry,Integer.MIN_VALUE);
        boolean[] hasEle = new boolean[ary.length+1];
        int min = Integer.MIN_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < ary.length; i++) {
            if(min==Integer.MIN_VALUE||ary[i]<min){
                min = ary[i];
            }
            if(ary[i]>max){
                max = ary[i];
            }
        }
        int interval = (max-min)/ary.length;
        for (int i = 0; i < ary.length; i++) {
            int index = (ary[i] - min)/interval;
            hasEle[index] = true;
            if(minAry[index]==Integer.MIN_VALUE||minAry[index]>ary[i]){
                minAry[index] = ary[i];
            }
            if(maxAry[index]<ary[i]){
                maxAry[index] = ary[i];
            }
        }
        int maxInterval = Integer.MIN_VALUE;
        for (int i = 0; i < ary.length; i++) {
            if(!hasEle[i]){
                continue;
            }
            for (int j = i+1; j < ary.length+1; j++) {
                if(hasEle[j]){
                    if(maxInterval < minAry[j] - maxAry[i]){
                        maxInterval = minAry[j] - maxAry[i];
                    }
                    break;
                }
            }
        }
        System.out.println(maxInterval);
    }
}