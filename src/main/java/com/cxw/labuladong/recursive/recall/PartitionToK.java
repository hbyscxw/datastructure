package com.cxw.labuladong.recursive.recall;

import java.util.Arrays;

/**
 * @author chengxuwei
 * @date 2021-03-05 10:19
 * @description 划分为k个相等的子集
 * 给定一个整数数组  nums 和一个正整数 k，找出是否有可能把这个数组分成 k 个非空子集，其总和都相等。
 * https://leetcode-cn.com/problems/partition-to-k-equal-sum-subsets/
 */
public class PartitionToK {
    public boolean canPartitionKSubsets(int[] nums, int k) {
        if(k>nums.length){
            return false;
        }
        int sum = 0;
        for (int num : nums) {
            sum+=num;
        }
        if(sum%k!=0){
            return false;
        }
        int target = sum/k;
        int[] bucket = new int[k];
        Arrays.sort(nums);
        return backtrack(nums,nums.length-1,bucket,target);
    }

    private boolean backtrack(int[] nums, int index,int[] bucket,int target) {
        if(index<0){
            return checkSum(bucket,target);
        }
        for (int i = 0; i < bucket.length; i++) {
            if(bucket[i] + nums[index]>target){
                continue;
            }
            bucket[i] += nums[index];
            if(backtrack(nums,index-1,bucket,target)){
                return true;
            }
            //回溯
            bucket[i] -= nums[index];
        }
        return false;
    }

    private boolean checkSum(int[] bucket, int target) {
        for (int b : bucket) {
            if(b!=target){
                return false;
            }
        }
        return true;
    }
}