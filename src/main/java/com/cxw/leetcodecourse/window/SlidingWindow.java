package com.cxw.leetcodecourse.window;

import java.util.LinkedList;

/**
 * @author chengxuwei
 * @date 2020-09-25 09:51
 * @description 滑动窗口
 */
public class SlidingWindow {
    public static void main(String[] args) {
        int[] ary = {5, 2, 4, 2, 5, 1, 6, 7, 1};
        int l = 1;
        int r = 4;
        LinkedList<Integer> list = new LinkedList<>();
        int max = slidingWindowMax(ary, list, l, r);
        System.out.println(max);
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
}