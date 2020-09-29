package com.cxw.leetcodecourse.stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @author chengxuwei
 * @date 2020-09-28 16:07
 * @description 矩形面积
 */
public class RectangleArea {
    public static void main(String[] args) {
        int[] ary = {4,3,2,5,6};
        Map<Integer,Integer> leftMap = new HashMap<>();
        Map<Integer,Integer> rightMap = new HashMap<>();
        aryWide(ary,leftMap,rightMap);
        //System.out.println();
        int area = aryRectangleMaxArea(ary,leftMap,rightMap);
        System.out.println(area);
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