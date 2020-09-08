package com.cxw.leetcodecourse.tree;


/**
 * @author chengxuwei
 * @date 2020-09-08 15:17
 * @description 求完全二叉树的节点个数，要求小于O(N)
 */
public class CompleteBinaryTreeCount {
    public static void main(String[] args) {
        Node head = null;
        int count = nodeNum(head);
    }

    private static int nodeNum(Node head) {
        if(head == null){
            return 0;
        }
        return bs(head,1,mostLeftLevel(head,1));
    }

    private static int bs(Node node, int level, int h) {
        if(level == h){
            return 1;
        }
        if(mostLeftLevel(node.right,level+1)==h){
            return (1<<(h-level))+bs(node.right,level+1,h);
        }else{
            return (1<<(h-level-1))+bs(node.left,level+1,h);
        }
    }

    private static int mostLeftLevel(Node node, int level) {
        if(node==null){
            return 0;
        }
        while(node!=null){
            level++;
            node = node.left;
        }
        return level-1;
    }
}