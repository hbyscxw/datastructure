package com.cxw.leetcodecourse.tree;


import java.util.LinkedList;
import java.util.Queue;

/**
 * @author chengxuwei
 * @date 2020-09-07 11:45
 * @description 判断树是否是完全二叉树
 *  若设二叉树的深度为k，除第 k 层外，其它各层 (1～k-1) 的结点数都达到最大个数，第k 层所有的结点都连续集中在最左边，这就是完全二叉树。
 */
public class TreeCheck {
    public static void main(String[] args) {
        Node root = null;
        boolean cbt = isCBT(root);
    }

    private static boolean isCBT(Node root) {
        if(root == null){
            return true;
        }
        Queue<Node> stack = new LinkedList<>();
        stack.add(root);
        boolean c = true;
        while(!stack.isEmpty()){
            Node node = stack.poll();
            if((!c && (node.left!=null||node.right!=null))
                    ||(node.left==null&&node.right!=null)){
                return false;
            }
            if(node.left!=null){
                stack.add(node.left);
            }
            if(node.right!=null){
                stack.add(node.right);
            }else{
                c = false;
            }
        }
        return true;
    }
}