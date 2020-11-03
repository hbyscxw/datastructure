package com.cxw.leetcodecourse.dynamic;

/**
 * @author chengxuwei
 * @date 2020-11-02 09:57
 * @description 求小于等于aim值的最长子数组的长度,数组所有数都是整数
 */
public class MaxCount2 {
    public static void main(String[] args) {
        int[] ary = {1,2,-3,5,-1,1,1,1,1,1};
        int aim = 4;
        int res = maxSubAryLen(ary,aim);
        System.out.println(res);
    }

    private static int maxSubAryLen(int[] ary, int aim) {
        int[] minSumAry = new int[ary.length];
        int[] minSumIndexAry = new int[ary.length];
        for (int i = ary.length-1; i >=0 ; i--) {
            if(i+1<ary.length&&minSumAry[i+1]<0) {
                minSumIndexAry[i] = minSumIndexAry[i + 1];
                minSumAry[i] = ary[i]+minSumAry[i+1];
            }else{
                minSumIndexAry[i] = i;
                minSumAry[i] = ary[i];
            }
        }
        int r = 0;
        int sum = 0;
        int maxSumLen = 0;
        for (int i = 0; i < ary.length; i++) {
            while (r<ary.length&&sum+ary[r]<=aim){
                sum += minSumAry[r];
                r = minSumIndexAry[r]+1;
            }
            sum-=r>i?ary[i]:0;
            maxSumLen=Math.max(maxSumLen,r-i);
            r = Math.max(r,i+1);
        }
        return maxSumLen;
    }

}