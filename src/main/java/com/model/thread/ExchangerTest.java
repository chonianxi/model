package com.model.thread;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExchangerTest {

    private static final Exchanger<String> exgr = new Exchanger<String>();

    private static ExecutorService threadPool = Executors.newFixedThreadPool(2);

    public static void main(String[] args){
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                String A = "银行流水A";
                try {
                    System.out.println("A ---begin:"+System.currentTimeMillis());
                    String c = exgr.exchange(A);
                    System.out.println("A ---end:"+System.currentTimeMillis());
                    System.out.println("C:"+c);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        threadPool.execute(new Runnable(){

            @Override
            public void run() {
                String B = "银行流水B";
                try {
                    System.out.println("B ---begin:"+System.currentTimeMillis());
                    Thread.sleep(5000l);
                    String A = exgr.exchange(B);
                    System.out.println("B ---end:"+System.currentTimeMillis());
                    System.out.println("A和B书籍是否一致:"+A.equals(B)+",A录入的是:"+A+",B录入的是:"+B);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        threadPool.shutdown();

    }


}
