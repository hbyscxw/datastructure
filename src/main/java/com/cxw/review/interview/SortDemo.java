package com.cxw.review.interview;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

import static com.cxw.review.sort.SortDemo.swap;

/**
 * @author chengxuwei
 * @create 2022/3/14 10:36
 * @desc
 */
public class SortDemo {
    public static void main(String[] args) {

        int[] ary = new int[10];
        for (int i = 0; i < ary.length; i++) {
            ary[i] = ThreadLocalRandom.current().nextInt(1000);
        }
        System.out.println(Arrays.toString(ary));
        bubblingSort(ary);
        System.out.println(Arrays.toString(ary));
    }

    public static void selectSort(int[] ary) {
        for (int i = 0; i < ary.length; i++) {
            int minIndex = i;
            for (int j = i+1; j < ary.length; j++) {
                if(ary[j]<ary[minIndex]){
                    minIndex = j;
                }
            }
            swap(ary,minIndex,i);
        }
    }
    public static void bubblingSort(int[] ary) {
        //外层循环 0~n
        //内层循环 0~n-1-i
        for (int i = 0; i <ary.length-1 ; i++) {
            for (int j = 0; j < ary.length-1 -i ; j++) {
                if(ary[j]>ary[j+1]){
                    swap(ary,j,j+1);
                }
            }
        }
    }
}
