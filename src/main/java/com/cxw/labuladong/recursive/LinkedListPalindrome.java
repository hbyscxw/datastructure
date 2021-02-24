package com.cxw.labuladong.recursive;

/**
 * @author chengxuwei
 * @date 2021-02-24 10:39
 * @description
 * 如何判断回文链表
 * https://labuladong.gitbook.io/algo/shu-ju-jie-gou-xi-lie/shou-ba-shou-shua-lian-biao-ti-mu-xun-lian-di-gui-si-wei/pan-duan-hui-wen-lian-biao
 */
public class LinkedListPalindrome {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode first = new ListNode(2);
        ListNode second = new ListNode(1);
        //ListNode third = new ListNode(1);
        head.next = first;
        first.next = second;
        //second.next = third;
        boolean palindrome = isPalindrome2(head);
        System.out.println(palindrome);
    }

    /**
     * 单链表逆序打印
     * @param head
     */
    public static void reversePrint(ListNode head){
        if(head == null){
            return ;
        }
        reversePrint(head.next);
        System.out.println(head.val);
    }
    static ListNode left = null;

    /**
     * 递归
     * @param head
     * @return
     */
    public static boolean isPalindrome(ListNode head) {
        left = head;
       return traverse(head);
    }
    public static boolean traverse(ListNode right){
        if(right == null){
            return true;
        }
        boolean flag  = traverse(right.next);
        flag = flag && right.val == left.val;
        left = left.next;
        return flag;
    }

    /**
     * 字符串
     * @param head
     * @return
     */
    public static boolean isPalindrome2(ListNode head) {
        String str = "";
       while(head!=null){
            str += String.valueOf(head.val);
           head = head.next;
       }
        return doublePointStr(str);
    }

    private static boolean doublePointStr(String str) {
        int left = 0;
        int right = str.length()-1;
        while(left<right){
            if(str.charAt(left)!=str.charAt(right)){
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}