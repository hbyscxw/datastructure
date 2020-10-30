package com.cxw.leetcodecourse.skiplist;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author chengxuwei
 * @date 2020-10-26 10:48
 * @description
 */
public class SkipListDemo {
    public static void main(String[] args) {
        SkipList list = new SkipList();
        list.add(10);
        list.add(20);
        list.add(20);
        list.add(30);
        System.out.println();
    }
}
class SkipList{
    SkipListNode head;
    int maxLevel;
    int size;
    static final double PROBABILITY = 0.5;

    public void add(int value){
        if(head == null){
            head = new SkipListNode(value);
            maxLevel = 1;
            size = 1;
            return;
        }
        SkipListNode node = find(value);
        if(node==null){
            int rl = randomLevel();
            while(rl>maxLevel){
                head.nextList.add(null);//头增加区域到最大层
                maxLevel++;
            }
            node = new SkipListNode(value);
            SkipListNode cur = this.head;
            int level = maxLevel;
            int levelAll = maxLevel;
//            while(cur.value<value&&level>0){
//                SkipListNode nextListNode = null;
//                if(cur.nextList.size()>level) {
//                    nextListNode = cur.nextList.get(level);
//                }
//                if(nextListNode==null){
//                    if(rl >= level) {
//                        node = new SkipListNode(value);
//                    }
//                    cur.nextList.add(1, node);
//                }else{
//                    if(nextListNode.value>value){
//                        //往下
//                        level--;
//                    }else{
//                        //往右
//                        cur = nextListNode;
//                    }
//                }
//            }
//            size++;
            do{
                cur = findNext(value,cur,levelAll);
                if(levelAll <= level){ //达到应该加入节点的层
                    node.nextList.add(0,cur.nextList.get(level));
                    cur.nextList.set(level,node);
                    level--;
                }
            }while (levelAll-- >0);
        }
    }

    private SkipListNode findNext(int value, SkipListNode cur, int level) {
        SkipListNode next = cur.nextList.get(level);
        while(next!=null){
            int v = next.value;
            if(value<v){
                break;
            }
            cur = next;
            next = cur.nextList.get(level);
        }
        return cur;
    }

    private SkipListNode find(int value) {
        SkipListNode cur = this.head;
        int level = maxLevel;
        while(cur.value<value&&level>0) {
            SkipListNode nextListNode = null;
            if(cur.nextList.size()>level) {
                nextListNode = cur.nextList.get(level);
            }
            if(nextListNode==null){
                //往下
                level--;
            }else{
                if(nextListNode.value==value) {
                    return nextListNode;
                }else if(nextListNode.value>value){
                    //往下
                    level--;
                }else{
                    //往右
                    cur = nextListNode;
                }
            }
        }
        return null;
    }

    private int randomLevel() {
        int level = 1;
        while(true) {
            double d = ThreadLocalRandom.current().nextDouble();
            System.out.println(d);
            if (d < PROBABILITY) {
                level++;
            }else{
                break;
            }
        }
        return level;
    }
}
class SkipListNode{
    int value;
    ArrayList<SkipListNode> nextList;
    public SkipListNode(int value) {
        this.value = value;
        nextList = new ArrayList<>();
        nextList.add(null);
    }
}