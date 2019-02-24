package com.model.thread.tech;

public class KeyPersionThread extends Thread {

    public void run(){
        System.out.println(getName()+"开始战斗了");
        for (int i=0;i<10;i++){
            System.out.println(getName()+"左图右击，击杀");
        }


        System.out.println(getName()+"结束了战斗");
    }


}




