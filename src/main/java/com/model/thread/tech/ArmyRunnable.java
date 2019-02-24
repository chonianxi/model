package com.model.thread.tech;

public class ArmyRunnable implements Runnable {

    volatile boolean keepRunning = true;


    @Override
    public void run() {
        while (keepRunning){
            for (int i = 0;i<5;i++){
                System.out.println(Thread.currentThread().getName()+"进攻第"+i+"次");
                Thread.yield();
            }
        }

        System.out.println(Thread.currentThread().getName()+"战斗停止");
    }
}
