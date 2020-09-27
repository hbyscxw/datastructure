package com.cxw.leetcodecourse.stack;

import java.util.Stack;

/**
 * @author chengxuwei
 * @date 2020-09-27 11:03
 * @description 单调栈
 * 打印数组内元素，左边最近比自己大，右边最近比自己大
 */
public class SingleStack {
    public static void main(String[] args) {
        int[] ary = {1,6,4,5,7,11,9,8};
        findAdjacentMax(ary);
    }

    private static void findAdjacentMax(int[] ary) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < ary.length; i++) {
            while(!stack.isEmpty()) {
                Integer index = stack.peek();
                if (ary[index] < ary[i]) {
                    String left = "null";
                    index = stack.pop();
                    if(!stack.isEmpty()){
                        left = ary[stack.peek()]+"";
                    }
                    System.out.println("cur="+ary[index]+" left="+left+" right="+ary[i]);
                }else if(ary[index] > ary[i]){
                    break;
                }else{
                    //相等先不管
                    break;
                }
            }
            stack.push(i);
        }

        while(!stack.isEmpty()) {
            Integer index = stack.pop();
            String left = "null";
            if(!stack.isEmpty()){
                left = ary[stack.peek()]+"";
            }
            System.out.println("cur="+ary[index]+" left="+left+" right=null");
        }
    }
}