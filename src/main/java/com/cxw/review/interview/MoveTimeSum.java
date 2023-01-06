package com.cxw.review.interview;

/**
 * @className MoveTimeSum
 * @Description:
 * 数组中只有G和B，所有G在左边或者G在右边，返回最小的移动次数
 * @Author: chengxuwei
 * @Date:2023/1/5 14:27
 */
public class MoveTimeSum {
    public static void main(String[] args) {
        char[] ary = {'B','B','G','G','B','G','B','B','G'};
        int sum = move3(ary);
        System.out.println(sum);
    }

    //G在左边 B在右边
    private static int move(char[] ary) {
        //已排序G的下标
        int l = 0;
        //下一个G的下标
        int i = 0;
        int sum = 0;
        while(l<ary.length){
            while(i< ary.length){
                if(ary[i]=='G') {
                    int num = i - l;
                    sum += num;
                    break;
                }else {
                    i++;
                }
            }
            l++;
            i++;
        }
        return sum;
    }

    //G在左边或者G在右边，返回最小的移动次数
    private static int move2(char[] ary) {
        //已排序G的下标
        int l = 0;
        //下一个G的下标
        int i = 0;
        int sum = 0;
        while(l<ary.length){
            while(i< ary.length){
                if(ary[i]=='G') {
                    int num = i - l;
                    sum += num;
                    break;
                }else {
                    i++;
                }
            }
            l++;
            i++;
        }
        //已排序B的下标
        l = 0;
        //下一个B的下标
        i = 0;
        int sum2 = 0;
        while(l<ary.length){
            while(i< ary.length){
                if(ary[i]=='B') {
                    int num = i - l;
                    sum2 += num;
                    break;
                }else {
                    i++;
                }
            }
            l++;
            i++;
        }
        return Math.min(sum,sum2);
    }

    //优化move2
    private static int move3(char[] ary) {
        //已排序G的下标
        int gi = 0;
        //已排序B的下标
        int bi = 0;
        int sum = 0;
        int sum2 = 0;
        for (int i = 0; i < ary.length; i++) {
            if(ary[i] == 'G'){
                int num = i - gi;
                gi++;
                sum += num;
            }else{
                int num = i - bi;
                bi++;
                sum2 += num;
            }
        }
        return Math.min(sum,sum2);
    }
}
