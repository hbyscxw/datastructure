package com.cxw.labuladong.recursive.tree;

import java.util.LinkedList;

/**
 * @author chengxuwei
 * @date 2021-02-26 18:02
 * @description 二叉树序列化和反序列化
 */
public class TreeCodec {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(4);
        TreeNode node4 = new TreeNode(5);
        root.left = node1;
        root.right = node2;
        node2.left = node3;
        node2.right = node4;
        String str = serialize(root);
        System.out.println(str);
        TreeNode newTree = deserialize(str);
        System.out.println(newTree);

    }
    // Encodes a tree to a single string.
    public static String serialize(TreeNode root) {
        if(root == null){
            return "#";
        }
        String left = serialize(root.left);
        String right = serialize(root.right);
        return root.val+","+left+","+right;
    }

    // Decodes your encoded data to tree.
    public static TreeNode deserialize(String data) {
        String[] ary = data.split(",");
        LinkedList<Integer> nodes = new LinkedList<>();
        for (String s : ary) {
            if(s.equals("#")){
                nodes.add(null);
            }else{
                nodes.add(Integer.parseInt(s));
            }
        }
        return deserializeNode(nodes);
    }

    private static TreeNode deserializeNode(LinkedList<Integer> nodes) {
        if(nodes.size()==0){
            return null;
        }
        Integer rootValue = nodes.removeFirst();
        if(rootValue==null){
            return null;
        }
        TreeNode root = new TreeNode(rootValue);
        root.left = deserializeNode(nodes);
        root.right = deserializeNode(nodes);
        return root;
    }
}