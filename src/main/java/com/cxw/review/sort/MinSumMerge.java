package com.cxw.review.sort;

/**
 * 小和
 * 一个数左边所有之和称之为小何
 * 计算一个数组中的所有小和之和
 */
public class MinSumMerge {
    public static void main(String[] args) {
        int[] ary = {1,2,5,4,8,20,10};
        int res = mergeSort(ary);
        System.out.println(res);
    }

    private static int mergeSort(int[] ary) {
        return doMerge(ary,0,ary.length-1);
    }

    private static int doMerge(int[] ary, int left, int right) {
        if(left == right){
            return 0;
        }
        int mid = left + (right - left) >> 1;
        // a/2 == a >> 1;
        //等同于 int mid = left + (right - left)/2;  相减可以保持不溢出
        //int mid = (left + right)/2;
        return doMerge(ary,left,mid) +
        doMerge(ary,mid+1,right) +
                merge(ary,left,mid,right);
    }

    private static int merge(int[] ary, int left, int mid, int right) {
        int l = left;
        int r = mid+1;
        int[] temp = new int[right - left + 1];
        int ti = 0;
        int res = 0;
        while(l<=mid&&r<=right){
            if(ary[l]<=ary[r]){
                res += ary[l]* (right-r+1);//左边当前数*右边个数
                temp[ti++] = ary[l++];
            }else{
                temp[ti++] = ary[r++];
            }
        }
        while(l<=mid){
            temp[ti++] = ary[l++];
        }
        while(r<=right){
            temp[ti++] = ary[r++];
        }
        System.arraycopy(temp,0,ary,left,temp.length);
        return res;
    }

}
