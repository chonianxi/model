package com.model.thread.tech;

public class Stage extends Thread {

    public void run (){
        ArmyRunnable armyRunnableSui = new ArmyRunnable();
        ArmyRunnable armyRunnable = new ArmyRunnable();

        Thread armyOfSui = new Thread(armyRunnableSui,"随军");
        Thread armyOf = new Thread(armyRunnable,"农民");


        armyOfSui.start();
        armyOf.start();;

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("陈咬金来了");
        Thread mrCheng = new KeyPersionThread();
        mrCheng.setName("程咬金");
        armyRunnableSui.keepRunning = false;
        System.out.println("------随军退兵---");
        armyRunnable.keepRunning = false;
        System.out.println("----农民军收兵-----");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        mrCheng.start();

        try {
            mrCheng.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("结束");

    }

    public static void main(String[] args){

        new Stage().start();

    }

}
