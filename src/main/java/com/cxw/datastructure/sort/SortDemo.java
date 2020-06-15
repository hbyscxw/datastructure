package com.cxw.datastructure.sort;

import java.util.Arrays;

/**
 * @author chengxuwei
 * @date 2020-06-15 11:30
 * @description 排序
 */
public class SortDemo {
    public static void main(String[] args) {
        int[] ary = {1,3,2,6,7,8};
        bubbleSort(ary);
        System.out.println(Arrays.toString(ary));
    }

    private static void bubbleSort(int[] ary) {
        for (int i = 0; i < ary.length-1; i++) {
            boolean changeFlag = false;
            for (int j = 0; j < ary.length-1-i; j++) {
                if(ary[j]>ary[j+1]){
                    changeFlag = true;
                    swap(ary,j,j+1);
                }
            }
            //如果一轮中没有交换，说明已经排好序
            if(!changeFlag){
                break;
            }
        }
    }

    private static void swap(int[] ary, int j, int i) {
        int temp = ary[i];
        ary[i] = ary[j];
        ary[j] = temp;
    }
}