package com.cxw.datastructure.tree;

import java.util.Arrays;

/**
 * @author chengxuwei
 * @date 2020-07-03 09:37
 * @description 堆排序
 */
public class HeapSortDemo {
    public static void main(String[] args) {
        int[] ary = {4, 6, 8, 5, 9};
        //将无序队列构建成一个顶堆
        for (int i = ary.length/2-1; i >= 0 ; i--) {
            adjustHeap(ary,i,ary.length);
        }
        for (int j = ary.length-1; j >0 ; j--) {
            swap(ary,0,j);
            adjustHeap(ary,0,j);
        }
        System.out.println(Arrays.toString(ary));
    }
    public static void adjustHeap(int[] ary,int i,int length){
        if(ary==null||ary.length<2){
            return;
        }
        int temp = ary[i];
        for (int k = i*2+1; k < length; k= k*2+1) {
            //判断左节点小于右节点
            if(k+1<length&&ary[k]<ary[k+1]){
                k++;
            }
            //子节点大于父节点
            if(ary[k]>temp){
                swap(ary,i,k);
                //i指向k,继续循环比较
                i = k;
            }else{
                break;
            }
        }
        //循环结束后i已经是最大值，放在堆顶（局部）
        ary[i] = temp;
    }

    private static void swap(int[] ary, int j, int i) {
        int temp = ary[i];
        ary[i] = ary[j];
        ary[j] = temp;
    }
}