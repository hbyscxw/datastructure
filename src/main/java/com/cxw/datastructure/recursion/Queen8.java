package com.cxw.datastructure.recursion;

public class Queen8 {
    private static int max = 8;
    private static int[] array = new int[max];
    private static int count = 0;
    public static void main(String[] args) {
        check(0);
        System.out.println(count);
    }

    /**
     * 每一次 check 都有递归  for (int i = 0; i < max; i++) {    所以会回溯
     * @param n
     */
    private static void check(int n){
        if(n == max){ //已经放完
            print(array);
            return;
        }
        for (int i = 0; i < max; i++) {
            array[n] = i;//放第n个到i列
            if(judge(n)){
                //不冲突接着放下一个
                check(n+1);
            }
            //冲突就接着放到i++列 array[n] = i;
        }
    }

    /**
     * 判断冲突
     * @param n
     * @return
     */
    private static boolean judge(int n){
        for (int i = 0; i < n; i++) {
            //是否在同一列
            //是否在同一斜线 斜率相同就在同一斜线    Math.abs求绝对值
            if(array[n] == array[i] || Math.abs(n-i)==Math.abs(array[n]-array[i])){
                return false;
            }
        }
        return true;
    }

    public static void print(int[] array){
        count++;
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]+" ");
        }
        System.out.println();
    }
}
