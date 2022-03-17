package com.cxw.leetcode.multithread;

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

        lockSupport(); //先调用unpack  再调用pack也不会阻塞 原理 https://www.cnblogs.com/takumicx/p/9328459.html#22-%E5%85%88%E5%94%A4%E9%86%92%E7%BA%BF%E7%A8%8B%E4%B8%A4%E6%AC%A1%E5%86%8D%E9%98%BB%E5%A1%9E%E4%B8%A4%E6%AC%A1%E4%BC%9A%E5%8F%91%E7%94%9F%E4%BB%80%E4%B9%88
//        waitNotify();
//        reentrantLock();

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
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
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