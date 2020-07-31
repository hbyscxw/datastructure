package com.cxw.algorithm.kruskal;

import java.util.Arrays;

/**
 * @author chengxuwei
 * @date 2020-07-31 11:39
 * @description 克鲁斯卡尔
 */
public class KruskalCase {

    public static void main(String[] args) {
        char[] data = {'A','B','C','D','E','F','G'};
        int[][] weight = {
                {0,5,7,10000,10000,10000,2},
                {5,0,10000,9,10000,10000,3},
                {7,10000,0,10000,8,10000,10000},
                {10000,9,10000,0,10000,4,10000},
                {10000,10000,8,10000,0,5,4},
                {10000,10000,10000,4,5,0,6},
                {2,3,10000,10000,4,6,0},
        };
        KruskalCase kruskalCase = new KruskalCase(data,weight);
        kruskalCase.print();
        kruskalCase.kruskal();

    }



    private int edgeNum;//边个数
    private char[] vertexs;//顶点数组
    private int[][] matrix;//邻接矩阵
    public KruskalCase(char[] vertexs,int[][] matrix){
        this.vertexs = vertexs;
        this.matrix = matrix;
        for (int i = 0; i < matrix.length ; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if(matrix[i][j]>0&&matrix[i][j]<10000){
                    edgeNum++;
                }
            }
        }
    }
    public void kruskal(){
        int index = 0;//标识最后结果数组的索引
        int[] ends = new int[edgeNum];//用于保存"已有最小生成树"中每个顶点在最小生成树种的终点
        //创建结果数组，保存最后的最小生成树
        EData[] rets = new EData[edgeNum];
        EData[] edges = getEdges();
        System.out.println("图的边的集合="+ Arrays.toString(edges)+" 共"+edges.length);
        //按权值大小进行排序
        sortEdges(edges);
        //遍历edges数组，将边添加到最小生成树种，判断是否准备加入的边形成回路
        for (int i = 0; i < edgeNum; i++) {
            //获取两个顶点的下标
            int p1 = getPosition(edges[i].start);
            int p2 = getPosition(edges[i].end);

            //获取p1的终点
            int m = getEnd(ends,p1);
            //获取p2的终点
            int n = getEnd(ends,p2);
            if(m!=n){
                ends[m] = n;
                rets[index++] = edges[i];
            }
        }
        System.out.println("最小生成树为：");
        for (int i = 0; i < index; i++) {
            System.out.println(rets[i]);
        }
    }
    private void print() {
        System.out.println("邻接矩阵为：");
        for (int i = 0; i < vertexs.length; i++) {
            for (int j = 0; j < vertexs.length; j++) {
                System.out.printf("%12d",matrix[i][j]);
            }
            System.out.println();
        }
    }
    /**
     * 获取下标 i 的顶点的终点
     * @param ends
     * @param i
     * @return
     */
    private int getEnd(int[] ends, int i) {
        while(ends[i]!=0){
            i = ends[i];
        }
        return i;
    }

    /**
     *
     * @param ch 顶点 eg:'A','B'
     * @return 返回顶点的下标
     */
    private int getPosition(char ch) {
        for (int i = 0; i < vertexs.length; i++) {
            if(vertexs[i]== ch){
                return i;
            }
        }
        return -1;
    }

    private void sortEdges(EData[] edges) {
        for (int i = 0; i < edges.length-1; i++) {
            for (int j = 0; j < edges.length-1-i; j++) {
                if(edges[j]!=null&&edges[j+1]!=null&&edges[j].weight>edges[j+1].weight){
                    EData temp = edges[j];
                    edges[j] = edges[j+1];
                    edges[j+1] = temp;
                }
            }
        }
    }

    private EData[] getEdges() {
        int index = 0;
        EData[] edges = new EData[edgeNum];
        for (int i = 0; i < vertexs.length; i++) {
            for (int j = i+1; j < vertexs.length; j++) {
                if(matrix[i][j]!=10000){
                    edges[index++] = new EData(vertexs[i],vertexs[j],matrix[i][j]);
                }
            }
        }
        return edges;
    }
}
class EData{
    char start;//边的一个点
    char end;//边的另一点
    int weight;//边的权值

    @Override
    public String toString() {
        return "EData{" +
                "<" + start +
                "," + end +
                ">=" + weight +
                '}';
    }

    public EData(char start, char end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }
}