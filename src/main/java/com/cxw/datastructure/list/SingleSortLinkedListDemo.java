package com.cxw.datastructure.list;

/**
 * @author chengxuwei
 * @date 2020-06-01 20:54
 * @description 带排序的单链表添删改查
 */
public class SingleSortLinkedListDemo {

    public static void main(String[] args) {
        SingleSortLinkedList list = new SingleSortLinkedList();
        list.add(new SingleNode(1,"a","a"));
        list.add(new SingleNode(3,"aaa","aaa"));
        list.add(new SingleNode(2,"aa","aa"));
        list.print();
        list.update(new SingleNode(2,"aa2","aa2"));
        list.print();
        list.delete(2);
        list.print();
        System.out.println("--------------");
        SingleSortLinkedList list2 = new SingleSortLinkedList();
        list2.add(new SingleNode(11,"b","b"));
        list2.add(new SingleNode(31,"d","d"));
        list2.add(new SingleNode(21,"c","c"));
        list.addAll(list2);
        list.print();
    }
}

class SingleSortLinkedList{
    private SingleNode head = new SingleNode(-1,"","");

    public void add(SingleNode node){
        SingleNode temp = head;
        boolean flag = false;
        while(temp.next!=null){
            if(temp.getNo()==node.getNo()){
                System.out.println("no="+node.getNo()+"已存在！");
                return;
            }
            if(temp.next.getNo()>node.getNo()){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if(flag) {
            node.next = temp.next;
            temp.next = node;
        }else{
            temp.next = node;
        }
    }

    public void addAll(SingleSortLinkedList list){
        SingleNode temp = list.head.next;
        while(temp!=null){
            SingleNode next = temp.next;
            temp.next = null;
            add(temp);
            temp = next;
        }
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
}
