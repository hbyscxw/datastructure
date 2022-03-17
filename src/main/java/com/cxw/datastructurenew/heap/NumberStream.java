package com.cxw.datastructurenew.heap;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * @author chengxuwei
 * @create 2022/3/15 19:52
 * @desc 在一个数据流中，随时取中位数
 */
public class NumberStream {
    public static void main(String[] args) {
        int[] ary = {5,3,7,4,6,8,9};
        int mid = midNum(ary);
        System.out.println(mid);
    }

    private static int midNum(int[] ary) {
        if(ary == null || ary.length == 0){
            return 0;
        }
        PriorityQueue<Integer> bigHeap = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> smallHeap = new PriorityQueue<>();
        for (int i = 0; i < ary.length; i++) {
            if(i==0){
                bigHeap.add(ary[i]);
            }else {
                int bt = bigHeap.peek();
                if (ary[i] <= bt) {
                    bigHeap.add(ary[i]);
                }else{
                    smallHeap.add(ary[i]);
                }
                //平衡大根堆和小根堆元素的个数 保持 size 相差 小于2
                balance(bigHeap,smallHeap);
            }
        }
        int bt = 0;
        int st = 0;
        if(bigHeap.size()==smallHeap.size()) {
            bt = bigHeap.poll();
            st = smallHeap.poll();
            return (bt+st)/2;
        }else if(bigHeap.size()>smallHeap.size()){
            return bigHeap.poll();
        }else{
            return smallHeap.poll();
        }
    }

    private static void balance(PriorityQueue<Integer> bigHeap, PriorityQueue<Integer> smallHeap) {
        if(bigHeap.size() - smallHeap.size()>1){
            smallHeap.add(bigHeap.poll());
        }else if(smallHeap.size() - bigHeap.size()>1){
            bigHeap.add(smallHeap.poll());
        }
    }
}
