package com.cxw.algorithm.dijkstra;

import java.util.Arrays;

/**
 * @author chengxuwei
 * @date 2020-08-03 16:12
 * @description 迪杰斯特拉算法
 */
public class DijkstraAlgorithm {
    final static int N = 65535;
    public static void main(String[] args) {
        char[] vertex = {'A','B','C','D','E','F','G'};
        //邻接矩阵
        int[][] matrix = {
                {N,5,7,N,N,N,2},
                {5,N,N,9,N,N,3},
                {7,N,N,N,8,N,N},
                {N,9,N,N,N,4,N},
                {N,N,8,N,N,5,4},
                {N,N,N,4,5,N,6},
                {2,3,N,N,4,6,N},
        };
        Graph graph = new Graph(vertex,matrix);
        graph.showGraph();
        graph.dsj(2);
        graph.showDijkstra();
    }
}
class Graph{
    private char[] vertex;//顶点数组
    private int[][] matrix;//邻接矩阵
    private VisitedVertex vv;//已访问顶点的集合
    public Graph(char[] vertex,int[][] matrix){
        this.vertex = vertex;
        this.matrix = matrix;
    }
    public void showDijkstra(){
        vv.show();
    }
    public void showGraph(){
        for (int[] ints : matrix) {
            System.out.println(Arrays.toString(ints));
        }
    }
    public void dsj(int index){
        vv = new VisitedVertex(vertex.length,index);
        update(index);
        for (int j = 1; j < vertex.length; j++) {
            index = vv.updateArr();
            update(index);
        }
    }

    //更新index下标顶点到周围顶点的距离和周围顶点的前驱顶点
    private void update(int index) {
        int len = 0;
        for (int j = 0; j < matrix[index].length; j++) {
            len = vv.getDis(index)+matrix[index][j];
            if(!vv.in(j)&&len<vv.getDis(j)){
                vv.updatePre(j,index);//更新j顶点的前驱为index顶点
                vv.updateDis(j,len);//更新触发顶点到j顶点的距离
            }
        }
    }
}

class VisitedVertex{
    //记录各个顶点是否访问过 1表示访问过 0表示未访问 动态更新
    int[] alreadyArr;
    //每个下标对应的值为前一个顶点下标，会动态更新
    int[] preVisited;
    //记录出发顶点到其他所有顶点的距离，比如G为出发顶点，就会记录G到其他顶点的距离，
    int[] dis;
    public VisitedVertex(int length,int index){
        alreadyArr = new int[length];
        preVisited = new int[length];
        dis = new int[length];
        Arrays.fill(dis,65535);
        alreadyArr[index] = 1;//设置出发顶点被访问过
        dis[index] = 0; //设置出发顶点的访问距离为0
    }
    //顶点是否访问过
    public boolean in(int index){
        return alreadyArr[index] == 1;
    }
    //更新出发顶点到index顶点的距离
    public void updateDis(int index,int len){
        dis[index] = len;
    }
    //更新pre这个顶点的前驱顶点为index顶点
    public void updatePre(int pre,int index){
        preVisited[pre] = index;
    }
    //返回出发顶点到index顶点的距离
    public int getDis(int index){
        return dis[index];
    }
    //继续选择并返回新的访问顶点，比如这里的G完成后，就是A点作为新的访问节点
    public int updateArr(){
        int min = 65535;
        int index = 0;
        for (int i = 0; i < alreadyArr.length; i++) {
            if(alreadyArr[i] == 0 && dis[i]<min){
                min = dis[i];
                index = i;
            }
        }
        alreadyArr[index] = 1;
        return index;
    }
    public void show(){
        System.out.println("alreadyArr="+Arrays.toString(alreadyArr));
        System.out.println("preVisited="+Arrays.toString(preVisited));
        System.out.println("dis="+Arrays.toString(dis));
    }
}