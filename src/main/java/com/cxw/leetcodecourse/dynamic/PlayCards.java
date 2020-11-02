package com.cxw.leetcodecourse.dynamic;

/**
 * @author chengxuwei
 * @date 2020-11-02 10:18
 * @description
 *
 * 排成一条线的纸牌博弈问题
 *
 * 【题目】
 *
 * 给定一个整型数组arr，代表数值不同的纸牌排成一条线。玩家A和玩家B依次拿走每张纸牌，规定玩家A先拿，玩家B后拿，但是每个玩家每次只能拿走最左或最右的纸牌，玩家A和玩家B都绝顶聪明。请返回最后获胜者的分数。
 *
 * 【举例】
 *
 * arr=[1,2,100,4]。
 *
 * 开始时玩家A只能拿走1或4。如果玩家A拿走1，则排列变为[2,100,4]，接下来玩家B可以拿走2或4，然后继续轮到玩家A。如果开始时玩家A拿走4，则排列变为[1,2,100]，接下来玩家B可以拿走1或100，然后继续轮到玩家A。玩家A作为绝顶聪明的人不会先拿4，因为拿4之后，玩家B将拿走100。所以玩家A会先拿1，让排列变为[2,100,4]，接下来玩家B不管怎么选，100都会被玩家A拿走。玩家A会获胜，分数为101。所以返回101。
 *
 * arr=[1,100,2]。
 *
 * 开始时玩家A不管拿1还是2，玩家B作为绝顶聪明的人，都会把100拿走。玩家B会获胜，分数为100。所以返回100。
 */
public class PlayCards {
    public static void main(String[] args) {
        int[] ary = {1,2,100,4};
        int res = win2(ary);
        System.out.println(res);
    }

    //暴力求解
    private static int win1(int[] ary) {
        if(ary==null||ary.length==0){
            return 0;
        }
        return Math.max(f(ary,0,ary.length-1),s(ary,0,ary.length-1));
    }

    //first  先拿
    private static int f(int[] ary, int l, int r) {
        if(l==r){
            return ary[l];
        }
        return Math.max(ary[l]+s(ary,l+1,r),ary[r]+s(ary,l,r-1));
    }
    //second 后拿
    private static int s(int[] ary, int l, int r) {
        if(l==r) {
            return 0;
        }
//        System.out.println("left"+f(ary,l+1,r));
//        System.out.println("right"+f(ary,l,r-1));
        //因为对手会拿走最好的，所以当前玩家只能拿最差的
        return Math.min(f(ary,l+1,r),f(ary,l,r-1));
    }

    //动态规划
    private static int win2(int[] ary) {
        if(ary==null||ary.length==0){
            return 0;
        }
        int[][] f = new int[ary.length][ary.length];
        int[][] s = new int[ary.length][ary.length];
        //对角线上的都是0
        for (int j = 0; j < ary.length; j++) {
            f[j][j] = ary[j];
            for (int i = j-1; i >= 0; i--) {
                f[i][j] = Math.max(ary[i]+s[i+1][j],ary[j]+s[i][j-1]);
                s[i][j] = Math.min(f[i+1][j],f[i][j-1]);
            }
        }
        return Math.max(f[0][ary.length-1],s[0][ary.length-1]);
    }
}