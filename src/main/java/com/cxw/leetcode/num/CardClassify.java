package com.cxw.leetcode.num;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author chengxuwei
 * @date 2020-12-23 16:22
 * @description 一手顺子
 * 爱丽丝有一手（hand）由整数数组给定的牌。
 *
 * 现在她想把牌重新排列成组，使得每个组的大小都是 W，且由 W 张连续的牌组成。
 *
 * 如果她可以完成分组就返回 true，否则返回 false。
 *
 *
 *
 * 注意：此题目与 1296 重复：https://leetcode-cn.com/problems/divide-array-in-sets-of-k-consecutive-numbers/
 *
 *
 *
 * 示例 1：
 *
 * 输入：hand = [1,2,3,6,2,3,4,7,8], W = 3
 * 输出：true
 * 解释：爱丽丝的手牌可以被重新排列为 [1,2,3]，[2,3,4]，[6,7,8]。
 * 示例 2：
 *
 * 输入：hand = [1,2,3,4,5], W = 4
 * 输出：false
 * 解释：爱丽丝的手牌无法被重新排列成几个大小为 4 的组。
 * https://leetcode-cn.com/problems/hand-of-straights/
 */
public class CardClassify {
    public static void main(String[] args) {
        int[] hand = new int[]{1,2,3,6,2,3,4,7,8};
        int w = 3;
        CardClassify cc = new CardClassify();
        boolean f = cc.isNStraightHand(hand, w);
        System.out.println(f);

        List<Integer> list = new ArrayList<>();
        list.add(3);
        list.add(3);
        list.remove(new Integer(3));
        System.out.println(list);
    }
    public boolean isNStraightHand(int[] hand, int w) {
        if(hand.length%w!=0){
            return false;
        }
        Arrays.sort(hand);
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < hand.length; i++) {
            list.add(hand[i]);
        }
        int n = hand.length/w;
        for (int i = 0; i < n; i++) {
            int num = Integer.MIN_VALUE;
            for (int j = 0; j < w; j++) {
                if(j==0){
                    num = list.get(0);
                    list.remove(new Integer(num));
                }else{
                    num++;
                    if(!list.contains(num)){
                        return false;
                    }else{
                        list.remove(new Integer(num));
                    }
                }
            }
        }
        return true;
    }
}