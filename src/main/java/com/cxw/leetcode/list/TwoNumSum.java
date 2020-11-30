package com.cxw.leetcode.list;

import java.math.BigDecimal;

/**
 * @author chengxuwei
 * @date 2020-11-06 17:45
 * @description
 */
public class TwoNumSum {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(9);
        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(9);
        l2.next.next = new ListNode(9);
        l2.next.next.next = new ListNode(9);
        l2.next.next.next.next = new ListNode(9);
        l2.next.next.next.next.next = new ListNode(9);
        l2.next.next.next.next.next.next = new ListNode(9);
        l2.next.next.next.next.next.next.next = new ListNode(9);
        l2.next.next.next.next.next.next.next.next = new ListNode(9);
        l2.next.next.next.next.next.next.next.next.next = new ListNode(9);
        TwoNumSum d = new TwoNumSum();
        ListNode l3 = d.addTwoNumbers(l1, l2);
        System.out.println(l3);
    }
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        BigDecimal l1sum = BigDecimal.valueOf(0);
        int l1len = 0;
        ListNode l1l=l1;
        while(l1l!=null){
            l1sum = l1sum.add(BigDecimal.valueOf(l1l.val).multiply(BigDecimal.valueOf(10).pow(l1len)));
            l1len++;
            l1l = l1l.next;
        }
        BigDecimal l2sum = BigDecimal.valueOf(0);
        int l2len = 0;
        ListNode l2l=l2;
        while(l2l!=null){
            l2sum = l2sum.add(BigDecimal.valueOf(l2l.val).multiply(BigDecimal.valueOf(10).pow(l2len)));
            l2len++;
            l2l = l2l.next;
        }
        BigDecimal sum = l1sum.add(l2sum);
        String sumStr = String.valueOf(sum);
        ListNode head = null;
        ListNode n = null;
        for(int i=sumStr.length()-1;i>=0;i--){
            int one = Integer.parseInt(sumStr.charAt(i)+"");
            if(head==null){
                head = new  ListNode(one);
                n = head;
            }else{
                n.next = new ListNode(one);
                n = n.next;
            }
        }
        return head;
    }
     static class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }
}