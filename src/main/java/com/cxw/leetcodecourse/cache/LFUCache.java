package com.cxw.leetcodecourse.cache;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author chengxuwei
 * @date 2020-10-21 11:46
 * @description LFU 访问次数最多的放在最前
 */
public class LFUCache<K,V> {

    private Map<K,LFUNode<K,V>> map;
    private Map<Integer,LFUNodeList<K,V>> timesMap;
    private int size;

    public static void main(String[] args) {
        LFUCache<String, Integer> testCache = new LFUCache<>(3);
        testCache.set("A", 1);
        testCache.set("B", 2);
        testCache.set("C", 3);
        System.out.println(testCache.get("B"));
        System.out.println(testCache.get("A"));
        System.out.println(testCache.get("C"));
        System.out.println(testCache.get("C"));
        System.out.println(testCache.get("C"));
        testCache.set("D", 4);
        System.out.println(testCache.get("D"));
        System.out.println(testCache.get("C"));

    }

    public LFUCache(int size){
        map = new HashMap<>();
        timesMap = new TreeMap<>();
        this.size = size;
    }
    public V get(K key){
        if(map.containsKey(key)){
            LFUNode<K, V> node = map.get(key);
            timesMapChangeTimes(node);
            return node.value;
        }
        return null;
    }

    public void set(K key,V value){
        if(map.containsKey(key)){
            LFUNode<K, V> node = map.get(key);
            timesMapChangeTimes(node);
        }else{
            //超过容量，需要清理
            if(map.size()>=size){
                Map.Entry<Integer, LFUNodeList<K, V>> entry = timesMap.entrySet().iterator().next();
                LFUNodeList<K, V> lfuNodeList = entry.getValue();
                if(lfuNodeList.head==lfuNodeList.tail){
                    timesMap.remove(entry.getKey());
                }else {
                    lfuNodeList.tail = lfuNodeList.tail.prev;
                    lfuNodeList.tail.next = null;
                }
                map.remove(entry.getValue().tail.key);
            }
            LFUNode<K, V> node = new LFUNode<>(key, value);
            map.put(key,node);
            LFUNodeList<K, V> nodeList = timesMap.get(1);
            if(nodeList==null){
                nodeList = new LFUNodeList<>(node);
                timesMap.put(1,nodeList);
            }else{
                nodeList.tail.next = node;
                node.prev = nodeList.tail;
                nodeList.tail = node;
            }

        }
    }

    private void timesMapChangeTimes(LFUNode<K,V> node){
        LFUNodeList<K, V> nodeList = timesMap.get(node.times);
        //只有一个元素就删除
        if(nodeList.head==node&&nodeList.tail==node){
            timesMap.remove(node.times);
        }else{
            nodeList.remove(node);
        }
        node.times++;
        nodeList = timesMap.get(node.times);
        if(nodeList==null){
            nodeList = new LFUNodeList<>(node);
            timesMap.put(node.times,nodeList);
        }else{
            node.prev = null;
            //新元素放到头部
            LFUNode<K, V> oldHead = nodeList.head;
            oldHead.prev = node;
            nodeList.head = node;
            node.next = oldHead;
        }
    }
}

class LFUNodeList<K,V>{
    LFUNode<K,V> head;
    LFUNode<K,V> tail;

    public LFUNodeList(LFUNode<K, V> node) {
        head = node;
        tail = node;
    }

    public void remove(LFUNode<K, V> node) {
        if(tail==node){
            tail.prev.next = null;
            tail = tail.prev;
        }else if(head==node){
            head = head.next;
        }else{
            LFUNode<K,V> n = head;
            while(n != null){
                if(n==node){
                    n.prev.next = n.next;
                    n.next.prev = n.prev;
                    break;
                }
                n = n.next;
            }
        }
    }
}

class LFUNode<K,V>{
    K key;
    V value;
    int times;
    LFUNode<K,V> prev;
    LFUNode<K,V> next;

    public LFUNode(K key, V value) {
        this.key = key;
        this.value = value;
        times = 1;
    }
}