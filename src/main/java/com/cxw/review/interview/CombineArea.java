package com.cxw.review.interview;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author chengxuwei
 * @create 2022/3/17 10:36
 * @desc
 *
 * 以数组 intervals 表示若干个面试区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请合并所有重叠的面试区间，并返回一个不重叠的面试区间数组，该数组需恰好覆盖输入中的所有区间。
 *
 * 输入：intervals = [[1,3], [5, 7], [6, 8]]
 * 输出：[[1,3], [5, 8]]
 * 解释：面试区间 [5,7] 和 [6,8] 重叠, 将它们合并为 [5,8]
 */
public class CombineArea {
    public static void main(String[] args) {
        int[][] intervals = {{1,3},{5, 7},{6, 8}};
        int[][] res = combine(intervals);
        System.out.println(res);
    }

    private static int[][] combine(int[][] intervals) {
        if(intervals.length == 0||intervals.length==1){
            return intervals;
        }
        List<Area> list = new ArrayList<>();
        for (int[] interval : intervals) {
            list.add(new Area(interval[0],interval[1]));
        }
        Collections.sort(list);
        int i = 1;
        while (i<list.size()&&list.size()>=2) {
            Area o1 = list.get(i-1);
            Area o2 = list.get(i);
            if(o1.end>=o2.start){
                o1.start = Math.min(o1.start,o2.start);
                o1.end = Math.max(o1.end,o2.end);
                list.remove(o2);
            }else{
                i++;
            }
        }
        int[][] res = new int[list.size()][2];
        for (int j=0;j<list.size();j++) {
            res[j] = new int[]{list.get(j).start,list.get(j).end};
        }
        return res;
    }

}

class Area implements Comparable<Area>{
    int start;
    int end;
    public Area(int start,int end){
        this.start = start;
        this.end = end;
    }

    @Override
    public int compareTo(Area o) {
        return start-o.start;
    }
}
