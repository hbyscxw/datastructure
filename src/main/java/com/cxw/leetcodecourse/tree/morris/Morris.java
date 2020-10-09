package com.cxw.leetcodecourse.tree.morris;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chengxuwei
 * @date 2020-10-09 09:57
 * @description 二叉树的morris遍历
 * https://zhuanlan.zhihu.com/p/101321696
 */
public class Morris {
    public static void main(String[] args) {
        int[] ary = {1,2,3,4,5,6};
        Node head = aryToTree(ary);
        morrisInfix(head);
        System.out.println("---------");
        printInfix(head);
    }

    private static void printPre(Node head) {
        if(head==null){
            return;
        }
        System.out.println(head.value);
        printPre(head.left);
        printPre(head.right);
    }

    private static void printInfix(Node head) {
        if(head==null){
            return;
        }
        printInfix(head.left);
        System.out.println(head.value);
        printInfix(head.right);
    }

    private static Node aryToTree(int[] ary) {
        List<Node> list = new ArrayList<>();
        for (int i = 0; i < ary.length; i++) {
            Node node = new Node(ary[i]);
            list.add(node);
        }
        for (int i = 0; i < ary.length; i++) {
            Node node = list.get(i);
            if(i*2+1<=ary.length-1){
                node.left = list.get(i*2+1);
            }
            if(i*2+2<=ary.length-1){
                node.right = list.get(i*2+2);
            }
        }
        return list.get(0);
    }

    private static void morrisIn(Node head) {
        if(head==null){
            return;
        }
        Node cur = head;
        Node mostRight = null;
        while(cur!=null){
            mostRight = cur.left;
            if(mostRight!=null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;
                }
            }
            cur = cur.right;
        }
    }

    private static void morrisPre(Node head) {
        if(head==null){
            return;
        }
        Node cur = head;
        Node mostRight = null;
        while(cur!=null){
            mostRight = cur.left;
            if(mostRight!=null){
                while(mostRight.right!=null&&mostRight.right!=cur){
                    mostRight = mostRight.right;
                }
                if(mostRight.right!=null){
                    mostRight.right = null;
                }else{
                    mostRight.right = cur;
                    System.out.println(cur.value);
                    cur = cur.left;
                    continue;
                }
            }else{
                System.out.println(cur.value);
            }
            cur = cur.right;
        }
    }

    private static void morrisInfix(Node head) {
        if (head == null) {
            return;
        }
        Node cur = head;
        Node mostRight = null;
        while (cur != null) {
            mostRight = cur.left;
            if(mostRight!=null){
                while (mostRight.right!=null&&mostRight.right!=cur){
                    mostRight = mostRight.right;
                }
                if(mostRight.right!=null){
                    mostRight.right = null;
                }else{
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                }
            }
            System.out.println(cur.value);
            cur = cur.right;
        }
    }
}

class Node{
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }
}