package com.cxw.datastructure.stack;

/**
 * @author chengxuwei
 * @date 2020-06-05 15:58
 * @description
 */
public class StackDemo {
    public static void main(String[] args) {
        ArrayStack stack = new ArrayStack(10);
        for (int i = 0; i < 11; i++) {
            stack.push(i);
        }
        stack.show();
        for (int i = 0; i < 11; i++) {
            int num = stack.pop();
            System.out.println(num);
        }
    }
}

class ArrayStack{
    private int maxSize;
    private int top;
    private int ary[];

    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        top = -1;
        ary = new int[maxSize];
    }

    public void push(int value){
        if(isFull()){
            System.out.println("栈已满，不能再添加元素！");
            return;
        }
        top++;
        ary[top] = value;
    }

    public int pop(){
        if(isEmpty()){
            throw new RuntimeException("栈已空，不能再弹出元素！");
        }
        int value = ary[top];
        top--;
        return value;
    }

    public void show(){
        for (int i = top; i >= 0; i--) {
            System.out.println(ary[i]);
        }
    }

    public boolean isFull(){
        return top==maxSize-1;
    }
    public boolean isEmpty(){
        return top==-1;
    }

}