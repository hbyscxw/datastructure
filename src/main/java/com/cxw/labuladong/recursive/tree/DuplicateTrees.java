package com.cxw.labuladong.recursive.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chengxuwei
 * @date 2021-02-26 16:01
 * @description 寻找重复的子树
 * https://leetcode-cn.com/problems/find-duplicate-subtrees/
 */
public class DuplicateTrees {
    Map<String,Integer> treeCount = new HashMap<>();
    List<TreeNode> nodeList = new ArrayList<>();
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        traverse(root);
        return nodeList;
    }
    public String traverse(TreeNode root) {
        if(root==null){
            return "#";
        }
        String left = traverse(root.left);
        String right = traverse(root.right);
        String treeStr = left+","+right+","+root.val;
        Integer count = treeCount.get(treeStr);
        if(count==null){
            treeCount.put(treeStr,1);
        }else if(count==1){
            nodeList.add(root);
            treeCount.put(treeStr,count+1);
        }else{
            treeCount.put(treeStr,count+1);
        }
        return treeStr;
    }
}