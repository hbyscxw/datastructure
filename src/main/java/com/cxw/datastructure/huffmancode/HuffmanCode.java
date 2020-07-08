package com.cxw.datastructure.huffmancode;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author chengxuwei
 * @date 2020-07-07 09:30
 * @description 赫夫曼编码
 */
public class HuffmanCode {
    private static Map<Byte, String> map = null;
    public static void main(String[] args) {
        String str = "i like like like a java do you like java";
        byte[] bytes = str.getBytes();
        byte[] huffmanCodeBytes = zip(bytes);
        //System.out.println(Arrays.toString(huffmanCodeBytes));
        bytes = unzip(huffmanCodeBytes);
        System.out.println(new String(bytes));
    }

    private static byte[] unzip(byte[] huffmanCodeBytes) {
        //将huffmanCodeBytes转成字符串
        StringBuilder builder = new StringBuilder();
        for (int i = 0;i<huffmanCodeBytes.length;i++) {
            byte huffmanCodeByte = huffmanCodeBytes[i];
            boolean flag = true;
            if(i==huffmanCodeBytes.length-1){
                flag = false;
            }
            builder.append(byteToBitString(flag,huffmanCodeByte));
        }
        Map<String, Byte> newMap = map.entrySet().stream().collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));
//        byte[] bytes = new byte[?];
        List<Byte> list = new ArrayList<>();
        int index = 1;
        for (int i = 0; i < builder.length(); i+=index) {
            index = 1;
            while(builder.length()>0){
                String str = builder.substring(i,i+index);
                Byte b = newMap.get(str);
                if(b!=null){
                    list.add(b);
                    break;
                }else{
                    index++;
                }
            }
        }
        byte[] bytes = new byte[list.size()];
        for (int i = 0; i <list.size() ; i++) {
            bytes[i] = list.get(i);
        }
        return bytes;
    }

    private static String byteToBitString(boolean flag,byte huffmanCodeByte) {
        int temp = huffmanCodeByte;
        if(flag){
            temp = huffmanCodeByte|256;
        }
        String str = Integer.toBinaryString(temp);
        if(flag){
            str = str.substring(str.length()-8);
        }
        return str;
    }


    private static byte[] zip(byte[] bytes) {
        List<Node> list = createList(bytes);
        Node huffmanTree = createHuffmanTree(list);
        //huffman编码表
        map = getHuffmanCodeMap(huffmanTree);
        return getHuffmanCodeBytes(bytes, map);
    }

    private static byte[] getHuffmanCodeBytes(byte[] bytes, Map<Byte, String> map) {
        StringBuilder codeStr = new StringBuilder();
        for (byte b : bytes) {
            String str = map.get(b);
            codeStr.append(str);
        }
        //如果不能被整除就进一
        int huffmanBytesLen = (codeStr.length() + 7) / 8;
        byte[] huffmanBytes = new byte[huffmanBytesLen];
        int index = 0;
        for (int i = 0; i < codeStr.length(); i += 8) {
            String substr = null;
            if (i + 8 > codeStr.length()) {
                substr = codeStr.substring(i);
            } else {
                substr = codeStr.substring(i, i + 8);
            }
            //字符串转成2进制
            huffmanBytes[index] = (byte) Integer.parseInt(substr, 2);
            index++;
        }
        return huffmanBytes;
    }

    private static Map<Byte, String> getHuffmanCodeMap(Node huffmanTree) {
        Map<Byte, String> map = new HashMap<>();
        getHuffmanCode(huffmanTree, "", "", map);
        return map;
    }

    private static void getHuffmanCode(Node node, String code, String fullCode, Map<Byte, String> map) {
        if (node == null) {
            return;
        }
        String newCode = fullCode + code;
        if (node.data == null) {
            getHuffmanCode(node.left, "0", newCode, map);
            getHuffmanCode(node.right, "1", newCode, map);
        } else {
            map.put(node.data, newCode);
        }
    }

    private static Node createHuffmanTree(List<Node> list) {
        while (list.size() > 1) {
            Collections.sort(list);
            Node left = list.get(0);
            Node right = list.get(1);
            Node parent = new Node(null, left.weight + right.weight, left, right);
            list.remove(left);
            list.remove(right);
            list.add(parent);
        }
        return list.get(0);
    }

    private static List<Node> createList(byte[] bytes) {
        Map<Byte, Integer> maps = new HashMap<>();
        for (byte b : bytes) {
            Integer count = maps.getOrDefault(b, 0);
            maps.put(b, count + 1);
        }
        return maps.entrySet().stream().map(e -> new Node(e.getKey(), e.getValue())).collect(Collectors.toList());
    }
}

class Node implements Comparable<Node> {

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

    public void preOrder() {
        System.out.println(this);
        if (left != null) {
            left.preOrder();
        }
        if (right != null) {
            right.preOrder();
        }
    }

    @Override
    public int compareTo(Node o) {
        return this.weight - o.weight;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + (data == null ? "null" : data) +
                ", weight=" + weight +
                ", left=" + (left == null || left.data == null ? "null" : left.data) +
                ", right=" + (right == null || right.data == null ? "null" : right.data) +
                '}';
    }
}