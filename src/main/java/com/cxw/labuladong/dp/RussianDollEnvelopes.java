package com.cxw.labuladong.dp;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author chengxuwei
 * @date 2021-03-03 17:58
 * @description 俄罗斯套娃
 * https://leetcode-cn.com/problems/russian-doll-envelopes/
 *
 * https://labuladong.gitbook.io/algo-en/v/master/dong-tai-gui-hua-xi-lie/zi-xu-lie-lei-xing-wen-ti/xin-feng-qian-tao-wen-ti
 */
public class RussianDollEnvelopes {
    public static void main(String[] args) {
//        int[][] envelopes = {{5,4},{6,4},{6,7},{2,3}};
        int[][] envelopes = {{1,8},{5,4},{6,7},{2,3},{5,2},{6,4}};
//        List<int[]> resList = Stream.of(envelopes).sorted(Comparator.comparingInt((int[] es) -> es[0])
//                .thenComparing(es -> -es[1])).collect(Collectors.toList());
//        System.out.println(resList);
        RussianDollEnvelopes re = new RussianDollEnvelopes();
        int res = re.maxEnvelopes(envelopes);
        System.out.println(res);
    }
    public int maxEnvelopes(int[][] envelopes) {
        List<int[]> resList = Stream.of(envelopes).sorted(Comparator.comparingInt((int[] es) -> es[0])
                .thenComparing(es -> -es[1])).collect(Collectors.toList());
        List<Integer> heightList = new ArrayList<>();
        for (int[] ints : resList) {
            heightList.add(ints[1]);
        }
       return lengthOfList(heightList);
    }

    private int lengthOfList(List<Integer> heightList) {
        int piles = 0;
        int n = heightList.size();
        int[] top = new int[n];
        for (int i = 0; i < n; i++) {
            Integer poker = heightList.get(i);
            int left = 0,right=piles;
            //二分查找
            while (left<right){
                int mid = (left+right)/2;
                if(top[mid]>=poker){
                    right = mid;
                }else{
                    left = mid+1;
                }
            }
            if(left == piles){
                piles++;
            }
            top[left] = poker;
        }
        return piles;
    }
}