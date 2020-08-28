package com.cxw.leetcodecourse.linked;

import java.util.LinkedList;

/**
 * @author chengxuwei
 * @date 2020-08-25 16:01
 * @description 打印两个有序链表的交集
 */
public class LinkedIntersection {
    public static void main(String[] args) {
        LinkedList<Integer> list1 = new LinkedList<>();
        list1.add(1);
        list1.add(2);
        list1.add(5);
        int head1 = 0;
        LinkedList<Integer> list2 = new LinkedList<>();
        list2.add(1);
        list2.add(5);
        int head2 = 0;
        while(head1<list1.size()&&head2<list2.size()) {
            Integer l1 = list1.get(head1);
            Integer l2 = list2.get(head2);
            if(l1<l2){
                head1++;
            }else if(l1>l2){
                head2++;
            }else{
                head1++;
                head2++;
                System.out.println(l1);
            }
        }
    }
}