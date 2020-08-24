package com.cxw.review.stack;

import java.util.Stack;

/**
 * @author chengxuwei
 * @date 2020-08-21 16:29
 * @description
 * 实现一个特殊的栈，在实现栈的基本功能的基础上，再实现返回栈中最小元素的操作。
 * [要求]
 * 1. pop、push、getMin操作的时间复杂度都是0(1)。
 * 2.设计的栈类型可以使用现成的栈结构。
 */
public class MinStack {
    Stack<Integer> data = new Stack<>();
    Stack<Integer> minData = new Stack<>();

    public static void main(String[] args) {
        MinStack stack = new MinStack();
        stack.push(11);
        System.out.println(stack.getMin());
        stack.push(10);
        System.out.println(stack.getMin());
        stack.push(12);
        System.out.println(stack.getMin());
        stack.pop();
        System.out.println(stack.getMin());
        stack.pop();
        System.out.println(stack.getMin());
        stack.pop();
        System.out.println(stack.getMin());
    }
    public Integer pop(){
        minData.pop();
        return data.pop();
    }
    public void push(Integer value){
        Integer minValue = null;
        if(!minData.isEmpty()) {
            minValue = minData.peek();
        }
        if(minValue==null||value<minValue) {
            minData.push(value);
        }else{
            minData.push(minValue);
        }
        data.push(value);
    }
    public Integer getMin(){
        if(!minData.isEmpty()) {
            return minData.peek();
        }
        return null;
    }
}