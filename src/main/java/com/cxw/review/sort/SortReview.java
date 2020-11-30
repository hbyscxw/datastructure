package com.cxw.review.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author chengxuwei
 * @date 2020-11-27 10:58
 * @description
 */
public class SortReview {
    public static void main(String[] args) {
        int[] ary = {1,5,2,3,8,7};
        //quickSort(ary, 0,ary.length-1);
        radixSort(ary);
        System.out.println(Arrays.toString(ary));
    }

    public static void radixSort(int[] ary){
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < ary.length; i++) {
            if(ary[i]>max){
                max = ary[i];
            }
        }
        int len = String.valueOf(max).length();
        for (int i = 0; i < len; i++) {
            Object[] objects = new Object[10];
            for (int j = 0; j < ary.length; j++) {
                int num = (ary[j]/(int)Math.pow(10,i))%10;
                if(objects[num]==null){
                    objects[num] = new ArrayList<Integer>();
                }
                List<Integer> list = (List<Integer>) objects[num];
                list.add(ary[j]);
            }
            List<Integer> list = getNum(objects);
            for (int j = 0; j < list.size(); j++) {
                ary[j] = list.get(j);
            }
        }
    }

    private static List<Integer> getNum(Object[] objects) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < objects.length; i++) {
            if(objects[i]==null){
                continue;
            }
            list.addAll((List<Integer>)objects[i]);
        }
        return list;
    }

    public static void quickSort(int[] ary, int start, int end) {
        if(start>=end){
            return;
        }
        int tag = ary[ThreadLocalRandom.current().nextInt(end-start+1)+start];
        int[] res = partition(ary,start,end,tag);
        if(res[0]>0) {
            quickSort(ary, start, res[0] - 1);
        }
        if(res[1]<ary.length-1){
            quickSort(ary, res[1] +1,end);
        }
    }

    private static int[] partition(int[] ary, int start, int end, int tag) {
        int less = start-1;
        int more = end+1;
        int cur = start;
        while(cur<more){
            if(ary[cur]>tag){
                s(ary,cur,--more);
            }else if(ary[cur]<tag){
                s(ary,cur++,++less);
            }else{
                cur++;
            }
        }
        return new int[]{less+1,more-1};
    }

    public static void insert(int[] ary){
        for (int i = 1; i < ary.length; i++) {
            for (int j = i-1; j >=0 ; j--) {
               if(ary[j]>ary[j+1]){
                   s(ary,j,j+1);
               }
            }
        }
    }

    public static void shellInsert(int[] ary){
        for (int i = ary.length/2; i > 0 ; i/=2) {
            for (int j = i; j < ary.length; j++) {
                for (int k = j-i; k >=0 ; k-=i) {
                    if(ary[k]>ary[k+i]){
                        s(ary,k,k+i);
                    }
                }
            }
        }
    }

    private static void s(int[] ary, int i,int j) {
        if(i==j){
            return;
        }
        ary[i] = ary[i] ^ ary[j];
        ary[j] = ary[i] ^ ary[j];
        ary[i] = ary[i] ^ ary[j];
    }
}