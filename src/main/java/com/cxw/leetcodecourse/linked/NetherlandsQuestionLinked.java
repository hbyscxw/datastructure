package com.cxw.leetcodecourse.linked;

import java.util.Arrays;

/**
 * @author chengxuwei
 * @date 2020-08-27 17:05
 * @description
 * 链表的荷兰国旗问题
 * 1.用额外的数组
 * 2.用额外的三个指针 less equals more
 */
public class NetherlandsQuestionLinked {
    public static void main(String[] args) {
        int[] ary = {10,3,5,2,3,6,4,10};
        int[] newAry = useArraySolve(ary,3);
        System.out.println(Arrays.toString(newAry));
        Node head = aryToLinked(ary);
        printLinked(head);
        Node newHead = usePoint(head,10);
        printLinked(newHead);
    }

    private static Node usePoint(Node head,int target) {
        Node lessHead = null;
        Node less = null;
        Node equalsHead = null;
        Node equals = null;
        Node moreHead = null;
        Node more = null;
        while(head!=null){
            if(head.value<target){
                if(less == null){
                    less = new Node(head.value);
                    lessHead = less;
                }else{
                    less.next = new Node(head.value);
                    less = less.next;
                }
            }else if(head.value==target){
                if(equals == null){
                    equals = new Node(head.value);
                    equalsHead = equals;
                }else{
                    equals.next = new Node(head.value);
                    equals = equals.next;
                }
            }else{
                if(more == null){
                    more = new Node(head.value);
                    moreHead = more;
                }else{
                    more.next = new Node(head.value);
                    more = more.next;
                }
            }
            head = head.next;
        }
        Node newHead = null;
        if(lessHead!=null&&less!=null){
            newHead = lessHead;
        }
        if(newHead!=null){
            if(equalsHead!=null&&equals!=null){
                less.next = equalsHead;
            }
        }else{
            newHead = equalsHead;
        }
        if(newHead!=null){
            if(equalsHead!=null&&equals!=null){
                equals.next = moreHead;
            }else if(lessHead!=null&&less!=null){
                less.next = moreHead;
            }
        }else{
            newHead = moreHead;
        }
        return newHead;
    }

    private static int[] useArraySolve(int[] ary, int target) {
       //return partition(ary,0,ary.length-1,target);
        int[] newAry = new int[ary.length];
        System.arraycopy(ary,0,newAry,0,ary.length);
        int less = -1;
        int more = newAry.length;
        int cur = 0;
        while(cur<more){
            if(newAry[cur]>target){
                swap(newAry,cur,--more);
            }else if(newAry[cur]<target){
                swap(newAry,cur++,++less);
            }else{
                cur++;
            }
        }
        return newAry;
    }

    private static Node aryToLinked(int[] ary) {
        Node head = null;
        Node pre = null;
        for (int i = 0; i < ary.length; i++) {
            if(i==0){
                head = new Node(ary[i]);
                pre = head;
            }else{
                pre.next = new Node(ary[i]);
                pre = pre.next;
            }
        }
        return head;
    }

    private static void printLinked(Node head) {
        while(head!=null){
            System.out.print(head.value+" ");
            head = head.next;
        }
        System.out.println();
    }



    private static int[] partition(int[] ary,int l,int r,int num){
        int less = l-1;
        int more = r+1;
        int cur = l;
        while(cur<more){
            if(ary[cur]>num){
                swap(ary,cur,--more);
            }else if(ary[cur]<num){
                swap(ary,cur++,++less);
            }else{
                cur++;
            }
        }
        return new int[]{less+1,more-1};
    }

    private static void swap(int[] ary,int i,int j){
        if(i==j){
            return;
        }
        ary[i] = ary[i] ^ ary[j];
        ary[j] = ary[i] ^ ary[j];
        ary[i] = ary[i] ^ ary[j];
    }
}
