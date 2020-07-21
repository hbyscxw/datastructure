package com.cxw.algorithm.hanoihower;

/**
 * @author chengxuwei
 * @date 2020-07-21 17:05
 * @description 汉诺塔算法
 */
public class HanoiTower {
    public static void main(String[] args) {
        int n = 2;
        hanoiTower(n,'A','B','C');
    }

    public static void hanoiTower(int n,char a,char b,char c){
        if(n == 1){
            System.out.println("第1次移动"+a+"->"+c);
        }else{
            //先把 除最底层之外的a->b
            hanoiTower(n-1, a, c, b);
            //再把a->c
            System.out.println("第"+n+"次移动"+a+"->"+c);
            //最后把所有的b->c
            hanoiTower(n-1, b, a, c);
        }

    }
}