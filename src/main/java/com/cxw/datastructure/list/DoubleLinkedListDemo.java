package com.cxw.datastructure.list;

/**
 * @author chengxuwei
 * @date 2020-06-01 20:54
 * @description 单链表添删改查
 */
public class DoubleLinkedListDemo {

    public static void main(String[] args) {
        DoubleLinkedList list = new DoubleLinkedList();
        System.out.println(list.size());
        list.add(new DoubleLinkedNode(1,"a","a"));
        list.add(new DoubleLinkedNode(2,"aa","aa"));
        list.add(new DoubleLinkedNode(3,"aaa","aaa"));
        list.print();
        System.out.println(list.size());
        list.update(new SingleNode(2,"aa2","aa2"));
        list.print();
        list.delete(2);
        list.print();
    }
}

class DoubleLinkedList{
    private DoubleLinkedNode head = new DoubleLinkedNode(-1,"","");

    public void add(DoubleLinkedNode node){
        DoubleLinkedNode temp = head;
        while(temp.next!=null){
            temp = temp.next;
        }
        node.prev = temp;
        temp.next = node;
    }

    public int size(){
        int count = 0;
        DoubleLinkedNode temp = head;
        while(temp.next!=null){
            temp = temp.next;
            count++;
        }
        return count;
    }

    public void update(SingleNode node){
        DoubleLinkedNode temp = head;
        boolean flag = false;
        while(temp.next!=null){
            temp = temp.next;
            if(temp.getNo()==node.getNo()){
                flag = true;
                break;
            }
        }
        if(flag) {
            temp.setName(node.getName());
            temp.setNikeName(node.getNikeName());
        }else{
            System.out.println("node no="+node.getNo()+"不存在");
        }
    }

    public void delete(int no){
        DoubleLinkedNode temp = head;
        boolean flag = false;
        while(temp!=null){
            if(temp.getNo()==no){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if(flag) {
            temp.next = temp.prev.next;
            temp.prev = temp.next.prev;
        }else{
            System.out.println("node no="+no+"不存在");
        }
    }

    public void print(){
        if(head.next==null){
            System.out.println("队列为空");
            return;
        }
        DoubleLinkedNode temp = head;
        while(temp.next!=null){
            temp = temp.next;
            System.out.printf("%d %s %s\n",temp.getNo(),temp.getName(),temp.getNikeName());
        }
    }

}

class DoubleLinkedNode{
    private int no;
    private String name;
    private String nikeName;
    protected DoubleLinkedNode next;
    protected DoubleLinkedNode prev;

    public DoubleLinkedNode() {
    }

    public DoubleLinkedNode(int no, String name, String nikeName) {
        this.no = no;
        this.name = name;
        this.nikeName = nikeName;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNikeName() {
        return nikeName;
    }

    public void setNikeName(String nikeName) {
        this.nikeName = nikeName;
    }
}