package com.cxw.review.interview;

import com.google.common.collect.HashBasedTable;

import java.util.Arrays;

/**
 * @className SumCaclNum
 * @Description: leetcode 494题
 * 题目7
 * 给定一个数组arr，你可以在每个数字之前决定+或者-
 * 但是必须所有数字都参与
 * 再给定一个数target，请问最后算出target的方法数是多少?
 * 优化点
 * 1.负数和非负答案一样 -9 +9 -(-9)   所以可以将所有数都取绝对值sum
 * 2.taget>所有值的绝对值之和 无解
 * 3.sum和target奇偶不一致 无解
 * 4.所有为+的集合p,所有为-的集合为n,则有p-n=target => p-n+(p+n)=target+(p+n) => 2p=target+sum => p=(target+sum)/2
 *  把动态规划的 arr.length*2sum规模的表(-sum到sum)变成 arr.length*sum规模，减少了常数规模时间
 * 5.动态规划二维表空间压缩技巧
 * i是0-ary.length
 * j是0-sum
 *             不使用i位置上的数          使用i位置上的数 j-i为了使结果是j则需要 j - i来满足
 * dp[i][j] = dp[i-i][j]          +   dp[i-1][j-i]
 * @Author: chengxuwei
 * @Date:2023/1/6 17:41
 */
public class SumCaclNum {
    public static void main(String[] args) {
        int[] ary = {1,2,10,3};
        int target = 10;
        HashBasedTable<Integer, Integer, Integer> map = HashBasedTable.create();
        int num = cacl3(ary,target,0, map);
        System.out.println(map);
        System.out.println(num);
    }

    private static int cacl(int[] ary, int target,int index,int sum) {
        if(index==ary.length) {
            if (sum == target) {
                return 1;
            }else {
                return 0;
            }
        }
        int num = cacl(ary, target, index+1, sum+ary[index]);
        int num2 = cacl(ary, target, index+1, sum-ary[index]);
        return num+num2;
    }

    //视频解
    private static int cacl2(int[] ary, int target,int index) {
        if(index==ary.length) {
            if (target == 0) {
                return 1;
            }else {
                return 0;
            }
        }
        int num = cacl2(ary, target+ary[index], index+1);
        int num2 = cacl2(ary, target-ary[index], index+1);
        return num+num2;
    }

    //缓存  index:target:num;
    private static int cacl3(int[] ary, int target, int index, HashBasedTable<Integer,Integer,Integer> map) {
        if(map.get(index,target)!=null){
            return map.get(index,target);
        }
        int sum = 0;
        if(index==ary.length) {
            sum = target==0?1:0;
        }else {
            int num = cacl3(ary, target + ary[index], index + 1,map);
            int num2 = cacl3(ary, target - ary[index], index + 1,map);
            sum = num + num2;
        }
        map.put(index,target,sum);
        return sum;
    }

    //动态规划
    private static int cacl4(int[] ary, int target){
        //正负一样,取绝对值
        int sum = Arrays.stream(ary).map(Math::abs).sum();
//        taget>所有值的绝对值之和 无解
        if(sum<target){
            return 0;
        }
        //判断奇偶是否一致
        if(((target&1)^(sum&1))!=0){
            return 0;
        }
        //背包动态规划
        //(sum+target)>>1 == (sum+target)/2
        return subset(ary,(sum+target)>>1);
    }

    private static int subset(int[] ary, int s) {
        int[] dp = new int[s+1];
        dp[0] = 1;
        for (int n : ary) {
            for (int i = s; i >= n ; i--) {
                dp[i] += dp[i-n];
            }
        }
        return dp[s];
    }



}
