package com.cxw.review.stack;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author chengxuwei
 * @date 2020-08-21 17:31
 * @description 使用队列实现栈
 */
public class StackQueue {
    private Queue<Integer> data;
    private Queue<Integer> help;
    public StackQueue(){
        data = new LinkedBlockingQueue<>();
        help = new LinkedBlockingQueue<>();
    }

    public static void main(String[] args) {
        StackQueue stack = new StackQueue();
        stack.add(1);
        stack.add(2);
        stack.add(3);
        System.out.println(stack.pop());
        stack.add(4);
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }
    public void add(Integer a){
        data.add(a);
    }
    public Integer pop(){
        if(data.size()==0){
            return null;
        }
        int size = data.size();
        for (int i = 0; i < size - 1; i++) {
            Integer a = data.poll();
            help.add(a);
        }
        Integer value = data.poll();
        swap();
        return value;
    }

    private void swap() {
        Queue<Integer> temp = data;
        data = help;
        help = temp;
    }
}