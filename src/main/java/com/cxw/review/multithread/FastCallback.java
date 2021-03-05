package com.cxw.review.multithread;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;

/**
 * @author chengxuwei
 * @date 2021-03-04 09:56
 * @description 有多个线程，如果有一个线程返回false结果就为false,要求尽快返回
 */
public class FastCallback {
    static CompletableFuture cf = null;
    static CompletableFuture cf2 = null;
    public static void main(String[] args) {

        Supplier<Boolean> t = ()->{
            ThreadLocalRandom tr = ThreadLocalRandom.current();
            int time = tr.nextInt(1000);
            boolean b = tr.nextBoolean();
            System.out.println(time+"_"+b);
            try {
                Thread.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return b;
        };

//        Consumer<Boolean> c = (Boolean r)->{
//            if(!r){
//                System.out.println("finish!");
//            }
//        };
        cf = CompletableFuture.supplyAsync(t).thenAccept(r->{
            if(!r){
                System.out.println("finish!");
                cf2.cancel(false);
            }
        });
        cf2 = CompletableFuture.supplyAsync(t).thenAccept(r->{
            if(!r){
                System.out.println("finish2!");
                cf.cancel(false);
            }
        });
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}