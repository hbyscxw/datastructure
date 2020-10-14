package com.cxw.leetcodecourse.tree;

import java.util.*;

/**
 * @author chengxuwei
 * @date 2020-10-13 09:55
 * @description
 * 给定一个行3列二维数组，每一行表示有一座大楼，一共有N座大楼。
 * 所有大楼的底部都坐落在X轴上，
 * 每一行的三个值(a,b,e)代表每座大楼的从(a, 0)点开始，到(b,0)点结束，
 * 高度为e.输入的数据可以保证acb, 且a, b, e均为正数。
 * 大楼之间可以有重合。请输出整体的轮廓线。
 * 例子:给定一个二维数组[ [1, 3, 3],[2, 4, 4],[5, 6,1] ]
 * 输出为轮廓线[ [1, 2,3], [2, 4,4],[5,6,1]]
 */
public class BuildingHeight {
    public static void main(String[] args) {
        int[][] buildingMap = {{1, 3, 3},{2, 4, 4},{5, 6,1}};
        int[][] buildingOutlineMap = getBuildingOutlineMap(buildingMap);
        System.out.println(buildingOutlineMap);
    }

    private static int[][] getBuildingOutlineMap(int[][] buildingMap) {
        TreeMap<Integer,BuildingNode> nodeMap = new TreeMap<>();
        for (int i = 0; i < buildingMap.length; i++) {
            int[] building = buildingMap[i];
            if(building.length<3){
                continue;
            }
            int startIndex = building[0];
            int endIndex = building[1];
            int height = building[2];
            if(!nodeMap.containsKey(startIndex)) {
                nodeMap.put(startIndex,new BuildingNode(startIndex,height,true));
            }else{
                BuildingNode buildingNode = nodeMap.get(startIndex);
                if(buildingNode.height<height){
                    nodeMap.put(startIndex,new BuildingNode(startIndex,height,true));
                }
            }
            if(!nodeMap.containsKey(endIndex)) {
                nodeMap.put(endIndex,new BuildingNode(endIndex,height,false));
            }else{
                BuildingNode buildingNode = nodeMap.get(endIndex);
                if(buildingNode.height<height){
                    nodeMap.put(endIndex,new BuildingNode(endIndex,height,false));
                }
            }
        }
        TreeMap<Integer,Integer> htMap = new TreeMap<>();//高度,次数
        TreeMap<Integer,Integer> pmMap = new TreeMap<>();//位置,高度
        for (BuildingNode node : nodeMap.values()) {
            int height = node.height;
            if(node.up) {
                if (htMap.get(height) == null) {
                    htMap.put(height, 1);

                } else {
                    htMap.computeIfPresent(height,(key,v)->v+1);
                }
            }else{
                if (htMap.get(height) == null) {
                    //忽略
                }else if(htMap.get(height)==1){
                    htMap.remove(height);
                } else {
                    htMap.computeIfPresent(height,(key,v)->v-1);
                }
            }
            if(htMap.isEmpty()){
                pmMap.put(node.startIndex,0);
            }else{
                pmMap.put(node.startIndex,htMap.lastKey());
            }
        }
        int index = 0;
        int height = 0;
        List<int[]> resList = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : pmMap.entrySet()) {
            int curIndex=entry.getKey();
            int curHeight=entry.getValue();
            if(height!=curHeight){
                if(height!=0) {
                    resList.add(new int[]{index, curIndex, height});
                }
                index = curIndex;
                height = curHeight;
            }
        }
        int[][] resAry = new int[resList.size()][3];
        for (int i = 0; i < resList.size(); i++) {
            resAry[i] = resList.get(i);
        }
        return resAry;
    }
}
class BuildingNode{
    int startIndex;
    int height;
    boolean up;
    public BuildingNode(){
    }
    public BuildingNode(int startIndex, int height, boolean up) {
        this.startIndex = startIndex;
        this.height = height;
        this.up = up;
    }
}