package com.cxw.datastructure.recursion;

/**
 * @author chengxuwei
 * @date 2020-06-10 15:08
 * @description 列出{1,2,3}所有全排列
 */
public class Permutation {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4};
        bfs(arr,0);
    }

    private static void bfs(int[] arr, int start) {
        /*
         * start和后面的集合去交换
         * 递归终止条件是，start后面没有集合
         */
        if(start==arr.length){
            for(int one : arr){
                System.out.print(one);
            }
            System.out.println();
        }

        for (int i = start; i < arr.length; i++) {
            if(isUnique(arr,start,i)){
                swap(arr,start,i);
                bfs(arr,start+1);
                /*
                 * 为什么要再交换呢？
                 * 你比如还是{1，2，3}，我拿着{1}去交换{2，3}中间的{2}，交换完成之后，
                 * 显然成了{2} {1，3} 即2,1,3和2,3,1
                 * 但是，我还要拿{1}去换{2，3}中的3啊，数组成了[2,1,3][2,3,1]我再拿第一个位置交换第三个位置显然乱套
                 * 所以，我们恢复原样。当递归完成，回到上一层的时候，上一层的start，i还在哪给你记着呢，你本来换了哪个数
                 * 原原本本给换回来。每一层都一样，所以不会乱。
                 */
                swap(arr,start,i);
            }
        }
    }

    private static void swap(int[] arr, int m, int n) {
        int temp = arr[m];
        arr[m] = arr[n];
        arr[n] = temp;
    }

    private static boolean isUnique(int[] arr, int start, int end) {
        /*
         * 如果在需要被交换的数a[end]之前出现了和它一样的数，例如{1}想要交换到{2，3，4}中的4没有问题，
         * 换完之后组成新的集合{2,3,1}进行递归，递归会处理好{2，3，1}的全排列
         * 但是如果{1}想要和{4，3，4}中的后面一个4进行交换就需要排除，因为当{1}和第一个4交换，已经将{1，3，4}的全排列
         * 结果全部给出了。
         * 因此，我们逐个检查a[end]这个元素之前，有没有和它 一样的数
         */
        for (int i = start; i < end; i++) {
            if(arr[i] == arr[end]){
                return false;
            }
        }
        return true;
    }
}
