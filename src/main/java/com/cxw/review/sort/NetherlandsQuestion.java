package com.cxw.review.sort;

import java.util.Arrays;

public class NetherlandsQuestion {
    public static void main(String[] args) {
        int[] ary = {1,5,3,6,5};
        System.out.println(Arrays.toString(partition(ary,0,ary.length-1,5)));
        System.out.println(Arrays.toString(ary));
    }

    public static int[] partition(int[] ary,int l,int r,int num){
        int less = l-1;
        int more = r+1;
        int cur = l;
        while(cur<more){
            if(ary[cur]<num){
                swap(ary,++less,cur++);
            }else if(ary[cur]>num){
                swap(ary,--more,cur);
            }else{
                cur++;
            }
        }
        return new int[]{less+1,more-1};
    }

    public static void swap(int[] ary,int i,int j){
        if(i==j){
            return;
        }
        ary[i] = ary[i] ^ ary[j];
        ary[j] = ary[i] ^ ary[j];
        ary[i] = ary[i] ^ ary[j];
    }
}
