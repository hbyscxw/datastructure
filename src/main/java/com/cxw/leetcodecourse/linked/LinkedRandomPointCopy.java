package com.cxw.leetcodecourse.linked;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author chengxuwei
 * @date 2020-08-27 18:29
 * @description
 */
public class LinkedRandomPointCopy {
    public static void main(String[] args) {
        RandomPointNode node5 = new RandomPointNode(5);
        RandomPointNode node4 = new RandomPointNode(4,node5);
        RandomPointNode node3 = new RandomPointNode(3,node4);
        RandomPointNode node2 = new RandomPointNode(2,node3);
        RandomPointNode head = new RandomPointNode(1,node2,node4);
        node3.random = node2;
        RandomPointNode newHead = deepCopy(head);
        printLinked(newHead);
    }

    private static RandomPointNode deepCopy(RandomPointNode head) {
        if(head == null){
            return null;
        }
        RandomPointNode cur = head;
        RandomPointNode newHead=null;
        //将原链表和新复制链表元素相连 1->1'->2->2'->3->3'
        while(cur!=null){
            RandomPointNode node = new RandomPointNode(cur.value);
            if(newHead == null){
                newHead = node;
            }
            RandomPointNode curNext = cur.next;
            cur.next = node;
            node.next = curNext;
            cur = curNext;
        }
        cur = head;
        //将新复制链表random元素赋值
        while(cur!=null){
            RandomPointNode newCur = cur.next;
            RandomPointNode curRand = cur.random;
            if(curRand!=null){
                newCur.random = curRand.next;
            }
            cur = cur.next.next;
        }
        //将原链表剥离
        cur = newHead;
        while(cur!=null&&cur.next!=null){
            cur.next = cur.next.next;
            cur = cur.next;
        }
        return newHead;
    }

    private static RandomPointNode deepCopyUseMap(RandomPointNode head) {
        Map<RandomPointNode,RandomPointNode> map = new HashMap<>();
        RandomPointNode newHead=null;
        RandomPointNode cur = head;
        while(cur!=null){
            RandomPointNode node = new RandomPointNode(cur.value);
            if(newHead == null){
                newHead = node;
            }
            map.put(cur,node);
            cur = cur.next;
        }
        cur = head;
        while(cur!=null){
            RandomPointNode newNode = map.get(cur);
            if(cur.next!=null) {
                RandomPointNode newNodeNext = map.get(cur.next);
                newNode.next = newNodeNext;
            }
            if(cur.random!=null) {
                RandomPointNode newNodeRandom = map.get(cur.random);
                newNode.random = newNodeRandom;
            }
            cur = cur.next;
        }
        return newHead;
    }

    private static void printLinked(RandomPointNode head) {
        while(head!=null){
            System.out.println(head);
            head = head.next;
        }
    }
}
class RandomPointNode{
    int value;
    RandomPointNode next;
    RandomPointNode random;

    @Override
    public boolean equals(Object o) {
        if (this == o){ return true;}
        if (o == null || getClass() != o.getClass()){ return false;}
        RandomPointNode that = (RandomPointNode) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    public RandomPointNode(int value) {
        this.value = value;
    }

    public RandomPointNode(int value, RandomPointNode next) {
        this.value = value;
        this.next = next;
    }
    public RandomPointNode(int value, RandomPointNode next,RandomPointNode random) {
        this.value = value;
        this.next = next;
        this.random = random;
    }

    @Override
    public String toString() {
        return "RandomPointNode{" +
                "value=" + value +
                ", next=" + (next!=null?next.value+"":" ") +
                ", random=" + (random!=null?random.value+"":" ") +
                '}';
    }
}