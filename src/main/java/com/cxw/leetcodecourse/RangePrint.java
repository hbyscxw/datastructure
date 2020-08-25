package com.cxw.leetcodecourse;

/**
 * @author chengxuwei
 * @date 2020-08-24 10:01
 * @description 转圈打印
 *        int[][] ary = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};
 *        打印 1，2，3，4，8，12，16，15，14，13，9，5，6，7，11，10
 */
public class RangePrint {
    public static void main(String[] args) {
        int[][] ary = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};
        rangePrint(ary,0,0,ary.length-1,ary[0].length-1);
    }

    private static void rangePrint(int[][] ary, int x0, int y0, int x1, int y1) {
        while(x0<=x1&&y0<=y1){
            doRangePrint(ary,x0++,y0++,x1--,y1--);
        }
    }

    private static void doRangePrint(int[][] ary, int a, int b, int c, int d) {
        if(a==c){
            for (int i = b; i <= d; i++) {
                System.out.print(ary[a][i]+" ");
            }
        }else if(b==d){
            for (int i = a; i <= c; i++) {
                System.out.print(ary[i][b]+" ");
            }
        }else{
            int curA = a;
            int curB = b;
            while(curB!=d){
                System.out.print(ary[a][curB]+" ");
                curB++;
            }
            while(curA!=c){
                System.out.print(ary[curA][d]+" ");
                curA++;
            }
            while(curB!=b){
                System.out.print(ary[c][curB]+" ");
                curB--;
            }
            while(curA!=a){
                System.out.print(ary[curA][b]+" ");
                curA--;
            }
        }
    }
}