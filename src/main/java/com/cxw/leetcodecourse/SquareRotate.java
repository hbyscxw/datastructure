package com.cxw.leetcodecourse;


/**
 * @author chengxuwei
 * @date 2020-08-24 11:14
 * @description 正方形数组旋转90度
 *
 */
public class SquareRotate {
    public static void main(String[] args) {
        int[][] ary={{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};
        rotate(ary,0,0,3,3);
        print(ary);
    }

    private static void rotate(int[][] ary, int a, int b, int c, int d) {
        while(a<=c&&b<=d){
            doRotate(ary,a++,b++,c--,d--);
        }
    }

    public static void doRotate(int[][] ary,int a,int b,int c,int d){
        int x1 = a;
        int y1 = b;
        int x2 = a;
        int y2 = d;
        int x3 = c;
        int y3 = d;
        int x4 = c;
        int y4 = b;
        while(y1<d&&x2<c&&y3>b&&x4>a) {
            swap(ary, x1, y1++, x2++, y2, x3, y3--, x4--, y4);
        }
    }

    private static void swap(int[][] ary, int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4) {
        int temp = ary[x4][y4];
        ary[x4][y4] = ary[x3][y3];
        ary[x3][y3] = ary[x2][y2];
        ary[x2][y2] = ary[x1][y1];
        ary[x1][y1] = temp;
    }

    private static void print(int[][] map){
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
    }
}