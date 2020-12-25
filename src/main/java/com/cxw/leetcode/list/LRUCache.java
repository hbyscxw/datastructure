package com.cxw.leetcode.list;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @author chengxuwei
 * @date 2020-08-11 11:31
 * @description
 * https://leetcode-cn.com/problems/lru-cache/
 */
public class LRUCache {
    private LinkedList<Entry> list;
    private Map<Integer,Entry> map;
    private int capacity;
    public LRUCache(int capacity) {
        list = new LinkedList<>();
        map = new HashMap<>(capacity);
        this.capacity = capacity;
    }

    public int get(int key) {
        Entry entry = map.get(key);
        if(entry == null){
            return -1;
        }
        list.remove(entry);
        list.add(entry);
        return entry.value;
    }

    public void put(int key, int value) {
        Entry entry1 = map.get(key);
        if(entry1==null) {
            if (list.size() >= capacity) {
                Entry first = list.removeFirst();
                map.remove(first.key);
            }
        }else{
            list.remove(entry1);
        }
        Entry entry = new Entry(key, value);
        list.add(entry);
        map.put(key, entry);
    }
    static class Entry{
        int key;
        int value;
        public Entry(int key,int value){
            this.key = key;
            this.value = value;
        }
    }

    public static void main(String[] args) {
//        ["LRUCache","get","put","get","put","put","get","get"]
//        [[2],[2],[2,6],[1],[1,5],[1,2],[1],[2]]
        LRUCache cache = new LRUCache(2);
        System.out.println(cache.get(2));
        cache.put(2,6);
        System.out.println(cache.get(1));
        cache.put(1,5);
        cache.put(1,2);
        System.out.println(cache.get(1));
        System.out.println(cache.get(2));
    }
}