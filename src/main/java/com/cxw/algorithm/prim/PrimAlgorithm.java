package com.cxw.algorithm.prim;

import java.util.Arrays;

/**
 * @author chengxuwei
 * @date 2020-07-30 18:06
 * @description 普里姆算法
 * 修路问题
 */
public class PrimAlgorithm {
    public static void main(String[] args) {
        char[] data = {'A','B','C','D','E','F','G'};
        int[][] weight = {
                {10000,5,7,10000,10000,10000,2},
                {5,10000,10000,9,10000,10000,3},
                {7,10000,10000,10000,8,10000,10000},
                {10000,9,10000,10000,10000,4,10000},
                {10000,10000,8,10000,10000,5,4},
                {10000,10000,10000,4,5,10000,6},
                {2,3,10000,10000,4,6,10000},
        };
        MGraph graph = new MGraph(data,weight);
        MinTree tree = new MinTree();
        tree.showGraph(graph);
        tree.prim(graph,0);
    }
}
class MinTree{
    public void showGraph(MGraph graph){
        for (int[] ints : graph.weight) {
            System.out.println(Arrays.toString(ints));
        }
    }

    /**
     * prim算法得到最小生成树
     * @param graph 图
     * @param v 从图的第几个顶点开始生成 'A'->0 'B'->1 ...
     */
    public void prim(MGraph graph,int v){
        int visited[] = new int[graph.vertexs];
        visited[v] = 1;//把当前节点标记已访问
        //h1 h2 记录两顶点的下标
        int h1 = -1;
        int h2 = -1;
        int minWeight = 10000;
        for (int k = 1; k < graph.vertexs; k++) {// graph.vertexs个顶点 就循环graph.vertexs-1次
            //确定每次生成的子图，和哪个节点距离最近
            for (int i = 0; i < graph.vertexs; i++) {
                for (int j = 0; j < graph.vertexs; j++) {
                    if(visited[i]==1&&visited[j]==0&&graph.weight[i][j]<minWeight){
                        minWeight = graph.weight[i][j];
                        h1 = i;
                        h2 = j;
                    }
                }
            }
            System.out.println("<"+graph.data[h1]+","+graph.data[h2]+"> 权值:"+minWeight);
            //将当前这个节点标记为已访问
            visited[h2]=1;
            //还原minWeight
            minWeight = 10000;
        }
    }
}
class MGraph{
    char[] data; //顶点名称
    int vertexs; //顶点个数
    int[][] weight; //图的邻接矩阵
    public MGraph(char[] data,int[][] weight){
        this.data = data;
        this.vertexs = data.length;
        this.weight = weight;
    }
}