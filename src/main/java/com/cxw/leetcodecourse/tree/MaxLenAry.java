package com.cxw.leetcodecourse.tree;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chengxuwei
 * @date 2020-10-14 10:38
 * @description 求一个数组累加和为aim的最长子数组
 */
public class MaxLenAry {
    public static void main(String[] args) {
        int[] ary = {7,3,2,1,1,-7,7};
        int aim = 7;
        int len = sumMaxLenAry(ary,aim);
        System.out.println(len);
    }

    private static int sumMaxLenAry(int[] ary, int aim) {
        int len = 0;
        int sum = 0;
        Map<Integer,Integer> map = new HashMap<>(); //sum,index
        map.put(0,-1);
        for (int i = 0; i < ary.length; i++) {
            sum += ary[i];
            if(map.containsKey(sum-aim)){
                len = Math.max(i-map.get(sum-aim),len);
            }
            if(!map.containsKey(sum)){
                map.put(sum,i);
            }
        }
        return len;
    }





    private static int sumMaxLenAry0(int[] ary, int aim) {
        int len = 0;
        int sum = 0;
        Map<Integer, Integer> map = new HashMap<>(); //sum,index
        map.put(0, -1);
        for (int i = 0; i < ary.length; i++) {
            sum += ary[i];
            if(map.containsKey(sum-aim)){
                len = Math.max(len,i-map.get(sum-aim));
            }
            if(!map.containsKey(sum)){
              map.put(sum,i);
            }
        }

        return len;
    }




}