package com.cxw.leetcodecourse.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author chengxuwei
 * @date 2020-09-16 11:30
 * @description 会议计划
 * 一些项目要占用一个会议室宣讲，会议室不能同时容纳两个项目
 * 的宣讲。给你每一个项目开始的时间和结束的时间(给你一个数
 * 组，里面是一个个具体的项目)，你来安排宣讲的日程，要求会
 * 议室进行的宣讲的场次最多。返回这个最多的宣讲场次。
 */
public class MeetingPlan {
    public static void main(String[] args) {
        List<Meeting> list = new ArrayList<>();
        list.add(new Meeting(0,8));
        list.add(new Meeting(0,6));
        list.add(new Meeting(7,8));
        list.add(new Meeting(8,9));
        list.add(new Meeting(9,10));
        list.add(new Meeting(7,10));
        int count = planMeeting(list,0);
        System.out.println(count);
    }

    private static int planMeeting(List<Meeting> list,int cur) {
        Collections.sort(list);
        int count = 0;
        for (Meeting m : list) {
            if(cur<=m.start){
                count++;
                cur=m.end;
            }
        }
        return count;
    }

}
class Meeting implements Comparable<Meeting>{
    int start;
    int end;

    public Meeting() {
    }

    public Meeting(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public int compareTo(Meeting o) {
        return this.end-o.end;
    }
}