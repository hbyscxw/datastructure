package com.cxw.leetcodecourse.array;

/**
 * @author chengxuwei
 * @date 2020-08-25 15:45
 * @description 一个二维数组（行列都排好序）中找数
 * 1  3  5  6
 * 2  5  7  9
 * 4  6  8  10
 * 找4
 */
public class ArraySearch {
    public static void main(String[] args) {
        int[][] ary = {{1,3,5,6},{2,5,7,9},{4,6,8,10}};
        searchNum(ary,10);
    }

    private static void searchNum(int[][] ary, int target) {
        doSearchNum(ary,target,0,ary[0].length-1);
    }

    private static void doSearchNum(int[][] ary, int target, int x, int y) {
        if(x<ary.length&&y>=0){
            if(ary[x][y]==target){
                System.out.println("找到目标数("+x+","+y+")");
            }else if(ary[x][y]<target){
                doSearchNum(ary,target,++x,y);
            }else{
                doSearchNum(ary,target,x,--y);
            }
        }else{
            System.out.println("未找到！");
        }

    }
}