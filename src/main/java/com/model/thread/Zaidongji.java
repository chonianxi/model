package com.model.thread;

public class Zaidongji implements Runnable{

    private TestUser testUser;

    public Zaidongji(TestUser testUser){
        this.testUser = testUser;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"------RUNNING:"+testUser.getUserNum());
        testUser = null;
    }
}
