package com.cxw.datastructure.sort;

public class SortDemo {
    public static void main(String[] args) {

        int[] ary = {-1,2,-3,6,10,4};
        selectSort(ary);
        print(ary);

    }

    /**
     * 算法时间复杂度 O(n^2) 交换过程比冒泡少，比冒泡快
     * @param ary
     */
    public static void selectSort(int[] ary){
        for (int i = 0; i < ary.length-1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < ary.length; j++) {
                //第一个元素与后面相比较
                if(ary[minIndex]>ary[j]){
                    minIndex = j;
                }
            }
            //发生变化才交换
            if(minIndex!=i){
                swap(ary,i,minIndex);
            }
        }

    }
    /**
     * 算法时间复杂度 O(n^2)
     * @param ary
     */
    public static void bubbleSort(int[] ary){
        for (int i = 0; i < ary.length -1; i++) {
            boolean changeFlag = false;
            for (int j = 0; j < ary.length-1-i; j++) {
                // 相邻的两个元素相比较
                if(ary[j]>ary[j+1]){
                    changeFlag = true;
                    swap(ary,j,j+1);
                }
            }
            if(!changeFlag){
                //如果一轮中没有交换说明数组已经是有序的
                break;
            }
        }
    }

    private static void swap(int[] ary, int a, int b) {
        int temp = ary[a];
        ary[a] = ary[b];
        ary[b] = temp;
    }

    private static void print(int[] ary) {
        for (int i = 0; i < ary.length; i++) {
            System.out.print(ary[i]+"\t");
        }
        System.out.println();
    }
}
