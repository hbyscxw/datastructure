package com.cxw.datastructure.list;

public class JosephRingDemo {
    public static void main(String[] args) {
        JosephRing ring = new JosephRing();
        ring.print();
        ring.addCount(5);
        ring.print();
        System.out.println("环形链表长度："+ring.size());
        int k = 2;//从第2个开始报数
        int m = 3;//数到3就出圈
        ring.out(k,m);
    }

}
class JosephRing{
    private JosephNode first = null;

    public void addCount(int count){
        for (int i = 0; i < count; i++) {
            add(new JosephNode(i+1));
        }
    }
    public void add(JosephNode node){
        if(first == null){
            first = node;
            first.setNext(node);
        }
        JosephNode temp = this.first;
        while(true){
            if(temp.getNext() == first){
                break;
            }
            temp = temp.getNext();
        }
        temp.setNext(node);
        node.setNext(first);
    }

    public void print(){
        if(first == null){
            System.out.println("约瑟夫环为空！");
            return;
        }
        JosephNode temp = this.first;
        while(true){
            System.out.println("当前节点："+temp.getNo());
            if(temp.getNext() == first){
                break;
            }
            temp = temp.getNext();
        }
    }
    public int size(){
        int count = 0;
        if(first == null){
            return count;
        }
        JosephNode temp = this.first;
        while(true){
            count++;
            if(temp.getNext() == first){
                break;
            }
            temp = temp.getNext();
        }
        return count;
    }

    public void out(int k, int m) {
        //取最后一个和当前节点
        JosephNode prev = this.first;
        for(int i=0;i<k-1+size()-1;i++){
            prev = prev.getNext();
        }
        while(true) {
            for (int i = 0; i < m - 1; i++) {
                prev = prev.getNext();
            }
            JosephNode cur = prev.getNext();
            if(prev == cur){
                System.out.println("最后出圈的是："+cur.getNo());
                break;
            }else{
                System.out.println("出圈："+cur.getNo());
                prev.setNext(cur.getNext());
            }
        }
    }
}


class JosephNode{
    private int no;
    private JosephNode next;

    public JosephNode() {
    }

    public JosephNode(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public JosephNode getNext() {
        return next;
    }

    public void setNext(JosephNode next) {
        this.next = next;
    }
}