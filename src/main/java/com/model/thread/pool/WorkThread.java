package com.model.thread.pool;

import java.util.concurrent.SynchronousQueue;

public class WorkThread<Job extends Runnable> implements Runnable {

    private volatile boolean running = true;

    public void shutDown(){
        this.running = false;
    }


    @Override
    public void run() {

        while(running){
            Job job = null;
            try {
                job = (Job) ThreadVariable.workQueue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }

            if (null!=job){
                try{
                    job.run();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }





        }


    }
}
