package com.cxw.review.interview;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chengxuwei
 * @create 2022/3/3 15:55
 * @desc
 */
public class EggDropDemo {
    public static void main(String[] args) {
        int N = 100;//N层楼的建筑
        int K = 2;//K个鸡蛋
        int res = superEggDrop(K,N);
        System.out.println(res);
    }
    private static Map<String,Integer> cache = new HashMap<>();
    public static int superEggDrop(int k,int n) {
        if(k == 0 || n == 0){
            return 0;
        }
        if(cache.get(k+"_"+n)!=null){
            return cache.get(k+"_"+n);
        }
        int temp = Integer.MAX_VALUE;
        int l = 1;
        int r = n;
        while (l<=r){
            int mid = (r-l)/2+l;
            int t1 = superEggDrop(k-1,mid-1);//碎了 在mid以下
            int t2 = superEggDrop(k,n-mid);//没碎   在mid以上
            //取最大 +1
            if(t1>t2){
                r = mid -1;
                temp = Math.min(temp,t1+1);
            }else {
                l = mid+1;
                temp = Math.min(t2+1, temp);
            }
        }
        cache.put(k+"_"+n,temp);
        return temp;

    }


    public static int superEggDrop2(int k,int n) {
        if(k == 0 || n == 0){
            return 0;
        }
        int[][] record = new int[k+1][n+1];
        return dp(record,k,n);
    }
    public static int dp(int[][] record,int k,int n) {
        if(k == 1){
            return n;
        }
        if(record[k][n]>0){
            return record[k][n];
        }
        int l = 1;
        int r = n;
        int temp = Integer.MAX_VALUE;
        while (l<=r){
            int mid = (r-l)/2+l;
            int t1 = dp(record, k-1, mid-1);//碎了 在mid-1楼及以下
            int t2 = dp(record, k, n-mid);//没碎  在 mid+1楼及以上
            //取最大 +1
            if(t1>t2){
                r = mid -1;
                temp = Math.min(temp,t1+1);
            }else {
                l = mid+1;
                temp = Math.min(t2+1, temp);
            }
        }
        record[k][n] = temp;
        return record[k][n];
    }
}
