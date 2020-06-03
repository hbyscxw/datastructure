package com.cxw.datastructure.queue;

import java.util.Scanner;

/**
 * @author chengxuwei
 * @date 2020-05-29 09:51
 * @description
 */
public class RingArrayQueueDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入命令：");
        RingArrayQueue queue = new RingArrayQueue(2);
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

class RingArrayQueue{
    private int maxSize;
    private int frontIndex;
    private int rearIndex;
    private int[] ary;
    public RingArrayQueue(int size){
        maxSize = size;
        ary = new int[size];
        frontIndex=0;
        rearIndex=0;
    }
    public boolean isEmpty(){
        return frontIndex==rearIndex;
    }
    public boolean isFull(){
        return (rearIndex+1)%maxSize==frontIndex;
    }
    public void add(int i){
        if(isFull()){
            System.out.println("队列已满，不能添加！");
            return;
        }
        int index = rearIndex;
        rearIndex=(rearIndex+1)%maxSize;
        ary[index] = i;
    }
    public int getFirst(){
        if(isEmpty()){
            System.out.println("队列已空，不能取第一个！");
            return -1;
        }
        int index = frontIndex;
        frontIndex=(frontIndex+1)%maxSize;
        int front = ary[index];
        return front;
    }
    public int size(){
        return (rearIndex-frontIndex+maxSize)%maxSize;
    }
    public void print(){
        System.out.printf("frontIndex=%d rearIndex=%d\t",frontIndex,rearIndex);
        for (int i = 0; i < frontIndex+size(); i++) {
            System.out.printf("ary[%d]=%d\t",i,ary[i]);
        }
        System.out.println();
    }
}