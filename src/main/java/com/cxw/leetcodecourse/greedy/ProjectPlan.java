package com.cxw.leetcodecourse.greedy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author chengxuwei
 * @date 2020-09-16 10:29
 * @description 做项目
 * 输入:参数1， 正数数组costs参数2，正数数组profits 参数3,
 * 正数k参数4，正数m
 * costs [i]表示i号项目的花费profits[i]表示i号项目在扣除花
 * 费之后还能挣到的钱(利润) k表示你不能并行、只能串行的最多
 * 做k个项目m表示你初始的资金
 * 说明:你每做完- -个项目，马上获得的收益，可以支持你去做下
 * 一个项目。
 * 输出:你最后 获得的最大钱数。
 */
public class ProjectPlan {
    public static void main(String[] args) {
        int[] costs = {10,20,15,7}; //项目花费
        int[] profits = {3,7,5,4}; //项目利润
        int k = 3; //可做项目数
        int m = 10; //初始资金
        int earning = maxEarnings(costs,profits,k,m);
        System.out.println(earning);
    }

    private static int maxEarnings(int[] costs, int[] profits, int k, int m) {
        List<Project> projects = getProjects(costs,profits);
        //默认就是小根堆
        PriorityQueue<Project> costsHeap = new PriorityQueue<>(Comparator.comparingInt(o -> o.cost));
        //大根堆
        PriorityQueue<Project> profitsHeap = new PriorityQueue<>((o1,o2)->Integer.compare(o2.profit,o1.profit));
        costsHeap.addAll(projects);

        canDoProject(costsHeap,profitsHeap,m);
        int n = 1;
        int e = 0;
        while(n<=k){
            if(profitsHeap.isEmpty()){
                break;
            }
            Project p = profitsHeap.poll();
            e += p.profit;
            n++;
            canDoProject(costsHeap,profitsHeap,m+e);
        }
        return e;
    }

    private static void canDoProject(PriorityQueue<Project> costsHeap, PriorityQueue<Project> profitsHeap, int m) {
        while(!costsHeap.isEmpty()){
            Project p = costsHeap.peek();
            if(p.cost<=m){
                profitsHeap.add(p);
                costsHeap.poll();
            }else{
                break;
            }
        }
    }

    private static List<Project> getProjects(int[] costs, int[] profits) {
        List<Project> list = new ArrayList<>();
        for (int i = 0; i < costs.length; i++) {
            list.add(new Project(costs[i],profits[i]));
        }
        return list;
    }
}
class Project{
    int cost;
    int profit;

    public Project() {
    }

    public Project(int cost, int profit) {
        this.cost = cost;
        this.profit = profit;
    }
}