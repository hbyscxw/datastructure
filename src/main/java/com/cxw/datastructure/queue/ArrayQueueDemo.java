package com.cxw.datastructure.queue;

import java.util.Scanner;

/**
 * @author chengxuwei
 * @date 2020-05-29 09:51
 * @description
 */
public class ArrayQueueDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入命令：");
        ArrayQueue queue = new ArrayQueue(2);
        while (true){
            String cmd = scanner.nextLine();
            if (cmd.equals("q")){
                scanner.close();
                break;
            }else if (cmd.equals("a")){
                System.out.println("请输入数字：");
                int i = scanner.nextInt();
                queue.add(i);
                queue.print();
            }else if (cmd.equals("p")){
                queue.print();
            }else if (cmd.equals("g")){
                int i = queue.getFirst();
                System.out.println("取第一个元素："+i);
                queue.print();
            }
        }
    }
}

class ArrayQueue{
    private int maxSize;
    private int frontIndex;
    private int rearIndex;
    private int[] ary;
    public ArrayQueue(int size){
        maxSize = size;
        ary = new int[size];
        frontIndex=-1;
        rearIndex=-1;
    }
    public boolean isEmpty(){
        return rearIndex-frontIndex==0;
    }
    public boolean isFull(){
        return rearIndex==maxSize-1;
    }
    public void add(int i){
        if(isFull()){
            System.out.println("队列已满，不能添加！");
            return;
        }
        rearIndex++;
        ary[rearIndex] = i;
    }
    public int getFirst(){
        if(isEmpty()){
            System.out.println("队列已空，不能取第一个！");
            return -1;
        }
        frontIndex++;
        int front = ary[frontIndex];
        return front;
    }
    public void print(){
        System.out.printf("frontIndex=%d rearIndex=%d\t",frontIndex,rearIndex);
        for (int i = 0; i < ary.length; i++) {
            System.out.printf("ary[%d]=%d\t",i,ary[i]);
        }
        System.out.println();
    }
}