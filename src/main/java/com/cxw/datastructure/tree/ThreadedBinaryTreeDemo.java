package com.cxw.datastructure.tree;

/**
 * @author chengxuwei
 * @date 2020-07-02 14:10
 * @description 线索化二叉树
 */
public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {
        ThreadedBinaryTree tree = new ThreadedBinaryTree();
        ThreadedBinaryNode node1 = new ThreadedBinaryNode(1,"1");
        ThreadedBinaryNode node2 = new ThreadedBinaryNode(2,"2");
        ThreadedBinaryNode node3 = new ThreadedBinaryNode(3,"3");
        ThreadedBinaryNode node4 = new ThreadedBinaryNode(4,"4");
        ThreadedBinaryNode node5 = new ThreadedBinaryNode(5,"5");
        ThreadedBinaryNode node6 = new ThreadedBinaryNode(6,"6");
        ThreadedBinaryNode node7 = new ThreadedBinaryNode(7,"7");
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;
        tree.root = node1;
        tree.infixThreadedNodes();
//        System.out.println(node2);
//        System.out.println(node4);
//        System.out.println(node6);
        tree.infixOrder();
    }
}
class ThreadedBinaryTree{
    ThreadedBinaryNode root;
    ThreadedBinaryNode pre;
    //重载一把 threadedNodes 方法
    public void infixThreadedNodes() {
        infixThreadedNodes(root);
    }
    //中序线索化
    public void infixThreadedNodes(ThreadedBinaryNode node) {
        //1.先线索化左节点
        if(node.left!=null){
            infixThreadedNodes(node.left);
        }
        //2.线索化当前节点
        //左节点
        if(node.left==null){
            node.left = pre;
            node.leftType = 1;
        }
        //处理后继节点
        if(pre!=null&&pre.right==null){
            //前驱节点的右指针指向当前节点
            pre.right = node;
            pre.rightType = 1;
        }
        //!!! 每处理一个结点后，让当前结点是下一个结点的前驱结点
        pre = node;
        //3.线索化右节点
        if(node.right!=null){
            infixThreadedNodes(node.right);
        }
    }

    public void infixOrder(){
        infixOrder(root);
    }

    private void infixOrder(ThreadedBinaryNode root) {
        if(root!=null){
            root.infixOrder();
        }
    }
}

class ThreadedBinaryNode{
    int no;
    String name;

    public ThreadedBinaryNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    ThreadedBinaryNode left;
    ThreadedBinaryNode right;
    /**
     * 0是左子树
     * 1是前驱节点
     */
    int leftType;
    /**
     * 0是右子树
     * 1是后继节点
     */
    int rightType;

    public void infixOrder() {
        if(left!=null&&leftType==0){
            left.infixOrder();
        }
        System.out.println(this);
        if(right!=null&&rightType==0){
            right.infixOrder();
        }
    }

    @Override
    public String toString() {
        return "ThreadedBinaryNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", left=" + (left==null?"null":left.no) +
                ", right=" + (right==null?"null":right.no) +
                ", leftType=" + leftType +
                ", rightType=" + rightType +
                '}';
    }
}