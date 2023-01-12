package com.cxw.review.interview;

import java.util.HashMap;
import java.util.Map;

/**
 * @className HashMapAndSetAll
 * @Description: HashMap 设置setAll方法 要求时间复杂度O(1)
 * @Author: chengxuwei
 * @Date:2023/1/12 20:19
 */
public class HashMapAndSetAll {
    private Map<Integer,Combine> map = new HashMap<>();
    private int all;
    private long time;
    private long setAllTime = Long.MIN_VALUE;

    public void put(Integer key,Integer value){
        map.put(key,new Combine(value,time++));
    }

    public Integer get(Integer key){
        if(map.get(key).time<setAllTime){
            return all;
        }else{
            return map.get(key).value;
        }
    }

    public void setAll(Integer value){
        all = value;
        setAllTime = time++;
    }

    public static class Combine{
        public Integer value;
        public Long time;
        public Combine(Integer value,Long time){
            this.value = value;
            this.time = time;
        }
    }
}
