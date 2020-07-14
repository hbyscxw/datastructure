package com.cxw.datastructure.avl;

/**
 * @author chengxuwei
 * @date 2020-07-13 09:40
 * @description AVL树
 */
public class AVLTreeDemo {
    public static void main(String[] args) {
        int[] ary = {7,6,8,4,3,5};
//        int[] ary = {4,3,6,5,7,8};
//        int[] ary = { 10, 12, 8, 9, 7, 6 };
        // int[] ary = { 10, 11, 7, 6, 8, 9 };
        AVLTree tree = new AVLTree();
        for (int i : ary) {
            tree.add(new Node(i));
        }
        tree.infixOrder();

    }
}
class AVLTree{
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
    public int height(){
        if(root == null){
            return 0;
        }
        return root.height();
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
        if(leftHeight()-rightHeight()>1){
            if(left!=null&&left.leftHeight()<left.rightHeight()){
                left.leftRotate();
            }
            rightRotate();
        }else if(rightHeight()-leftHeight()>1){
            if(right!=null&&right.rightHeight()<right.leftHeight()){
                right.rightRotate();
            }
            leftRotate();
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

    public int height() {
        int leftHeight = 0;
        if(left!=null){
            leftHeight = left.height();
        }
        int rightHeight = 0;
        if(right!=null){
            rightHeight = right.height();
        }
        return Math.max(leftHeight,rightHeight)+1;
    }

    public int leftHeight() {
        int leftHeight = 0;
        if(left!=null){
            leftHeight = left.height();
        }
        return leftHeight;
    }

    public int rightHeight() {
        int rightHeight = 0;
        if(right!=null){
            rightHeight = right.height();
        }
        return rightHeight;
    }

    public void leftRotate(){
        //1.以当前值创建一个新节点
        Node newNode = new Node(value);
        //2.新节点的左节点指向原先左节点
        newNode.left = left;
        //3.新节点的右节点指向原先右节点的左子节点
        newNode.right = right.left;
        //4.原先节点的值改为原先节点的右子节点值
        value = right.value;
        //5.原先节点的右节点指向右节点的右节点
        right = right.right;
        //6.把当前节点的左节点设置成新节点
        left = newNode;
    }

    public void rightRotate(){
        //1.以当前值创建一个新节点
        Node newNode = new Node(value);
        //2.新节点的右节点指向原先右节点
        newNode.right = right;
        //3.新节点的左节点指向原先左节点的右子节点
        newNode.left = left.right;
        //4.原先节点的值改为原先节点的左子节点值
        value = left.value;
        //5.原先节点的左节点指向左节点的左节点
        left = left.left;
        //6.把当前节点的右节点设置成新节点
        right = newNode;
    }


    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                " left=" + (left==null?"null":left.value)+
                " right=" + (right==null?"null":right.value)+
                '}';
    }
}