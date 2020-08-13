package com.cxw.leetcode;

import java.util.LinkedHashMap;

/**
 * @author chengxuwei
 * @date 2020-08-13 16:19
 * @description
 */
public class LRUCache2 {
    private LinkedHashMap<Integer,Integer> cache;
    public LRUCache2(int capacity) {
        cache = new LinkedHashMap<>(capacity,0.75f,true);
    }

    public int get(int key) {
        Integer res = cache.get(key);
        if(res==null){
            return -1;
        }
        return res;
    }

    public void put(int key, int value) {
        cache.put(key,value);
    }

    public static void main(String[] args) {
//        ["LRUCache","get","put","get","put","put","get","get"]
//        [[2],[2],[2,6],[1],[1,5],[1,2],[1],[2]]
        LRUCache2 cache = new LRUCache2(2);
        System.out.println(cache.get(2));
        cache.put(2,6);
        System.out.println(cache.get(1));
        cache.put(1,5);
        cache.put(1,2);
        System.out.println(cache.get(1));
        System.out.println(cache.get(2));
    }
}