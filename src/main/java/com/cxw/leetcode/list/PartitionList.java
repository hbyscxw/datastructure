package com.cxw.leetcode.list;

import java.util.ArrayList;
import java.util.List;


/**
 * @author chengxuwei
 * @date 2021-01-05 18:39
 * @description 分隔链表
 * 给你一个链表和一个特定值 x ，请你对链表进行分隔，使得所有小于 x 的节点都出现在大于或等于 x 的节点之前。
 *
 * 你应当保留两个分区中每个节点的初始相对位置。
 *
 *
 *
 * 示例：
 *
 * 输入：head = 1->4->3->2->5->2, x = 3
 * 输出：1->2->2->4->3->5
 * https://leetcode-cn.com/problems/partition-list/
 */
public class PartitionList {
    public ListNode partition(ListNode head, int x) {
        if(head==null){
            return head;
        }
        List<Integer> smallList = new ArrayList<>();
        List<Integer> bigList = new ArrayList<>();
        ListNode node = head;
        while(node!=null){
            if(node.val<x) {
                smallList.add(node.val);
            }else{
                bigList.add(node.val);
            }
            node = node.next;
        }
        List<Integer> allList = new ArrayList<>();
        allList.addAll(smallList);
        allList.addAll(bigList);
        ListNode newHead = new ListNode(allList.get(0));
        node = newHead;
        for (int i = 1; i < allList.size(); i++) {
           node.next = new ListNode(allList.get(i));
           node = node.next;
        }
        return newHead;
    }


}