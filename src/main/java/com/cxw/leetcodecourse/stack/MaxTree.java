package com.cxw.leetcodecourse.stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @author chengxuwei
 * @date 2020-09-28 11:05
 * @description
 * 构造数组的MaxTree
 * [题目]
 * 定义二叉树节点如下:
 * public class Node {
 * public int value;
 * public Node left;
 * public Node right;
 * public Node(int data) {
 * this.value = data;
 * }
 * }
 * 一个数组的MaxTree定义如下:
 * 数组必须没有重复元素。
 * MaxTree是一棵二叉树，数组的每一个值对应一个二叉树节点。
 * 包括MaxTree树在内且在其中的每一棵子树上，值最大的节点都是树的头。
 * 无重复元素的数组arr,写出生成这个数组的MaxTree的函数，要求如果数组长度为N，则时间复杂度为O(N),空间复杂度为O(N)。
 * 解法一：大根堆
 * 解法二：单调栈
 */
public class MaxTree {
    public static void main(String[] args) {
        int[] arr = {1,3,2,5,9,11,4,8,10};
        Node root = maxTree(arr);
        infixPrintTree(root);
    }

    private static void infixPrintTree(Node root) {
        if(root==null){
            return ;
        }
        infixPrintTree(root.left);
        System.out.println(root);
        infixPrintTree(root.right);
    }

    private static Node maxTree(int[] arr) {
        Map<Integer,Node> map = new HashMap<>();
        Map<Integer,Node> leftMap = new HashMap<>();
        Map<Integer,Node> rightMap = new HashMap<>();
        Node root = null;
        for (int i = 0; i < arr.length; i++) {
            Node node = new Node(arr[i]);
            map.put(arr[i],node);
        }
        //从大到小的单调栈
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            while(!stack.isEmpty()){
                Integer v = stack.peek();
                if(arr[i]>v){
                    v = stack.pop();
                    if(!stack.isEmpty()){
                        leftMap.put(v,map.get(stack.peek()));
                    }
                    rightMap.put(v,map.get(arr[i]));
                }else{
                    break;
                }
            }
            stack.push(arr[i]);
        }
        while(!stack.isEmpty()){
            Integer v = stack.pop();
            if(!stack.isEmpty()){
                leftMap.put(v,map.get(stack.peek()));
            }
        }
        for (int v : arr) {
            Node node = map.get(v);
            Node left = leftMap.get(v);
            Node right = rightMap.get(v);
            if(left!=null&&right!=null){
                //node挂载到左右相比小的节点上
                Node p = null;
                if(left.value<right.value){
                    p = left;
                }else{
                    p = right;
                }
                if(p.left == null){
                    p.left = node;
                }else{
                    p.right = node;
                }
            }else if(left!=null){
                if(left.left==null) {
                    left.left = node;
                }else{
                    left.right = node;
                }
            }else if(right!=null){
                if(right.left==null) {
                    right.left = node;
                }else{
                    right.right = node;
                }
            }else{
                root = node;
            }
        }
        return root;
    }


    static class Node {
        public int value;
        public Node left;
        public Node right;
        public Node(int data) {
            this.value = data;
        }
        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    ", left=" + (left!=null?left.value:" ") +
                    ", right=" + (right!=null?right.value:" ") +
                    '}';
        }
    }
}
