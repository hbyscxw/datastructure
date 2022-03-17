package com.cxw.review.interview;

import java.util.Stack;

/**
 * @author chengxuwei
 * @create 2022/3/17 14:46
 * @desc 翻转链表
 *
 * 1->2->3->4->5
 * 1->5->2->4->3
 */
public class ReOrderLink {
    public static void main(String[] args) {
        Node node5 = new Node(5,null);
        Node node4 = new Node(4,node5);
        Node node3 = new Node(3,node4);
        Node node2 = new Node(2,node3);
        Node node1 = new Node(1,node2);
        LinkNode link = new LinkNode(node1);
        print(link);
        link = reOrder(link);
        print(link);

    }

    public static LinkNode reOrder(LinkNode link) {
        Node fast = link.root;
        Node slow = link.root;
        while(fast.next!=null&&fast.next.next!=null){
            fast = fast.next.next;
            slow = slow.next;
        }
        Node end = slow;
        Stack<Node> stack = new Stack<>();
        while(slow!=null){
            stack.add(new Node(slow.value,null));
            slow = slow.next;
        }
        Node cur = link.root;
        while(cur.value != end.value){
            Node next = cur.next;
            cur.next = stack.pop();
            cur.next.next = next;
            cur = next;
        }
        end.next = null;
        return link;
    }
    public static void print(LinkNode link) {
        Node cur = link.root;
        while(cur!=null){
            System.out.println(cur.value);
            cur = cur.next;
        }
    }


}

class LinkNode{
    Node root;
    public LinkNode(Node node){
        root = node;
    }
}

class Node{
    int value;
    Node next;

    public Node(int value,Node next){
        this.value = value;
        this.next = next;
    }
}
