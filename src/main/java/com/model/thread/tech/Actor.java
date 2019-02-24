package com.model.thread.tech;

public class Actor extends  Thread{

    public void run(){
        System.out.println(getName() + "是一个演员");
        int count = 0;
        boolean keepRunning = true;
        while(keepRunning){
            System.out.println(getName() + "开始登台" + (++count));
            if (count == 100){
                keepRunning = false;
            }
            if (count % 10 ==0){
                try {
                    Thread.sleep(3000l);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }


        System.out.println(getName() + "演出结束");

    }


    public static void main(String[] args){
        Thread actor = new Actor();
        actor.setName("Mr.thread");
        actor.start();
        Thread actressThread = new Thread(new Actress(),"Ms.Runnable");
        actressThread.start();
    }

    static class Actress implements  Runnable{

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "是一个演员");
            int count = 0;
            boolean keepRunning = true;
            while(keepRunning){
                System.out.println(Thread.currentThread().getName() + "开始登台" + (++count));
                if (count == 100){
                    keepRunning = false;
                }
                if (count % 10 ==0){
                    try {
                        Thread.sleep(3000l);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }


            System.out.println(Thread.currentThread().getName() + "演出结束");
        }
    }


}
