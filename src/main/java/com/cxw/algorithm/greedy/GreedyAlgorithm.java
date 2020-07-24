package com.cxw.algorithm.greedy;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author chengxuwei
 * @date 2020-07-24 10:21
 * @description 贪心算法
 * 广播台
 * 覆盖地区
 * K1   "北京","上海","天津"
 * K2   "广州","北京","深圳"
 * K3   "成都","上海","杭州"
 * K4   "上海","天津"
 * K5   "杭州","大连"
 *
 * 要求需要覆盖所有地区的最小集合
 * 1) 遍历所有的广播电台, 找到一个覆盖了最多未覆盖的地区的电台(此电台可能包含一些已覆盖的地区，但没有关系)
 * 2) 将这个电台加入到一个集合中(比如ArrayList),想办法把该电台覆盖的地区在下次比较时去掉。
 * 3) 重复第1步直到覆盖了全部的地区
 */
public class GreedyAlgorithm {
    public static void main(String[] args) {
        Map<String, Set<String>> broadcastMap = new HashMap<>();
        Set<String> set1 = Stream.of("北京","上海","天津").collect(Collectors.toSet());
        Set<String> set2 = Stream.of("广州","北京","深圳").collect(Collectors.toSet());
        Set<String> set3 = Stream.of("成都","上海","杭州").collect(Collectors.toSet());
        Set<String> set4 = Stream.of("上海","天津").collect(Collectors.toSet());
        Set<String> set5 = Stream.of("杭州","大连").collect(Collectors.toSet());
        broadcastMap.put("K1",set1);
        broadcastMap.put("K2",set2);
        broadcastMap.put("K3",set3);
        broadcastMap.put("K4",set4);
        broadcastMap.put("K5",set5);
        Set<String> allCity = new HashSet<>();
        allCity.addAll(set1);
        allCity.addAll(set2);
        allCity.addAll(set3);
        allCity.addAll(set4);
        allCity.addAll(set5);
        List<String> list = new ArrayList<>();
        while(allCity.size()>0) {
            String key = null;
            int intersection = 0;
            for (Map.Entry<String, Set<String>> entry : broadcastMap.entrySet()) {
                Set<String> set = entry.getValue();
                HashSet<String> temp = new HashSet<>(allCity);
                temp.retainAll(set);
                if(temp.size()>intersection){
                    key = entry.getKey();
                    intersection = temp.size();
                }
            }
            list.add(key);
            allCity.removeAll(broadcastMap.get(key));
        }
        System.out.println(list);
    }
}