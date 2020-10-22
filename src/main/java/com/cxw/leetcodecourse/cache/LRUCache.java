package com.cxw.leetcodecourse.cache;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author chengxuwei
 * @date 2020-10-21 10:31
 * @description LRU算法
 */
public class LRUCache<K,V> {
    private Map<K,CacheNode<K,V>> map;
    private CacheLinkedList<K,V> list;
    private int size;
    public LRUCache(int size){
        map = new HashMap<>();
        list = new CacheLinkedList<>();
        this.size = size;
    }

    public static void main(String[] args) {
        LRUCache<String, Integer> testCache = new LRUCache<>(3);
        testCache.set("A", 1);
        testCache.set("B", 2);
        testCache.set("C", 3);
        System.out.println(testCache.get("B"));
        System.out.println(testCache.get("A"));
        testCache.set("D", 4);
        System.out.println(testCache.get("D"));
        System.out.println(testCache.get("C"));

    }

    public V get(K key){
        CacheNode<K,V> cacheNode = map.get(key);
        if(cacheNode == null){
            return null;
        }
        list.moveFirst(cacheNode);
        return cacheNode.value;
    }

    public void set(K key,V value){
        if(map.containsKey(key)){
            CacheNode<K, V> node = map.get(key);
            node.value = value;
            list.moveFirst(node);
        }else {
            CacheNode<K, V> node = new CacheNode<>(key, value);
            map.put(key, node);
            list.addHead(node);
            if(map.size()>size){
                CacheNode<K, V> tail = list.tail;
                map.remove(tail.key);
                list.tail = tail.prev;
                list.tail.next = null;
            }
        }
    }
}
class CacheLinkedList<K,V>{
    CacheNode<K,V> head;
    CacheNode<K,V> tail;

    public void moveFirst(CacheNode<K, V> cacheNode) {
        if(head.equals(cacheNode)){
           //不做动作
        }else if(tail.equals(cacheNode)){
            CacheNode<K,V> newNode = new CacheNode<>(cacheNode.key,cacheNode.value);
            tail = tail.prev;
            tail.next = null;
            CacheNode<K,V> oldHead = head;
            head = newNode;
            newNode.next = oldHead;
        }else{
            CacheNode<K, V> prev = cacheNode.prev;
            CacheNode<K, V> next = cacheNode.next;
            next.prev = prev;
            prev.next = next;
            CacheNode<K, V> oldHead = head;
            head = cacheNode;
            head.next = oldHead;
            oldHead.prev = head;
            head.prev = null;
        }
    }

    public void addHead(CacheNode<K, V> node) {
        if(head==null){
            head = node;
            tail = node;
        }else{
            CacheNode<K, V> oldHead = head;
            head = node;
            head.next = oldHead;
            oldHead.prev = head;
        }
    }
}
class CacheNode<K,V>{
    K key;
    V value;
    CacheNode<K,V> prev;
    CacheNode<K,V> next;

    public CacheNode(K key, V value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){ return true;}
        if (o == null || getClass() != o.getClass()){ return false;}
        CacheNode<?, ?> cacheNode = (CacheNode<?, ?>) o;
        return key.equals(cacheNode.key) &&
                value.equals(cacheNode.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, value);
    }
}