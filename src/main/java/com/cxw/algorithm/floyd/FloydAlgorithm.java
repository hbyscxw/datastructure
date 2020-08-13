package com.cxw.algorithm.floyd;


import java.util.Arrays;

public class FloydAlgorithm {

    final static int N = 65535;

    public static void main(String[] args) {
        char[] vertex = {'A','B','C','D','E','F','G'};
        //邻接矩阵
        int[][] matrix = {
                {0,5,7,N,N,N,2},
                {5,0,N,9,N,N,3},
                {7,N,0,N,8,N,N},
                {N,9,N,0,N,4,N},
                {N,N,8,N,0,5,4},
                {N,N,N,4,5,0,6},
                {2,3,N,N,4,6,0},
        };
        Graph graph = new Graph(vertex,matrix);
        graph.floyd();
        graph.show();
    }
}
class Graph {
    private char[] vertex;//顶点数组
    private int[][] dis;//节点到节点之间的距离
    private int[][] pre;//前驱节点下标
    private int length;

    public Graph(char[] vertex, int[][] matrix) {
        this.vertex = vertex;
        this.dis = matrix;
        this.length = vertex.length;
        pre = new int[length][length];
        for (int i = 0; i < length; i++) {
            Arrays.fill(pre[i],i);
        }
    }

    public void floyd(){
        //中间节点下标
        for (int k = 0; k < length; k++) {
            //起点下标
            for (int i = 0; i < length; i++) {
                //终点下标
                for (int j = 0; j < length; j++) {
                    if(dis[i][j]>dis[i][k]+dis[k][j]){
                        dis[i][j]=dis[i][k]+dis[k][j];
                        pre[i][j] = pre[k][j];
                    }
                }
            }
        }
    }
    public void show(){
        for (int i = 0; i < length; i++) {
            System.out.println("dis"+Arrays.toString(dis[i]));
            System.out.println("pre"+Arrays.toString(pre[i]));
        }
    }
}
