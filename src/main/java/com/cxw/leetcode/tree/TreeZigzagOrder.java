package com.cxw.leetcode.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author chengxuwei
 * @date 2020-12-23 10:45
 * @description 二叉树的锯齿形层序遍历
 * 给定一个二叉树，返回其节点值的锯齿形层序遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
 *
 * 例如：
 * 给定二叉树 [3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回锯齿形层序遍历如下：
 *
 * [
 *   [3],
 *   [20,9],
 *   [15,7]
 * ]
 * https://leetcode-cn.com/problems/binary-tree-zigzag-level-order-traversal/
 */
public class TreeZigzagOrder {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        LinkedList<TreeNode> list = new LinkedList<>();
        List<List<Integer>> resList = new ArrayList<>();
        list.offer(root);
        boolean ltr = true;
        while(list.size()>0){
            List<Integer> nodeList = new ArrayList<>();
            int size = list.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = list.pop();
                if(ltr) {
                    nodeList.add(node.val);
                }else{
                    nodeList.add(0,node.val);
                }
                if(node.left!=null) {
                    list.add(node.left);
                }
                if(node.right!=null) {
                    list.add(node.right);
                }
            }
            resList.add(nodeList);
            ltr = !ltr;
        }
        return resList;
    }
}