package com.cxw.datastructure.hashtab;

/**
 * @author chengxuwei
 * @date 2020-06-28 09:33
 * @description 哈希表
 */
public class HashTabDemo {
    public static void main(String[] args) {
        HashTab hashTab = new HashTab(10);
        hashTab.add(new Emp(2,"abc"));
        hashTab.add(new Emp(12,"abcd"));
        hashTab.add(new Emp(22,"jj"));
        hashTab.add(new Emp(3,"ff"));
        hashTab.list();
        //Emp e = hashTab.query(32);
//        System.out.println(e.name);
//        hashTab.modify(new Emp(22,"kk"));
//        hashTab.list();
        hashTab.delete(2);
        hashTab.list();
    }
}

class HashTab{
    private EmpLinkedList[] list;
    private int size;
    public HashTab(int size){
        this.size = size;
        list = new EmpLinkedList[size];
        for (int i = 0; i < size; i++) {
            list[i] = new EmpLinkedList(i);
        }
    }

    public void add(Emp emp){
        int index = emp.id%size;
        list[index].add(emp);
    }
    public Emp query(int id){
        int index = id%size;
        return list[index].query(id);
    }
    public void modify(Emp emp){
        Emp e = query(emp.id);
        if(e == null){
            System.out.println("未找到该雇员，修改失败！");
            return;
        }
        e.name = emp.name;
    }
    public void delete(int id){
        int index = id%size;
        list[index].delete(id);
    }
    public void list(){
        for (EmpLinkedList empLinkedList : list) {
            empLinkedList.list();
        }
    }
}
class EmpLinkedList{
    Emp head;
    int no;
    public EmpLinkedList(int no) {
        this.no = no;
    }

    public void add(Emp emp) {
        if(head == null){
            head = emp;
            return;
        }
        Emp temp = head;
        while(temp.next != null){
            temp = temp.next;
        }
        temp.next = emp;
    }
    public void list(){
        System.out.printf("第%d个列表元素:",no+1);
        if(head == null){
            System.out.println();
            return;
        }
        Emp temp = head;
        while(temp != null){
            System.out.printf("{id:%d,name:%s} -> ",temp.id,temp.name);
            temp = temp.next;
        }
        System.out.println();

    }

    public Emp query(int id) {
        if(head == null){
            return null;
        }
        Emp temp = head;
        while(temp != null){
            if(temp.id == id){
                return temp;
            }
            temp = temp.next;
        }
        return null;
    }

    public void delete(int id) {
        if(head == null){
            return;
        }
        if(head.id == id){
            head = head.next;
            return;
        }
        Emp temp = head;
        while(temp.next != null){
            if(temp.next.id == id){
                if(temp.next.next!=null) {
                    temp.next = temp.next.next;
                }else{
                    temp.next = null;
                }
                return;
            }
            temp = temp.next;
        }
    }
}
class Emp{
    int id;
    String name;
    Emp next;
    public Emp(int id,String name){
        this.id = id;
        this.name = name;
    }
}