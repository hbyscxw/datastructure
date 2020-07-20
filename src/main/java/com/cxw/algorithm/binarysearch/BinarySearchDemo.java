package com.cxw.algorithm.binarysearch;

public class BinarySearchDemo {
    public static void main(String[] args) {
        int[] ary = {1,2,35,77,88,98};
        int target = 88;
        int res = binarySearch(ary,target);
        System.out.println(res);
    }

    public static int binarySearch(int[] ary,int target){
        int left = 0;
        int right = ary.length-1;
        while(left<=right){
            int mid = (left+right)/2;
            if(ary[mid] == target){
                return mid;
            }else if(ary[mid]>target){
                right = mid-1;
            }else{
                left = mid+1;
            }
        }
        return -1;
    }
}
