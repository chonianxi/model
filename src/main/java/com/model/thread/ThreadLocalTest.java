package com.model.thread;

public class ThreadLocalTest {

    public static void main(String[] args){

        ThreadLocal<String> threadLocal = new ThreadLocal<String>();
        threadLocal.set("LOVE YOU");
        ThreadLocal<String> threadLocal1 = new ThreadLocal<String>();
        System.out.println(threadLocal1.get());

    }



}
