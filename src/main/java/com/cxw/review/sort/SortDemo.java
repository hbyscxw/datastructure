package com.cxw.review.sort;

import java.util.Arrays;

/**
 * @author chengxuwei
 * @date 2020-08-12 09:45
 * @description 排序算法
 */
public class SortDemo {
    public static void main(String[] args) {
        int[] ary = {1,20,10};
        selectionSort(ary);
        System.out.println(Arrays.toString(ary));
    }
    public static void bubbleSort(int[] ary){
        for (int i = 0; i < ary.length-1; i++) {
            for (int j = 0; j < ary.length-1-i; j++) {
                if(ary[j+1]<ary[j]){
                    swap(ary,j,j+1);
                }
            }
        }
    }

    public static void selectionSort(int[] ary){
        for (int i = 0; i < ary.length; i++) {
            int minIndex = i;
            for (int j = i+1; j < ary.length; j++) {
                if(ary[j]<ary[minIndex]){
                    minIndex = j;
                }
            }
            if(minIndex!=i) {
                swap(ary, minIndex, i);
            }
        }
    }

    public static void insertSort(int[] ary){
        for (int i = 1; i < ary.length; i++) {
            int index = i-1;
            int insertValue = ary[i];
            while(index>=0&&ary[index]>insertValue){
                ary[index+1] = ary[index];
                index--;
            }
            ary[index+1] = insertValue;
        }
    }

    public static void swap(int[] ary,int i,int j){
        ary[i] = ary[i] ^ ary[j];
        ary[j] = ary[i] ^ ary[j];
        ary[i] = ary[i] ^ ary[j];
    }
}