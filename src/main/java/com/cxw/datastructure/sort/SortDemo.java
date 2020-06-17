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

    private static void shellSort(int[] ary) {

    }
    private static void insertSort(int[] ary) {
        for (int i = 1; i < ary.length; i++) {
            int insertValue = ary[i];
            boolean lt = false;
            int index = 0;
            for (int j = i-1; j >=0 ; j--) {
                if(ary[j]>insertValue){
                    ary[j+1]=ary[j];
                    lt = true;
                }else{
                    index = j+1;
                    break;
                }
            }
            if(lt) {
                ary[index] = insertValue;
            }
        }
    }

    /**
     * 算法时间复杂度 O(n^2) 交换过程比冒泡少，比冒泡快
     * @param ary
     */
    public static void selectSort(int[] ary){
        for (int i = 0; i < ary.length-1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < ary.length; j++) {
                //第一个元素与后面相比较
                if(ary[minIndex]>ary[j]){
                    minIndex = j;
                }
            }
            //发生变化才交换
            if(minIndex!=i){
                swap(ary,i,minIndex);
            }
        }

    }
    /**
     * 算法时间复杂度 O(n^2)
     * @param ary
     */
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