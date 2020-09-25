package com.cxw.leetcodecourse.window;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author chengxuwei
 * @date 2020-09-25 09:57
 * @description 生成窗口最大值数组
 * [题目]
 * 有一个整型数组arr和一个大小为w的窗口从数组的最左边滑到最右边，窗口每次向右边滑一个位置。
 * 例如，数组为[4,3,5,4,3,3,6,7]，窗口大小为3时:
 * [4 3 5] 4 3 3 6 7窗口中最大值为5
 * 4 [3 5 4] 3 3 6 7窗口中最大值为5
 * 4 3 [5 4 3] 3 6 7窗口中最大值为5
 * 4 3 5 [4 3 3] 6 7窗口中最大值为4
 * 4 3 5 4 [3 3 6] 7窗口中最大值为6
 * 4 3 5 4 3 [3 6 7]窗口中最大值为7
 * 如果数组长度为n，窗口大小为w,则一共产生n-w+1个窗口的最大值。
 * 请实现一个函数。
 * 输入:整型数组arr,窗口大小为w.
 * 输出: 一个长度为n-w+1的数组res, res[i]表示每一种窗口状态下的为例，结果应该返回[5,5,5,4,6,7]
 */
public class WindowMax {
    public static void main(String[] args) {
        int[] ary = {4, 3, 5, 4, 3, 3, 6, 7};
        int w = 3;
        int[] maxAry = windowMaxAry(ary, w);
        System.out.println(Arrays.toString(maxAry));
    }

    private static int[] windowMaxAry(int[] ary, int w) {
        int[] maxAry = new int[ary.length - w + 1];
        int l = -1;
        int r = -1;
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < ary.length; i++) {
            r = l + w + 1;
            if (r <= ary.length) {
                maxAry[i] = slidingWindowMax(ary, list, l++, r);
            }
        }
        return maxAry;
    }

    private static int slidingWindowMax(int[] ary, LinkedList<Integer> list, int l, int r) {
        if (l >= r) {
            return Integer.MIN_VALUE;
        }
        for (int i = 0; i < r; i++) {
            int value = ary[i];
            while (!list.isEmpty() && ary[list.peekLast()] <= value) {
                list.pollLast();
            }
            list.addLast(i);
        }
        int resIndex = 0;
        for (int i = 0; i <= l; i++) {
            if (!list.isEmpty() && list.peek() == i) {
                list.pollFirst();
            }
            if (i == l) {
                resIndex = list.peekFirst();
            }
        }
        return ary[resIndex];
    }

    public static int[] getMaxWindow(int[] arr, int w) {
        if (arr == null || w < 1 || arr.length < w) {
            return null;
        }
        LinkedList<Integer> qmax = new LinkedList<Integer>();
        int[] res = new int[arr.length - w + 1];
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            //双端队列不为空，并且尾部的数值小于新加入的数值，则弹出尾部下标
            while (!qmax.isEmpty() && arr[qmax.peekLast()] <= arr[i]) {
                qmax.pollLast();
            }
            qmax.addLast(i);
            //判断首位上的数据是否过期，进行下标和i-w的判断，过期就弹出下标，不过期就继续下面的操作
            //窗口如果没有形成完全，则不会弹出。此处针对不同题目需要进行修改，看窗口是否要全完全显示之后才开始计算最大值
            if (qmax.peekFirst() == i - w) {
                qmax.pollFirst();
            }
            //如果i还在窗口之中，则返回第一个数值。
            if (i >= w - 1) {
                res[index++] = arr[qmax.peekFirst()];
            }
        }
        return res;
    }
}