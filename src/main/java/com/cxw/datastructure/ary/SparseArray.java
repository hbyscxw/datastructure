package com.cxw.datastructure.ary;

/**
 * @author chengxuwei
 * @date 2020-05-28 16:01
 * @description 压缩成稀疏数组
 */
public class SparseArray {

    public static void main(String[] args) {
        int[][] ary = new int[11][11];
        ary[0][1] = 1;
        ary[1][2] = 2;
        System.out.println("原始数组：");
        print(ary);
        int[][] spareAry = compress(ary);
        System.out.println("压缩成稀疏数组:");
        print(spareAry);
    }

    private static void print(int[][] ary) {
        for (int i = 0; i < ary.length; i++) {
            for (int j = 0; j < ary[i].length; j++) {
                System.out.print(ary[i][j]+"\t");
            }
            System.out.println();
        }
    }

    private static int[][] compress(int[][] ary) {
        int count = 0;
        for (int i = 0; i < ary.length; i++) {
            for (int j = 0; j < ary[i].length; j++) {
                if(ary[i][j]!=0){
                    count++;
                }
            }
        }
        int num = 0;
        int[][] spareAry = new int[count+1][3];
        spareAry[0][0] = ary.length;
        spareAry[0][1] = ary[ary.length-1].length;
        spareAry[0][2] = count;
        for (int i = 0; i < ary.length; i++) {
            for (int j = 0; j < ary[i].length; j++) {
                if(ary[i][j]!=0){
                    num++;
                    spareAry[num][0] = i;
                    spareAry[num][1] = j;
                    spareAry[num][2] = ary[i][j];
                }
            }
        }
        return spareAry;
    }
}