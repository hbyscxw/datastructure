package com.cxw.leetcodecourse.tree.huffmantree;

import static com.cxw.review.sort.SortDemo.swap;

/**
 * @author chengxuwei
 * @date 2020-09-15 11:17
 * @description
 */
public class HeapSort {
    public static void main(String[] args) {
        int[] ary = {40,50,10,60,20,30};
        heapSort(ary);
    }

    private static void heapSort(int[] ary) {
        if(ary==null||ary.length<2){
            return ;
        }
        for (int i = 0; i < ary.length ; i++) {
            heapInsert(ary,i);
        }
        int index = ary.length-1;
        swap(ary,0,index--);
        while(index>0){
            heapify(ary,0,index);
            swap(ary,0,index--);
        }

    }

    private static void heapify(int[] ary, int index, int heapSize) {
        int left = index*2+1;
        while(left < heapSize){
            int biggest = left+1<heapSize&&ary[left+1]>ary[left] ? left+1 : left;
            biggest = ary[index]>ary[biggest]?index:biggest;
            if(biggest == index){
                break;
            }
            swap(ary,biggest,index);
            index = biggest;
            left = index*2+1;
        }
    }

    private static void heapInsert(int[] ary, int i) {
        while(ary[i]>ary[(i-1)/2]){
            swap(ary,i,(i-1)/2);
            i = (i-1)/2;
        }
    }
}