package com.cxw.leetcodecourse.petqueue;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 猫狗队列[题目] 宠物、 狗和猫的类如下:
 public class Pet{ private Str ing type;
 public Pet (String type){ this. type = type; }
 public String getPetType() { return this. type;}
 public class Dog extends Pet { public Dog() { super ("dog"); } }
 public class Cat extends Pet { public Cat() { super("cat"); } }
 实现一种狗猫队列的结构，要求如下:用户可以调用add方 法将cat类或dog类的
 实例放入队列中;用户可以调用pollAII方法， 将队列中所有的实例按照进队列
 的先后顺序依次弹出;用户可以调用pol IDog方法，将队列中dog类的实例按照
 进队列的先后顺序依次弹出;用户可以调用pol ICat方法，将队列中cat类的实
 例按照进队列的先后顺序依次弹出;用户可以调用 isEmpty方法，检查队列中是
 否还有dog或cat的实例;用户可以调用 isDogEmpty方法，检查队列中是否有dog
 类的实例;用户可以调用 isCatEmpty方法，检查队列中是否有cat类的实例。
 */
public class PetQueue {
    private Queue<PetWrapper> dogQueue;
    private Queue<PetWrapper> catQueue;

    public PetQueue() {
        dogQueue = new LinkedBlockingQueue<>();
        catQueue = new LinkedBlockingQueue<>();
    }
    public boolean isEmpty(){
        return dogQueueIsEmpty()&&catQueueIsEmpty();
    }
    public boolean dogQueueIsEmpty(){
        return dogQueue.isEmpty();
    }
    public boolean catQueueIsEmpty(){
        return catQueue.isEmpty();
    }
    public void add(Pet p){
        String petType = p.getPetType();
        if("dog".equals(petType)){
            dogQueue.add(new PetWrapper(p,System.currentTimeMillis()));
        }else if("cat".equals(petType)){
            catQueue.add(new PetWrapper(p,System.currentTimeMillis()));
        }else{
            throw new RuntimeException("error pet type");
        }
    }
    public Pet poll(){
        if(!dogQueueIsEmpty()&&!catQueueIsEmpty()) {
            PetWrapper dog = dogQueue.peek();
            PetWrapper cat = catQueue.peek();
            if (dog.getTime() < cat.getTime()) {
                dogQueue.poll();
                return dog.getPet();
            } else {
                catQueue.poll();
                return cat.getPet();
            }
        }else if(!dogQueueIsEmpty()){
            PetWrapper dog = dogQueue.poll();
            return dog.getPet();
        }else if(!catQueueIsEmpty()){
            PetWrapper cat = catQueue.poll();
            return cat.getPet();
        }else{
            return null;
        }
    }
}
