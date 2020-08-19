package com.cxw.review.sort;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author chengxuwei
 * @date 2020-08-12 09:45
 * @description 排序算法
 */
public class SortDemo {
    public static void main(String[] args) {
        int[] ary = {72498, 97809, 10411, 91457, 10293, 32701, 3322, 5710, 46107, 94417};
        heapSort(ary);
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
        doQuickSort3(ary,0,ary.length-1);

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

    public static void doQuickSort2(int[] ary,int start,int end){
        if(start>=end){
            return;
        }
        int i = start;
        int j = end;
        int temp = ary[start];
        while(i<j){
            while (temp<=ary[j]&&i<j){
                j--;
            }
            while (temp>=ary[i]&&i<j){
                i++;
            }
            if(i<j) {
                swap(ary, i, j);
            }
        }
        if(start!=i){
            ary[start] = ary[i];
            ary[i] = temp;
        }
        doQuickSort2(ary,start,j-1);
        doQuickSort2(ary,j+1,end);
    }

    public static void doQuickSort3(int[] ary,int start,int end){
        if(start>=end){
            return;
        }
        int r = ThreadLocalRandom.current().nextInt(end-start+1)+start;
        int[] numAry = NetherlandsQuestion.partition(ary,start,end,ary[r]);
        doQuickSort3(ary,start,numAry[0]-1);
        doQuickSort3(ary,numAry[1]+1,end);

    }

    public static void heapSort(int[] ary){
        //1.形成大根堆
        for (int i = 0; i < ary.length; i++) {
            doHeapInsert(ary,i);
        }
        for (int i = ary.length-1; i >0 ; i--) {
            //2.将第一个元素（最大）和最后一个元素交换
            swap(ary,0,i);
            //3.调整剩下的元素为大根堆
            doHeapChange(ary,0,i-1);
        }

    }

    private static void doHeapChange(int[] ary, int l, int r) {
        int index = l;
        while((index*2+1)<=r){
            int child1 = index*2+1;
            int child2 = index*2+2;
            int bigger = (child2>r||Math.max(ary[child1],ary[child2])==ary[child1])?child1:child2;
            bigger = (Math.max(ary[bigger],ary[index])==ary[index])?index:bigger;
            if(index==bigger){
                break;
            }
            swap(ary,index,bigger);
            index = bigger;
        }
    }

    private static void doHeapInsert(int[] ary, int i) {
        int index = i;
        while(ary[(index-1)/2]<ary[index]){
           swap(ary,index,(index-1)/2);
           index = (index-1)/2;
        }
    }

    public static void swap(int[] ary,int i,int j){
        if(i==j){
            return;
        }
        ary[i] = ary[i] ^ ary[j];
        ary[j] = ary[i] ^ ary[j];
        ary[i] = ary[i] ^ ary[j];
    }

}