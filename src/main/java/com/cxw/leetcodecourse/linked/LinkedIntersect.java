package com.cxw.leetcodecourse.linked;

import java.util.HashSet;
import java.util.Set;

/**
 * @author chengxuwei
 * @date 2020-08-28 10:38
 * @description 判断单链表有环无环，如果有相交，则返回第一个相交的点
 * 1.用HashSet
 * 2.使用两个指针,快指针一次走两步，慢指针一次走一步，相遇后快指针回到head，然后快指针一次走一步，快指针和慢指针一定会在第一个入环节点上相遇
 */
public class LinkedIntersect {
    public static void main(String[] args) {
        Node node5 = new Node(5);
        Node node4 = new Node(4,node5);
        Node node3 = new Node(3,node4);
        Node node2 = new Node(2,node3);
        Node head1 = new Node(1,node2);
        //node5.next = node2;
        Node head2 = new Node(31,node4);
//        Node iNode = getIntersectNodeUseSet(head1,head2);
//        System.out.println(iNode.value);
        Node loopNode = getIntersectNodeUseSet(head1,head2);
        System.out.println(loopNode.value);
    }

    private static Node getIntersectNodeUseSet(Node head1, Node head2) {
        Node cur1 = head1;
        Node cur2 = head2;
        Set<Node> set = new HashSet<>();
        while(cur1!=null){
            if(set.contains(cur1)){
                break;
            }else {
                set.add(cur1);
            }
            cur1 = cur1.next;
        }
        Set<Node> set2 = new HashSet<>();
        while(cur2!=null){
            if(set.contains(cur2)){
                return cur2;
            }
            if(set2.contains(cur2)){
                break;
            }else {
                set2.add(cur2);
            }
            cur2 = cur2.next;
        }
        return null;
    }

    private static Node getIntersectNode(Node head1, Node head2) {
        if(head1==null||head2==null){
            return null;
        }
        Node loop1 = getLoopNode(head1);
        Node loop2 = getLoopNode(head2);
        if(loop1==null && loop2==null){
            return noLoop(head1,head2);
        }
        if(loop1!=null&&loop2!=null){
            return bothLoop(head1,head2,loop1,loop2);
        }
        return null;
    }

    private static Node bothLoop(Node head1, Node head2, Node loop1, Node loop2) {
        if(loop1==loop2){
            return noLoop(head1,head2);
        }else{
            Node cur1 = loop1.next;
            while(cur1!=loop1){
                if(cur1==loop2){
                    return loop1; // loop1和loop2都是对的
                }
                cur1 = cur1.next;
            }
            return null;
        }
    }

    private static Node noLoop(Node head1, Node head2) {
        if(head1==null||head2==null){
            return null;
        }
        Node cur1 = head1;
        Node cur2 = head2;
        int n1 = 0;
        while(cur1!=null){
            n1++;
            cur1=cur1.next;
        }
        int n2 = 0;
        while(cur2!=null){
            n2++;
            cur2=cur2.next;
        }
        cur1 = head1;
        cur2 = head2;
        if(n1>n2){
            for (int i = 0; i <n1-n2 ; i++) {
                cur1 = cur1.next;
            }
        }else if(n1<n2){
            for (int i = 0; i <n2-n1 ; i++) {
                cur2 = cur2.next;
            }
        }
        while(cur1!=null&&cur2!=null&&cur1!=cur2){
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        return cur1;
    }

    private static Node getLoopNode(Node head) {
        if(head==null||head.next==null||head.next.next==null){
            return null;
        }
        Node f = head.next.next;
        Node s = head.next;
        while(f!=s){
            if(s.next==null||f.next.next==null){
                return null;
            }
            s = s.next;
            f = f.next.next;
        }
        f = head;
        while(s!=f){
            s = s.next;
            f = f.next;
        }
        return s;
    }
}