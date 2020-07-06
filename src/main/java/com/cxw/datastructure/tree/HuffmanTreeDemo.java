package com.cxw.datastructure.tree;

import java.util.*;

public class HuffmanTreeDemo {
    public static void main(String[] args) {
        int[] ary = {1,3,5,2,14,24,8,34};
        Node tree = createHuffmanTree(ary);
        tree.preOrder();
    }

    private static Node createHuffmanTree(int[] ary) {
        List<Node> list = new ArrayList<>();
        for (int i : ary) {
            list.add(new Node(i));
        }
        Collections.sort(list);

        while(list.size()>1){
            Node left = list.get(0);
            Node right = list.get(1);
            Node parent = new Node(left.value+right.value);
            parent.left = left;
            parent.right = right;
            list.remove(left);
            list.remove(right);
            list.add(parent);
            Collections.sort(list);
        }
        return list.get(0);
    }
}

class Node implements Comparable<Node>{
    int value;
    Node left;
    Node right;
    public void preOrder(){
        System.out.println(this.value);
        if(left!=null){
            left.preOrder();
        }
        if(right!=null){
            right.preOrder();
        }
    }
    public Node(int value){
        this.value = value;
    }
    @Override
    public int compareTo(Node o) {
        return this.value - o.value;
    }
}
