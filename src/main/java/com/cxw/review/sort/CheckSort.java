package com.cxw.review.sort;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
//import com.cxw.datastructure.sort.SortDemo;

/**
 * @author chengxuwei
 * @date 2020-08-12 09:50
 * @description 对数器
 */
public class CheckSort {
    public static void main(String[] args) {
        checkSort(100000,10,100000);
    }
    private static void mySort(int[] ary) {
        //SortDemo.heapSort(ary);
        SortReview.shellInsert(ary);
    }

    private static void checkSort(int max,int length,int time){
        System.out.println(check(max, length, time));
    }
    private static boolean check(int max,int length,int time){
        int[] ary = new int[length];
        for (int k = 0; k < time; k++) {
            for (int i = 0; i < ary.length; i++) {
                ary[i] = randomNum(max);
            }
            int[] cAry = copyAry(ary);
            rightSort(cAry);
            int[] cAry2 = copyAry(ary);
            mySort(cAry2);
            boolean f = compareAry(cAry,cAry2);
            if(!f){
                System.out.println("出错的例子："+Arrays.toString(ary)+Arrays.toString(cAry2));
                return false;
            }
        }
        return true;
    }

    private static void rightSort(int[] ary){
        Arrays.sort(ary);
//        System.out.println(Arrays.toString(ary));
    }

    private static boolean compareAry(int[] cAry, int[] cAry2) {
        if(cAry.length != cAry2.length){
            return false;
        }
        for (int i = 0; i < cAry.length; i++) {
            if(cAry[i] != cAry2[i]){
                return false;
            }
        }
        return true;
    }

    private static int[] copyAry(int[] ary) {
        int[] newAry = new int[ary.length];
        System.arraycopy(ary,0,newAry,0,ary.length);
        return newAry;
    }

    private static int randomNum(int max) {
        return ThreadLocalRandom.current().nextInt(max);
    }
}