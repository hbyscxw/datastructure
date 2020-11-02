package com.cxw.leetcodecourse.window;

/**
 * @author chengxuwei
 * @date 2020-11-02 09:57
 * @description 求指定值的最大子数组的长度,数组所有数都是正数
 */
public class MaxCount {
    public static void main(String[] args) {
        int[] ary = {1,2,3,5,1,1,1,1,1,1};
        int aim = 6;
        int res = maxSubAryLen(ary,aim);
        System.out.println(res);
    }

    private static int maxSubAryLen(int[] ary, int aim) {
        int l = 0;
        int r = 0;
        int sum = 0;
        int maxLen = 0;
        while(r<ary.length){
            if(sum == aim){
                maxLen = Math.max(maxLen,r-l);
                sum = sum - ary[l] + ary[r];
                l++;
                r++;
            }else if(sum>aim){
                sum = sum - ary[l];
                l++;
            }else{
                sum = sum + ary[r];
                r++;
            }
        }
        return sum;
    }

}