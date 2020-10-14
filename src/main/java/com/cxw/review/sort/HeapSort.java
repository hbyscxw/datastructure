package com.cxw.review.sort;

import java.util.Arrays;

import static com.cxw.review.sort.SortDemo.swap;

/**
 * @author chengxuwei
 * @date 2020-10-10 16:23
 * @description 堆排序
 */
public class HeapSort {
    public static void main(String[] args) {
        int[] ary = {1,5,6,4,11,3,51,41};
        //调整成大根堆
        for (int i = 0; i < ary.length; i++) {
            adjustHeap(ary,i);
        }
        System.out.println(Arrays.toString(ary));
        for (int i = ary.length-1; i >=0 ; i--) {
            swap(ary,0,i);
            doAdjustHeap(ary,0,i-1);
        }
        System.out.println(Arrays.toString(ary));
    }

    private static void doAdjustHeap(int[] ary, int start, int end) {
        int index = start;
        while((index*2+1)<=end){
            //当前节点大于父节点 则交换
            int left = index*2+1;
            int right = index*2+2;
            int bigger = right<=end&&ary[right]>ary[left]?right:left;
            bigger = ary[bigger]>ary[index]?bigger:index;
            if(bigger==index){
                break;
            }else{
                swap(ary,bigger,index);
            }
            index = bigger;
        }
    }

    private static void adjustHeap(int[] ary, int i) {
        int index = i;
        while(index>0){
            //当前节点大于父节点 则交换
            if(ary[index]>ary[(index-1)/2]){
                swap(ary,index,(index-1)/2);
            }
            index = (index-1)/2;
        }
    }
}