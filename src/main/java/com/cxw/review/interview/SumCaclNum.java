package com.cxw.review.interview;

import com.google.common.collect.HashBasedTable;


/**
 * @className SumCaclNum
 * @Description: leetcode 494题
 * 题目7
 * 给定一个数组arr，你可以在每个数字之前决定+或者-
 * 但是必须所有数字都参与
 * 再给定一个数target，请问最后算出target的方法数是多少?
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
}
