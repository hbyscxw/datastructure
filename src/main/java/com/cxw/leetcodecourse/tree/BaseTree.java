package com.cxw.leetcodecourse.tree;

/**
 * @author chengxuwei
 * @date 2020-10-19 09:44
 * @description
 */
public class BaseTree {
    public static TreeNode aryToTree(int[] ary){
        TreeNode[] treeNodes = new TreeNode[ary.length];
        for (int i = 0; i < ary.length; i++) {
            treeNodes[i] = new TreeNode(ary[i]);
        }
        for (int i = 0; i < ary.length; i++) {
            if(i*2+1<ary.length){
                treeNodes[i].left = treeNodes[i*2+1];
            }
            if(i*2+2<ary.length){
                treeNodes[i].right = treeNodes[i*2+2];
            }
        }
        return treeNodes[0];
    }
}
class TreeNode{
    int value;
    TreeNode left;
    TreeNode right;

    public TreeNode(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                ", left=" + (left == null ? "null" : left.value) +
                ", right=" + (right == null ? "null" : right.value) +
                '}';
    }
}