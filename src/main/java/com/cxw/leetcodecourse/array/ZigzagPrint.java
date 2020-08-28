package com.cxw.leetcodecourse.array;

/**
 * @author chengxuwei
 * @date 2020-08-25 09:34
 * @description 之字形打印
 * 1  2  3  4
 * 5  6  7  8
 * 9 10 11 12
 * 打印：1 2 5 9 6 3 4 7 10 11 8 12
 */
public class ZigzagPrint {
    public static void main(String[] args) {
        int[][] ary = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        printMatrixZigZag(ary);
    }

    private static void printBias(int[][] ary, int x1, int y1, int x2, int y2, boolean printOrder) {
        if (printOrder) {
            while (x1 != x2 + 1) {
                System.out.print(ary[x1++][y1--] + " ");
            }
        } else {
            while (x2 != x1 - 1) {
                System.out.print(ary[x2--][y2++] + " ");
            }
        }
    }

    public static void printMatrixZigZag(int[][] matrix) {
//        int x1 = 0;
//        int y1 = 0;
//        int x2 = 0;
//        int y2 = 0;
//        int endX = matrix.length - 1;
//        int endY = matrix[0].length - 1;
//        boolean fromUp = false;
//        while (x1 != endX + 1) {
//            printBias(matrix, x1, y1, x2, y2, fromUp);
//            x1 = y1 == endY ? x1 + 1 : x1;
//            y1 = y1 == endY ? y1 : y1 + 1;
//            y2 = x2 == endX ? y2 + 1 : y2;
//            x2 = x2 == endX ? x2 : x2 + 1;
//            fromUp = !fromUp;
//        }
//        System.out.println();

        int[][] ary = matrix;
        //x1,y1往右，然后往下移动
        int x1 = 0;
        int y1 = 0;
        //x2,y2往下，然后往右移动
        int x2 = 0;
        int y2 = 0;
        int endY = ary[0].length-1;
        int endX = ary.length-1;
        boolean printOrder = false;
        while(x1<=endX&&y1<=endY&&x2<=endX&&y2<=endY){
            printBias(ary,x1,y1,x2,y2,printOrder);
            if(x1==endX&&y1==endY){
                break;
            }
            if(y1<endY){
                y1++;
            }else if(x1<endX){
                x1++;
            }
            if(x2<endX){
                x2++;
            }else if(y2<endY){
                y2++;
            }
            printOrder = !printOrder;
        }
    }
}