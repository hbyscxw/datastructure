package com.cxw.leetcodecourse.linked;

import java.util.Stack;

/**
 * 判断链表是否是回文结构
 * 回文结构:正反遍历都一样
 * 1-2-2-1
 */
public class CheckPalindrome {
    public static void main(String[] args) {
        Node node4 = new Node(1);
        Node node3 = new Node(2,node4);
//        Node node2 = new Node(2,node3);
        Node node1 = new Node(1,node3);
//        System.out.println(checkPalindromeStack(node1));
        System.out.println(checkPalindromeHalfStack(node1));
    }

    private static boolean checkPalindromeHalfStack(Node node) {
        if(node==null||node.next==null){
            return true;
        }
        Stack<Integer> stack  =new Stack<>();
        Node fastNode = node;
        Node slowNode = node;
        while(fastNode.next!=null&&fastNode.next.next!=null){
            fastNode = fastNode.next.next;
            slowNode = slowNode.next;
        }
        while(slowNode!=null){
            stack.push(slowNode.value);
            slowNode = slowNode.next;
        }
        Integer sv = null;
        while(!stack.isEmpty()){
            sv = stack.pop();
            int value = node.value;
            if(sv != value){
                return false;
            }
            node = node.next;
        }
        return true;
    }

    private static boolean checkPalindromeStack(Node node) {
        Stack<Integer> stack  =new Stack<>();
        Node oldNode = node;
        while(node!=null){
            stack.add(node.value);
            node = node.next;
        }
        while(oldNode!=null){
            int value = oldNode.value;
            Integer sv = stack.pop();
            if(value!=sv){
                return false;
            }
            oldNode = oldNode.next;
        }
        return true;
    }
}
class Node{
    int value;
    Node next;

    public Node(int value) {
        this.value = value;
    }

    public Node(int value, Node next) {
        this.value = value;
        this.next = next;
    }
}
