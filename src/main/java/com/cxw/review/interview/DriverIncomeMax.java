package com.cxw.review.interview;

import com.google.common.collect.HashBasedTable;

/**
 * @className DriverIncomeMax
 * @Description:
 *
 * 题目4
 * 现有司机N*2人，调度中心会将所有司机平分给A、B两个区域
 * 第i个司机去A可得收入为income[i][0],
 * 第i个司机去B可得收入为income[i][1],
 * 返回所有调度方案中能使所有司机总收入最高的方案，是多少钱
 *
 * @Author: chengxuwei
 * @Date:2023/1/12 19:41
 */
public class DriverIncomeMax {
    public static void main(String[] args) {

    }

    // 0 [9,12]
    // 1 [46,23]
    // ...
    public static int maxIncome(int[][] income) {
        if(income==null||income.length<2||(income.length&1)!=0){
            return 0;
        }
        int n = income.length;
        int m = n>>1;
        return process(income,0,m);
    }

    //income数组
    //i下标
    //rest A区还剩多少个位置
    //返回 i..司机分配完，并且A/B区域司机同样多的情况下，司机的收益最大是多少
    private static int process(int[][] income, int i, int rest) {
        if(i==income.length){
            return 0;
        }
        //还剩司机
        if(income.length-i == rest){ //A区域剩余空位数 == 剩余司机数  说明B区域已满
            return income[i][0] + process(income,i+1,rest-1);
        }
        if(rest == 0){//A区域已满 只能去B区域
            return income[i][1] + process(income,i+1,rest);
        }
        //当前司机可以去A也可以去B
        int p1 = income[i][0] + process(income,i+1,rest-1);
        int p2 = income[i][1] + process(income,i+1,rest);
        return Math.max(p1,p2);
    }

    private static int process2(int[][] income, int i, int rest, HashBasedTable<Integer,Integer,Integer> table) {
        if(table.get(i,rest)!=null){
            return table.get(i,rest);
        }
        if(i==income.length){
            return 0;
        }
        //还剩司机
        if(income.length-i == rest){ //A区域剩余空位数 == 剩余司机数  说明B区域已满
            int max = income[i][0] + process2(income,i+1,rest-1,table);
            table.put(i,rest,max);
            return max;
        }
        if(rest == 0){//A区域已满 只能去B区域
            int max = income[i][1] + process2(income,i+1,rest,table);
            table.put(i,rest,max);
            return max;
        }
        //当前司机可以去A也可以去B
        int p1 = income[i][0] + process2(income,i+1,rest-1,table);
        int p2 = income[i][1] + process2(income,i+1,rest,table);
        int max = Math.max(p1,p2);
        table.put(i,rest,max);
        return max;
    }
}
