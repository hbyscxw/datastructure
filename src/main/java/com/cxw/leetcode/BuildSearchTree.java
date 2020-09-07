package com.cxw.leetcode;

/**
 * @author chengxuwei
 * @date 2020-09-03 17:57
 * @description  一个有序数组建立平衡二叉树 AVLTree
 * https://leetcode-cn.com/problems/convert-sorted-array-to-binary-search-tree/
 */
public class BuildSearchTree {
    public static void main(String[] args) {
//        int[] ary = {-10,-3,0};
        int[] ary = {-10,-3,0,5,9};
//        TreeNode root = binaryBuildTree(ary);
//        printTree(root);
        //System.out.println(root.val);
        TreeNode root = loopAddAndAdjust(ary);
        printTree(root);
    }

    private static void printTree(TreeNode root) {
        if(root!=null){
            System.out.println(root);
            printTree(root.left);
            printTree(root.right);
        }
    }


    private static TreeNode binaryBuildTree(int[] ary) {
        int mid = ary.length/2;
        TreeNode root = new TreeNode(ary[mid]);
        root.left = doBinaryBuildTree(ary,0,mid-1);
        root.right = doBinaryBuildTree(ary,mid+1,ary.length-1);
        return root;
    }

    private static TreeNode doBinaryBuildTree(int[] ary, int start, int end) {
        if(start == end){
            return new TreeNode(ary[start]);
        }else if (start>end){
            return null;
        }else{
            //防溢出
            int mid = start + (end-start)/2;
            TreeNode node = new TreeNode(ary[mid]);
            node.left = doBinaryBuildTree(ary,start,mid-1);
            node.right = doBinaryBuildTree(ary,mid+1,end);
            return node;
        }
    }


    private static TreeNode loopAddAndAdjust(int[] ary){
        TreeNode root = null;
        for (int i = 0; i < ary.length; i++) {
            if(i==0){
                root = new TreeNode(ary[i]);
            }else{
                root = addAndAdjust(root,new TreeNode(ary[i]));
            }
        }
        return root;
    }

    private static TreeNode addAndAdjust(TreeNode root, TreeNode node) {
        addNode(root,node);
        return adjust(root);
    }

    private static TreeNode adjust(TreeNode root) {
        if(root == null){
            return null;
        }
        int ld = getDepth(root.left);
        int rd = getDepth(root.right);
        if(rd-ld>1){
            return leftRotate(root);
        }
        return root;
    }

    private static TreeNode leftRotate(TreeNode root) {
        if(root==null) {
            return null;
        }
        //
        if(root.right!=null&&getDepth(root.right.left)>getDepth(root.right.right)){
            doRightRotate(root.right);
            doLeftRotate(root);
        }else{
            doLeftRotate(root);
        }
        return root;
    }

    private static void doRightRotate(TreeNode root) {
        TreeNode newNode = new TreeNode(root.val);
        newNode.left = root.left.right;
        newNode.right = root.right;
        root.val = root.right.val;
        root.left = root.left.left;
        root.right = newNode;
    }

    private static void doLeftRotate(TreeNode root) {
        //1.创建新节点
        TreeNode newNode = new TreeNode(root.val);
        //2.新节点的左子树为节点的左子树
        newNode.left = root.left;
        //3.新节点的右子树为节点的右子树的左子树
        newNode.right = root.right.left;
        //4.将右子树的值赋值给原节点
        root.val = root.right.val;
        //5.原节点的左子树指向新节点
        root.left = newNode;
        //6.原节点的右子树指向原节点的右子树的右子树
        root.right = root.right.right;
    }

    private static int getDepth(TreeNode root) {
        if(root==null){
            return 0;
        }
        int ld = getDepth(root.left);
        int rd = getDepth(root.right);
        return Math.max(ld,rd)+1;
    }

    private static void addNode(TreeNode root, TreeNode node) {
        if(root==null||node == null){
            return;
        }
        if(root.val<=node.val){
            if(root.right!=null){
                addNode(root.right,node);
            }else{
                root.right = node;
            }
        }else{
            if(root.left!=null){
                addNode(root.left,node);
            }else{
                root.left = node;
            }
        }
    }
}