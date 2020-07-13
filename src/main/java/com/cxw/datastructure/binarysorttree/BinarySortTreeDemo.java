package com.cxw.datastructure.binarysorttree;

public class BinarySortTreeDemo {
    public static void main(String[] args) {
        int[] ary = {7,3,10,12,5,1,9,2};
        BinarySortTree tree = new BinarySortTree();
        for (int i : ary) {
            tree.add(new Node(i));
        }
        tree.infixOrder();
        System.out.println("~~~~");
        //tree.deleteNode(2);
        tree.deleteNode(2);
        tree.deleteNode(5);
        tree.deleteNode(9);
        tree.deleteNode(12);
        tree.deleteNode(7);
        tree.deleteNode(3);
        tree.deleteNode(10);
//        tree.deleteNode(1);
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
    public Node searchNode(int value){
        if(root == null){
            return null;
        }
        return root.searchNode(value);
    }

    public Node searchParentNode(int value){
        if(root == null){
            return null;
        }
        return root.searchParentNode(value);
    }
    public void deleteNode(int value){
        System.out.println(value);
        if(root == null){
            return;
        }
        Node target = searchNode(value);
        if(target==null){
            return;
        }
        if(root.left==null&&root.right==null){
            root = null;
            return;
        }
        Node parent = searchParentNode(value);
        //target是叶子节点的时候
        if(target.left==null&&target.right==null){
            if(parent.left==target){
                parent.left=null;
            }else{
                parent.right = null;
            }
        }else if(target.left==null){//target只有右子节点的时候
            if(parent==null){
                root = target.right;
                return;
            }
            if(parent.left==target){
                parent.left=target.right;
            }else{
                parent.right = target.right;
            }
        }else if(target.right==null){//target只有左子节点的时候
            if(parent==null){
                root = target.left;
                return;
            }
            if(parent.left==target){
                parent.left = target.left;
            }else{
                parent.right = target.left;
            }
        }else{//target有左右两个节点的时候
            //找到target右子节点下的最小值，删除，并将其值赋值给target
            //也可以删除target左子节点下的最大值
            target.value = deleteRightTreeMin(target.right);
        }
    }

    /**
     * 1.返回node为根节点的最小节点值
     * 2.删除找到的节点
     * @param node
     * @return
     */
    private int deleteRightTreeMin(Node node){
        Node target = node;
        while(target.left!=null){
            target = target.left;
        }
        deleteNode(target.value);
        return target.value;
    }

    /**
     * 1.返回node为根节点的最大节点值
     * 2.删除找到的节点
     * @param node
     * @return
     */
    private int deleteLeftTreeMax(Node node){
        Node target = node;
        while(target.right!=null){
            target = target.right;
        }
        deleteNode(target.value);
        return target.value;
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

    public Node searchNode(int value){
        if(this.value == value){
            return this;
        }else if(this.value>value){
            if(left!=null) {
                return left.searchNode(value);
            }
        }else{
            if(right!=null) {
                return right.searchNode(value);
            }
        }
        return null;
    }

    public Node searchParentNode(int value){
        if((left!=null&&left.value==value)
                ||(right!=null&&right.value==value)){
            return this;
        }else {
            if (left != null && value < this.value) {
                return left.searchParentNode(value);
            } else if (right != null && value >= this.value) {
                return right.searchParentNode(value);
            } else {
                return null;
            }
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

}