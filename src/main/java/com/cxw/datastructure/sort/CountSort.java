package com.cxw.datastructure.sort;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author chengxuwei
 * @date 2020-08-20 10:11
 * @description 计数排序
 *  适用于 数组取值范围较小的情况
 *  eg: 数组里有100个随机数，取值范围为从0到20，要求最快排出顺序
 */
public class CountSort {
    public static void main(String[] args) {
        int[] ary = getArray(5,10);
        System.out.println(Arrays.toString(ary));
        countSort(ary,5);
        System.out.println(Arrays.toString(ary));
    }

    private static void countSort(int[] ary,int range) {
        int[] rangeAry = new int[range];
        for (int i = 0; i < ary.length; i++) {
            rangeAry[ary[i]] = ++rangeAry[ary[i]];
        }
        int index = 0;
        for (int i = 0; i < rangeAry.length; i++) {
            int count = rangeAry[i];
            for (int j = 0; j < count; j++) {
                ary[index++] = i;
            }
        }
    }

    private static int[] getArray(int range,int size) {
        int[] ary = new int[size];
        for (int i = 0; i < size; i++) {
            ary[i] = ThreadLocalRandom.current().nextInt(range);
        }
        return ary;
    }
}