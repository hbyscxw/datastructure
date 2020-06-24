package com.cxw.datastructure.search;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SearchDemo {
    public static void main(String[] args) {
        int[] ary = {1,1,1,1,2,3,4,5,6,7,8,9};
        System.out.println(binarySearch(ary,1000));
    }

    /**
     * 二分查找算法前提是数组有序
     * @param ary
     * @param value
     * @return
     */
    public static int binarySearch(int[] ary,int value){
        return doInsertValueSearch(ary,0,ary.length-1,value);
    }

    public static int doBinarySearch(int[] ary,int left,int right,int value){
        if(left>right){
            return -1;
        }
        int mid = (left+right)/2;
        if(ary[mid]==value){
            return mid;
        }else if(ary[mid]<value){
            return doBinarySearch(ary,mid+1,right,value);
        }else{
            return doBinarySearch(ary,left,mid-1,value);
        }
    }

    public static int doInsertValueSearch(int[] ary,int left,int right,int value){
        if(left>right||value<ary[0]||value>ary[ary.length-1]){
            return -1;
        }
        //差值算法
        int mid = left+(right-left)*(value-ary[left])/(ary[right]-ary[left]);
        if(ary[mid]==value){
            return mid;
        }else if(ary[mid]<value){
            return doBinarySearch(ary,mid+1,right,value);
        }else{
            return doBinarySearch(ary,left,mid-1,value);
        }
    }

    /**
     * 二分查找算法返回所有符合条件的下标
     * @param ary
     * @param value
     * @return
     */
    public static List<Integer> binarySearch2(int[] ary, int value){
        return doBinarySearch2(ary,0,ary.length-1,value);
    }

    public static List<Integer> doBinarySearch2(int[] ary,int left,int right,int value){
        if(left>right){
            return new ArrayList<>();
        }
        int mid = (left+right)/2;
        if(ary[mid]==value){
            List<Integer> list = Stream.of(mid).collect(Collectors.toList());
            for (int i = mid-1; i >= left ; i--) {
                if(ary[i]==value){
                    list.add(i);
                }else{
                    break;
                }
            }
            for (int i = mid+1; i <= right ; i++) {
                if(ary[i]==value){
                    list.add(i);
                }else{
                    break;
                }
            }
            return list;
        }else if(ary[mid]<value){
            return doBinarySearch2(ary,mid+1,right,value);
        }else{
            return doBinarySearch2(ary,left,mid-1,value);
        }
    }
}
