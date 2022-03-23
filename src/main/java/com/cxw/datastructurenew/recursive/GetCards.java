package com.cxw.datastructurenew.recursive;

/**
 * @author chengxuwei
 * @create 2022/3/22 16:46
 * @desc [1,2,100,4]  两个人拿纸牌，只能最左或者最右边拿
 */
public class GetCards {
    public static void main(String[] args) {
        int[] ary = {1,2,100,4};
        int r = Math.max(first(ary,0,ary.length-1),last(ary,0,ary.length-1));
        System.out.println(r);
    }
    public static int first(int[] ary,int l,int r){
        //base case
        if(l == r){
            return ary[l];
        }
        //拿左侧纸牌
        int lr = ary[l]+last(ary,l+1,r);
        //拿右侧纸牌
        int rr = ary[r]+last(ary,l,r-1);
        //取最大的
        return Math.max(lr,rr);
    }

    public static int last(int[] ary,int l,int r){
        //base case
        if(l == r){
            return 0;
        }
        //先手拿左侧纸牌  后手无纸牌
        int lr = first(ary,l+1,r);
        //拿右侧纸牌
        int rr = first(ary,l,r-1);
        //取最大的
        return Math.min(lr,rr);
    }
}
