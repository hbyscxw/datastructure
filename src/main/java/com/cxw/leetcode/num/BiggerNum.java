package com.cxw.leetcode.num;

import java.util.Arrays;

/**
 * @author chengxuwei
 * @date 2021-03-09 18:12
 * @description 下一个更大元素 II
 * https://leetcode-cn.com/problems/next-greater-element-ii/
 */
public class BiggerNum {
    public static void main(String[] args) {
        int[] nums = {1,2,1};
        BiggerNum bn = new BiggerNum();
        int[] newNums = bn.nextGreaterElements(nums);
        System.out.println(Arrays.toString(newNums));
    }
    public int[] nextGreaterElements(int[] nums) {
        int[] bigNums = new int[nums.length];
        for(int i=0;i<nums.length;i++){
            getBigger(nums,i,bigNums);
        }
        return bigNums;
    }
    public void getBigger(int[] nums,int index,int[] bigNums) {
        int bigger = -1;
        for(int i=1;i<=nums.length;i++){
            int ui = (index+i)%nums.length;
            if(nums[ui]>nums[index]){
                bigger=nums[ui];
                break;
            }
        }
        bigNums[index] = bigger;
    }
}