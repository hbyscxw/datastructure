package com.cxw.leetcode.num;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * @author chengxuwei
 * @date 2021-01-14 14:40
 * @description
 *
 */
public class Division5 {
    public static void main(String[] args) {
        int[] ary = {1,0,0,1,0,1,0,0,1,0,1,1,1,1,1,1,1,1,1,1,0,0,0,0,1,0,1,0,0,0,0,1,1,0,1,0,0,0,1};
        List<Boolean> list = prefixesDivBy5(ary);
        System.out.println(list);
    }
    public static List<Boolean> prefixesDivBy5(int[] A) {
        List<Boolean> list = new ArrayList<>();
        if(A==null){
            return list;
        }
        for(int i=0;i<A.length;i++){
            double num = getNum(A,i);
            if(num%5==0){
                list.add(true);
            }else{
                list.add(false);
            }
        }
        return list;
    }
    public static double getNum(int[] A,int index){
        double sum = 0;
        for(int i = 0;i<=index;i++ ){
            sum+=Math.pow(2,index-i)*A[i];
        }
        return sum;
    }
}