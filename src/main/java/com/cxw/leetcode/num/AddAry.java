package com.cxw.leetcode.num;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chengxuwei
 * @date 2021-01-22 17:29
 * @description
 */
public class AddAry {
    public static void main(String[] args) {
        int[] ary = {2,7,4};
        int k = 181;
        AddAry aa = new AddAry();
        List<Integer> res = aa.addToArrayForm(ary, k);
        System.out.println(res);
    }
    public List<Integer> addToArrayForm(int[] A, int K) {
        int[] kAry = getAry(K);
        int temp = 0;
        int len = Math.max(A.length,kAry.length);
        List<Integer> list = new ArrayList<>();
        for(int i=0;i<len;i++){
            int resValue = 0;
            int kIndex = kAry.length-1-i;
            int aIndex = A.length-1-i;
            int a = 0;
            if(aIndex>=0){
                a = A[aIndex];
            }
            int k = 0;
            if(kIndex>=0){
                k = kAry[kIndex];
            }
            int res = a + k + temp;
            if(res<10){
                resValue = res;
                temp = 0;
            }else{
                resValue = res%10;
                temp = 1;
            }
            list.add(0,resValue);
        }
        if(temp==1){
            list.add(0,temp);
        }
        return list;
    }
    public int[] getAry(int k){
        char[] kChar = String.valueOf(k).toCharArray();
        int[] kAry = new int[kChar.length];
        for(int i=0;i<kChar.length;i++){
            kAry[i] = kChar[i] - '0';
        }
        return kAry;
    }
}