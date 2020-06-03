package com.cxw.datastructure.list;

/**
 * @author chengxuwei
 * @date 2020-06-01 20:54
 * @description 单链表添删改查
 */
public class SingleLinkedListDemo {

    public static void main(String[] args) {
        SingleLinkedList list = new SingleLinkedList();
        System.out.println(list.size());
        list.add(new SingleNode(1,"a","a"));
        list.add(new SingleNode(2,"aa","aa"));
        list.add(new SingleNode(3,"aaa","aaa"));
        list.print();
        list.reversal();
        System.out.println(list.size());
        list.update(new SingleNode(2,"aa2","aa2"));
        list.print();
        list.delete(2);
        list.print();
    }
}
class SingleLinkedList{
    private SingleNode head = new SingleNode(-1,"","");

    public void add(SingleNode node){
        SingleNode temp = head;
        while(temp.next!=null){
            temp = temp.next;
        }
        temp.next = node;
    }

    public int size(){
        int count = 0;
        SingleNode temp = head;
        while(temp.next!=null){
            temp = temp.next;
            count++;
        }
        return count;
    }

    public void update(SingleNode node){
        SingleNode temp = head;
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
        SingleNode temp = head;
        boolean flag = false;
        while(temp.next!=null){
            if(temp.next.getNo()==no){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if(flag) {
            temp.next = temp.next.next;
        }else{
            System.out.println("node no="+no+"不存在");
        }
    }

    public void print(){
        if(head.next==null){
            System.out.println("队列为空");
            return;
        }
        SingleNode temp = head;
        while(temp.next!=null){
            temp = temp.next;
            System.out.printf("%d %s %s\n",temp.getNo(),temp.getName(),temp.getNikeName());
        }
    }

    public void reversal() {
        if(head.next==null||head.next.next==null){
            System.out.println("队列为空,或者只有一个元素无需翻转");
            return;
        }
        SingleNode cur = head.next;
        SingleNode next = null;
        SingleNode newHead = new SingleNode(-1,"","");
        while(cur!=null){
            next = cur.next;
            cur.next = newHead.next;
            newHead.next = cur;
            cur = next;
        }
        head.next = newHead.next;
    }
}
class SingleNode{
    private int no;
    private String name;
    private String nikeName;
    protected SingleNode next;

    public SingleNode() {
    }

    public SingleNode(int no, String name, String nikeName) {
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