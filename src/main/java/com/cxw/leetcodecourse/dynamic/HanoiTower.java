package com.cxw.leetcodecourse.dynamic;

/**
 * @author chengxuwei
 * @date 2020-09-16 10:17
 * @description 汉诺塔算法
 */
public class HanoiTower {
    public static void main(String[] args) {
        int n = 3;
        move(n);
    }

    private static void move(int n) {
        doMove(n,"a","b","c");
    }

    private static void doMove(int n, String a, String b, String c) {
        if(n == 1){
            System.out.println("move from "+a+" to " + b);
        }else{
            //n-1 从 a 移到 c ,b作为备用
            doMove(n-1,a,c,b);
            //移动第n个 从 a 移到 b ,c作为备用
            doMove(1,a,b,c);
            //n-1 从 c 移到 b ,a作为备用
            doMove(n-1,c,b,a);
        }
    }
}