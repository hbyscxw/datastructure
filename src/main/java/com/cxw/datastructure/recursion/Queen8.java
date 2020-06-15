package com.cxw.datastructure.recursion;

/**
 * @author chengxuwei
 * @date 2020-06-11 09:41
 * @description 8皇后问题
 */
public class Queen8 {
    private static int max = 8;
    private static int[] array = new int[max];
    public static void main(String[] args) {
        check(0);
    }

    public static void check(int n){
        if(n == max){
            print();
            return;
        }
        for (int i = 0; i < max; i++) {
            array[n] = i;
            if(judge(n)){
                check(n+1);
            }
        }
    }

    /**
     * 判断冲突
     * @param n
     * @return
     */
    public static boolean judge(int n){
        for (int i = 0; i < n; i++) {
            if(array[i]==array[n]||Math.abs(n-i)==Math.abs(array[n]-array[i])){
                return false;
            }
        }
        return true;
    }

    public static void print(){
        for (int i = 0; i < max; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
}