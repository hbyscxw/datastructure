package com.cxw.datastructure.sort;

import java.util.Arrays;

/**
 * @author chengxuwei
 * @date 2020-06-15 11:30
 * @description 排序
 */
public class SortDemo {
    public static void main(String[] args) {
//        int[] ary = {10, 1, 4, 5, 100, 50, 30, 20, 6, 7, 8};
        int[] ary = {10,9,8,7};
        quickSort(ary);
        System.out.println(Arrays.toString(ary));
    }

    private static void radixSort(int[] ary) {
        //取最大位
        int max = ary[0];
        for (int i = 1; i < ary.length ; i++) {
            if(max<ary[i]) {
                max = ary[i];
            }
        }
        int maxIndex = (max+"").length();

        int[][] radix = new int[10][ary.length];
        int[] radixCount = new int[10];
        for (int n = 0; n < maxIndex; n++) {
            for (int k = 0; k < ary.length; k++) {
                //取n位   Math.pow 计算 n次方
                int value = ary[k]/(int)Math.pow(10,n)%10;
                radix[value][radixCount[value]] = ary[k];
                radixCount[value]++;
            }
            int index = 0;
            for (int i = 0; i < radixCount.length; i++) {
                int count = radixCount[i];
                if(count>0){
                    for (int j = 0; j < count; j++) {
                        ary[index++] = radix[i][j] ;
                    }
                }
                radixCount[i] = 0;
            }
        }

        /*
        int[][] radix = new int[10][ary.length];
        int[] radixCount = new int[10];
        for (int k = 0; k < ary.length; k++) {
            //取个位
            int value = ary[k]%10;
            radix[value][radixCount[value]] = ary[k];
            radixCount[value]++;
        }
        int index = 0;
        for (int i = 0; i < radixCount.length; i++) {
            int count = radixCount[i];
            if(count>0){
                for (int j = 0; j < count; j++) {
                    ary[index++] = radix[i][j] ;
                }
            }
            radixCount[i] = 0;
        }


        for (int k = 0; k < ary.length; k++) {
            //取十位
            int value = ary[k]/10 % 10;
            radix[value][radixCount[value]] = ary[k];
            radixCount[value]++;
        }
        index = 0;
        for (int i = 0; i < radixCount.length; i++) {
            int count = radixCount[i];
            if(count>0){
                for (int j = 0; j < count; j++) {
                    ary[index++] = radix[i][j] ;
                }
            }
            radixCount[i] = 0;
        }

        for (int k = 0; k < ary.length; k++) {
            //取百位
            int value = ary[k]/100 % 10;
            radix[value][radixCount[value]] = ary[k];
            radixCount[value]++;
        }
        index = 0;
        for (int i = 0; i < radixCount.length; i++) {
            int count = radixCount[i];
            if(count>0){
                for (int j = 0; j < count; j++) {
                    ary[index++] = radix[i][j] ;
                }
            }
            radixCount[i] = 0;
        }
        */
    }

    private static void mergeSort(int[] ary) {
        doMergeSort(ary, 0, ary.length - 1);
    }

    private static void doMergeSort(int[] ary, int start, int end) {
        if (start < end) {
            int mid = (start + end) / 2;
            doMergeSort(ary, start, mid);
            doMergeSort(ary, mid + 1, end);
            doMerge(ary, start, mid, end);
        }
    }

    private static void doMerge(int[] ary, int start, int mid, int end) {
        int i = start;
        int j = mid + 1;
        int[] temp = new int[end - start + 1];
        int t = 0;
        while (i <= mid && j <= end) {
            if (ary[i] <= ary[j]) {
                temp[t] = ary[i];
                i++;
                t++;
            } else {
                temp[t] = ary[j];
                j++;
                t++;
            }
        }

        while (j <= end) {
            temp[t] = ary[j];
            j++;
            t++;
        }

        while (i <= mid) {
            temp[t] = ary[i];
            i++;
            t++;
        }
        System.arraycopy(temp, 0, ary, start, temp.length);
    }

    public static void quickSort(int[] ary) {
        doQuickSort2(ary, 0, ary.length - 1);
    }
    private static void doQuickSort2(int[] ary, int left, int right) {
        if (left >= right) {
            return;
        }
        int l = left;
        int r = right;
        int minValue = ary[l];
        while (l < r) {
            while (ary[r] >= minValue && l < r) {
                r--;
            }
            while (ary[l] <= minValue && l < r) {
                l++;
            }
            if (l < r) {
                swap(ary, l, r);
            }
        }
        if(l!=left) {
            // 将基准数放到中间的位置（基准数归位）
            ary[left] = ary[l];
            ary[l] = minValue;
        }
        doQuickSort2(ary, left, l - 1);
        doQuickSort2(ary, l + 1, right);
    }


