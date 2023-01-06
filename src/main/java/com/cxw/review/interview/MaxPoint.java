package com.cxw.review.interview;

/**
 * @className MaxPoint
 * @Description:  绳子覆盖最多的点数
 * @Author: chengxuwei
 * @Date:2023/1/4 20:51
 */
public class MaxPoint {
    public static void main(String[] args) {
        int[] ary = {1,3,4,7,10,11,12,13};
        int length = 4;
        int sum = maxPoint(ary, length);
        System.out.println(sum);
    }

    /**
     *
     * @param ary 有序数组
     * @param length 绳子长度
     * @return 点个数
     */
    public static int maxPoint(int[] ary,int length) {
        int l = 0;
        int r = l;
        int sum = 0;
        while(l<ary.length){
            int count = 0;
            while(r<ary.length&&ary[r]-ary[l]<=length){
                r++;
                count++;
            }
            sum = Math.max(sum,count);
            l++;
            r=l;
        }
        return sum;
    }
}
