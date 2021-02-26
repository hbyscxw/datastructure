package com.cxw.labuladong.recursive.tree;

/**
 * @author chengxuwei
 * @date 2021-02-26 11:15
 * @description 从前序与中序遍历序列构造二叉树
 * https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 */
public class InPostBuildBinaryTree {
    public static void main(String[] args) {

    }
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if(preorder==null||preorder.length==0||inorder==null||inorder.length==0){
            return null;
        }
        return buildTree(preorder,0,preorder.length-1,inorder,0,inorder.length-1);
    }

    public TreeNode buildTree(int[] inorder,int iStart,int iEnd, int[] postorder,int pStart,int pEnd) {
        if(iEnd<iStart||pEnd<pStart){
            return null;
        }
        int rootValue = postorder[pEnd];
        int iRootIndex = -1;
        for(int i = iStart;i<=iEnd;i++){
            if(rootValue==inorder[i]){
                iRootIndex = i;
                break;
            }
        }
        int inLen = iRootIndex-iStart;
        TreeNode root = new TreeNode(rootValue);
        root.left = buildTree(inorder,iStart,iRootIndex-1,postorder,pStart,pStart+inLen-1);
        root.right = buildTree(inorder,iRootIndex+1,iEnd,postorder,pStart+inLen,pEnd-1);
        return root;
    }
}