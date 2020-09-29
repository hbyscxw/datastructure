package com.cxw.leetcodecourse.stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @author chengxuwei
 * @date 2020-09-28 16:07
 * @description
 * 求最大子矩阵的大小
 * [题目]
 * 给定一个整型矩阵map，其中的值只有0和1两种，求其中全是1的所有矩形区域中，最大的矩形区域为1的数量。
 * 例如:
 * 1110
 * 其中，最大的矩形区域有3个1，所以返回3。
 * 再如:
 * 1011
 * 1111
 * 1110
 * 其中，最大的矩形区域有6个1,所以返回6。
 */
public class MapArea {
    public static void main(String[] args) {
        int[][] map =  {{1,0,1,1},
                        {1,1,1,1},
                        {1,1,1,0},};
        int area = mapMaxArea(map);
        System.out.println(area);
    }

    private static int mapMaxArea(int[][] map) {
        int maxArea = 0;
        for (int i = 0; i < map.length; i++) {
            int[] ary = new int[map[i].length];
            for (int j = 0; j < map[i].length; j++) {
                if(map[i][j]==0){
                    ary[j] = 0;
                }else{
                    ary[j] = getHigh(map,i,j);
                }
            }
            Map<Integer,Integer> leftMap = new HashMap<>();
            Map<Integer,Integer> rightMap = new HashMap<>();
            aryWide(ary,leftMap,rightMap);
            int area = aryRectangleMaxArea(ary,leftMap,rightMap);
            if(area>maxArea){
                maxArea = area;
            }
        }
        return maxArea;
    }

    private static int getHigh(int[][] map, int i, int j) {
        int high = 0;
        for (int k = i; k >= 0 ; k--) {
            if(map[k][j]==1){
                high++;
            }else{
                break;
            }
        }
        return high;
    }

    private static int aryRectangleMaxArea(int[] ary, Map<Integer, Integer> leftMap, Map<Integer, Integer> rightMap) {
        int maxArea = 0;
        for (int i = 0; i < ary.length; i++) {
            int v = ary[i];
            Integer leftIndex = leftMap.getOrDefault(v, -1);
            Integer rightIndex = rightMap.getOrDefault(v, ary.length);
            int area = v * (rightIndex - leftIndex - 1);
            if(maxArea<area){
                maxArea = area;
            }
        }
        return maxArea;
    }

    private static void aryWide(int[] ary, Map<Integer, Integer> leftMap, Map<Integer, Integer> rightMap) {
        //从小到大的单调栈
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < ary.length; i++) {
            Integer v = ary[i];
            while(!stack.isEmpty()){
                Integer index = stack.peek();
                if(ary[index]>v){
                    index = stack.pop();
                    if(!stack.isEmpty()) {
                        leftMap.put(ary[index], stack.peek());
                    }
                    rightMap.put(ary[index],i);
                }else{
                    break;
                }
            }
            stack.push(i);
        }
        while(!stack.isEmpty()){
            Integer index = stack.pop();
            if(!stack.isEmpty()){
                leftMap.put(ary[index], stack.peek());
            }
        }
    }
}