package com.cxw.leetcodecourse.dynamic;

/**
 * @author chengxuwei
 * @date 2020-09-17 11:40
 * @description
 * 给你一-个二维数组，二维数组中的每个数都是正数，要求从左上
 * 角走到右下角，每一步只能向右或者向下。沿途经过的数字要累
 * 加起来。返回最小的路径和。
 */
public class MinPathSum {
    public static void main(String[] args) {
        int[][] matrix = {{ 3, 1, 0, 2 }, { 4, 3, 2, 1 }, { 5, 2, 1, 0 }};
        int sum = 0;
        sum = walk(matrix,0,0,sum);
        System.out.println(sum);
    }

    private static int walk(int[][] matrix, int i, int j,int sum) {
        if(i==matrix.length-1&&j==matrix[0].length-1){
            sum += matrix[i][j];
            return sum;
        }
        if(i==matrix.length-1){
            return walk(matrix,i,j+1,sum+matrix[i][j]);
        }
        if(j==matrix[0].length-1){
            return walk(matrix,i+1,j,sum+matrix[i][j]);
        }
        int right = walk(matrix,i,j+1,sum+matrix[i][j]);
        int down = walk(matrix,i+1,j,sum+matrix[i][j]);
        return Math.min(right,down);
    }
}