package com.cxw.datastructure.huffmancode;

import java.util.*;
import java.util.stream.Collectors;

public class HuffmanCode {
    public static void main(String[] args) {
        String str = "i like like like a java do you like java";
        List<Node> list = createNodeList(str);
        Node root = createHuffmanTree(list);
        Map<Byte, String> codeMap = getCodes(root);
        System.out.println(codeMap);
    }

    private static List<Node> createNodeList(String str) {
        byte[] bytes = str.getBytes();
        Map<Byte,Integer> map = new HashMap<>();
        for (byte b : bytes) {
            Integer count = map.getOrDefault(b, 0);
            map.put(b,count+1);
        }
        return map.entrySet().stream().map(e->new Node(e.getKey(),e.getValue())).collect(Collectors.toList());
    }

    private static Node createHuffmanTree(List<Node> list) {
       while(list.size()>1){
           Collections.sort(list);
           Node left = list.get(0);
           Node right = list.get(1);
           Node parent = new Node(null,left.weight+right.weight,left,right);
           list.remove(left);
           list.remove(right);
           list.add(parent);
       }
       return list.get(0);
    }

    private static Map<Byte, String> getCodes(Node root){
        Map<Byte,String> codeMap = new HashMap<>();
        getCodes(root,"","",codeMap);
        return codeMap;
    }
    private static void getCodes(Node node,String code,String fullCode,Map<Byte,String> codeMap){
        if(node==null){
            return;
        }
        String newCode = fullCode + code;
        if(node.data==null){
            //向左递归
            getCodes(node.left,"0",newCode,codeMap);
            //向右递归
            getCodes(node.right,"1",newCode,codeMap);
        }else{
            codeMap.put(node.data,newCode);
        }
    }
}

class Node implements Comparable<Node>{
    Byte data;
    int weight;
    Node left;
    Node right;

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    public Node(Byte data, int weight, Node left, Node right) {
        this.data = data;
        this.weight = weight;
        this.left = left;
        this.right = right;
    }

    @Override
    public int compareTo(Node o) {
        return this.weight-o.weight;
    }
}
