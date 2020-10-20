package com.cxw.leetcodecourse.tree;


/**
 * @author chengxuwei
 * @date 2020-10-20 11:16
 * @description
 * 二叉树中，一个节点可以往上走和往下走，那么从节点A总能走到节点B.
 * 节点A走到节点B的距离为: A走到8最短路径上的节点个数。
 * 求一棵二叉树上的最远距离
 */
public class MaxPathTree {
    public static void main(String[] args) {
        int[] ary = {};
        TreeNode head = BaseTree.aryToTree(ary);
        ReturnType ret = maxPath(head);
        System.out.println(ret);
    }

    private static ReturnType maxPath(TreeNode head) {
        if(head==null){
            return new ReturnType();
        }
        ReturnType leftRet = maxPath(head.left);
        ReturnType rightRet = maxPath(head.right);
        int h = Math.max(leftRet.h,rightRet.h)+1;
        int maxDis = Math.max(Math.max(leftRet.maxDis,rightRet.maxDis),leftRet.h+rightRet.h+1);
        return new ReturnType(maxDis,h);
    }
}
class ReturnType{
    int maxDis;
    int h;

    public ReturnType() {
        maxDis = 0;
        h = 0;
    }

    public ReturnType(int maxDis, int h) {
        this.maxDis = maxDis;
        this.h = h;
    }
}