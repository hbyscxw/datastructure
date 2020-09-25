package com.cxw.leetcodecourse.bfprt;


/**
 * @author chengxuwei
 * @date 2020-09-24 10:35
 * @description bfprt算法
 * 从n个元素中选出第k小或第k大的元素，同时也能选出前k小或前k大的所有元素。
 * 步骤：
 * 1.每5个元素一组，分组
 * 2.将分组后的数组各自排序
 * 3.取中位数，形成新数组
 * 4.递归调用bfprt(newAry,newAry.length/2);
 * 5.取第4步结果X判断是否要找的k，如果大于k就找右边，小于找左边
 */
public class BfprtAlgorithm {

    public static void main(String args []){
        int [] array ={4,45,32,67,5,78,4556,781,1,3,43,765,9,22};
        int k ;
        for (k =1; k<= array.length; k ++){
            int k_Num= quickSearch( array,0, array .length-1, k);
            System.out.println(" 第 "+ k +" 小的元素是： " +k_Num );
        }
    }
    public static int quickSearch( int []array , int left, int right, int k){
        //left,right为下标， k 为第k 小的元素
        if( left== right){
            return array [left ];
        }
        int mid =getArrayMid (array , left, right); //mid 为中位数 id
        int mid_new =partition (array , left, right, mid );// 根据中位数划分数组，是中位数在最终的位置上
        // 比较中位数与要查找数值的大小
        if( mid_new== k-1){
            return array [mid_new ];
        } else if (mid_new <k -1){
            left= mid_new+1;
            return quickSearch( array, left, right, k);
        } else {
            right= mid_new-1;
            return quickSearch( array, left, right, k);
        }
    }
    public static int getArrayMid( int a [], int l, int r){ //l,r均为数组下标 , 返回中位数的位置
        if( l== r){
            return l ;
        }
        int i =l ;
        for (;i <=r -l -5;i +=5){// 子数组的元素个数为 5 个时
            insertSort( a, i, i+4); //对当前的五个数数进行排序
            swap( a, l+( i- l)/5, i+2); //将所有中位数放在数组 array 的前几位上
        }
        if( i< r- l){
            insertSort( a, i, r- l);
            swap( a, l+( i- l)/5,( i+ r- l)/2); //将最后一组数的中位数放在数组 array 的前几位上
        }
        return getArrayMid( a, l, l+( i- l)/5); // 返回中位数的中位数的 id

    };
    public static int partition( int a [], int l, int r, int mid){
        int pivot =a [l ];
        while (l <r ){
            while (l <r && pivot<= a[ r]){
                r--;
            }
            a[ l]= a[ r];
            while (l <r && pivot> a[ l]){
                l++;
            }
            a[ r]= a[ l];
        }
        a[ l]= pivot;
        return l ;
    };
    public static void swap(int []a , int i , int j ){
        int temp= a[ i];
        a[ i]= a[ j];
        a[ j]= temp;
    }
    public static void insertSort( int a [], int l , int r ){ // 对下标为 l到 r之间的元素进行排序
        if( l< r){
            for( int i= l; i< r; i++){
                int j= i+1;
                int temp= a[ j];
                while( j> l&& temp< a[ j-1]){
                    a[ j]= a[ j-1];
                    j--;
                }
                a[ j]= temp;
            }
        }
    }
    public static void printArray( int arr []){
        for( int k=0; k< arr.length; k++){
            System.out.print( arr[ k]+ " ");
        }
        System.out.println();
    }

}