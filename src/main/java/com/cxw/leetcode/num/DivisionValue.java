package com.cxw.leetcode.num;

import java.util.*;

/**
 * @author chengxuwei
 * @date 2021-01-06 16:14
 * @description 除法求值
 * 给你一个变量对数组 equations 和一个实数值数组 values 作为已知条件，其中 equations[i] = [Ai, Bi] 和 values[i] 共同表示等式 Ai / Bi = values[i] 。每个 Ai 或 Bi 是一个表示单个变量的字符串。
 *
 * 另有一些以数组 queries 表示的问题，其中 queries[j] = [Cj, Dj] 表示第 j 个问题，请你根据已知条件找出 Cj / Dj = ? 的结果作为答案。
 *
 * 返回 所有问题的答案 。如果存在某个无法确定的答案，则用 -1.0 替代这个答案。
 *
 *  
 *
 * 注意：输入总是有效的。你可以假设除法运算中不会出现除数为 0 的情况，且不存在任何矛盾的结果。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：equations = [["a","b"],["b","c"]], values = [2.0,3.0], queries = [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]
 * 输出：[6.00000,0.50000,-1.00000,1.00000,-1.00000]
 * 解释：
 * 条件：a / b = 2.0, b / c = 3.0
 * 问题：a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ?
 * 结果：[6.0, 0.5, -1.0, 1.0, -1.0 ]
 * 示例 2：
 *
 * 输入：equations = [["a","b"],["b","c"],["bc","cd"]], values = [1.5,2.5,5.0], queries = [["a","c"],["c","b"],["bc","cd"],["cd","bc"]]
 * 输出：[3.75000,0.40000,5.00000,0.20000]
 * 示例 3：
 *
 * 输入：equations = [["a","b"]], values = [0.5], queries = [["a","b"],["b","a"],["a","c"],["x","y"]]
 * 输出：[0.50000,2.00000,-1.00000,-1.00000]
 *  
 *
 * 提示：
 *
 * 1 <= equations.length <= 20
 * equations[i].length == 2
 * 1 <= Ai.length, Bi.length <= 5
 * values.length == equations.length
 * 0.0 < values[i] <= 20.0
 * 1 <= queries.length <= 20
 * queries[i].length == 2
 * 1 <= Cj.length, Dj.length <= 5
 * Ai, Bi, Cj, Dj 由小写英文字母与数字组成
 *
 * 链接：https://leetcode-cn.com/problems/evaluate-division
 */
public class DivisionValue {
    public static void main(String[] args) {
        //[["x1","x2"],["x2","x3"],["x3","x4"],["x4","x5"]]
        //[3.0,4.0,5.0,6.0]
        //[["x1","x5"],["x5","x2"],["x2","x4"],["x2","x2"],["x2","x9"],["x9","x9"]]
        //输出：
        //[12.00000,-3.00000,-0.33333,1.00000,-1.00000,-1.00000]
        //预期结果：
        //[360.0,0.00833,20.0,1.0,-1.0,-1.0]
        String[][] equationsAry = {{"x1","x2"},{"x2","x3"},{"x3","x4"},{"x4","x5"}};
        List<List<String>> equations = getList(equationsAry);

        double[] values = {3.0,4.0,5.0,6.0};
        String[][] queriesAry = {{"x1","x5"},{"x5","x2"},{"x2","x4"},{"x2","x2"},{"x2","x9"},{"x9","x9"}};
        List<List<String>> queries = getList(queriesAry);

        DivisionValue dv = new DivisionValue();
        double[] res = dv.calcEquation(equations, values, queries);
        System.out.println(Arrays.toString(res));

    }

    private static List<List<String>> getList(String[][] equationsAry) {
        List<List<String>> list =  new ArrayList<>();
        for (String[] strs : equationsAry) {
            List<String> l =  Arrays.asList(strs);
            list.add(l);
        }
        return list;
    }

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        List<String> vertexList = getVertex(equations);
        String[] vertex = vertexList.toArray(new String[]{});
        double[][] matrix = getMap(vertexList,equations,values);
        Graph g = new Graph(matrix,vertex);
        g.floyd();
        double[] res = new double[queries.size()];
        for(int i=0;i<queries.size();i++){
            List<String> q = queries.get(i);
            if(vertexList.contains(q.get(0))&&vertexList.contains(q.get(1))){
                int index0 = vertexList.indexOf(q.get(0));
                int index1 = vertexList.indexOf(q.get(1));
                res[i] = matrix[index0][index1];
            }else{
                res[i] = -1.0;
            }
        }
        return res;
    }
    public List<String> getVertex(List<List<String>> equations){
        Set<String> vSet = new LinkedHashSet<>();
        for(List<String> e : equations){
            vSet.add(e.get(0));
            vSet.add(e.get(1));
        }
        return new ArrayList<>(vSet);
    }
    public double[][] getMap(List<String> vertexList,List<List<String>> equations, double[] values){
        double[][] map = new double[vertexList.size()][vertexList.size()];
        //初始化
        for(int i=0;i<vertexList.size();i++){
            for(int j=0;j<vertexList.size();j++){
                if(i==j){
                    map[i][j]=1.0;
                }else{
                    map[i][j]=-1.0;
                }
            }
        }
        for(int i=0;i<equations.size();i++){
            List<String> e = equations.get(i);
            String zero = e.get(0);
            String one = e.get(1);
            int zeroIndex = vertexList.indexOf(zero);
            int oneIndex = vertexList.indexOf(one);
            map[zeroIndex][oneIndex] = values[i];
            map[oneIndex][zeroIndex] = 1/values[i];
        }
        return map;
    }

}
class Graph{

    private String[] vertex;//顶点
    private double[][] dis;//各个顶点到其他顶点的距离

    public Graph(double[][] matrix,String[] vertex){
        this.vertex = vertex;
        this.dis = matrix;
    }
    public void floyd(){
        int length = vertex.length;
        //中间点
        for(int k=0;k<length;k++){
            //开始点
            for(int i=0;i<length;i++){
                //结束点
                for(int j=0;j<length;j++){
                    if (j == k || dis[i][j] > 0) {
                        continue;
                    }
                    if(dis[i][k]>0&&dis[k][j]>0){
                        dis[i][j]=dis[i][k]*dis[k][j];
                    }
                }
            }
        }
        System.out.println(Arrays.toString(vertex));
    }
}