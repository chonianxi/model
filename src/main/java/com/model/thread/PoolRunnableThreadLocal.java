package com.model.thread;

public class PoolRunnableThreadLocal implements Runnable {
    private static ThreadLocal<UserTest> threadLocal = new ThreadLocal<UserTest>();

    public PoolRunnableThreadLocal(UserTest num){
        threadLocal = new ThreadLocal<UserTest>();
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
