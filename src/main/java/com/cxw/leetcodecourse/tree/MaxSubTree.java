package com.cxw.leetcodecourse.tree;

/**
 * @author chengxuwei
 * @date 2020-10-19 09:41
 * @description 求二叉树的最大搜索二叉子树节点的个数
 */
public class MaxSubTree {
    public static void main(String[] args) {
        int[] ary = {7,3,2,1,1,-7,7};
        TreeNode head = BaseTree.aryToTree(ary);
        ReturnData returnData = process(head);
        System.out.println(returnData);
    }

    private static ReturnData process(TreeNode head) {
        if(head==null){
            return new ReturnData();
        }
        int maxSize = 0;
        TreeNode subHead = null;
        ReturnData leftData = process(head.left);
        ReturnData rightData = process(head.right);
        if(leftData.head==head.left
                &&rightData.head==head.right
                &&leftData.max<head.value
                &&rightData.min>head.value
        ){
            maxSize = leftData.size+1+rightData.size;
            subHead = head;
        }
        maxSize = Math.max(Math.max(leftData.size,rightData.size),maxSize);
        if(subHead==null) {
            if (leftData.size >= rightData.size) {
                subHead = leftData.head;
            } else {
                subHead = rightData.head;
            }
        }
        return new ReturnData(subHead,maxSize,
                Math.min(Math.min(leftData.min,rightData.min),head.value),
                Math.max(Math.max(leftData.max,rightData.max),head.value));
    }
}
class ReturnData{
    TreeNode head;
    int size;
    int min;
    int max;

    public ReturnData() {
        size = 0;
        min = Integer.MAX_VALUE;
        max = Integer.MIN_VALUE;
    }

    public ReturnData(TreeNode head, int size, int min, int max) {
        this.head = head;
        this.size = size;
        this.min = min;
        this.max = max;
    }

    @Override
    public String toString() {
        return "ReturnData{" +
                "head=" + head.toString() +
                ", size=" + size +
                ", min=" + min +
                ", max=" + max +
                '}';
    }
}