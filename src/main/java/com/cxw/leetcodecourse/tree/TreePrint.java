package com.cxw.leetcodecourse.tree;

import java.util.Stack;

/**
 * @author chengxuwei
 * @date 2020-09-01 09:32
 * @description
 */
public class TreePrint {
    public static void main(String[] args) {
        int[] ary = {1,2,3,4,5,6,7,8};
        Node head = aryToTree(ary);
        prePrint(head);
        System.out.println();
        prePrintUnRecursion(head);
        infixPrint(head);
        System.out.println();
        infixPrintUnRecursion(head);
        postPrint(head);
        System.out.println();
        postPrintUnRecursion(head);
    }

    private static void infixPrintUnRecursion(Node head) {
        if(head==null){
            return;
        }
        Stack<Node> stack = new Stack<>();
        Node cur = head;
        while(!stack.isEmpty()||cur!=null){
            if(cur!=null){
                stack.add(cur);
                cur = cur.left;
            }else{
                cur = stack.pop();
                System.out.print(cur.value+" ");
                cur = cur.right;
            }
        }
        System.out.println();
    }

    private static void prePrintUnRecursion(Node head) {
        if(head==null){
           return;
        }
        Stack<Node> stack = new Stack<>();
        Node cur = head;
        stack.add(cur);
        while(!stack.isEmpty()){
            cur = stack.pop();
            System.out.print(cur.value+" ");
            if(cur.right!=null){
                stack.push(cur.right);
            }
            if(cur.left!=null){
                stack.push(cur.left);
            }
        }
        System.out.println();
    }

    private static void postPrintUnRecursion(Node head) {
        if(head==null){
            return;
        }
        Stack<Node> stack = new Stack<>();
        Stack<Node> stack2 = new Stack<>();
        Node cur = head;
        stack.add(cur);
        while(!stack.isEmpty()){
            cur = stack.pop();
            //System.out.print(cur.value+" ");
            stack2.push(cur);
            if(cur.left!=null){
                stack.push(cur.left);
            }
            if(cur.right!=null){
                stack.push(cur.right);
            }
        }
        while(!stack2.isEmpty()){
            cur = stack2.pop();
            System.out.print(cur.value+" ");
        }
        System.out.println();
    }


    private static void infixPrint(Node head) {
        if(head!=null){
            infixPrint(head.left);
            System.out.print(head.value+" ");
            infixPrint(head.right);
        }
    }

    private static void postPrint(Node head) {
        if(head!=null){
            postPrint(head.left);
            postPrint(head.right);
            System.out.print(head.value+" ");
        }
    }

    private static void prePrint(Node head) {
        if(head!=null){
            System.out.print(head.value+" ");
            prePrint(head.left);
            prePrint(head.right);
        }
    }

    private static Node aryToTree(int[] ary) {
        if(ary==null||ary.length==0){
            return null;
        }
        Node[] nodes = new Node[ary.length];
        for (int i = 0; i < ary.length; i++) {
            nodes[i] = new Node(ary[i]);
        }
        for (int i = 0; i < ary.length; i++) {
            if(i*2+1<ary.length){
                nodes[i].left = nodes[i*2+1];
            }
            if(i*2+2<ary.length){
                nodes[i].right = nodes[i*2+2];
            }

        }
        return nodes[0];
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