package com.cxw.leetcode.tree;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

/**
 * @author chengxuwei
 * @date 2021-03-12 17:10
 * @description 验证二叉树的前序序列化
 * https://leetcode-cn.com/problems/verify-preorder-serialization-of-a-binary-tree/
 */
public class CheckTwoBinaryTree {
    public static void main(String[] args) {
        String str = "9,3,4,#,#,1,#,#,2,#,6,#,#";
        CheckTwoBinaryTree ct = new CheckTwoBinaryTree();
        boolean flag = ct.isValidSerialization(str);
        System.out.println(flag);
    }
    public boolean isValidSerialization(String preorder) {
        String[] ary = preorder.split(",");
        Deque<String> stack = new ArrayDeque<>();
        for(int i=0;i<ary.length;i++){
            stack.push(ary[i]);
        }
        while (stack.size()>=3){
            String right = stack.pop();
            String left = stack.pop();
            String root = stack.pop();
            if(right.equals("#")&&left.equals("#")&&!root.equals("#")){
                stack.addFirst("#");
            }
        }
        if(stack.size()==1&&stack.pop().equals("#")){
            return true;
        }
        return false;
    }
}