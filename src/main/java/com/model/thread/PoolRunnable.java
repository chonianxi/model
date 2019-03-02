package com.model.thread;

import java.util.concurrent.atomic.AtomicInteger;

public class PoolRunnable implements  Runnable{
    private UserTest userTest;
    private ThreadLocal<UserTest> threadLocal = new ThreadLocal<UserTest>();

    public PoolRunnable(UserTest num){
        this.userTest = num;
    }

    public void setThreadLocal(UserTest num){
        threadLocal.set(num);
    }

    @Override
    public void run() {
        System.out.println(System.currentTimeMillis()+"--"+Thread.currentThread().getName()+":--" +  threadLocal.get().getName() + "---" + threadLocal.get().getNum() + "----gogogo---");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
