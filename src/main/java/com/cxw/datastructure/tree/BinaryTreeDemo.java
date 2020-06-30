package com.cxw.datastructure.tree;

public class BinaryTreeDemo {
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        BinaryNode nodo1 = new BinaryNode(1,"abc");
        BinaryNode nodo2 = new BinaryNode(2,"bcd");
        BinaryNode nodo3 = new BinaryNode(3,"cde");
        BinaryNode nodo4 = new BinaryNode(4,"edf");
        nodo1.left = nodo2;
        nodo1.right = nodo3;
        nodo3.right = nodo4;
        tree.root = nodo1;
//        BinaryNode resNode = tree.preOrderSearch(3);
//        System.out.println(resNode);
        tree.infixOrder();
        tree.deleteNode(nodo1.id);
        System.out.println("~~~~~~~");
        tree.infixOrder();

    }
}
class BinaryTree{
    BinaryNode root;
    public void preOrder(){
        if(root!=null){
            root.preOrder();
        }
    }
    public void infixOrder(){
        if(root!=null){
            root.infixOrder();
        }
    }
    public void postOrder(){
        if(root!=null){
            root.postOrder();
        }
    }
    public BinaryNode preOrderSearch(int no){
        BinaryNode resNode = null;
        if(root!=null){
            resNode = root.preOrderSearch(no);
        }
        return resNode;
    }
    public BinaryNode infixOrderSearch(int no){
        BinaryNode resNode = null;
        if(root!=null){
            resNode = root.infixOrderSearch(no);
        }
        return resNode;
    }
    public BinaryNode postOrderSearch(int no){
        BinaryNode resNode = null;
        if(root!=null){
            resNode = root.postOrderSearch(no);
        }
        return resNode;
    }

    public BinaryNode deleteNode(int no){
        if(root==null ){
            System.out.println("树为空！");
            return null;
        }
        if(root.id == no){
            BinaryNode node = root;
            root = null;
            return node;
        }else{
            return root.deleteNode(no);
        }
    }
}
class BinaryNode{
    int id;
    String name;
    BinaryNode left;
    BinaryNode right;


    public BinaryNode(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public void preOrder(){
        System.out.println(this);
        if(left!=null){
            left.preOrder();
        }
        if(right!=null){
            right.preOrder();
        }
    }
    public void infixOrder(){
        if(left!=null){
            left.infixOrder();
        }
        System.out.println(this);
        if(right!=null){
            right.infixOrder();
        }
    }
    public void postOrder(){
        if(left!=null){
            left.postOrder();
        }
        if(right!=null){
            right.postOrder();
        }
        System.out.println(this);
    }
    public BinaryNode preOrderSearch(int no){
        if(id == no){
            return this;
        }
        BinaryNode resNode = null;
        if(left!=null){
            resNode = left.preOrderSearch(no);
        }
        if(resNode != null){
            return resNode;
        }
        if(right != null){
            resNode = right.preOrderSearch(no);
        }
        return resNode;
    }
    public BinaryNode infixOrderSearch(int no){
        BinaryNode resNode = null;
        if(left!=null){
            resNode = left.infixOrderSearch(no);
        }
        if(resNode != null){
            return resNode;
        }
        if(id == no){
            return this;
        }
        if(right != null){
            resNode = right.infixOrderSearch(no);
        }
        return resNode;
    }
    public BinaryNode postOrderSearch(int no){
        BinaryNode resNode = null;
        if(left!=null){
            resNode = left.postOrderSearch(no);
        }
        if(resNode != null){
            return resNode;
        }
        if(right != null){
            resNode = right.postOrderSearch(no);
        }
        if(resNode != null){
            return resNode;
        }
        if(id == no){
            resNode = this;
        }
        return resNode;
    }

    public BinaryNode deleteNode(int no) {
        BinaryNode node = null;
        if(left!=null && left.id == no){
            node = this.left;
            this.left = null;
            return node;
        }
        if(right!=null && right.id == no){
            node = this.right;
            this.right = null;
            return node;
        }
        if(left!=null){
            node = left.deleteNode(no);
        }
        if(node!=null){
            return node;
        }
        if(right!=null){
            node  = right.deleteNode(no);
        }
        return node;
    }

    @Override
    public String toString() {
        return "BinaryNode{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
