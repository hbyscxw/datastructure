package com.cxw.leetcodecourse.tree.prefixtree;

/**
 * @author chengxuwei
 * @date 2020-10-27 09:58
 * @description 给定一个数组，求子数组的最大异或和。
 * 一个数组的异或和为，数组中所有的数异或起来的结果。
 */
public class MaxXorSum {
    public static void main(String[] args) {
        int[] ary = {1,3,3,4,5,6};
        int res = maxXorSumE2(ary);
        System.out.println(res);
        res = maxXorSumE3(ary);
        System.out.println(res);
    }

    private static int maxXorSumE3(int[] ary) {
        int maxXor = Integer.MIN_VALUE;
        int xor = 0;
        PrefixTree tree = new PrefixTree();
        tree.add(0);
        for (int i = 0; i < ary.length; i++) {
            xor ^= ary[i];
            maxXor = Math.max(maxXor,tree.maxXor(xor));
            tree.add(xor);
        }
        return maxXor;
    }

    private static int maxXorSumE2(int[] ary) {
        int maxXor = Integer.MIN_VALUE;
        int[] dp = new int[ary.length];
        int xor = 0;
        for (int i = 0; i < ary.length; i++) {
            xor ^= ary[i];
            maxXor = Math.max(maxXor,xor);
            dp[i] = xor;
            for (int j = 1; j <= i; j++) {
                int curXor = xor^dp[j-1];
                maxXor = Math.max(maxXor,curXor);
            }
        }
        return maxXor;
    }

    private static int maxXorSumE1(int[] ary) {
        int maxXor = Integer.MIN_VALUE;
        for (int i = 0; i < ary.length; i++) {
            for (int j = 0; j <= i; j++) { //0~j
                int res = 0;
                for (int k = j; k <=i ; k++) { //j~i
                    res ^= ary[k];
                }
                maxXor = Math.max(maxXor,res);
            }
        }
        return maxXor;
    }

}
class PrefixTree{

    PrefixTreeNode head = new PrefixTreeNode();

    public void add(int value){
        PrefixTreeNode cur = head;
        //整数32位
        for (int i = 31; i >=0 ; i--) {
            int path = (value>>i) & 1;
            if(cur.nexts[path]==null){
                cur.nexts[path]=new PrefixTreeNode();
            }
            cur = cur.nexts[path];
        }
    }

    public int maxXor(int value){
        PrefixTreeNode cur = head;
        int res = 0;
        //整数32位
        for (int i = 31; i >=0 ; i--) {
            int path = (value >> i) & 1;
            //期望
            //最高位为符号位
            int expect = (i == 31)?path:(path^1);
            //实际
            int best = cur.nexts[expect]==null?expect^1:expect;
            //将当前位置值放回结果里
            res |= (best^path)<<i;
            cur = cur.nexts[best];
        }
        return res;
    }
}
class PrefixTreeNode{
    PrefixTreeNode[] nexts = new PrefixTreeNode[2];
}