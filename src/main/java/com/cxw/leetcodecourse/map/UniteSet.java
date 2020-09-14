package com.cxw.leetcodecourse.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chengxuwei
 * @date 2020-09-11 10:20
 * @description 两个树链接
 *  判断是否在一个树上
 */
public class UniteSet {

    private Map<Node,Node> fatherMap;
    private Map<Node,Integer> sizeMap;

    public UniteSet(List<Node> list) {
        fatherMap = new HashMap<>();
        sizeMap = new HashMap<>();
        initMap(list);
    }

    private void initMap(List<Node> list) {
        if(list==null||list.size()==0){
            return;
        }
        for (Node node : list) {
            fatherMap.put(node,node);
            sizeMap.put(node,1);
        }
    }

    public void uniteSet(Node one,Node two) {
        if(one==null||two==null){
            return;
        }
        Node pOne = one;
        List<Node> list = new ArrayList<>();
        while(!pOne.equals(fatherMap.get(one))) {
            list.add(pOne);
            pOne = findParentNode(pOne);
        }
        for (Node node : list) {
            fatherMap.put(node,pOne);
        }
        list.clear();
        Node pTwo = two;
        while(!pTwo.equals(fatherMap.get(two))) {
            list.add(pTwo);
            pTwo = findParentNode(pTwo);
        }
        for (Node node : list) {
            fatherMap.put(node,pTwo);
        }
        Integer oneSize = sizeMap.get(pOne);
        Integer twoSize = sizeMap.get(pTwo);
        if(oneSize>twoSize){
            fatherMap.put(pTwo,pOne);
            sizeMap.put(pOne,oneSize+twoSize);
        }else{
            fatherMap.put(pOne,pTwo);
            sizeMap.put(pTwo,oneSize+twoSize);
        }
    }

    private Node findParentNode(Node one) {
        return fatherMap.get(one);
    }

    public boolean isSameSet(Node one,Node two) {
        Node pOne = one;
        List<Node> list = new ArrayList<>();
        while(!pOne.equals(fatherMap.get(one))) {
            list.add(pOne);
            pOne = findParentNode(pOne);
        }
        for (Node node : list) {
            fatherMap.put(node,pOne);
        }
        list.clear();
        Node pTwo = two;
        while(!pTwo.equals(fatherMap.get(two))) {
            list.add(pTwo);
            pTwo = findParentNode(pTwo);
        }
        for (Node node : list) {
            fatherMap.put(node,pTwo);
        }
        return pOne.equals(pTwo);
    }

    private static class Node{

    }
}
