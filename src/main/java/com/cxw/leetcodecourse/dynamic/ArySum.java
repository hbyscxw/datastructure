package com.cxw.leetcodecourse.dynamic;

/**
 * @author chengxuwei
 * @date 2020-09-18 10:07
 * @description
 * 给你一-个数组arr,和一-个整数aim。如果可以任意选择arr中的
 * 数字，能不能累加得到aim,返回true或者false
 */
public class ArySum {
    public static void main(String[] args) {
        int[] ary = {5,3,8};
        int aim = 18;
        boolean f = cal(ary,0,0,aim);
        System.out.println(f);
    }

    private static boolean cal(int[] ary, int i,int sum, int aim) {
        if(aim == sum){
            return true;
        }
        if(i==ary.length){
            return false;
        }
        return cal(ary,i+1,sum,aim)||cal(ary,i+1,sum+ary[i],aim);

    }
}