package com.cxw.datastructure.binarysorttree;

public class BinarySortTreeDemo {
    public static void main(String[] args) {
        int[] ary = {7,3,10,12,5,1,9};
        BinarySortTree tree = new BinarySortTree();
        for (int i : ary) {
            tree.add(new Node(i));
        }
        tree.infixOrder();
    }
}
class BinarySortTree{
    Node root;
    public void add(Node node){
        if(root == null){
            root = node;
        }else{
            root.add(node);
        }
    }
    public void infixOrder(){
        if(root == null){
            System.out.println("二叉排序树为空，不能遍历！");
        }else{
            root.infixOrder();
        }
    }
}
class Node{
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    public void add(Node node){
        if(value>node.value) {
            if (left == null) {
                left = node;
            }else{
                left.add(node);
            }
        }else{
            if (right == null) {
                right = node;
            }else{
                right.add(node);
            }
        }
    }

    public void infixOrder() {
        if(left!=null){
            left.infixOrder();
        }
        System.out.println(this);
        if(right!=null){
            right.infixOrder();
        }
    }
    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

}