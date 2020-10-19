package com.cxw.leetcodecourse.tree;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chengxuwei
 * @date 2020-10-16 10:19
 * @description
 * 定义数组的异或和的概念：
 *
 * 数组中所有的数异或起来，得到的结果叫做数组的异或和，
 *
 * 比如数组{3,2,1}的异或和是，3^2^1 = 0
 *
 * 给定一个数组arr，你可以任意把arr分成很多不相容的子数组，你的目的是：
 *
 *   分出来的子数组中，异或和为0的子数组最多。
 *
 *   请返回：分出来的子数组中，异或和为0的子数组最多是多少？
 */
public class MaxLenXorAry {
    public static void main(String[] args) {
        int[] ary = {1,2,3,0,2,3,1,0};
        int len = sumMaxLenXorAry(ary);
        System.out.println(len);
//        int sum = 1^2^3;
//        System.out.println(sum);
    }

    private static int sumMaxLenXorAry(int[] ary) {
        int sum = 0;//异或和
        int len = 0;
        int[] dp = new int[ary.length];
        Map<Integer,Integer> map = new HashMap<>();//index,sum
        map.put(0,-1);
        for (int i = 0; i < ary.length; i++) {
            sum ^= ary[i];
            if(map.containsKey(sum)){//存在证明异或和区域+1
                int pre = map.get(sum);
                dp[i] = pre==-1?1:(dp[pre]+1);
            }
            if(i>0){//拿最优情况
                dp[i] = Math.max(dp[i-1],dp[i]);
            }
            map.put(sum,i);
            len = Math.max(len,dp[i]);
        }
        return len;
    }


    private static int sumMaxLenXorAry0(int[] ary) {
        int xor = 0;//异或和
        int ans = 0;
        int[] dp = new int[ary.length];
        Map<Integer, Integer> map = new HashMap<>();//index,sum
        map.put(0, -1);
        for (int i = 0; i < ary.length; i++) {
            xor ^= ary[i];
            if(map.containsKey(xor)){
                int pre = map.get(xor);
                dp[i] = pre==-1?1:(dp[pre]+1);
            }
            if (i>0){
                dp[i] = Math.max(dp[i],dp[i-1]);
            }
            map.put(xor,i);
            ans = Math.max(dp[i],ans);
        }
        return ans;
    }
}