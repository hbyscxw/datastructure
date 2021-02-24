package com.cxw.leetcode.multithread;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author chengxuwei
 * @date 2021-02-05 15:19
 * @description 交替打印
 * thead1 : 12345
 * thread2: abcde
 * thread3: ABCDE
 */
public class AlternatePrint {
    static Thread t1 = null;
    static Thread t2 = null;
    static Thread t3 = null;
    static String s1 = "12345";
    static String s2 = "abcde";
    static String s3 = "ABCDE";
    static ReentrantLock lock = new ReentrantLock();
    static Condition condition1 = lock.newCondition();
    static Condition condition2 = lock.newCondition();
    static Condition condition3 = lock.newCondition();
    public static void main(String[] args) {

//        lockSupport();
//        waitNotify();
        reentrantLock();

    }

    private static void reentrantLock() {
        //CyclicBarrier cyclicBarrier = new CyclicBarrier(3);
        t1 = new Thread(()->{
            char[] chars = s1.toCharArray();
            for (char a : chars) {
                lock.lock();
                try {
                    //cyclicBarrier.await();
                    System.out.println(a);
                    condition2.signal();
                    condition1.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }
            }
        });

        t2 = new Thread(()->{
            char[] chars = s2.toCharArray();
            for (char a : chars) {
                lock.lock();
                try {
                    //cyclicBarrier.await();
                    System.out.println(a);
                    condition3.signal();
                    condition2.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }
            }
        });
        t3 = new Thread(()->{
            char[] chars = s3.toCharArray();
            for (char a : chars) {
                lock.lock();
                try {
                   // cyclicBarrier.await();
                    System.out.println(a);
                    condition1.signal();
                    condition3.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }
            }
        });
        t1.start();
        t2.start();
        t3.start();
        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void waitNotify() {
        Object o1 = new Object();
        Object o2 = new Object();
        Object o3 = new Object();
        t1 = new Thread(()->{
            char[] chars = s1.toCharArray();
            for (char a : chars) {
                synchronized (o1) {
                    synchronized (o2) {
                        System.out.println(a);
                        o2.notify();
                    }
                    try {
                        o1.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        t2 = new Thread(()->{
            char[] chars = s2.toCharArray();
            for (char a : chars) {
                synchronized (o2) {
                    synchronized (o3) {
                        System.out.println(a);
                        o3.notify();
                    }
                    try {
                        o2.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        t3 = new Thread(()->{
            char[] chars = s3.toCharArray();
            for (char a : chars) {
                synchronized (o3) {
                    try {
                        o3.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (o1) {
                        System.out.println(a);
                        o1.notify();
                    }

                }
            }
        });
        t1.start();
        t2.start();
        t3.start();
        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void lockSupport() {
        t1 = new Thread(()->{
            char[] chars = s1.toCharArray();
            for (char a : chars) {
                System.out.println(a);
                LockSupport.unpark(t2);
                LockSupport.park();
            }
        });
        t2 = new Thread(()->{
            char[] chars = s2.toCharArray();
            for (char a : chars) {
                LockSupport.park();
                System.out.println(a);
                LockSupport.unpark(t3);
            }
        });
        t3 = new Thread(()->{
            char[] chars = s3.toCharArray();
            for (char a : chars) {
                LockSupport.park();
                System.out.println(a);
                LockSupport.unpark(t1);
            }
        });
        t1.start();
        t2.start();
        t3.start();
        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}