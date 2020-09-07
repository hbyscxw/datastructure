package com.cxw.leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author chengxuwei
 * @date 2020-09-03 16:37
 * @description
 * 给定一个二叉树，检查它是否是镜像对称的。
 *
 *  
 *
 * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
 *
 *     1
 *    / \
 *   2   2
 *  / \ / \
 * 3  4 4  3
 *  
 *
 * 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
 *
 *     1
 *    / \
 *   2   2
 *    \   \
 *    3    3
 *  
 *
 * 进阶：
 *
 * 你可以运用递归和迭代两种方法解决这个问题吗？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/symmetric-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class TreeSymmetry {

    public static void main(String[] args) {
        int[] ary = {1,2,2,3,4,4,3};
        TreeNode root = aryToTree(ary);
        TreeSymmetry ts = new TreeSymmetry();
        System.out.println(ts.isSymmetric(root));
    }

    public boolean isSymmetric(TreeNode root) {
        if(root!=null){
            return loopCheck(root.left,root.right);
        }else{
            return true;
        }
    }

    private boolean recursiveCheck(TreeNode left, TreeNode right) {
        if(left==null&&right==null){
            return true;
        }else if(left!=null&&right!=null){
            if(left.val == right.val) {
                return recursiveCheck(left.left, right.right) && recursiveCheck(left.right, right.left);
            }
            return false;
        }else{
            return false;
        }

    }
    private boolean loopCheck(TreeNode left, TreeNode right) {
        if(left==null&&right==null){
            return true;
        }else if(left!=null&&right!=null){
            Queue<TreeNode> queue = new LinkedList<>();
            if(left.val == right.val) {
                queue.add(left.left);
                queue.add(right.right);
                queue.add(left.right);
                queue.add(right.left);
                while(!queue.isEmpty()){
                    TreeNode one = queue.poll();
                    TreeNode two = queue.poll();
                    if(one == null && two == null){
                        //continue;
                    }else if(one != null && two!=null){
                        if(one.val == two.val){
                            queue.add(one.left);
                            queue.add(two.right);
                            queue.add(one.right);
                            queue.add(two.left);
                        }else{
                            return false;
                        }
                    }else{
                        return false;
                    }
                }
                return true;
            }
            return false;
        }else{
            return false;
        }

    }
    private static TreeNode aryToTree(int[] ary) {
        if(ary==null||ary.length==0){
            return null;
        }
        TreeNode[] nodes = new TreeNode[ary.length];
        for (int i = 0; i < ary.length; i++) {
            nodes[i] = new TreeNode(ary[i]);
        }
        for (int i = 0; i < ary.length; i++) {
            if(i*2+1<ary.length){
                nodes[i].left = nodes[i*2+1];
            }
            if(i*2+2<ary.length){
                nodes[i].right = nodes[i*2+2];
            }

        }
        return nodes[0];
    }
}
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }

    @Override
    public String toString() {
        return "TreeNode{" +
                "val=" + val +
                ", left=" + (left!=null?left.val:" ") +
                ", right=" + (right!=null?right.val:" ") +
                '}';
    }
}