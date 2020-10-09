package com.cxw.leetcodecourse.stack;

import java.util.Stack;

/**
 * @author chengxuwei
 * @date 2020-09-30 11:03
 * @description
 * 最大可见山峰对
 * 问题描述：
 * 指定一个数组arr；
 * 数组的中元素的含义为一个环形山脉每座山的高度；
 * 规定，相邻的两座山脉之间可以彼此看见；
 * 如果两座山脉不相邻，两座山脉之间的所有山脉不大于这两座山脉的最小值时，这两座山脉可以互相看见。
 * 现在希望你可以求出可以彼此看见的山脉总对数。
 */
public class MaxPeak {
    public static void main(String[] args) {
        int[] ary = {5,5,4,4,3,3};
        int maxPeakNum = maxPeak(ary);
        System.out.println(maxPeakNum);
    }

    private static int maxPeak(int[] ary) {
        int res = 0;
        int maxIndex = getMaxIndex(ary);
        int index = -1;
        Stack<Peak> stack = new Stack<>();
        stack.push(new Peak(maxIndex));
        while(!stack.isEmpty()&&maxIndex!=index) {
            if(index==-1){
                index = maxIndex;
            }
            Peak p = stack.peek();
            int nextIndex = getNextIndex(ary, index);
            if (ary[index] == ary[nextIndex]) {
                p.times++;
            } else if (ary[index] > ary[nextIndex]) {
                stack.push(new Peak(nextIndex));
            } else {
                p = stack.pop();
                if (p.times >= 2) {
                    res += getInternalNum(p.times)+2*p.times;
                } else {
                    res += getInternalNum(p.times);
                }
            }
            index = nextIndex;
        }
        while(!stack.isEmpty()){
            int times = stack.pop().times;
            res+=getInternalNum(times);
            if(!stack.isEmpty()){
                res+=times;
                if(stack.size()>1){
                    res+=times;
                }else{
                    res+=stack.peek().times>1?times:0;
                }
            }
        }
        return res;
    }

    private static int getInternalNum(int times) {
        if(times>1) {
            return times*(times-1)/2;
        }else{
            return 0;
        }
    }

    private static int getNextIndex(int[] ary, int index) {
        if(index+1>=ary.length){
            return 0;
        }else{
            return index+1;
        }
    }

    private static int getMaxIndex(int[] ary) {
        int maxIndex = 0;
        for (int i = 1; i < ary.length; i++) {
            if(ary[maxIndex]<ary[i]){
                maxIndex = i;
            }
        }
        return maxIndex;
    }
}
class Peak{
    int index;
    int times;
    public Peak(int index){
        this.index = index;
        times = 1;
    }
}