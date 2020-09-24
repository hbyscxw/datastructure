package com.cxw.leetcodecourse.sort;

import java.util.Arrays;

import static com.cxw.review.sort.SortDemo.swap;

/**
 * @author chengxuwei
 * @date 2020-09-24 14:54
 * @description 荷兰国旗问题
 */
public class NetherlandsQuestion {
    public static void main(String[] args) {
        int[] ary = {1,1,5,3,4,6,7,7,0,12,11};
        int[] part = partition(ary,0,ary.length-1,7);
        System.out.println(Arrays.toString(ary));
        System.out.println(Arrays.toString(part));
    }

    private static int[] partition(int[] ary, int start, int end, int target) {
       int s = start-1;
       int e = end + 1;
       int cur = start;
       while(cur<end){
           if(ary[cur]<target){
               s++;
               swap(ary,s,cur);
               cur++;
           }else if(ary[cur]>target){
               e--;
               swap(ary,e,cur);
               cur++;
           }else{
               cur++;
           }
       }
       return new int[]{s+1,e-1};
    }
}