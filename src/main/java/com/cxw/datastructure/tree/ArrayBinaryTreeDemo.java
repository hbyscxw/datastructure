package com.cxw.datastructure.tree;

/**
 * 顺序存储二叉树
 * 特点：
 * 1.只考虑完全二叉树
 * 2.下标为n的节点的左子节点下标为2*n+1
 * 3.下标为n的节点的右子节点下标为2*n+2
 * 4.下标为n的节点的父节点下标(n-1)/2
 *             1
 *          /   \
 *         2     3
 *       /  \   /  \
 *     4    5  6    7
 *
 */
public class ArrayBinaryTreeDemo {
    public static void main(String[] args) {
        int[] ary = {1,2,3,4,5,6,7};
        ArrayBinaryTree tree = new ArrayBinaryTree(ary);
        tree.preOrder();
    }
}
class ArrayBinaryTree{
    int[] ary;
    public ArrayBinaryTree(int[] ary) {
        this.ary = ary;
    }
    public void preOrder(){
        preOrder(0);
    }
    public void preOrder(int index){
        if(ary==null||ary.length==0){
            System.out.println("数组二叉树为空！");
            return;
        }
        System.out.println(ary[index]);
        //向左递归
        if(index*2+1<ary.length){
            preOrder(index*2+1);
        }
        //向右递归
        if(index*2+2<ary.length){
            preOrder(index*2+2);
        }
    }

    public void infixOrder(){
        infixOrder(0);
    }
    public void infixOrder(int index){
        if(ary==null||ary.length==0){
            System.out.println("数组二叉树为空！");
            return;
        }
        //向左递归
        if(index*2+1<ary.length){
            infixOrder(index*2+1);
        }
        System.out.println(ary[index]);
        //向右递归
        if(index*2+2<ary.length){
            infixOrder(index*2+2);
        }
    }
    public void postOrder(){
        postOrder(0);
    }

    public void postOrder(int index){
        if(ary==null||ary.length==0){
            System.out.println("数组二叉树为空！");
            return;
        }
        //向左递归
        if(index*2+1<ary.length){
            postOrder(index*2+1);
        }
        //向右递归
        if(index*2+2<ary.length){
            postOrder(index*2+2);
        }
        System.out.println(ary[index]);
    }

}
