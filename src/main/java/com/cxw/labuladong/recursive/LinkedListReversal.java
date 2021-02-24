package com.cxw.labuladong.recursive;

/**
 * @author chengxuwei
 * @date 2020-11-13 10:46
 * @description 单链表翻转
 * 递归反转链表的一部分
 * https://labuladong.gitbook.io/algo/shu-ju-jie-gou-xi-lie/shou-ba-shou-shua-lian-biao-ti-mu-xun-lian-di-gui-si-wei/di-gui-fan-zhuan-lian-biao-de-yi-bu-fen
 */
public class LinkedListReversal {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode first = new ListNode(2);
        ListNode second = new ListNode(3);
        ListNode third = new ListNode(4);
        head.next = first;
        first.next = second;
        second.next = third;
        printList(head);
        ListNode newHead = reverseBetween(head,2,3);
        printList(newHead);
    }
    /**
     * 翻转n个元素链表 递归
     * @param head
     * @return
     */
    static ListNode s = null;
    private static ListNode reversalN(ListNode head, int n) {
        if(n==1){
            s = head.next;
            return head;
        }else{
            ListNode next = head.next;
            ListNode last = reversalN(head.next,n-1);
            next.next = head;
            head.next = s;
            return last;
        }
    }

    /**
     * 翻转m-n个元素链表 递归
     * @param head
     * @return
     */
    private static ListNode reverseBetween(ListNode head, int m, int n){
       if(m==1){
           return reversalN(head,n);
       }else{
           head.next = reverseBetween(head.next,m-1,n-1);
           return head;
       }
    }


    public static void printList(ListNode head) {
        while(head!=null){
            System.out.println(head.val);
            head = head.next;
        }
    }
}

class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}