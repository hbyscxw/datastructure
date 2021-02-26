package com.cxw.labuladong.recursive.tree;



/**
 * @author chengxuwei
 * @date 2021-02-25 15:49
 * @description 最大二叉树
 * https://leetcode-cn.com/problems/maximum-binary-tree/
 */
public class BuildMaxTree {
    public static void main(String[] args) {
        int[] nums = {3,2,1,6,0,5};
        BuildMaxTree t = new BuildMaxTree();
        TreeNode treeNode = t.constructMaximumBinaryTree(nums);
        System.out.println(treeNode);
    }
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        if(nums==null||nums.length==0){
            return null;
        }
        return buildTree(nums,0,nums.length-1);
    }
    public int getMaxIndex(int[] nums,int left,int right){
        int index = left;
        for(int i=left+1;i<=right;i++){
            if(nums[i]>nums[index]){
                index = i;
            }
        }
        return index;
    }
    public TreeNode buildTree(int[] nums,int left,int right) {
        if(left>right){
            return null;
        }
        int index = getMaxIndex(nums,left,right);
        TreeNode root = new TreeNode(nums[index]);
        root.left = buildTree(nums,left,index-1);
        root.right = buildTree(nums,index+1,right);
        return root;
    }

}
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}