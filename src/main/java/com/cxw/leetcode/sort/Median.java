package com.cxw.leetcode.sort;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chengxuwei
 * @date 2020-12-08 11:15
 * @description 两个数组的中位数
 */
public class Median {
    public static void main(String[] args) {
        int[] nums1 = {1,2};
        int[] nums2 = {3};
        double num = findMedianSortedArrays(nums1,nums2);
        System.out.println(num);
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        List<Integer> ary = merge(nums1,nums2);
        if(ary.size()==0){
            return 0d;
        }
        if(ary.size()%2==0){
            int num1 = ary.get(ary.size()/2-1);
            int num2 = ary.get(ary.size()/2);
            return (num1+num2)/2d;
        }else{
            return ary.get(ary.size()/2);
        }
    }

    private static List<Integer> merge(int[] nums1, int[] nums2) {
        List<Integer> list = new ArrayList<>();
        int i = 0;
        int j = 0;
        while(i<nums1.length&&j<nums2.length){
            if(nums1[i]<nums2[j]){
                list.add(nums1[i]);
                i++;
            }else if(nums1[i]>nums2[j]){
                list.add(nums2[j]);
                j++;
            }else{
                list.add(nums1[i]);
                list.add(nums2[j]);
                i++;
                j++;
            }
        }
        if(i<nums1.length){
            for (; i < nums1.length; i++) {
                list.add(nums1[i]);
            }
        }
        if(j<nums2.length){
            for (; j < nums2.length; j++) {
                list.add(nums2[j]);
            }
        }
        return list;
    }
}