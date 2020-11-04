package com.cxw.leetcodecourse.window;

/**
 * @author chengxuwei
 * @date 2020-11-03 09:33
 * @description
 * 给定一个数组arr，值可正，可负，可0；一个整数aim，求累加和小于等于aim的，最长子数组，要求时间复杂度O(N)
 * 动态规划+滑动窗口
 * https://www.cnblogs.com/zzw1024/p/11097757.html
 */
public class MaxAryLen {
    public static void main(String[] args) {
        int[] ary = {4,3,-1,6,-7,2};
        int aim = 5;
        int res = maxAryLen(ary,aim);
        System.out.println(res);
    }

    private static int maxAryLen(int[] ary, int aim) {
        int[] minSumAry = new int[ary.length];
        int[] minSumIndexAry = new int[ary.length];
        for (int i = ary.length-1; i >=0 ; i--) {
            if(i<ary.length-1&&minSumAry[i+1]<0){
                minSumAry[i] = minSumAry[i+1]+ary[i];
                minSumIndexAry[i] = minSumIndexAry[i+1];
            }else{
                minSumAry[i] = ary[i];
                minSumIndexAry[i] = i;
            }
        }
        int l = -1;
        int r = 0;
        int sum = 0;
        int len = 0;
        for (int i = 0; i < ary.length ; i++) {
            sum += minSumAry[i];
            r = minSumIndexAry[i];
            while(l<r&&sum>aim){
                ++l;
                sum-=ary[l];
            }
            len = Math.max(r-l,len);
        }
        return len;
    }
}