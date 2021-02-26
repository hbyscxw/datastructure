package com.cxw.labuladong.recursive.tree;

/**
 * @author chengxuwei
 * @date 2021-02-26 11:15
 * @description 从前序与中序遍历序列构造二叉树
 * https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 */
public class PreInBuildBinaryTree {
    public static void main(String[] args) {
        //[3,9,20,15,7]
        //[9,3,15,20,7]
        int[] pAry = {3,9,20,15,7};
        int[] iAry = {9,3,15,20,7};
        PreInBuildBinaryTree t = new PreInBuildBinaryTree();
        TreeNode root = t.buildTree(pAry, iAry);
        System.out.println(root);
    }
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if(preorder==null||preorder.length==0||inorder==null||inorder.length==0){
            return null;
        }
        return buildTree(preorder,0,preorder.length-1,inorder,0,inorder.length-1);
    }

    private TreeNode buildTree(int[] preorder,int pStart, int pEnd, int[] inorder,  int iStart, int iEnd) {
        if(pEnd<pStart||iEnd<iStart){
            return null;
        }
        int rootValue = preorder[pStart];
        TreeNode root = new TreeNode(rootValue);
        int rootInIndex = -1;
        for (int i = iStart; i <= iEnd ; i++) {
            if(rootValue==inorder[i]){
                rootInIndex = i;
                break;
            }
        }
        //左树长度
        int leftLen = rootInIndex-iStart;
        root.left = buildTree(preorder,pStart+1,pStart+1+leftLen-1,inorder,iStart,rootInIndex-1);
        root.right = buildTree(preorder,pStart+1+leftLen-1+1,pEnd,inorder,rootInIndex+1,iEnd);
        return root;
    }
}