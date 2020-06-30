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
        BinaryNode resNode = tree.preOrderSearch(3);
        System.out.println(resNode);
    }
}
class BinaryTree{
    BinaryNode root;
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

    public BinaryNode preOrderSearch(int no){
        System.out.println("preOrderSearch");
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

    @Override
    public String toString() {
        return "BinaryNode{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
