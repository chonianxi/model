package com.model.thread.pool;

import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

@Component("xxPool")
public class ThreadVariable<Job extends Runnable> implements ThreadPool<Job>{


    public static LinkedBlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<Runnable>(81920);

    private static final int minWorkThread = 2;

    private static final int maxWorkThread = 16;

    private AtomicInteger threadNum = new AtomicInteger();

    private int workThread = minWorkThread;

    private volatile LinkedList<WorkThread> listWork = new LinkedList<WorkThread>();

    public ThreadVariable (int num){
        if (num>maxWorkThread){
            num = maxWorkThread;
        }
        if (num<minWorkThread){
            num = minWorkThread;
        }
        initThreadPool(num);
        this.workThread = num;
    }

    public LinkedList<WorkThread> getListWork(){
        return this.listWork;
    }

    public void addListWork(WorkThread workThread){
        listWork.addLast(workThread);
    }

    public void removeWork(WorkThread workThread){
        listWork.remove(workThread);
    }

    public int getWorks(){
        return listWork.size();
    }

    public void initThreadPool(int num){
        if (num<minWorkThread) {
            num = minWorkThread;
        }
        if (num>maxWorkThread){
            num = maxWorkThread;
        }

        for (int i = 0;i<num;i++){
            WorkThread<Job> workerThread = new WorkThread<Job>();
            Thread thread = new Thread(workerThread,"ZTT-WORKTHREAD-"+threadNum.incrementAndGet());
            thread.start();
        }

        this.workThread = num;

    }


    @Override
    public void execute(Job job) {
        try {
            workQueue.put(job);
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void shutdown() {
        for (int i=0;i<listWork.size();i++){
            WorkThread workThread = (WorkThread) listWork.get(i);
            workThread.shutDown();
        }
    }

    @Override
    public void addWorkers(int num) {
        if (num>(maxWorkThread-workThread)){
            num = maxWorkThread-workThread;
        }

        initThreadPool(workThread+num);
        this.workThread = workThread+num;
    }

    @Override
    public void removeWorker(int num) {
        for(int i = 0;i<listWork.size();i++){
            WorkThread workThread = listWork.get(i);
            workThread.shutDown();
            listWork.remove(i);
        }
    }

    @Override
    public int getJobSize() {
        return workQueue.size();
    }
}
