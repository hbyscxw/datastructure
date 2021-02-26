package com.cxw.labuladong.recursive.link;

/**
 * @author chengxuwei
 * @date 2021-02-23 15:11
 * @description
 * 如何k个一组反转链表
 * https://labuladong.gitbook.io/algo/shu-ju-jie-gou-xi-lie/shou-ba-shou-shua-lian-biao-ti-mu-xun-lian-di-gui-si-wei/k-ge-yi-zu-fan-zhuan-lian-biao
 */
public class LinkedListReversalK {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode first = new ListNode(2);
        ListNode second = new ListNode(3);
        ListNode third = new ListNode(4);
        head.next = first;
        first.next = second;
        second.next = third;
        LinkedListReversal.printList(head);
        ListNode node = reverseKGroup(head,2);
        LinkedListReversal.printList(node);
    }
    public static ListNode reverseKGroup(ListNode head, int n){
        if(head==null){
            return null;
        }
        ListNode a = head;
        ListNode b = head;
        for (int i = 0; i < n; i++) {
            if(b==null){
                return head;
            }
            b = b.next;
        }
        ListNode newHead = reverse(a, b);
        a.next = reverseKGroup(b,n);
        return newHead;
    }
    public static ListNode reverse(ListNode head){
        ListNode pre = null;
        ListNode cur = head;
        ListNode next = head;
        while(cur!=null){
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    /** 反转区间 [a, b) 的元素，注意是左闭右开 */
    public static ListNode reverse(ListNode a, ListNode b) {
        ListNode pre = null;
        ListNode cur = a;
        ListNode next = a;
        while(cur!=b){
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }
}