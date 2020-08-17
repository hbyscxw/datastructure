package com.cxw.review.sort;

/**
 * 小和
 * 一个数左边所有之和称之为小何
 * 计算一个数组中的所有小和之和
 */
public class MinSum {
    public static void main(String[] args) {
        int[] ary = {1,2,5,4,8,20,10};
        int res = sum(ary);
        System.out.println(res);
    }

    private static int sum(int[] ary) {
        int res = 0;
        for (int i = ary.length-1; i >0 ; i--) {
            for (int j = 0; j <i ; j++) {
                if(ary[j]<ary[i]){
                    res  += ary[j];
                }
            }
        }
        return res;
    }


}
