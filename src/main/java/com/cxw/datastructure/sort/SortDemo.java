package com.cxw.datastructure.sort;

import java.util.Arrays;

/**
 * @author chengxuwei
 * @date 2020-06-15 11:30
 * @description 排序
 */
public class SortDemo {
    public static void main(String[] args) {
        int[] ary = {10,1,4,5,100,50,30,20,6,7,8};
        insertSort(ary);
        System.out.println(Arrays.toString(ary));
    }

    private static void shellChangeSort(int[] ary) {
        /*
        //第一轮
        for (int i = 3; i < ary.length; i++) {
            for (int j = i-3; j >=0 ; j-=3) {
                if(ary[j]>ary[j+3]){
                    swap(ary,j,j+3);
                }
            }
        }
        //第二轮
        for (int i = 1; i < ary.length; i++) {
            for (int j = i-1; j >=0 ; j-=1) {
                if(ary[j]>ary[j+1]){
                    swap(ary,j,j+1);
                }
            }
        }
        */

        for (int step = ary.length/2; step > 0 ; step/=2) {
            for (int i = step; i < ary.length; i++) {
                for (int j = i-step; j >=0; j-=step) {
                    if(ary[j]>ary[j+step]){
                        swap(ary,j,j+step);
                    }
                }
            }
        }
    }

    private static void shellInsertSort(int[] ary) {
        for (int step = ary.length/2; step > 0 ; step/=2) {
            for (int i = step; i < ary.length; i++) {
                int insertValue = ary[i];
                boolean lt = false;
                int index = 0;
                for (int j = i-step; j >=0; j-=step) {
                    if(ary[j]>insertValue){
                        ary[j+step] = ary[j];
                        index = j;
                        lt = true;
                    }else{
                        break;
                    }
                }
                if(lt){
                    ary[index] = insertValue;
                }
            }
        }

//        for (int i = 5; i < ary.length; i++) {
//            int insertValue = ary[i];
//            boolean lt = false;
//            int index = 0;
//            for (int j = i-5; j >=0; j-=5) {
//                if(ary[j]>insertValue){
//                    ary[j+5] = ary[j];
//                    index = j;
//                    lt = true;
//                }else {
//                    break;
//                }
//            }
//            if(lt){
//                ary[index] = insertValue;
//            }
//        }
    }
    private static void insertSort(int[] ary) {
        for (int i = 1; i < ary.length; i++) {
            int insertValue = ary[i];
            boolean lt = false;
            int index = 0;
            for (int j = i-1; j >=0 ; j--) {
                if(ary[j]>insertValue){
                    ary[j+1]=ary[j];
                    index = j;
                    lt = true;
                }else{
                    break;
                }
            }
            if(lt) {
                ary[index] = insertValue;
            }
        }
    }

    /**
     * 算法时间复杂度
     * @param ary
     */
    public static void insertSort2(int[] ary){
        for (int i = 1; i < ary.length; i++) {
            int index = i-1 ;
            int insertValue = ary[i];
            while(index >=0 && ary[index]>insertValue){
                ary[index+1] = ary[index];
                index--;
            }
            ary[index+1] = insertValue;
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