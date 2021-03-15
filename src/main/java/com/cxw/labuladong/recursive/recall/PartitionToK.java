package com.cxw.labuladong.recursive.recall;

import java.util.Arrays;

/**
 * @author chengxuwei
 * @date 2021-03-05 10:19
 * @description 划分为k个相等的子集
 * 给定一个整数数组  nums 和一个正整数 k，找出是否有可能把这个数组分成 k 个非空子集，其总和都相等。
 * https://leetcode-cn.com/problems/partition-to-k-equal-sum-subsets/
 *
 * https://mp.weixin.qq.com/s/fsLKaWBvSWtM0jA-CfOxyA
 */
public class PartitionToK {
    public static void main(String[] args) {
        int[] nums = {4,3,2,3,5,2,1};
        int k = 4;
        PartitionToK pk = new PartitionToK();
        boolean b = pk.canPartitionKSubsets3(nums, k);
        System.out.println(b);
    }
    /**
     * 以数字的视角 分为k个桶里
     * @param nums
     * @param k
     * @return
     */
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

    /**
     * 以桶的视角 取数字
     * @param nums
     * @param k
     * @return
     */
    public boolean canPartitionKSubsets2(int[] nums, int k) {
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
        boolean[] nUsed = new boolean[nums.length];
        Arrays.sort(nums);
        return backtrack2(nums,0,nUsed,bucket,target);
    }

    private boolean backtrack2(int[] nums, int index, boolean[] nUsed, int[] bucket, int target) {
        if(index==bucket.length){
            //checkSum(bucket)
            return true;
        }
        if(bucket[index]==target){
            return backtrack2(nums,index+1,nUsed,bucket,target);
        }
        for (int i = nums.length-1; i >= 0; i--) {
            if(nUsed[i]){
                continue;
            }
            if(bucket[index]+nums[i]>target){
                continue;
            }
            bucket[index]+=nums[i];
            nUsed[i] = true;
            if(backtrack2(nums,index,nUsed,bucket,target)){
                return true;
            }
            bucket[index]-=nums[i];
            nUsed[i] = false;
        }
        return false;
    }

    /**
     * 以桶的视角 取数字 优化
     * @param nums
     * @param k
     * @return
     */
    public boolean canPartitionKSubsets3(int[] nums, int k) {
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
        boolean[] nUsed = new boolean[nums.length];
        Arrays.sort(nums);
        return backtrack3(nums,0,nums.length-1,nUsed,bucket,target);
    }

    private boolean backtrack3(int[] nums, int index,int start, boolean[] nUsed, int[] bucket, int target) {
        if(index==bucket.length){
            //checkSum(bucket)
            return true;
        }
        if(bucket[index]==target){
            return backtrack3(nums,index+1,nums.length-1,nUsed,bucket,target);
        }
        for (int i = start; i >= 0; i--) {
            if(nUsed[i]){
                continue;
            }
            if(bucket[index]+nums[i]>target){
                continue;
            }
            bucket[index]+=nums[i];
            nUsed[i] = true;
            if(backtrack3(nums,index,i-1,nUsed,bucket,target)){
                return true;
            }
            bucket[index]-=nums[i];
            nUsed[i] = false;
        }
        return false;
    }
}