    private static void doQuickSort(int[] ary, int left, int right) {
        int l = left;
        int r = right;
        int midValue = ary[(l + r) / 2];
        while (l < r) {
            while (ary[l] < midValue) {
                l++;
            }
            while (ary[r] > midValue) {
                r--;
            }
            if (l >= r) {
                break;
            }
            swap(ary, l, r);
            if (ary[l] == midValue) {
                r--;
            }
            if (ary[r] == midValue) {
                l++;
            }

        }
        if (l == r) {
            l++;
            r--;
        }
        //左递归
        if (left < r) {
            doQuickSort(ary, left, r);
        }
        //右递归
        if (l < right) {
            doQuickSort(ary, l, right);
        }

    }

    private static void shellChangeSort(int[] ary) {
        /*
        //第一轮
        for (int i = 3; i < ary.length; i++) {
            for (int j = i-3; j >=0 ; j-=3) {
                if(ary[j]>ary[j+3]){
                    swap(ary,j,j+3);
                }
            }
        }
        //第二轮
        for (int i = 1; i < ary.length; i++) {
            for (int j = i-1; j >=0 ; j-=1) {
                if(ary[j]>ary[j+1]){
                    swap(ary,j,j+1);
                }
            }
        }
        */

        for (int step = ary.length / 2; step > 0; step /= 2) {
            for (int i = step; i < ary.length; i++) {
                for (int j = i - step; j >= 0; j -= step) {
                    if (ary[j] > ary[j + step]) {
                        swap(ary, j, j + step);
                    }
                }
            }
        }
    }

    private static void shellInsertSort(int[] ary) {
        for (int step = ary.length / 2; step > 0; step /= 2) {
            for (int i = step; i < ary.length; i++) {
                int insertValue = ary[i];
                boolean lt = false;
                int index = 0;
                for (int j = i - step; j >= 0; j -= step) {
                    if (ary[j] > insertValue) {
                        ary[j + step] = ary[j];
                        index = j;
                        lt = true;
                    } else {
                        break;
                    }
                }
                if (lt) {
                    ary[index] = insertValue;
                }
            }
        }

//        for (int i = 5; i < ary.length; i++) {
//            int insertValue = ary[i];
//            boolean lt = false;
//            int index = 0;
//            for (int j = i-5; j >=0; j-=5) {
//                if(ary[j]>insertValue){
//                    ary[j+5] = ary[j];
//                    index = j;
//                    lt = true;
//                }else {
//                    break;
//                }
//            }
//            if(lt){
//                ary[index] = insertValue;
//            }
//        }
    }

    private static void insertSort(int[] ary) {
        for (int i = 1; i < ary.length; i++) {
            int insertValue = ary[i];
            boolean lt = false;
            int index = 0;
            for (int j = i - 1; j >= 0; j--) {
                if (ary[j] > insertValue) {
                    ary[j + 1] = ary[j];
                    index = j;
                    lt = true;
                } else {
                    break;
                }
            }
            if (lt) {
                ary[index] = insertValue;
            }
        }
    }

    /**
     * 算法时间复杂度
     *
     * @param ary
     */
    public static void insertSort2(int[] ary) {
        for (int i = 1; i < ary.length; i++) {
            int index = i - 1;
            int insertValue = ary[i];
            while (index >= 0 && ary[index] > insertValue) {
                ary[index + 1] = ary[index];
                index--;
            }
            ary[index + 1] = insertValue;
        }


    }

    /**
     * 算法时间复杂度 O(n^2) 交换过程比冒泡少，比冒泡快
     *
     * @param ary
     */
    public static void selectSort(int[] ary) {
        for (int i = 0; i < ary.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < ary.length; j++) {
                //第一个元素与后面相比较
                if (ary[minIndex] > ary[j]) {
                    minIndex = j;
                }
            }
            //发生变化才交换
            if (minIndex != i) {
                swap(ary, i, minIndex);
            }
        }

    }

    /**
     * 算法时间复杂度 O(n^2)
     *
     * @param ary
     */
    private static void bubbleSort(int[] ary) {
        for (int i = 0; i < ary.length - 1; i++) {
            boolean changeFlag = false;
            for (int j = 0; j < ary.length - 1 - i; j++) {
                if (ary[j] > ary[j + 1]) {
                    changeFlag = true;
                    swap(ary, j, j + 1);
                }
            }
            //如果一轮中没有交换，说明已经排好序
            if (!changeFlag) {
                break;
            }
        }
    }

    private static void swap(int[] ary, int j, int i) {
        int temp = ary[i];
        ary[i] = ary[j];
        ary[j] = temp;
    }
}