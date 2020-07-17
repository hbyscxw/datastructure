package com.cxw.datastructure.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author chengxuwei
 * @date 2020-07-17 09:41
 * @description 图
 *  深度遍历 dfs
 *  广度遍历 bfs
 */
public class Graph {
    private static List<String> nodeList;
    private static int[][] sideAry;
    private static int sideSize;
    private static boolean[] isVisited;

    public static void main(String[] args) {
        Graph graph = new Graph(5);
        graph.insertNode("A");
        graph.insertNode("B");
        graph.insertNode("C");
        graph.insertNode("D");
        graph.insertNode("E");
        graph.insertSide(0, 1, 1); // A-B
        graph.insertSide(0, 2, 1); //
        graph.insertSide(1, 2, 1); //
        graph.insertSide(1, 3, 1); //
        graph.insertSide(1, 4, 1); //
        graph.showGraph();
        graph.bfs();
    }



    public Graph(int n){
        nodeList = new ArrayList<>();
        sideAry = new int[n][n];
        isVisited = new boolean[n];
    }

    //深度优先遍历
    public void dfs(){
        for (int i = 0; i < nodeList.size(); i++) {
            if(!isVisited[i]){
                dfs(i);
            }
        }
    }

    private void dfs(int i) {
        //输出元素
        System.out.print(nodeList.get(i)+"->");
        isVisited[i] = true;
        //第一个相邻节点
        int w = getFirstNeighbour(i);
        while(w!=-1){
            if(!isVisited[w]){
                dfs(w);
            }
            w = getNextNeighbour(i,w);
        }
    }
    //广度优先算法
    public void bfs(){
        for (int i = 0; i < nodeList.size(); i++) {
            if(!isVisited[i]){
                bfs(i);
            }
        }
    }

    private void bfs(int i) {
        //队列头下标
        int u;
        //邻接节点下标
        int w;
        LinkedList<Integer> queue = new LinkedList<>();
        //输出元素
        System.out.print(nodeList.get(i)+"->");
        isVisited[i] = true;
        queue.addLast(i);
        while(!queue.isEmpty()){
            //取出头节点
            u = queue.removeFirst();
            //取u的第一个邻接节点
            w = getFirstNeighbour(u);
            while(w!=-1){
                if(!isVisited[w]){
                    //输出元素
                    System.out.print(nodeList.get(w)+"->");
                    isVisited[w] = true;
                    queue.addLast(w);
                }
                //以u为前驱找下一个邻接节点
                w = getNextNeighbour(u,w);
            }
        }
    }

    private void insertNode(String node) {
        nodeList.add(node);
    }
    private void insertSide(int i, int i1,int value) {
        sideAry[i][i1] = value;
        sideAry[i1][i] = value;
        sideSize++;
    }

    public int getFirstNeighbour(int index){
        for (int i = 0; i < nodeList.size(); i++) {
            if(sideAry[index][i]!=0){
                return i;
            }
        }
        return -1;
    }
    public int getNextNeighbour(int v1,int v2){
        for (int i = v2+1; i < nodeList.size(); i++) {
            if(sideAry[v1][i]!=0){
                return i;
            }
        }
        return -1;
    }
    public void showGraph(){
        for (int[] side : sideAry) {
            System.out.println(Arrays.toString(side));
        }
    }
}