package com.cxw.leetcodecourse.tree;

/**
 * @author chengxuwei
 * @date 2020-09-03 14:56
 * @description 有父节点树的中序遍历的后继节点
 */
public class PrintSucceedNode {
    public static void main(String[] args) {
        int[] ary = {1,2,3,4,5,6,7,8};
        ParentSucceedNode head = aryToTree(ary);
        ParentSucceedNode node = getSucceedNode(head);
        System.out.println(node.value);
    }

    private static ParentSucceedNode aryToTree(int[] ary) {
        if(ary==null||ary.length==0){
            return null;
        }
        ParentSucceedNode[] nodes = new ParentSucceedNode[ary.length];
        for (int i = 0; i < ary.length; i++) {
            nodes[i] = new ParentSucceedNode(ary[i]);
        }
        for (int i = 0; i < ary.length; i++) {
            if(i*2+1<ary.length){
                nodes[i].left = nodes[i*2+1];
            }
            if(i*2+2<ary.length){
                nodes[i].right = nodes[i*2+2];
            }
            if(i!=0){
                nodes[i].parent = nodes[(i+1)/2];
            }
        }
        return nodes[0];
    }

    private static ParentSucceedNode getSucceedNode(ParentSucceedNode node) {
        if(node==null){
            return null;
        }
        if(node.right!=null){
            //右节点不为空，返回右节点的最左子节点
            return getLastLeftNode(node.right);
        }
        return getParentLeftNode(node);
    }

    private static ParentSucceedNode getParentLeftNode(ParentSucceedNode node) {
        if(node==null){
            return null;
        }
        ParentSucceedNode parent = node.parent;
        while(parent!=null&&parent.left!=node){
            node = parent;
            parent = node.parent;
        }
        return parent;
    }

    private static ParentSucceedNode getLastLeftNode(ParentSucceedNode node) {
        ParentSucceedNode n = null;
        while(node!=null){
            n = node;
            node = node.left;
        }
        return n;
    }
}
class ParentSucceedNode{
    int value;
    ParentSucceedNode left;
    ParentSucceedNode right;
    ParentSucceedNode parent;

    public ParentSucceedNode(int value) {
        this.value = value;
    }
}