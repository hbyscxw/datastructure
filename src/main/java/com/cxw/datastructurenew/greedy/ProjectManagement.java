package com.cxw.datastructurenew.greedy;

import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author chengxuwei
 * @create 2022/3/15 17:19
 * @desc
 * 题意：输入: 正数数组costs、正数数组profits、正数K、正数M
 * costs[i]表示i号项目的花费
 * profits[i]表示i号项目在扣除花费之后还能挣到的钱(利润)
 * K表示你只能串行的最多做k个项目
 * M表示你初始的资金
 * 说明: 每做完一个项目，马上获得的收益，可以支持你去做下一个项目。不能并行的做项目。
 * 输出：你最后获得的最大钱数。
 *
 *  花费小根堆  利润大根堆
 */
public class ProjectManagement {
    public static void main(String[] args) {
        int[] costs = {1,1,2,2,3,4};
        int[] profits = {1,4,3,7,2,10};
        int k = 4;
        int m = 1;
        int sum = doProject(costs,profits,k,m);
        System.out.println(sum);
    }

    private static int doProject(int[] costs, int[] profits, int k, int m) {
        //花费小根堆
        PriorityQueue<Project> costHeap = new PriorityQueue<>(Comparator.comparingInt(o -> o.cost));
        //利润大根堆
        PriorityQueue<Project> profitHeap = new PriorityQueue<>((o1,o2)->o2.profit-o1.profit);
        for (int i = 0; i < costs.length; i++) {
            Project p = new Project(costs[i],profits[i]);
            costHeap.add(p);
        }

        while(k>0){
            while(costHeap.size()>0&&costHeap.peek().cost<=m){
                Project p = costHeap.poll();
                profitHeap.add(p);//放入利润大根堆里
            }
            if(profitHeap.size()>0){
                Project p = profitHeap.poll();
                m += p.profit;
                k--;
            }else{
                return m;
            }
        }
        return m;
    }

    static class Project{
        int cost;
        int profit;

        public Project(int cost, int profit) {
            this.cost = cost;
            this.profit = profit;
        }
    }
}
