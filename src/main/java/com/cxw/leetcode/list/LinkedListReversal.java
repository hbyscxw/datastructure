package com.cxw.leetcode.list;

/**
 * @author chengxuwei
 * @date 2020-11-13 10:46
 * @description 单链表翻转
 */
public class LinkedListReversal {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode first = new ListNode(2);
        ListNode second = new ListNode(3);
        head.next = first;
        first.next = second;
        printList(head);
        ListNode newHead = reversal(head);
        printList(newHead);
    }

    private static ListNode reversal(ListNode head) {
        ListNode pre = null;
        ListNode next;
        while(head!=null){
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    private static void printList(ListNode head) {
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