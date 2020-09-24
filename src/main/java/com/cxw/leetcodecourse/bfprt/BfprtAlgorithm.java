package com.cxw.leetcodecourse.bfprt;

import java.util.Arrays;

/**
 * @author chengxuwei
 * @date 2020-09-24 10:35
 * @description bfprt算法
 * 从n个元素中选出第k小或第k大的元素，同时也能选出前k小或前k大的所有元素。
 * 步骤：
 * 1.每5个元素一组，分组
 * 2.将分组后的数组各自排序
 * 3.取中位数，形成新数组
 * 4.递归调用bfprt(newAry,newAry.length/2);
 * 5.取第4步结果X判断是否要找的k，如果大于k就找右边，小于找左边
 */
public class BfprtAlgorithm {
    public static void main(String[] args) {
        int[] ary = {1,11,2,4,3,6,5,8,19,7,9};
        int[] newAry = bfprt(ary,10);

    }

    private static int[] bfprt(int[] ary, int k) {
        int[][] splitAry = subAry(ary,3);
        //printAry(splitAry);
        sortAry(splitAry);
        //printAry(splitAry);
        int[] midAry = midAry(splitAry);
        //System.out.println(Arrays.toString(midAry));
        return null;
    }


    private static int[] midAry(int[][] ary) {
        int[] midAry = new int[ary.length];
        for (int i = 0; i < ary.length; i++) {
            int[] one = ary[i];
            midAry[i] = one[one.length/2];
        }
        return midAry;
    }

    private static void sortAry(int[][] splitAry) {
        for (int i = 0; i < splitAry.length; i++) {
            int[] ary = splitAry[i];
            sort(ary);
        }
    }
    private static void sort(int[] ary){
        Arrays.sort(ary);
    }

    private static int[][] subAry(int[] ary, int n) {
        int length = (ary.length+n-1)/n;
        int[][] newAry = new int[length][n];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < n; j++) {
                int count = i*n+j;
                if(count>=ary.length){
                    break;
                }else{
                    newAry[i][j] = ary[count];
                }
            }
        }
        return newAry;
    }

    private static void printAry(int[][] splitAry) {
        for (int[] ary:splitAry){
            System.out.println(Arrays.toString(ary));
        }
    }

}