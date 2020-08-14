package com.cxw.review.sort;

import java.util.Arrays;

/**
 * @author chengxuwei
 * @date 2020-08-12 09:45
 * @description 排序算法
 */
public class SortDemo {
    public static void main(String[] args) {
        int[] ary = {84724, 2978, 43851, 17502, 71312, 37356, 23106, 67021, 75096, 27942};
        quickSort(ary);
        System.out.println(Arrays.toString(ary));
    }
    public static void bubbleSort(int[] ary){
        for (int i = 0; i < ary.length-1; i++) {
            for (int j = 0; j < ary.length-1-i; j++) {
                if(ary[j+1]<ary[j]){
                    swap(ary,j,j+1);
                }
            }
        }
    }

    public static void selectionSort(int[] ary){
        for (int i = 0; i < ary.length; i++) {
            int minIndex = i;
            for (int j = i+1; j < ary.length; j++) {
                if(ary[j]<ary[minIndex]){
                    minIndex = j;
                }
            }
            if(minIndex!=i) {
                swap(ary, minIndex, i);
            }
        }
    }

    public static void insertSort(int[] ary){
        for (int i = 1; i < ary.length; i++) {
            int temp = ary[i];
            int j = 0;
            for (j = i-1; j >=0 && ary[j]>temp; j--) {
                ary[j+1] = ary[j];
            }
            ary[j+1] = temp;
        }
    }

    public static void shellSort(int[] ary){
        for (int k = ary.length/2; k > 0 ; k/=2) {
            for (int i = k; i < ary.length; i+=k) {
                int temp = ary[i];
                int j = 0;
                boolean m = false;
                for (j = i-k; j >=0 ; j-=k) {
                    if(ary[j]>temp){
                        ary[j+k] = ary[j];
                        m = true;
                    }else{
                        break;
                    }
                }
                if(m) {
                    ary[j + k] = temp;
                }
            }
        }
    }

    public static void mergeSort(int[] ary){
        doMergeSort(ary,0,ary.length-1);
    }

    private static void doMergeSort(int[] ary,int left,int right){
        if(left == right){
            return;
        }
        int mid = (left+right)/2;
        doMergeSort(ary,left,mid);
        doMergeSort(ary,mid+1,right);
        merge(ary,left,mid,right);
    }

    private static void merge(int[] ary, int left,int mid, int right) {
        int[] temp = new int[right-left+1];
        int tIndex = 0;
        int l = left;
        int r = mid+1;
        while(l<=mid&&r<=right){
            if(ary[l]<=ary[r]){
                temp[tIndex++] = ary[l++];
            }else{
                temp[tIndex++] = ary[r++];
            }
        }
        while(l<=mid){
            temp[tIndex++] = ary[l++];
        }
        while(r<=right){
            temp[tIndex++] = ary[r++];
        }
        //copy回原数组
        System.arraycopy(temp,0,ary,left,temp.length);
    }

    public static void quickSort(int[] ary){
        doQuickSort(ary,0,ary.length-1);

    }

    private static void doQuickSort(int[] ary, int left, int right) {
//        if(left >= right){
//            return;
//        }
//        int mid = left+((right-left)>>1);
//        int l = left;
//        int r = right;
//        int temp = ary[mid];
//        while(l<r){
//            while (ary[l] < temp) {
//                l++;
//            }
//            while (ary[r] > temp) {
//                r--;
//            }
//            if(ary[l]>ary[r]){
//                swap(ary,l,r);
//            }
//        }
//        doQuickSort(ary,left,r-1);
//        doQuickSort(ary,r+1,right);
        if(left > right) {
            return;
        }
        // base中存放基准数
        int base = ary[left];
        int i = left, j = right;
        while(i != j) {
            // 顺序很重要，先从右边开始往左找，直到找到比base值小的数
            while(ary[j] >= base && i < j) {
                j--;
            }

            // 再从左往右边找，直到找到比base值大的数
            while(ary[i] <= base && i < j) {
                i++;
            }

            // 上面的循环结束表示找到了位置或者(i>=j)了，交换两个数在数组中的位置
            if(i < j) {
                int tmp = ary[i];
                ary[i] = ary[j];
                ary[j] = tmp;
            }
        }

        // 将基准数放到中间的位置（基准数归位）
        ary[left] = ary[i];
        ary[i] = base;

        // 递归，继续向基准的左右两边执行和上面同样的操作
        // i的索引处为上面已确定好的基准值的位置，无需再处理
        doQuickSort(ary, left, i - 1);
        doQuickSort(ary, i + 1, right);
    }


    public static void swap(int[] ary,int i,int j){
        ary[i] = ary[i] ^ ary[j];
        ary[j] = ary[i] ^ ary[j];
        ary[i] = ary[i] ^ ary[j];
    }
}