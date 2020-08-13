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
        insertSort(ary);
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
            int temp = ary[i];
            int j = 0;
            for (j = i-1; j >=0 && ary[j]>temp; j--) {
                ary[j+1] = ary[j];
            }
            ary[j+1] = temp;
        }
    }

    public static void mergeSort(int[] ary){
        doMergeSort(ary,0,ary.length-1);
    }

    private static void doMergeSort(int[] ary,int left,int right){
        if(left == right){
            return;
        }
        int mid = (left+right)/2;
        doMergeSort(ary,left,mid);
        doMergeSort(ary,mid+1,right);
        merge(ary,left,mid,right);
    }

    private static void merge(int[] ary, int left,int mid, int right) {
        int[] temp = new int[right-left+1];
        int tIndex = 0;
        int l = left;
        int r = mid+1;
        while(l<=mid&&r<=right){
            if(ary[l]<=ary[r]){
                temp[tIndex++] = ary[l++];
            }else{
                temp[tIndex++] = ary[r++];
            }
        }
        while(l<=mid){
            temp[tIndex++] = ary[l++];
        }
        while(r<=right){
            temp[tIndex++] = ary[r++];
        }
        //copy回原数组
        System.arraycopy(temp,0,ary,left,temp.length);
    }

    public static void swap(int[] ary,int i,int j){
        ary[i] = ary[i] ^ ary[j];
        ary[j] = ary[i] ^ ary[j];
        ary[i] = ary[i] ^ ary[j];
    }
}