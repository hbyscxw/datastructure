package com.cxw.datastructurenew.greedy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author chengxuwei
 * @create 2022/3/15 15:42
 * @desc 贪心算法
 *  要求一天时间安排最多的会议，以结束时间早进行排序
 */
public class MeetingArrangement {
    public static void main(String[] args) {
        List<Meeting> list = new ArrayList<>();
        list.add(new Meeting(6,8));
        list.add(new Meeting(5,7));
        list.add(new Meeting(7,9));
        list = arrangement(list,5,8);
        System.out.println(list);
    }
    public static List<Meeting> arrangement(List<Meeting> list,int startTimePoint,int endTimePoint){
        //先排序
        Collections.sort(list);
        List<Meeting> meetings = new ArrayList<>();
        for (Meeting meeting : list) {
            if(startTimePoint<=meeting.start&&endTimePoint>=meeting.end){
                meetings.add(meeting);
                startTimePoint = meeting.end;
            }
        }
        return meetings;
    }
}

class Meeting implements Comparable<Meeting>{
    public int start;
    public int end;

    public Meeting(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public int compareTo(Meeting m) {
        return end-m.end;
    }
}
